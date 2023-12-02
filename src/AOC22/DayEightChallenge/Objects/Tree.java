package AOC22.DayEightChallenge.Objects;

public class Tree implements Comparable {
    private int height;

    public Tree(String height) {
        this.height = Integer.parseInt(height);
    }

    @Override
    public int compareTo(Object o) {
        if (o.getClass() != Tree.class) return -1;
        return this.height - ((Tree) o).height;
    }

    @Override
    public String toString() {
        return "" + this.height;
    }
}
