package shashki;

public enum Mark {
    W, B, E;

    @Override
    public String toString() {
        return switch (this) {
            case W -> "W";
            case B -> "B";
            default -> ".";
        };
    }
}
