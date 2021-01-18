package shashki;

public class PlayerMove {
    final Player player;
    final String startColumn;
    final int startRow;
    final String endColumn;
    final int endRow;

    public PlayerMove(Player player, String startColumn, int startRow, String endColumn, int endRow) {
        this.player = player;
        this.startColumn = startColumn;
        this.startRow = startRow;
        this.endColumn = endColumn;
        this.endRow = endRow;
    }
}
