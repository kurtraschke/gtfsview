/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kurtraschke.gtfsview;

import com.kurtraschke.gtfsview.Comparators.DoubleComparator;
import com.kurtraschke.gtfsview.Comparators.IntegerComparator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import javax.swing.DefaultRowSorter;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import org.onebusaway.gtfs.model.ShapePoint;

/**
 *
 * @author kurt
 */
public class ShapePointsTableModel extends AbstractTableModel {
    
    private final List<ShapePoint> shapePoints = new ArrayList<>();
    
    public enum Column {
        
        SHAPE_PT_SEQUENCE("Sequence", new IntegerComparator()),
        SHAPE_PT_LAT("Latitude", new DoubleComparator()),
        SHAPE_PT_LON("Longitude", new DoubleComparator()),
        SHAPE_DIST_TRAVELED("Distance Traveled", new DoubleComparator());
        
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
    
    public ShapePointsTableModel() {
        
    }
    
    public static void configureTableSorting(JTable theTable) {
        DefaultRowSorter rs = (DefaultRowSorter) theTable.getRowSorter();
        
        for (Column c : Column.values()) {
            rs.setComparator(c.ordinal(), c.getComparator());
        }
    }
    
    public void setShapePoints(Collection<ShapePoint> newShapePoints) {
        shapePoints.clear();
        shapePoints.addAll(newShapePoints);
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return shapePoints.size();
    }
    
    @Override
    public int getColumnCount() {
        return Column.values().length;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ShapePoint sp = shapePoints.get(rowIndex);
        
        Column column = Column.values()[columnIndex];
        
        switch (column) {
            case SHAPE_PT_SEQUENCE:
                return sp.getSequence();
            case SHAPE_PT_LAT:
                return sp.getLat();
            case SHAPE_PT_LON:
                return sp.getLon();
            case SHAPE_DIST_TRAVELED:
                return sp.isDistTraveledSet() ? sp.getDistTraveled() : null;
        }
        return null;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return Column.values()[columnIndex].getName();
    }
    
    public ShapePoint getShapePointForRow(int row) {
        return shapePoints.get(row);
    }
    
}
