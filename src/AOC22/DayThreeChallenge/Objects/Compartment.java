package AOC22.DayThreeChallenge.Objects;

public class Compartment {
    private Character[] content;

    public Compartment(String content) {
        this.content = new Character[content.length()];
        for (int i = 0; i < content.length(); i++) {
            this.content[i] = content.charAt(i);
        }
    }

    public boolean contains(Character c) {
        for (Character item : content) {
            if (item.equals(c)) return true;
        }

        return false;
    }

    public Character equal(Compartment other) {
        for (Character c : other.content) {
            if (contains(c)) return c;
        }

        return null;
    }

    public Character equal(Compartment first, Compartment second) {
        for (Character c : first.content) {
            if (contains(c) && second.contains(c)) return c;
        }

        return null;
    }
}
