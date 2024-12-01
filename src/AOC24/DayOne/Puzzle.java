package AOC24.DayOne;

import AOC24.DayOne.Objects.LocationList;
import Utility.InputManager;

import java.util.List;

public class Puzzle {
    public static void main(String[] args) {
        List<String> locations = InputManager.INSTANCE.getAdventOfCodeInput(1, 2024);
        List<String> test = List.of(("3   4\n" +
                        "4   3\n" +
                        "2   5\n" +
                        "1   3\n" +
                        "3   9\n" +
                        "3   3").split("\n"));
        // System.out.println(locations.toString());
        LocationList locationList = new LocationList(locations);
        locationList.sortAscending();
        System.out.println(locationList.totalDistance());
        System.out.println(locationList.totalSimilarityScore());
    }
}
