package AOC22.DayThreeChallenge.Objects;

import java.util.HashMap;

public class Items {
    private static HashMap<Character, Integer> priorities;

    private static void setPriorities() {
        priorities = new HashMap<>();
        int value = 1;
        for (int i = 97; i < 123; i++) {
            priorities.put(Character.valueOf((char) i), value++);
        }

        for (int i = 65; i < 91; i++) {
            priorities.put(Character.valueOf((char) i), value++);
        }
    }

    public static Integer getPriority(Character character) {
        if (priorities == null) setPriorities();
        return priorities.get(character);
    }
}
