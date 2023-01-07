package battleship;

public class Main {

    public static void main(String[] args) {
        // Write your code here
        Map area = new Map();
        area.printMap();
        Ship aircraftCarrier = new Ship("Aircraft Carrier", 5);
        Ship battleship = new Ship("Battleship", 4);
        Ship submarine = new Ship("Submarine", 3);
        Ship cruiser = new Ship("Cruiser", 3);
        Ship destroyer = new Ship("Destroyer", 2);
        area.placeShip(aircraftCarrier);
        area.placeShip(battleship);
        area.placeShip(submarine);
        area.placeShip(cruiser);
        area.placeShip(destroyer);
    }
}
