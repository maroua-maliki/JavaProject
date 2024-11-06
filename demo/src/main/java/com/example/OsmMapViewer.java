package com.example;
import javax.swing.SwingUtilities;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class OsmMapViewer extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create the JXMapViewer
        JXMapViewer mapViewer = new JXMapViewer();

        // Set the tile factory using OSM tile server
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer.setTileFactory(tileFactory);

        // Set the focus
        GeoPosition frankfurt = new GeoPosition(50.11, 8.68);
        mapViewer.setZoom(7);
        mapViewer.setAddressLocation(frankfurt);

        // Add interactions
        mapViewer.addMouseListener(new PanMouseInputListener(mapViewer));
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapViewer));

        // Create a SwingNode to embed the JXMapViewer in JavaFX
        SwingNode swingNode = new SwingNode();
        createSwingContent(swingNode, mapViewer);

        // Create the layout
        BorderPane root = new BorderPane();
        root.setCenter(swingNode);

        // Create the scene and show the stage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("OpenStreetMap Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createSwingContent(final SwingNode swingNode, final JXMapViewer mapViewer) {
        SwingUtilities.invokeLater(() -> swingNode.setContent(mapViewer));
    }

    public static void main(String[] args) {
        launch(args);
    }
}