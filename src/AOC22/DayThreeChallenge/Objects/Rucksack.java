package AOC22.DayThreeChallenge.Objects;

public class Rucksack {
    private Compartment left;
    private Compartment right;

    public Rucksack(String content) {
        String left = content.substring(0, content.length() / 2);
        String right = content.substring(content.length() / 2);

        this.left = new Compartment(left);
        this.right = new Compartment(right);
    }

    public int getSameItemPriority() {
        Character same = left.equal(right);
        if (same == null) return 0;
        return Items.getPriority(same);
    }
}
