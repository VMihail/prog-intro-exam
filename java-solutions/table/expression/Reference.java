package table.expression;

import java.util.List;
import java.util.Objects;

public class Reference implements Expression {
    private final int cellPosition;
    private Integer cache = null;

    public Reference(int cellPosition) {
        this.cellPosition = cellPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reference aConst = (Reference) o;
        return Objects.equals(cellPosition, aConst.cellPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cellPosition);
    }

    @Override
    public String toString() {
        return "$" + cellPosition;
    }

    @Override
    public int evaluate(List<Expression> cells) {
        if (cache == null) {
            if (cellPosition < 0 || cellPosition > cells.size()) {
                throw new IllegalArgumentException("Invalid reference: " + cellPosition + ", number of cells: " + cells.size());
            }
            cache = cells.get(cellPosition - 1).evaluate(cells);
        }
        return cache;
    }
}
