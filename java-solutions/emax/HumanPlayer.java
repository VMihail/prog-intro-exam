package shashki;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class HumanPlayer extends Player {
    protected HumanPlayer(Mark mark) {
        super(mark);
    }

    @Override
    PlayerMove makeMove(Board board, Scanner scanner) {
        while (true) {
            System.out.println("Player " + mark + " make your move:");
            try {
                StringTokenizer tokenizer = new StringTokenizer(scanner.nextLine());
                String startColumn = tokenizer.nextToken();
                int startRow = Integer.parseInt(tokenizer.nextToken());
                String endColumn = tokenizer.nextToken();
                int endRow = Integer.parseInt(tokenizer.nextToken());

                if (tokenizer.hasMoreTokens() || !board.isValidMove(this, startColumn, startRow, endColumn, endRow)) {
                    System.err.println("Invalid input! Try again.");
                } else {
                    return new PlayerMove(this, startColumn, startRow, endColumn, endRow);
                }
            } catch (NumberFormatException | NoSuchElementException e) {
                System.err.println("Invalid input! Try again.");
            }
        }
    }

    @Override
    public boolean shouldPrintBoard() {
        return true;
    }
}
