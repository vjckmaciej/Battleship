package battleship;

public class Main {

    public static void main(String[] args) {
        Map area1 = new Map(1);
        Map area2 = new Map(2);
        Ship aircraftCarrier = new Ship("Aircraft Carrier", 5);
        Ship battleship = new Ship("Battleship", 4);
        Ship submarine = new Ship("Submarine", 3);
        Ship cruiser = new Ship("Cruiser", 3);
        Ship destroyer = new Ship("Destroyer", 2);

        area1.placeAllShips(aircraftCarrier, battleship, submarine, cruiser, destroyer);
        Game.nextRound();
        area2.placeAllShips(aircraftCarrier, battleship, submarine, cruiser, destroyer);
        Game game = new Game(area1, area2);
        game.startGame();
    }
}
