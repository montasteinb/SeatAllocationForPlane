import java.util.Scanner;

public class SeatingCreator {

    public static void main(String args[]){

        final String SENTINEL = "Quit";
        String response = "";
        Scanner sc = new Scanner(System.in);

        Airplane plane = new Airplane();

        while (!response.equalsIgnoreCase(SENTINEL)) {
            System.out.println();
            System.out.println("Option List:");
            System.out.println("-------------");
            System.out.println("Add passengers: Enter 1");
            System.out.println("Show seating: Enter 2");
            System.out.println("Quit: Enter \"Quit\"");
            String choice = sc.nextLine();
            System.out.println();

            if (choice.equals("1")) {
                System.out.print("Would you like to choose or "
                        + "automatically assign seats? (choose/auto) ");
                String reChoice = sc.nextLine();
                if (reChoice.equalsIgnoreCase("choose")
                        || reChoice.equalsIgnoreCase("manual")) {
                    System.out.print("First class or economy? ");
                    String flightClass = sc.nextLine();
                    System.out.println();
                    Display.printSeating(plane, flightClass);
                    System.out.println();
                    System.out.print("Please enter your choice of row: ");
                    String row = sc.nextLine();
                    System.out.print("Please enter your choice of aisle: ");
                    String aisle = sc.nextLine();
                    plane.occupySeat(flightClass, rowToInt(row), Integer.parseInt(aisle));
                    System.out.println("Seat assigned!");
                }

                else if (reChoice.substring(0, 4).equalsIgnoreCase("auto")) {
                    System.out.print("First or economy class? ");
                    String flightClass = sc.nextLine();

                    if (flightClass.equalsIgnoreCase("first")){
                        System.out.print("Enter the number of seats you would "
                                + "like reserved together (up to 2). ");
                    }else {
                        System.out.print("Enter the number of seats you would "
                                + "like reserved together (up to 3). ");
                    }

                    int together = Integer.parseInt(sc.nextLine());

                    String pref = "";
                    if (together == 1){

                        if (flightClass.equalsIgnoreCase("first")){
                            System.out.print("Do you have any seating preference? "
                                    + "Window, aisle, or none? ");
                        }else {
                            System.out.print("Do you have any seating preference? "
                                    + "Window, middle, aisle, or none? ");
                        }
                        pref = sc.nextLine();

                        if (!flightClass.equalsIgnoreCase("first")
                                && pref.equalsIgnoreCase("aisle")) {
                            pref = "middle";
                        }
                    }

                    int rows;
                    int startingRow;
                    int aisles;

                    if (flightClass.equalsIgnoreCase("first")) {
                        rows = plane.NUMBER_OF_FIRST_CLASS_ROWS;
                        aisles = plane.NUMBER_OF_FIRST_CLASS_AISLES;
                        startingRow = 1;
                    }else {
                        aisles = plane.NUMBER_OF_ECONOMY_CLASS_AISLES;
                        startingRow = plane.NUMBER_OF_FIRST_CLASS_ROWS + 1;
                        rows = startingRow + plane.NUMBER_OF_ECONOMY_CLASS_ROWS;
                    }
                    if (together == 3) {
                        boolean foundSeat = false;
                        for (int i = startingRow; i < rows; i++) {
                            for (int j = 1; j < aisles - 1; j++) {
                                if ((foundSeat == false)
                                        && !plane.checkOccupied(flightClass, i, j)) {
                                    if (!plane.checkOccupied(flightClass, i, j + 1)) {
                                        if (!plane.checkOccupied(flightClass, i, j + 2)) {
                                            foundSeat = true;
                                            plane.occupySeat(flightClass, i, j);
                                            plane.occupySeat(flightClass, i, j + 1);
                                            plane.occupySeat(flightClass, i, j + 2);
                                            System.out.println("Seats assigned!");
                                        }
                                    }
                                }
                            }
                        }
                        Display.printSeating(plane, flightClass);
                    }else if (together == 2) {
                        boolean foundSeat = false;
                        for (int i = startingRow; i < rows; i++) {
                            for (int j = 1; j < aisles; j++) {
                                if ((foundSeat == false)
                                        && !plane.checkOccupied(flightClass, i, j)) {
                                    if (!plane.checkOccupied(flightClass, i, j + 1)) {
                                        foundSeat = true;
                                        plane.occupySeat(flightClass, i, j);
                                        plane.occupySeat(flightClass, i, j + 1);
                                        System.out.println("Seats assigned!");
                                    }
                                }
                            }
                        }
                        Display.printSeating(plane, flightClass);
                    }else if (pref.equalsIgnoreCase("window")){
                        boolean foundSeat = false;
                        for (int i = startingRow; i < rows; i++) {
                            if ((foundSeat == false)
                                    && !plane.checkOccupied(flightClass, i, 1)) {
                                foundSeat = true;
                                plane.occupySeat(flightClass, i, 1);
                                System.out.println("Seat assigned!");
                            }
                            if ((foundSeat == false)
                                    && !plane.checkOccupied(flightClass, i, aisles)) {
                                foundSeat = true;
                                plane.occupySeat(flightClass, i, aisles);
                                System.out.println("Seat assigned!");
                            }
                        }
                    }else if (pref.equalsIgnoreCase("middle")) {
                        boolean foundSeat = false;
                        for (int i = startingRow; i < rows; i++){
                            if ((foundSeat == false)
                                    && !plane.checkOccupied(flightClass, i, aisles / 2 - 1)) {
                                foundSeat = true;
                                plane.occupySeat(flightClass, i, 2);
                                System.out.println("Seat assigned!");
                            }
                            if ((foundSeat == false)
                                    && !plane.checkOccupied(flightClass, i, aisles - 1)) {
                                foundSeat = true;
                                plane.occupySeat(flightClass, i, aisles - 1);
                                System.out.println("Seat assigned!");
                            }
                        }
                    }else if (pref.equalsIgnoreCase("aisle")){
                        boolean foundSeat = false;
                        for (int i = startingRow; i < rows; i++){
                            if ((foundSeat == false)
                                    && !plane.checkOccupied(flightClass, i, aisles / 2)){
                                foundSeat = true;
                                plane.occupySeat(flightClass, i, aisles / 2);
                                System.out.println("Seat assigned!");
                            }
                            if ((foundSeat == false)
                                    && !plane.checkOccupied(flightClass, i, aisles / 2 + 1)){
                                foundSeat = true;
                                plane.occupySeat(flightClass, i, aisles / 2 + 1);
                                System.out.println("Seat assigned!");
                            }
                        }
                    }else {
                        System.out.println("That is not a valid option.");
                    }
                }
            }else if (choice.equals("2")){
                Display.printSeating(plane, "whole");
            }else if (choice.equalsIgnoreCase("Quit")){
                response = choice;
                System.out.println("Seat reserving complete.");
            }else{
                System.out.println("That is not a valid option!");
            }
        }
        sc.close();
    }

    /**
     * Converts a row letter into a char which then is converted into an int.
     *  Row letter of a seat
     *  Row number of a seat
     */
    private static int rowToInt(String row) {
        Character c = row.charAt(0);
        return Character.getNumericValue(c) - 9;
    }
}
