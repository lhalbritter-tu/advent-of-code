package AOC23.DayEleven;

import AOC23.DayEleven.Objects.Universe;
import Utility.InputManager;

import java.util.List;

public class Puzzle {
    public static void main(String[] args) {
        List<List<String>> input = InputManager.INSTANCE.getAdventOfCodeInput2D(11, 2023);
        List<List<String>> test = InputManager.INSTANCE.get2D(("...#......\n" +
                ".......#..\n" +
                "#.........\n" +
                "..........\n" +
                "......#...\n" +
                ".#........\n" +
                ".........#\n" +
                "..........\n" +
                ".......#..\n" +
                "#...#....."));
        // input = test;

        Universe universe = new Universe(input);
        // universe.expand(1000000);
        universe.expand(999999);
        System.out.println(universe.totalSteps());
    }
}
