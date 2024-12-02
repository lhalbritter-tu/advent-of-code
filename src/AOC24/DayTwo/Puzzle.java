package AOC24.DayTwo;

import AOC24.DayTwo.Objects.Report;
import Utility.InputManager;

import java.util.ArrayList;
import java.util.List;

public class Puzzle {
    public static void main(String[] args) {
        List<String> inp = InputManager.INSTANCE.getAdventOfCodeInput(2, 2024);
        List<String> test = List.of(("7 6 4 2 1\n" +
                "1 2 7 8 9\n" +
                "9 7 6 2 1\n" +
                "1 3 2 4 5\n" +
                "8 6 4 4 1\n" +
                "1 3 6 7 9").split("\n"));
        // inp = test;
        List<Report> reports = new ArrayList<>();
        for (String report : inp) {
            reports.add(new Report(report));
        }

        // Level 1
        int secureReports = 0;
        for (Report report : reports) {
            if (report.isSecure()) {
                System.out.println(report);
                secureReports++;
            }
        }
        System.out.println("There are " + secureReports + " secure reports.");

        // Level 2
        int dampenedReports = 0;
        for (Report report : reports) {
            if (report.isSecureDampened(report.getLevels(), 0)) {
                System.out.println(report + " is a success");
                dampenedReports++;
            } else {
                System.out.println(report + " is not a success");
            }
        }
        System.out.println("There are " + dampenedReports + " dampened secure reports.");
    }
}
