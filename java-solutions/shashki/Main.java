package shashki;

import java.util.Scanner;

// :NOTE: # нет случайного и эвристического игрока

// :NOTE: # нет поддержки турнира
// :NOTE: # фишка не съедается

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You started a new game.");
        HumanPlayer white = new HumanPlayer(Mark.W);
        HumanPlayer black = new HumanPlayer(Mark.B);
        Board board = new Board();
        Game game = new Game(board, new Player[]{white, black}, scanner);

        int result = game.start();

        if (result == 0) {
            System.out.println("White player won.");
        } else if (result == 1) {
            System.out.println("Black player won.");
        } else {
            System.out.println("Draw.");
        }
    }
}
