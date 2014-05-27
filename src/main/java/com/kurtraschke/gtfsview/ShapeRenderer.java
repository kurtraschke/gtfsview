/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kurtraschke.gtfsview;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import org.onebusaway.gtfs.model.AgencyAndId;

/**
 *
 * @author kurt
 */
public class ShapeRenderer extends BasicComboBoxRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);

        if (value != null) {
            AgencyAndId shapeId = (AgencyAndId) value;

            setText(shapeId.getId());
        } else {
            setText("Select Shape...");
        }
        return this;
    }
}
