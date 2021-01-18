package shashki;

public class Board {
    private final Mark[][] cells = new Mark[8][8];
    private int[] fishki = new int[] {8, 8};

    public Board() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i < 3) {
                    if (i % 2 == 0) {
                        if (j % 2 != 0) {
                            cells[i][j] = Mark.B;
                        } else {
                            cells[i][j] = Mark.E;
                        }
                    } else {
                        if (j % 2 == 0) {
                            cells[i][j] = Mark.B;
                        } else {
                            cells[i][j] = Mark.E;
                        }
                    }
                } else if (i > 4) {
                    if (i % 2 == 0) {
                        if (j % 2 != 0) {
                            cells[i][j] = Mark.W;
                        } else {
                            cells[i][j] = Mark.E;
                        }
                    } else {
                        if (j % 2 == 0) {
                            cells[i][j] = Mark.W;
                        } else {
                            cells[i][j] = Mark.E;
                        }
                    }
                } else {
                    cells[i][j] = Mark.E;
                }
            }
        }
    }

    boolean isValidMove(Player player, String startColumn, int startRow, String endColumn, int endRow) {
        if (startRow <= 0 || startRow > 8 || endRow <= 0 || endRow > 8) {
            return false;
        }
        int startColumnIndex = indexByLetter(startColumn);
        int endColumnIndex = indexByLetter(endColumn);
        if (startColumnIndex < 0 || startColumnIndex >= 8 || endColumnIndex < 0 || endColumnIndex >= 8) {
            return false;
        }

        if (cells[8 - startRow][startColumnIndex] != player.mark || cells[8 - endRow][endColumnIndex] != Mark.E) {
            return false;
        }

        int columnDiff = endColumnIndex - startColumnIndex;
        int rowDiff = endRow - startRow;
        if (Math.abs(columnDiff) != Math.abs(rowDiff) || Math.abs(rowDiff) <= 0 || Math.abs(rowDiff) > 2) {
            return false;
        }

        if (Math.abs(rowDiff) == 1) {
            return true;
        }
        // assert Math.abs(rowDiff) == 2;
        int midRow = (startRow + endRow) / 2;
        int midColumnIndex = (startColumnIndex + endColumnIndex) / 2;

        if (cells[8 - midRow][midColumnIndex] == player.mark || cells[8 - midRow][midColumnIndex] == Mark.E) {
            return false;
        }
        return true;
    }

    // a 3 b 4
    // b 6 a 5
    // b 4 c 5
    // d 6 b 4
    MoveResult makeMove(PlayerMove move) {
        if (!isValidMove(move.player, move.startColumn, move.startRow, move.endColumn, move.endRow)) {
            throw new IllegalArgumentException("Invalid move!");
        }

        int startColumnIndex = indexByLetter(move.startColumn);
        int endColumnIndex = indexByLetter(move.endColumn);

        cells[8 - move.startRow][startColumnIndex] = Mark.E;
        cells[8 - move.endRow][endColumnIndex] = move.player.mark;

//        boolean didWin = checkVertical(move.row, move.column)
//                || checkHorizontal(move.row, move.column)
//                || checkDiagonalMain(move.row, move.column)
//                || checkDiagonalPobochnoe(move.row, move.column);
//
//        if (didWin) {
//            return MoveResult.WIN;
//        } else if (filledCells == height * width) {
//            return MoveResult.DRAW;
//        } else {
//            return MoveResult.UNKNOWN;
//        }
        return MoveResult.UNKNOWN;
    }

    public void print() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("  a b c d e f g h\n");
        for (int i = 0; i < 8; i++) {
            sb.append(8 - i).append(" ");
            for (Mark cell : cells[i]) {
                sb.append(cell).append(" ");
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    private int indexByLetter(String letter) {
        int result = -1;
        switch (letter) {
            case "a" -> result = 0;
            case "b" -> result = 1;
            case "c" -> result = 2;
            case "d" -> result = 3;
            case "e" -> result = 4;
            case "f" -> result = 5;
            case "g" -> result = 6;
            case "h" -> result = 7;
        }
        return result;
    }
}
