package AOC24.DaySeven;

import AOC24.DaySeven.Objects.Calibration;
import Utility.InputManager;

import java.util.ArrayList;
import java.util.List;

public class Puzzle {
    public static void main(String[] args) {
        List<String> input = InputManager.INSTANCE.getAdventOfCodeInput(7, 2024);
        List<String> test = List.of(("190: 10 19\n" +
                "3267: 81 40 27\n" +
                "83: 17 5\n" +
                "156: 15 6\n" +
                "7290: 6 8 6 15\n" +
                "161011: 16 10 13\n" +
                "192: 17 8 14\n" +
                "21037: 9 7 18 13\n" +
                "292: 11 6 16 20").split("\n"));
        //input = test;
        
        List<Calibration> calibrations = new ArrayList<>();
        for (String line : input) {
            calibrations.add(new Calibration(line));
        }
        // PART ONE
        long totalResult = 0;
        for (Calibration calibration : calibrations) {
            long result = calibration.testCalibration();
            totalResult += result;
            System.out.println("Calibration success? " + result);
        }
        System.out.println(totalResult);
        System.out.println();
        // PART TWO
        totalResult = 0;
        for (Calibration calibration : calibrations) {
            long result = calibration.testCalibrationTrinary();
            totalResult += result;
            System.out.println("Calibration success? " + result);
        }

        System.out.println(totalResult);
    }
}
