public class Seat {

    private boolean occupied;

    //Creates a seat with an occupancy status.

    public Seat(){
        occupied = false;
    }

     //Occupies a seat.

    public void occupy(){
        occupied = true;
    }

    //Checks the occupancy status of a seat.

    public boolean checkOccupancy(){
        return occupied;
    }
}
