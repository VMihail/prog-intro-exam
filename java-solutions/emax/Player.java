package shashki;

import java.util.Scanner;

public abstract class Player {
    final Mark mark;

    protected Player(Mark mark) {
        this.mark = mark;
    }

    abstract PlayerMove makeMove(Board board, Scanner scanner);

    public abstract boolean shouldPrintBoard();
}
