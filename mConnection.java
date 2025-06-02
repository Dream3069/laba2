package app.model;
public class mConnection {

    private int ID1;
    private int ID2;
    private int Int;
    private int isTransfer;

    public mConnection(int station1Id, int station2Id, int travelTime, int IsTransfer) {

        ID1 = station1Id;
        ID2 = station2Id;
        Int = travelTime;
        isTransfer = IsTransfer;
    }



    // Getters
    public int getID1() { return ID1; }
    public int getID2() { return ID2; }
    public int getInt() { return Int; }
    public int isTransfer() { return isTransfer; }
}