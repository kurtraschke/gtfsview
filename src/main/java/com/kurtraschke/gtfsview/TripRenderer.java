/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kurtraschke.gtfsview;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import org.onebusaway.gtfs.model.Trip;

/**
 *
 * @author kurt
 */
public class TripRenderer extends BasicComboBoxRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);

        if (value != null) {
            Trip trip = (Trip) value;

            if (trip.getTripShortName() != null) {
                setText(trip.getTripShortName());
            } else if (trip.getTripHeadsign() != null) {
                setText(trip.getTripHeadsign());
            } else {
                setText(trip.getId().getId());
            }

        } else {
            setText("Select Trip...");
        }
        return this;
    }
}
