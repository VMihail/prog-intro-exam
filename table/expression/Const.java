package table.expression;
import java.util.List;
import java.util.Objects;

public class Const implements Expression {
    private final int val;

    public Const(int val) {
        this.val = val;
    }

    public int evaluate(List<Expression> cells) {
        return val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Const aConst = (Const) o;
        return Objects.equals(val, aConst.val);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
