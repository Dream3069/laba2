 package app.model;

 import java.util.ArrayList;

 public class Station {
     private int id;
     private String name;
     private int lineId;
     private double x;
     private double y;

     public Station(int id, String name, int lineId, double x, double y) {
         this.id = id;
         this.name = name;
         this.lineId = lineId;
         this.x = x;
         this.y = y;
     }
     public int ispassed=0;
     public ArrayList<Integer> path=new ArrayList<>();
     public int pathint=-1;
     public int getID() { return id; }
     public String getName() { return name; }
     public int getLineId() { return lineId; }
     public double getX() { return x; }
     public double getY() { return y; }
 }