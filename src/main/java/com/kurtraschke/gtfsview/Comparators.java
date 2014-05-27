/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kurtraschke.gtfsview;

import java.util.Comparator;
import org.onebusaway.gtfs.model.Agency;
import org.onebusaway.gtfs.model.AgencyAndId;
import org.onebusaway.gtfs.model.Route;
import org.onebusaway.gtfs.model.Trip;

/**
 *
 * @author kurt
 */
public class Comparators {

    private Comparators() {

    }

    public static class IntegerComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(o1, o2);
        }
    }

    public static class DoubleComparator implements Comparator<Double> {

        @Override
        public int compare(Double o1, Double o2) {
            return Double.compare(o1, o2);
        }

    }

    public static class StringComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    public static class AgencyAndIdComparator implements Comparator<AgencyAndId> {

        @Override
        public int compare(AgencyAndId o1, AgencyAndId o2) {
            return o1.getId().compareTo(o2.getId());
        }
    }

    public static class AgencyComparator implements Comparator<Agency> {

        @Override
        public int compare(Agency o1, Agency o2) {
            return o1.getName().compareTo(o2.getName());
        }

    }

    public static class RouteComparator implements Comparator<Route> {

        @Override
        public int compare(Route o1, Route o2) {
            //TODO: really should compare by short name/long name/etc. as available
            return o1.getId().compareTo(o2.getId());
        }

    }

    public static class TripComparator implements Comparator<Trip> {

        @Override
        public int compare(Trip o1, Trip o2) {
            //TODO: really should compare by trip start time
            return o1.getId().compareTo(o2.getId());
        }

    }

}
