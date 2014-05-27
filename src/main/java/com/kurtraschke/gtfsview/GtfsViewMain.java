/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kurtraschke.gtfsview;

import com.kurtraschke.gtfsview.Comparators.AgencyAndIdComparator;
import com.kurtraschke.gtfsview.Comparators.AgencyComparator;
import com.kurtraschke.gtfsview.Comparators.RouteComparator;
import com.kurtraschke.gtfsview.Comparators.TripComparator;
import com.sun.javafx.property.adapter.PropertyDescriptor;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import org.onebusaway.gtfs.model.Agency;
import org.onebusaway.gtfs.model.AgencyAndId;
import org.onebusaway.gtfs.model.Route;
import org.onebusaway.gtfs.model.ShapePoint;
import org.onebusaway.gtfs.model.StopTime;
import org.onebusaway.gtfs.model.Trip;

/**
 *
 * @author kurt
 */
public class GtfsViewMain extends javax.swing.JFrame {

    private final GtfsViewController gvc = new GtfsViewController();
    private final StopTimesTableModel sttm = new StopTimesTableModel();
    private final ShapePointsTableModel sptm = new ShapePointsTableModel();
    private boolean refreshInProgress = false;
    private final JFXPanel jfxPanel;
    private WebEngine engine;
    private WebView view;

