package AOC22.DayTwelveChallenge;

import AOC22.DayTwelveChallenge.Objects.Map;
import AOC22.DayTwelveChallenge.Objects.MapManager;
import Utility.InputManager;

public class PuzzleTwelve {
    public static void main(String[] args) {
        Map map = new Map(
                InputManager.INSTANCE.getAdventOfCodeInput2D(12, 2022)
                // InputManager.INSTANCE.getFile2D(InputManager.DEFAULT_INPUT_PATH + "/resources/input/daytwelve_example")
        );

        MapManager.buildMaps(map.getString());
        //MapManager.printMaps();
        System.out.println("Part 1: " + map.getBestRouteLength());
        System.out.println("Part 2: " + MapManager.getBestRoute());
    }
}
