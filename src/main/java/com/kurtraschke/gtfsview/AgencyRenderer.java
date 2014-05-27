/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kurtraschke.gtfsview;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import org.onebusaway.gtfs.model.Agency;

/**
 *
 * @author kurt
 */
class AgencyRenderer extends BasicComboBoxRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);

        if (value != null) {
            Agency agency = (Agency) value;
            setText(String.format("%s (%s)", agency.getName(), agency.getId()));
        } else {
            setText("Select Agency...");
        }
        return this;
    }
}
