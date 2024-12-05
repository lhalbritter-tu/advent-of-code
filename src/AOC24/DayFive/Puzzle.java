package AOC24.DayFive;

import AOC24.DayFive.Objects.PrintPages;
import AOC24.DayFive.Objects.Rule;
import Utility.InputManager;

import java.util.ArrayList;
import java.util.List;

public class Puzzle {
    public static void main(String[] args) {
        List<String> test = List.of(("47|53\n" +
                "97|13\n" +
                "97|61\n" +
                "97|47\n" +
                "75|29\n" +
                "61|13\n" +
                "75|53\n" +
                "29|13\n" +
                "97|29\n" +
                "53|29\n" +
                "61|53\n" +
                "97|53\n" +
                "61|29\n" +
                "47|13\n" +
                "75|47\n" +
                "97|75\n" +
                "47|61\n" +
                "75|61\n" +
                "47|29\n" +
                "75|13\n" +
                "53|13\n" +
                "\n" +
                "75,47,61,53,29\n" +
                "97,61,53,29,13\n" +
                "75,29,13\n" +
                "75,97,47,61,53\n" +
                "61,13,29\n" +
                "97,13,75,29,47\n").split("\n"));
        List<String> input = InputManager.INSTANCE.getAdventOfCodeInput(5, 2024);
        // input = test;

        List<PrintPages> pagesList = new ArrayList<>();
        for (String line : input) {
            if (line.contains("|")) {
                // Rule
                String[] split = line.split("\\|");
                int key = Integer.parseInt(split[0]);
                int value = Integer.parseInt(split[1]);
                Rule.getRule(key).addValue(value);
            }
            if (line.contains(",")) {
                // Page
                pagesList.add(new PrintPages(line));
            }
        }

        System.out.println("Correct pages sum: " + pagesList.stream().map(PrintPages::correctPage).reduce(0, Integer::sum));
        System.out.println("Incorrect Pages sum: " + pagesList.stream().map(PrintPages::incorrectPage).reduce(0, Integer::sum));
    }
}
