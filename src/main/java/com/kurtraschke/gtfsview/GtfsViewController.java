/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kurtraschke.gtfsview;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.onebusaway.gtfs.impl.GtfsRelationalDaoImpl;
import org.onebusaway.gtfs.model.Agency;
import org.onebusaway.gtfs.model.AgencyAndId;
import org.onebusaway.gtfs.model.Route;
import org.onebusaway.gtfs.model.ShapePoint;
import org.onebusaway.gtfs.model.StopTime;
import org.onebusaway.gtfs.model.Trip;
import org.onebusaway.gtfs.serialization.GtfsReader;
import org.onebusaway.gtfs.services.GtfsRelationalDao;

/**
 *
 * @author kurt
 */
public class GtfsViewController {

    private GtfsRelationalDao dao;

    public void setGtfsBundle(File gtfsBundle) throws IOException {
        GtfsReader reader = new GtfsReader();
        reader.setInputLocation(gtfsBundle);

        dao = new GtfsRelationalDaoImpl();
        reader.setEntityStore((GtfsRelationalDaoImpl) dao);
        reader.run();
    }

    public Collection<Agency> getAgencies() {
        return dao.getAllAgencies();
    }

    public Collection<Route> getRoutes(Agency agency) {
        return dao.getRoutesForAgency(agency);
    }

    public Collection<AgencyAndId> getShapesForAgency(Agency agency) {
        Set<AgencyAndId> agencyShapes = new HashSet<>();

        for (Route r : dao.getRoutesForAgency(agency)) {
            for (Trip t : dao.getTripsForRoute(r)) {
                AgencyAndId shapeId = t.getShapeId();
                if (shapeId != null) {
                    agencyShapes.add(shapeId);
                }
            }
        }
        return agencyShapes;
    }

    public Collection<AgencyAndId> getShapesForRoute(Route route) {
        Set<AgencyAndId> shapesForRoute = new HashSet<>();
        for (Trip t : dao.getTripsForRoute(route)) {
            if (t.getShapeId() != null) {
                shapesForRoute.add(t.getShapeId());
            }
        }
        return shapesForRoute;
    }

    public Collection<AgencyAndId> getShapeForTrip(Trip trip) {
        AgencyAndId shapeId = trip.getShapeId();
        if (shapeId != null) {
            return Collections.singleton(shapeId);
        } else {
            return Collections.EMPTY_LIST;
        }

    }

    public Collection<Trip> getTripsForRoute(Route route) {
        return dao.getTripsForRoute(route);
    }

    public Collection<Trip> getTripsForRouteAndShape(Route route, AgencyAndId shape) {
        Collection<Trip> tripsForRoute = dao.getTripsForRoute(route);
        List<Trip> tripsForRouteAndShape = new ArrayList<>(tripsForRoute.size());

        for (Trip t : tripsForRoute) {
            if (t.getShapeId() != null && t.getShapeId().equals(shape)) {
                tripsForRouteAndShape.add(t);
            }
        }

        return tripsForRouteAndShape;
    }

    public Collection<Route> getRoutesForShape(AgencyAndId shape) {
        Collection<Trip> trips = dao.getTripsForShapeId(shape);
        Set<Route> routes = new HashSet<>();

        for (Trip t : trips) {
            routes.add(t.getRoute());
        }

        return routes;
    }

    public Collection<StopTime> getStopTimesForTrip(Trip trip) {
        return dao.getStopTimesForTrip(trip);
    }
    
    public Collection<ShapePoint> getShapePointsForShape(AgencyAndId shape) {
        return dao.getShapePointsForShapeId(shape);
    }
    
}
