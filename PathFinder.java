package app.algorithms;

import app.model.*;

import java.util.ArrayList;

public class PathFinder{
    private static ArrayList<Station> Stations;
    private static ArrayList<mConnection> Connections;
    public PathFinder(ArrayList<Station> st, ArrayList<mConnection> mcn){
        Stations=st;
        Connections=new ArrayList<>(mcn);
        for (mConnection conn: mcn){
         mConnection c1 = new mConnection(conn.getID2(),conn.getID1(),conn.getInt(),conn.isTransfer());
         Connections.add(c1);
        }
    }
    public static ArrayList<Integer> Finder(int station1_id,int station2_id){
        Station curStation=Stations.get(station1_id);
        Station Station2=Stations.get(station2_id);

        ArrayList<mConnection> Available=new ArrayList<mConnection>();

        curStation.pathint=0;
        curStation.path.add(station1_id);
        curStation.ispassed=1;

        for (mConnection conn: Connections){
            if (station1_id==conn.getID1()) {
                Available.add(conn);
            }
        }

        while(curStation.getID()!=Station2.getID()){


            mConnection shortest=Available.get(0);
            ArrayList<mConnection> c2=new ArrayList<>(Available);
            for (mConnection conn: c2){
                if((Stations.get(conn.getID2()).ispassed==0) && (Stations.get(conn.getID1()).pathint+conn.getInt())<(Stations.get(shortest.getID1()).pathint+shortest.getInt())){

                    shortest=conn;
                }
            }
            curStation=Stations.get(shortest.getID1());
            Stations.get(shortest.getID2()).pathint=curStation.pathint+shortest.getInt();
            ArrayList<Integer> curar=new ArrayList<>(curStation.path);
            curar.add(shortest.getID2());
            Stations.get(shortest.getID2()).path=curar;
            Available.remove(shortest);
            curStation=Stations.get(shortest.getID2());
            curStation.ispassed=1;

            for (mConnection conn:Connections){
                if (conn.getID1()==curStation.getID()&&(conn.getInt()+curStation.pathint<Stations.get(conn.getID2()).pathint)){
                    Stations.get(conn.getID2()).pathint=conn.getInt()+curStation.pathint;
                    ArrayList<Integer> curar1=new ArrayList<>(curar);
                    curar1.add(conn.getID2());
                    Stations.get(conn.getID2()).path=curar1;
                }
                if (conn.getID2()!=shortest.getID1()&&conn.getID1()==curStation.getID()){
                    Available.add(conn);
                }
            }
        }
        return curStation.path;
    }
}