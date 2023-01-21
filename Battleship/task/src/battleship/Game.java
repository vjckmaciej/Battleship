package battleship;

import java.text.Format;
import java.util.Scanner;

public class Game {
    private final Map[] players;

    private int currentPlayer = 1;

    public Game(Map player1, Map player2) {
        this.players = new Map[]{player1, player2};
    }

    public void startGame() {
        while (players[0].allShipsNumber != 0 && players[1].allShipsNumber != 0) {
            playerTakesHisMove();
        }
    }
    public void playerTakesHisMove() {
        nextRound();
        currentPlayer = 1 - currentPlayer;
        players[1 - currentPlayer].printMap(false);
        System.out.println("---------------------");
        players[currentPlayer].printMap(true);
        System.out.printf("%nPlayer %d, it's your turn:%n%n", currentPlayer + 1);
        players[1 - currentPlayer].takeShot();
    }

    public static void nextRound() {
        System.out.println("Press enter and pass the move to another player");
        System.out.println("...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }


}