    /**
     * Creates new form GtfsViewMain
     */
    public GtfsViewMain() {
        initComponents();
        initTables();

        jfxPanel = new JFXPanel();

        jPanel1.add(jfxPanel, BorderLayout.CENTER);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(jfxPanel);
            }
        });

    }

    private void initTables() {
        //TODO: find a better place for this
        StopTimesTableModel.configureTableSorting(stopTimesTable);
        ShapePointsTableModel.configureTableSorting(shapePointsTable);

        stopTimesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int row = stopTimesTable.getSelectedRow();

                    if (row != -1) {
                        final StopTime selectedStopTime = sttm.getStopTimeForRow(row);

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                ((JSObject) engine.executeScript("window")).call("highlightStopTime", selectedStopTime);
                            }
                        });
                    }
                }
            }
        });

        shapePointsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int row = shapePointsTable.getSelectedRow();

                    if (row != -1) {
                        final ShapePoint selectedShapePoint = sptm.getShapePointForRow(row);

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                ((JSObject) engine.executeScript("window")).call("highlightShapePoint", selectedShapePoint);
                            }
                        });
                    }
                }
            }
        });

    }

    private void initFX(JFXPanel fxPanel) {
        // This method is invoked on the JavaFX thread
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }

    private Scene createScene() {
        view = new WebView();
        engine = view.getEngine();

        engine.load(this.getClass().getResource("map.html").toExternalForm());
        Scene scene = new Scene(view);

        return scene;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        agencyLabel = new JLabel();
        routeLabel = new JLabel();
        tripLabel = new JLabel();
        shapeLabel = new JLabel();
        agencyScrollPane = new JScrollPane();
        agencyList = new JList<Agency>();
        routeScrollPane = new JScrollPane();
        routeList = new JList<Route>();
        tripScrollPane = new JScrollPane();
        tripList = new JList<Trip>();
        shapeScrollPane = new JScrollPane();
        shapeList = new JList<AgencyAndId>();
        jSeparator1 = new JSeparator();
        jTabbedPane1 = new JTabbedPane();
        stopTimesScrollPane = new JScrollPane();
        stopTimesTable = new JTable();
        shapePointsScrollPane = new JScrollPane();
        shapePointsTable = new JTable();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        jPanel1 = new JPanel();
        jSeparator2 = new JSeparator();
        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        fileOpenMenu = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1240, 700));
        setPreferredSize(new Dimension(1240, 800));

        agencyLabel.setText("Agency");

        routeLabel.setText("Route");

        tripLabel.setText("Trip");

        shapeLabel.setText("Shape");

        agencyList.setModel(new DefaultListModel<Agency>());
        agencyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        agencyList.setCellRenderer(new AgencyRenderer());
        agencyList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                agencyListValueChanged(evt);
            }
        });
        agencyScrollPane.setViewportView(agencyList);

        routeList.setModel(new DefaultListModel<Route>());
        routeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        routeList.setToolTipText("");
        routeList.setCellRenderer(new RouteRenderer());
        routeList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                routeListValueChanged(evt);
            }
        });
        routeScrollPane.setViewportView(routeList);

        tripList.setModel(new DefaultListModel<Trip>());
        tripList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tripList.setCellRenderer(new TripRenderer());
        tripList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                tripListValueChanged(evt);
            }
        });
        tripScrollPane.setViewportView(tripList);

        shapeList.setModel(new DefaultListModel<AgencyAndId>());
        shapeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        shapeList.setCellRenderer(new ShapeRenderer());
        shapeList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                shapeListValueChanged(evt);
            }
        });
        shapeScrollPane.setViewportView(shapeList);

        stopTimesTable.setAutoCreateRowSorter(true);
        stopTimesTable.setModel(sttm);
        stopTimesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        stopTimesTable.getTableHeader().setReorderingAllowed(false);
        stopTimesScrollPane.setViewportView(stopTimesTable);

        jTabbedPane1.addTab("StopTimes", stopTimesScrollPane);

        shapePointsTable.setAutoCreateRowSorter(true);
        shapePointsTable.setModel(sptm);
        shapePointsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        shapePointsTable.getTableHeader().setReorderingAllowed(false);
        shapePointsScrollPane.setViewportView(shapePointsTable);

        jTabbedPane1.addTab("Shape Points", shapePointsScrollPane);

        jTable1.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTabbedPane1.addTab("Messages", jScrollPane1);

        jPanel1.setLayout(new BorderLayout());

        fileMenu.setText("File");

        fileOpenMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.META_MASK));
        fileOpenMenu.setText("Open Bundle...");
        fileOpenMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                fileOpenMenuActionPerformed(evt);
            }
        });
        fileMenu.add(fileOpenMenu);

        menuBar.add(fileMenu);

        setJMenuBar(menuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(jPanel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(agencyScrollPane, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(agencyLabel)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(routeScrollPane, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                            .addComponent(routeLabel))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(tripScrollPane, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                            .addComponent(tripLabel))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(shapeLabel)
                            .addComponent(shapeScrollPane, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(agencyLabel)
                    .addComponent(routeLabel)
                    .addComponent(tripLabel)
                    .addComponent(shapeLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(agencyScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(routeScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(tripScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(shapeScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fileOpenMenuActionPerformed(ActionEvent evt) {//GEN-FIRST:event_fileOpenMenuActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "GTFS Bundle", "zip");
        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int returnVal = chooser.showOpenDialog(getContentPane());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File gtfsBundle = chooser.getSelectedFile();

            try {
                gvc.setGtfsBundle(gtfsBundle); //TODO: move this into a SwingWorker
            } catch (IOException ex) {
                Logger.getLogger(GtfsViewMain.class.getName()).log(Level.SEVERE, null, ex);
            }

            agencyList.clearSelection();
            routeList.clearSelection();
            tripList.clearSelection();
            shapeList.clearSelection();
            refreshLists(null);
        }
    }//GEN-LAST:event_fileOpenMenuActionPerformed

    private void agencyListValueChanged(ListSelectionEvent evt) {//GEN-FIRST:event_agencyListValueChanged
        refreshLists(evt);
    }//GEN-LAST:event_agencyListValueChanged

    private void routeListValueChanged(ListSelectionEvent evt) {//GEN-FIRST:event_routeListValueChanged
        refreshLists(evt);
    }//GEN-LAST:event_routeListValueChanged

    private void tripListValueChanged(ListSelectionEvent evt) {//GEN-FIRST:event_tripListValueChanged
        refreshLists(evt);

        Trip selectedTrip = tripList.getSelectedValue();

        if (selectedTrip != null) {
            final Collection<StopTime> stopTimes = gvc.getStopTimesForTrip(selectedTrip);
            sttm.setStopTimes(stopTimes);

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ((JSObject) engine.executeScript("window")).call("addStopTimesToMap", new ArrayList<StopTime>(stopTimes));
                }
            });

        } else {
            sttm.setStopTimes(Collections.EMPTY_LIST);

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ((JSObject) engine.executeScript("window")).call("removeStopTimes");
                }
            });

        }
    }//GEN-LAST:event_tripListValueChanged

    private void shapeListValueChanged(ListSelectionEvent evt) {//GEN-FIRST:event_shapeListValueChanged
        refreshLists(evt);

        AgencyAndId selectedShape = shapeList.getSelectedValue();

        if (selectedShape != null) {
            final Collection<ShapePoint> shapePoints = gvc.getShapePointsForShape(selectedShape);
            sptm.setShapePoints(shapePoints);

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ((JSObject) engine.executeScript("window")).call("addShapePointsToMap", new ArrayList<ShapePoint>(shapePoints));
                }
            });

        } else {
            sptm.setShapePoints(Collections.EMPTY_LIST);

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ((JSObject) engine.executeScript("window")).call("removeShapePoints");
                }
            });

        }


    }//GEN-LAST:event_shapeListValueChanged

    private void refreshLists(ListSelectionEvent evt) {
        //TODO: this is long and ugly and complicated; fix
        if (refreshInProgress) {
            return;
        } else {
            refreshInProgress = true;
        }

        if (evt != null) {
            JList source = (JList) evt.getSource();

            if (source.equals(agencyList)) {
                routeList.clearSelection();
                tripList.clearSelection();
                shapeList.clearSelection();
            } else if (source.equals(routeList)) {
                shapeList.clearSelection();
            }
        }

        Agency selectedAgency = agencyList.getSelectedValue();
        Route selectedRoute = routeList.getSelectedValue();
        Trip selectedTrip = tripList.getSelectedValue();
        AgencyAndId selectedShape = shapeList.getSelectedValue();

        boolean agencySelected = selectedAgency != null;
        boolean routeSelected = selectedRoute != null;
        boolean tripSelected = selectedTrip != null;
        boolean shapeSelected = selectedShape != null;

        if (agencySelected && routeSelected && tripSelected && shapeSelected) {
            if (evt != null) {
                JList source = (JList) evt.getSource();

                if (source.equals(agencyList)) {
                    refreshOnAgencyChange(selectedAgency);
                } else if (source.equals(routeList)) {
                    refreshOnRouteChange(selectedRoute);
                } else if (source.equals(tripList)) {
                    refreshOnTripChange(selectedTrip);
                } else if (source.equals(shapeList)) {
                    refreshOnShapeChange(selectedShape);
                }
            }
        } else if (agencySelected && routeSelected && tripSelected && !shapeSelected) {
            //populate routes with routes for agency; populate trips with trips for route; populate shapes with shape for trip
            resetRouteList(gvc.getRoutes(selectedAgency));
            resetTripList(gvc.getTripsForRoute(selectedRoute));
            refreshOnTripChange(selectedTrip);
        } else if (agencySelected && routeSelected && !tripSelected && shapeSelected) {
            //populate trip with trips for shape and route; populate shape with shapes for route
            resetTripList(gvc.getTripsForRouteAndShape(selectedRoute, selectedShape));
            resetShapeList(gvc.getShapesForRoute(selectedRoute));
        } else if (agencySelected && routeSelected && !tripSelected && !shapeSelected) {
            //populate trip with trips for route; shape with shapes for route
            resetRouteList(gvc.getRoutes(selectedAgency));
            refreshOnRouteChange(selectedRoute);
        } else if (agencySelected && !routeSelected && !tripSelected && shapeSelected) {
            //populate route with routes for shape; populate trip with trips for route and shape
            refreshOnShapeChange(selectedShape);
        } else if (agencySelected && !routeSelected && !tripSelected && !shapeSelected) {
            //populate routes with routes for agency; empty trips; populate shapes with shapes for agency
            refreshOnAgencyChange(selectedAgency);
        } else if (!agencySelected && !routeSelected && !tripSelected && !shapeSelected) {
            //empty routes; empty trips; empty shapes
            resetAgencyList(gvc.getAgencies());
            resetRouteList(Collections.EMPTY_LIST);
            resetTripList(Collections.EMPTY_LIST);
            resetShapeList(Collections.EMPTY_LIST);
        }

        attemptSelection(agencyList, selectedAgency);
        attemptSelection(routeList, selectedRoute);
        attemptSelection(tripList, selectedTrip);
        attemptSelection(shapeList, selectedShape);
        refreshInProgress = false;
    }

    private void refreshOnAgencyChange(Agency selectedAgency) {
        resetRouteList(gvc.getRoutes(selectedAgency));
        resetTripList(Collections.EMPTY_LIST);
        resetShapeList(gvc.getShapesForAgency(selectedAgency));
    }

    private void refreshOnRouteChange(Route selectedRoute) {
        resetTripList(gvc.getTripsForRoute(selectedRoute));
        resetShapeList(gvc.getShapesForRoute(selectedRoute));
    }

    private void refreshOnTripChange(Trip selectedTrip) {
        resetShapeList(gvc.getShapeForTrip(selectedTrip));
    }

    private void refreshOnShapeChange(AgencyAndId selectedShape) {
        resetRouteList(gvc.getRoutesForShape(selectedShape));

        Route selectedRoute = routeList.getSelectedValue();

        if (selectedRoute != null) {
            resetTripList(gvc.getTripsForRouteAndShape(selectedRoute, selectedShape));
        } else {
            resetTripList(Collections.EMPTY_LIST);
        }
    }

    private void resetAgencyList(Collection<Agency> agencies) {
        resetList(agencyList, agencies, new AgencyComparator());
    }

    private void resetRouteList(Collection<Route> routes) {
        resetList(routeList, routes, new RouteComparator());
    }

    private void resetTripList(Collection<Trip> trips) {
        resetList(tripList, trips, new TripComparator());
    }

    private void resetShapeList(Collection<AgencyAndId> shapes) {
        resetList(shapeList, shapes, new AgencyAndIdComparator());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GtfsViewMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GtfsViewMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GtfsViewMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GtfsViewMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GtfsViewMain().setVisible(true);
            }
        });
    }

    private static <T> void attemptSelection(JList<T> list, T item) {
        T selected = list.getSelectedValue();

        if (item != null && ((selected != null && !selected.equals(item)) || selected == null)) {
            DefaultListModel<T> listModel = ((DefaultListModel<T>) list.getModel());
            int index = listModel.indexOf(item);

            if (index != -1) {
                list.setSelectedIndex(index);
            }
        }
    }

    private static <T> void resetList(JList<T> list, Collection<T> items, Comparator<T> comparator) {
        ArrayList<T> temp = new ArrayList<>(items);
        Collections.sort(temp, comparator);
        DefaultListModel<T> listModel = ((DefaultListModel<T>) list.getModel());

        listModel.removeAllElements();
        for (T o : temp) {
            if (o != null) {
                listModel.addElement(o);
            }
        }
        list.clearSelection();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel agencyLabel;
    private JList<Agency> agencyList;
    private JScrollPane agencyScrollPane;
    private JMenu fileMenu;
    private JMenuItem fileOpenMenu;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JTabbedPane jTabbedPane1;
    private JTable jTable1;
    private JMenuBar menuBar;
    private JLabel routeLabel;
    private JList<Route> routeList;
    private JScrollPane routeScrollPane;
    private JLabel shapeLabel;
    private JList<AgencyAndId> shapeList;
    private JScrollPane shapePointsScrollPane;
    private JTable shapePointsTable;
    private JScrollPane shapeScrollPane;
    private JScrollPane stopTimesScrollPane;
    private JTable stopTimesTable;
    private JLabel tripLabel;
    private JList<Trip> tripList;
    private JScrollPane tripScrollPane;
    // End of variables declaration//GEN-END:variables
}
