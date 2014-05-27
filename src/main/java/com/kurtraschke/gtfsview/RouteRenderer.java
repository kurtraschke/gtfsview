/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kurtraschke.gtfsview;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import org.onebusaway.gtfs.model.Route;

/**
 *
 * @author kurt
 */
public class RouteRenderer extends BasicComboBoxRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);

        if (value != null) {
            Route route = (Route) value;

            String routeLabel;

            if (route.getShortName() != null && route.getLongName() != null) {
                if (route.getShortName().equals(route.getLongName())) {
                    routeLabel = route.getShortName();
                } else {
                    routeLabel = route.getShortName() + " " + route.getLongName();
                }
            } else if (route.getShortName() != null) {
                routeLabel = route.getShortName();
            } else if (route.getLongName() != null) {
                routeLabel = route.getLongName();
            } else {
                routeLabel = route.getId().getId();
            }

            setText(routeLabel);
        } else {
            setText("Select Route...");
        }
        return this;
    }
}
