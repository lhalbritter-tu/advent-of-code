package AOC24.DayFour;

import AOC24.DayFour.Objects.WordSearch;
import Utility.InputManager;

import java.util.List;

public class Puzzle {
    public static void main(String[] args) {
        List<List<String>> input = InputManager.INSTANCE.getAdventOfCodeInput2D(4, 2024);
        List<List<String>> test = InputManager.INSTANCE.get2D("MMMSXXMASM\n" +
                "MSAMXMSMSA\n" +
                "AMXSXMAAMM\n" +
                "MSAMASMSMX\n" +
                "XMASAMXAMM\n" +
                "XXAMMXXAMA\n" +
                "SMSMSASXSS\n" +
                "SAXAMASAAA\n" +
                "MAMMMXMMMM\n" +
                "MXMXAXMASX");
        // input = test;

        WordSearch wordSearch = new WordSearch(input);
        System.out.println("XMAS: " + wordSearch.search("XMAS"));

        // part two
        System.out.println("X-Mas: " + wordSearch.searchCrossMas());
    }
}
