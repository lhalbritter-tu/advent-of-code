package AOC24.DaySix;

import AOC24.DaySix.Objects.PatrolMap;
import Utility.InputManager;

import java.util.List;

public class Puzzle {
    public static void main(String[] args) {
        List<List<String>> input = InputManager.INSTANCE.getAdventOfCodeInput2D(6, 2024);
        List<List<String>> test = InputManager.INSTANCE.get2D("....#.....\n" +
                ".........#\n" +
                "..........\n" +
                "..#.......\n" +
                ".......#..\n" +
                "..........\n" +
                ".#..^.....\n" +
                "........#.\n" +
                "#.........\n" +
                "......#...");
        // input = test;

        PatrolMap patrol = new PatrolMap(input);
        System.out.println(patrol.patrol());
        patrol.printPatrol();
        
        patrol.resetGuard();

        System.out.println(patrol.loopPositions());
    }
}
