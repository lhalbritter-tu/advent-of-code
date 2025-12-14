package AOC25.DayOne;

import AOC25.DayOne.Objects.Dial;
import Utility.InputManager;

import java.util.Collections;
import java.util.List;

public class Puzzle {
    public static void main(String[] args) {
        // List<String> input = List.of(("L68\n" +
        //         "L30\n" +
        //         "R48\n" +
        //         "L5\n" +
        //         "R60\n" +
        //         "L55\n" +
        //         "L1\n" +
        //         "L99\n" +
        //         "R14\n" +
        //         "L82").split("\n"));
        List<String> input = InputManager.INSTANCE.getAdventOfCodeInput(1, 2025);
        Dial dial = new Dial(input);
        System.out.println("Zero count is " + dial.executeRotationsAndGetZeroCount());
    }
}
