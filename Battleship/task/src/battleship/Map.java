package battleship;

import java.sql.SQLOutput;
import java.util.Scanner;

import static java.lang.Math.abs;

public class Map {

    private static final int N = 10;
    char[][] field = new char[N][N];
    public Map() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.field[i][j] = '~';
            }
        }
    }

    public char[][] getField() {
        return field;
    }

    public void setField(char[][] field) {
        this.field = field;
    }

    public void printMap() {
        System.out.println();
        System.out.print(" ");
        for (int i = 1; i <= N; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        char filler = 'A';
        for (int i = 0; i < N; i++) {
            System.out.print(filler);
            filler++;
            for (int j = 0; j < N; j++) {
                System.out.print(" " + field[i][j]);
            }
            System.out.println();
        }
    }

    public void placeShip(Ship ship) {
        System.out.println("Enter the coordinates of the "+ship.getName()+ "("+ship.getSize()+" cells):");
        String firstCoordinate, secondCoordinate;
        Scanner scanner = new Scanner(System.in);
        int checker = 0;
        do {
            if (scanner.hasNext()) {
                firstCoordinate = scanner.next();
                secondCoordinate = scanner.next();
                checker = setShip(ship, firstCoordinate, secondCoordinate);
            }
        } while(checker == -1);
        printMap();

    }

    public int setShip(Ship ship, String firstCoordinate, String secondCoordinate) {
        try {
            int x1 = Character.getNumericValue(firstCoordinate.charAt(0)-17);
            int x2 = Character.getNumericValue(secondCoordinate.charAt(0)-17);
//            int y1 = Character.getNumericValue(firstCoordinate.charAt(1));
//            int y2 = Character.getNumericValue(secondCoordinate.charAt(1));
            int y1 = Integer.parseInt(firstCoordinate.substring(1)) - 1;
            int y2 = Integer.parseInt(secondCoordinate.substring(1)) - 1;
            checkCoordinates(ship, x1, x2, y1, y2);

            for (int i = Math.min(x1,x2); i <= Math.max(x1,x2); i++) {
                for (int j = Math.min(y1,y2); j <= Math.max(y1,y2); j++) {
                    this.field[i][j] = 'O';
                }
            }

        } catch (CustomShipException e) {
            System.out.println(e.getMessage());
            return -1;
        } catch (IllegalArgumentException e) {
            System.out.println("\nError! Wrong coordinates! Try again:\n");
            return -1;
        }

        return 0;
    }

    public void checkCoordinates(Ship ship, int x1, int x2, int y1, int y2) throws CustomShipException{
        if((x1 != x2) && (y1 != y2)) {
            throw new CustomShipException("\nError! Wrong ship location! Try again:");
        }

        if(!checkIfMapArea(x1) || !checkIfMapArea(x2) || !checkIfMapArea(y1) || !checkIfMapArea(y2)) {
            throw new CustomShipException("\nWrong coordinates.");
        }

        if ((abs(x2-x1) != ship.getSize()-1) && (abs(y2-y1) != ship.getSize()-1)) {
            throw new CustomShipException("\nError! Wrong length of the " + ship.getName() +
                    "! Try again:");
        }

        //checking if not too close to another ship
        int minX = Math.min(x1,x2);
        int maxX = Math.max(x1,x2);
        int minY = Math.min(y1,y2);
        int maxY = Math.max(y1,y2);

        for (int i = minX - 1; i <= maxX + 1; i++) {
            for (int j = minY - 1; j <= maxY + 1; j++) {
                if (checkIfMapArea(i) && checkIfMapArea(j) && field[i][j] == 'O') {
                    throw new CustomShipException("Error! You placed it too close to another one. Try again:");
                }
            }
        }


    }

    public static boolean checkIfMapArea(int coordinate) {
        return coordinate >= 0 && coordinate < N;
    }
}
