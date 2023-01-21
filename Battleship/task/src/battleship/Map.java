package battleship;

import java.sql.SQLOutput;
import java.util.Scanner;

import static java.lang.Math.abs;

public class Map {
    protected static final int N = 10;
    protected int allShipsNumber = 5;
    protected char[][] field = new char[N][N];

    protected int playerNumber;
    public Map(int playerNumber) {
        this.playerNumber = playerNumber;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.field[i][j] = '~';
            }
        }
    }
    public void printMap(boolean isMine) {
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
                if (!isMine && this.field[i][j] == 'O') {
                    System.out.print(" ~");
                } else {
                    System.out.print(" " + field[i][j]);
                }
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
        printMap(true);

    }

    public int setShip(Ship ship, String firstCoordinate, String secondCoordinate) {
        try {
            int x1 = Character.getNumericValue(firstCoordinate.charAt(0)-17);
            int x2 = Character.getNumericValue(secondCoordinate.charAt(0)-17);
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

    public void checkCoordinates(int x, int y) throws CustomShipException {
        if(!checkIfMapArea(x) || !checkIfMapArea(y) ) {
            throw new CustomShipException("\nError! You entered the wrong coordinates! Try again: ");
        }
    }
    public void takeShot() {
        Scanner scanner = new Scanner(System.in);
        int checker = 0;
        String shotCoordinates;
        do {
            if (scanner.hasNext()) {
                shotCoordinates = scanner.next();
                checker = shot(shotCoordinates);
            }
        } while(checker == -1);
    }

    public int shot(String shotCoordinates) {
        try {
            int x = Character.getNumericValue(shotCoordinates.charAt(0)-17);
            int y = Integer.parseInt(shotCoordinates.substring(1)) - 1;
            checkCoordinates(x,y);

            if (this.field[x][y] == 'O') {
                this.field[x][y] = 'X';
//                printShotMap();
                if (isSunk(x,y,false)) {
                    this.allShipsNumber--;
                    if(this.allShipsNumber == 0) {
                        System.out.println();
                        System.out.println("You sank the last ship. You won. Congratulations!");
                    } else {
                        System.out.println("You sank a ship! Specify a new target:");
                    }
                } else {
                    System.out.println(" ");
                    System.out.println("You hit a ship!");
                }
            } else if (this.field[x][y] == '~' || this.field[x][y] == 'M'){
                this.field[x][y] = 'M';
//                printShotMap();
                System.out.println("You missed!");
            } else {
//                printShotMap();
                System.out.println();
                System.out.println("You hit a ship!");
            }
        } catch (CustomShipException e) {
            System.out.println(e.getMessage());
            return -1;
        } catch (IllegalArgumentException e) {
            System.out.println("\nError! You entered the wrong coordinates! Try again: ");
            return -1;
        }
        return 0;
    }

    public boolean isSunk(int x, int y, boolean checker) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (checkIfMapArea(i) && checkIfMapArea(j) && field[i][j] == 'O') {
                    return false;
                }
                if (checkIfMapArea(i) && checkIfMapArea(j) && field[i][j] == 'X' && !(x == i && y == j)) {
                    if (i + j <  x + y && !checker){
                        return isSunk(i, j, false);
                    }
                    if (i + j > x + y) {
                        return isSunk(i, j, true);
                    }
                }
            }
        }

        return true;
    }

    public void placeAllShips(Ship ship1, Ship ship2, Ship ship3, Ship ship4, Ship ship5) {
        System.out.println("Player " + playerNumber + ", place your ships on the game field");
        printMap(true);
        placeShip(ship1);
        placeShip(ship2);
        placeShip(ship3);
        placeShip(ship4);
        placeShip(ship5);
    }




}
