package app;
import app.db.Metro;
import app.model.Station;
import app.algorithms.*;
import app.model.mConnection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PointConnectorApp extends Application {
    private final Map<Integer, double[]> points = new HashMap<>();
    private final Pane drawingPane = new Pane();
    private final Metro metro=new Metro();
    private final ArrayList<Station> Stations;
    private final ArrayList<mConnection> Connections;
    public PointConnectorApp() throws SQLException{
        try {
            Stations=metro.getAllStations();
            Connections=metro.getAllConnections();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    // Храним координаты точек


    //@Override
    public void start(Stage primaryStage) {
        // 1. Задаём координаты
        for (int i=0;i<Stations.size();i++) {
            points.put(i, new double[]{Stations.get(i).getX(), Stations.get(i).getY()});
        }

        for (int i=0;i<Connections.size();i++){
            Station S1=Stations.get(Connections.get(i).getID1());
            Station S2=Stations.get(Connections.get(i).getID2());
            Line line = new Line(S1.getX(), S1.getY(), S2.getX(), S2.getY());
            line.setStroke(Color.YELLOW);
            line.setStrokeWidth(2);
            drawingPane.getChildren().add(line);
        }
        // 2. Рисуем точки и подписи
        for (Map.Entry<Integer, double[]> entry : points.entrySet()) {
            double[] coords = entry.getValue();
            String label = String.valueOf(entry.getKey());

            Circle point = new Circle(coords[0], coords[1], 5, Color.BLUE);
            Text text = new Text(coords[0] + 10, coords[1] - 10,  label);

            drawingPane.getChildren().addAll(point, text);
        }

        // 3. Интерфейс выбора
        ComboBox<Integer> startBox = new ComboBox<>();
        ComboBox<Integer> endBox = new ComboBox<>();
        startBox.getItems().addAll(points.keySet());
        endBox.getItems().addAll(points.keySet());

        Button drawLineButton = new Button("Проложить маршрут");

        drawLineButton.setOnAction(e -> {
            Integer from = startBox.getValue();
            Integer to = endBox.getValue();

            if (from != null && to != null && !from.equals(to)) {
                PathFinder PF=new PathFinder(Stations,Connections);
                ArrayList<Integer> StationsP=PF.Finder(from,to);
            for (int i=0;i<StationsP.size()-1;i++){
                Station S1=Stations.get(StationsP.get(i));
                Station S2=Stations.get(StationsP.get(i+1));
                Line line = new Line(S1.getX(), S1.getY(), S2.getX(), S2.getY());
                line.setStroke(Color.RED);
                line.setStrokeWidth(3);
                //Text text = new Text( 100, 100,  String.valueOf(StationsP));
                drawingPane.getChildren().add(line);
            }
            }
        });

        VBox root = new VBox(10, startBox, endBox, drawLineButton, drawingPane);
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("METRO");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}