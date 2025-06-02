package app.db;
import app.model.*;
import app.db.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Metro {
    public ArrayList<Station> getAllStations() throws SQLException {
        ArrayList<Station> stations = new ArrayList<>();
        try (java.sql.Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM stations")) {

            while (rs.next()) {

                stations.add(new Station(
                        rs.getInt("id")-1,
                        rs.getString("name"),
                        rs.getInt("line_id"),
                        rs.getInt("x"),
                        rs.getInt("y")
                ));
            }
        }

        return stations;
    }


    public ArrayList<mConnection> getAllConnections() throws SQLException {
        ArrayList<mConnection> connections = new ArrayList<>();

        try (java.sql.Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM connections")) {

            while (rs.next()) {
                connections.add(new mConnection(
                        rs.getInt("station1_id")-1,
                        rs.getInt("station2_id")-1,
                        rs.getInt("travel_time"),
                        rs.getInt("isTransfer")
                ));
            }
        }

        return connections;
    }

}