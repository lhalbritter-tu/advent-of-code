package AOC24.DayTwo.Objects;

import java.util.ArrayList;
import java.util.List;

public class Report {

    List<Integer> levels = new ArrayList<>();

    public Report (String levels) {
        for (String level : levels.split(" ")) {
            this.levels.add(Integer.parseInt(level));
        }
    }

    public boolean isSecure() {
        boolean ascending = levels.get(0) < levels.get(1);

        for (int i = 0; i < levels.size() - 1; i++) {
            int a = levels.get(i);
            int b = levels.get(i + 1);

            if (ascending) {
                if (b <= a || (b - a) > 3) return false;
            }
            else {
                if (b >= a || (a - b) > 3) return false;
            }
        }

        return true;
    }

    public boolean isSecureDampened(List<Integer> levels, int depth) {
        boolean ascending = levels.get(0) < levels.get(1);
        boolean success = true;

        System.out.println("Checking levels " + levels.toString());

        for (int i = 0; i < levels.size() - 1; i++) {
            int a = levels.get(i);
            int b = levels.get(i + 1);

            if (ascending) {
                if (b <= a || (b - a) > 3) {
                    System.out.println("Hello there is a mistakes");
                    if (depth >= 1) return false;
                    List<Integer> firstLevelCopy = new ArrayList<>(levels);
                    List<Integer> secondLevelCopy = new ArrayList<>(levels);
                    firstLevelCopy.remove(i);
                    secondLevelCopy.remove(i + 1);
                    success = isSecureDampened(firstLevelCopy, depth + 1) || isSecureDampened(secondLevelCopy, depth + 1);
                    if (success) return true;
                }
            }
            else {
                if (b >= a || (a - b) > 3) {
                    System.out.println("Hello there is a mistakes");
                    if (depth >= 1) return false;
                    List<Integer> firstLevelCopy = new ArrayList<>(levels);
                    List<Integer> secondLevelCopy = new ArrayList<>(levels);
                    firstLevelCopy.remove(i);
                    secondLevelCopy.remove(i + 1);
                    success = isSecureDampened(firstLevelCopy, depth + 1) || isSecureDampened(secondLevelCopy, depth + 1);
                    if (success) return true;
                }
            }
        }

        // Check it without the first level
        if (depth == 0) {
            List<Integer> levelCopy = new ArrayList<>(levels);
            levelCopy.remove(0);
            success = isSecureDampened(levelCopy, depth + 1);
            if (success) return true;
        }

        // Check it without the last level
        if (depth == 0) {
            List<Integer> levelCopy = new ArrayList<>(levels);
            levelCopy.remove(levelCopy.size() - 1);
            success = isSecureDampened(levelCopy, depth + 1);
        }

        return success;
    }

    public List<Integer> getLevels() {
        return levels;
    }

    @Override
    public String toString() {
        return levels.toString();
    }
}
