package shashki;

import java.util.Scanner;

public class Game {
    private final Board board;
    private final Player[] players;
    private final Scanner scanner;

    public Game(Board board, Player[] players, Scanner scanner) {
        this.board = board;
        this.players = players;
        this.scanner = scanner;
    }

    int start() {
        while (true) {
            for (Player player : players) {
                if (player.shouldPrintBoard()) {
                    board.print();
                }
                PlayerMove move = player.makeMove(board, scanner);
                MoveResult result = board.makeMove(move);

                if (result == MoveResult.WIN) {
                    board.print();
                    if (player.mark == Mark.W) {
                        return 0;
                    } else {
                        return 1;
                    }
                } else if (result == MoveResult.DRAW) {
                    board.print();
                    return 2;
                }
            }
        }
    }
}
