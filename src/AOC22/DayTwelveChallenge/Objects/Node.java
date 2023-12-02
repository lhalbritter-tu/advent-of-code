package AOC22.DayTwelveChallenge.Objects;

public class Node {
    private char symbol;
    private int height;

    private int[] position;

    public Node(char symbol, int row, int col) {
        this.position = new int[] {row, col};
        this.symbol = symbol;
        this.height = symbol == 'S' ? 'a' : (symbol == 'E' ? 'z' : symbol);
    }

    public boolean validMove(Node other) {
        return other.height - this.height < 2;
    }

    public int[] position() {
        return position;
    }

    @Override
    public String toString() {
        return symbol + " at (" + position[0] + ", " + position[1] + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != Node.class) return false;
        Node other = (Node) obj;
        return other.position[0] == this.position[0] && other.position[1] == this.position[1];
    }

    @Override
    public int hashCode() {
        return position[0] * 420 + position[1] * 69;
    }

    public String symbol() {
        return "" + symbol;
    }

    public Node copy() {
        return new Node(symbol, position[0], position[1]);
    }

    public void setSymbol(char s) {
        this.symbol = s;
    }
}
