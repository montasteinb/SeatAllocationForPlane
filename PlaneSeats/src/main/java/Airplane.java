public class Airplane {

    public final int NUMBER_OF_FIRST_CLASS_ROWS = 5;
    public final int NUMBER_OF_FIRST_CLASS_AISLES = 4;
    public final int NUMBER_OF_ECONOMY_CLASS_ROWS = 15;
    public final int NUMBER_OF_ECONOMY_CLASS_AISLES = 6;
    private Seat[][] firstClassSeats;
    private Seat[][] economyClassSeats;

    /*
     * Creates an airplane with 2 classes: first class and economy.
     * First class has 5 rows and 2 aisles on each side.
     * Economy class has 15 rows and 3 seats on each side.
     */
    public Airplane(){

        firstClassSeats = new Seat[NUMBER_OF_FIRST_CLASS_ROWS][];
        economyClassSeats = new Seat[NUMBER_OF_ECONOMY_CLASS_ROWS][];

        for (int rowIndex = 0; rowIndex < NUMBER_OF_FIRST_CLASS_ROWS; rowIndex++){

            firstClassSeats[rowIndex] = new Seat[NUMBER_OF_FIRST_CLASS_AISLES];
            for (int aisleIndex = 0; aisleIndex < NUMBER_OF_FIRST_CLASS_AISLES; aisleIndex++){

                firstClassSeats[rowIndex][aisleIndex] = new Seat();
            }
        }
        for (int rowIndex = 0; rowIndex < NUMBER_OF_ECONOMY_CLASS_ROWS; rowIndex++){
            economyClassSeats[rowIndex] = new Seat[NUMBER_OF_ECONOMY_CLASS_AISLES];
            for (int aisleIndex = 0; aisleIndex < NUMBER_OF_ECONOMY_CLASS_AISLES; aisleIndex++){

                economyClassSeats[rowIndex][aisleIndex] = new Seat();
            }
        }
    }

    /*
     * Occupies a seat using the seat's class, row number, and aisle number.
     * seatingClass Class of the seat
     * row Row number of a seat
     * aisle Aisle number of a seat
     */
    public void occupySeat(String seatingClass, int row, int aisle)
    {
        if (seatingClass.equalsIgnoreCase("first")
                || seatingClass.equalsIgnoreCase("1st"))
        {
            firstClassSeats[row - 1][aisle - 1].occupy();
        }
        else if (seatingClass.equalsIgnoreCase("economy")
                || seatingClass.equalsIgnoreCase("econ"))
        {
            economyClassSeats[row - NUMBER_OF_FIRST_CLASS_ROWS - 1][aisle - 1].occupy();
        }
    }

    /*
     * Checks if a seat is occupied.
     * seatingClass Class of the seat
     * Row number of a seat
     * aisle Aisle number of a seat
     * If a seat is occupied or not
     */
    public boolean checkOccupied(String seatingClass, int row, int aisle)
    {
        if (seatingClass.equalsIgnoreCase("first")
                || seatingClass.equalsIgnoreCase("1st"))
        {
            return firstClassSeats[row - 1][aisle - 1].checkOccupancy();
        }
        else if (seatingClass.equalsIgnoreCase("economy")
                || seatingClass.equalsIgnoreCase("econ"))
        {
            return economyClassSeats[row - 1]
                    [aisle - 1].checkOccupancy();
        }
        return false;
    }
}

