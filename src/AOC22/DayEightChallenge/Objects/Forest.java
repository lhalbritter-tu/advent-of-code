package AOC22.DayEightChallenge.Objects;

import java.util.List;

public class Forest {
    private Tree[][] forest;

    private Forest() { }

    public static Forest buildForest(List<List<String>> forest) {
        Forest result = new Forest();

        result.forest = new Tree[forest.size()][forest.get(0).size()];

        for (int i = 0; i < result.forest.length; i++) {
            for (int j = 0; j < result.forest[i].length; j++) {
                result.forest[i][j] = new Tree(forest.get(i).get(j));
            }
        }

        return result;
    }

    public void printForest() {
        for (int i = 0; i < this.forest.length; i++) {
            for (int j = 0; j < this.forest[i].length; j++) {
                System.out.print(this.forest[i][j]);
            }
            System.out.println();
        }
    }

    public long getVisible() {
        long result = (this.forest.length + this.forest[0].length) * 2 - 4;
        for (int i = 1; i < this.forest.length - 1; i++) {
            for (int j = 1; j < this.forest[i].length - 1; j++) {
                if (isVisible(i, j)) {
                    result += 1;
                }
            }
        }

        return result;
    }

    public long getMaxScenicScore() {
        long result = -1;
        for (int i = 0; i < this.forest.length; i++) {
            for (int j = 0; j < this.forest[i].length; j++) {
                result = Math.max(result, getScenicScore(i, j));
            }
        }
        return result;
    }

    public boolean isVisible(int i, int j) {
        Tree t = this.forest[i][j];
        boolean top = true, bottom = true, left = true, right = true;
        for (int x = 0; x < i; x++) {
            if (t.compareTo(this.forest[x][j]) <= 0) top = false;
        }

        for (int x = i + 1; x < this.forest.length; x++) {
            if (t.compareTo(this.forest[x][j]) <= 0) bottom = false;
        }

        for (int x = 0; x < j; x++) {
            if (t.compareTo(this.forest[i][x]) <= 0) left = false;
        }

        for (int x = j + 1; x < this.forest[i].length; x++) {
            if (t.compareTo(this.forest[i][x]) <= 0) right = false;
        }

        return top || bottom || left || right;
    }

    public long getScenicScore(int i, int j) {
        Tree t = this.forest[i][j];
        int top = 0, bottom = 0, left = 0, right = 0;
        for (int x = i - 1; x > -1; x--) {
            top++;
            if (t.compareTo(this.forest[x][j]) <= 0) break;
        }

        for (int x = i + 1; x < this.forest.length; x++) {
            bottom++;
            if (t.compareTo(this.forest[x][j]) <= 0) break;
        }

        for (int x = j - 1; x > -1; x--) {
            left++;
            if (t.compareTo(this.forest[i][x]) <= 0) break;
        }

        for (int x = j + 1; x < this.forest[i].length; x++) {
            right++;
            if (t.compareTo(this.forest[i][x]) <= 0) break;
        }

        return top * bottom * left * right;
    }
}
