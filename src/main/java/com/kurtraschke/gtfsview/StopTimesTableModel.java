/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kurtraschke.gtfsview;

import com.kurtraschke.gtfsview.Comparators.AgencyAndIdComparator;
import com.kurtraschke.gtfsview.Comparators.DoubleComparator;
import com.kurtraschke.gtfsview.Comparators.IntegerComparator;
import com.kurtraschke.gtfsview.Comparators.StringComparator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import javax.swing.DefaultRowSorter;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import org.onebusaway.gtfs.model.StopTime;

/**
 *
 * @author kurt
 */
public class StopTimesTableModel extends AbstractTableModel {

    private final List<StopTime> stopTimes = new ArrayList<>();

    public enum Column {

        STOP_SEQUENCE("Stop Sequence", new IntegerComparator()),
        ARRIVAL_TIME("Arrival Time", new IntegerComparator()),
        DEPARTURE_TIME("Departure Time", new IntegerComparator()),
        STOP_NAME("Stop Name", new StringComparator()),
        STOP_ID("Stop ID", new AgencyAndIdComparator()),
        STOP_LAT("Stop Latitude", new DoubleComparator()),
        STOP_LON("Stop Longitude", new DoubleComparator()),
        SHAPE_DIST_TRAVELED("Shape Distance Traveled", new DoubleComparator());

        private final String name;
        private final Comparator comparator;

        Column(String name, Comparator comparator) {
            this.name = name;
            this.comparator = comparator;
        }

        public String getName() {
            return name;
        }

        public Comparator getComparator() {
            return comparator;
        }
    }

    public StopTimesTableModel() {

    }

    public static void configureTableSorting(JTable theTable) {
        DefaultRowSorter rs = (DefaultRowSorter) theTable.getRowSorter();

        for (Column c : Column.values()) {
            rs.setComparator(c.ordinal(), c.getComparator());
        }
    }

    public void setStopTimes(Collection<StopTime> newStopTimes) {
        stopTimes.clear();
        stopTimes.addAll(newStopTimes);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return stopTimes.size();
    }

    @Override
    public int getColumnCount() {
        return Column.values().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StopTime theStopTime = stopTimes.get(rowIndex);

        Column column = Column.values()[columnIndex];

        switch (column) {
            case STOP_SEQUENCE:
                return theStopTime.getStopSequence();
            case ARRIVAL_TIME:
                return theStopTime.isArrivalTimeSet() ? theStopTime.getArrivalTime() : null;
            case DEPARTURE_TIME:
                return theStopTime.isDepartureTimeSet() ? theStopTime.getDepartureTime(): null;
            case STOP_NAME:
                return theStopTime.getStop().getName();
            case STOP_ID:
                return theStopTime.getStop().getId();
            case STOP_LAT:
                return theStopTime.getStop().getLat();
            case STOP_LON:
                return theStopTime.getStop().getLon();
            case SHAPE_DIST_TRAVELED:
                return theStopTime.isShapeDistTraveledSet() ? theStopTime.getShapeDistTraveled() : null;

        }
        return null;

    }
    
    public StopTime getStopTimeForRow(int row) {
        return stopTimes.get(row);
    }

    @Override
    public String getColumnName(int columnIndex) {
        return Column.values()[columnIndex].getName();
    }

}
