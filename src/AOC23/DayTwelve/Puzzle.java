package AOC23.DayTwelve;

import AOC23.DayTwelve.Objects.Report;
import Utility.InputManager;

import java.util.List;

public class Puzzle {
    public static void main(String[] args) {
        List<String> input = InputManager.INSTANCE.getAdventOfCodeInput(12, 2023);
        List<String> test = List.of(("???.### 1,1,3\n" +
                ".??..??...?##. 1,1,3\n" +
                "?#?#?#?#?#?#?#? 1,3,1,6\n" +
                "????.#...#... 4,1,1\n" +
                "????.######..#####. 1,6,5\n" +
                "?###???????? 3,2,1").split("\n"));

        input = test;

        for (String line : input) {
            Report report = new Report(line);
            System.out.println(report + " " + report.isValid());
            System.out.println(report.place());
        }
    }
}
