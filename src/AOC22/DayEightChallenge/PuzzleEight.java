package AOC22.DayEightChallenge;

import AOC22.DayEightChallenge.Objects.Forest;
import Utility.InputManager;

import java.util.List;

public class PuzzleEight {
    public static void main(String[] args) {
        List<List<String>> input =
                InputManager.INSTANCE.getAdventOfCodeInput2D(8, 2022);
                //InputManager.INSTANCE.getFile2D(InputManager.DEFAULT_INPUT_PATH + "\\resources\\input\\dayeight_example");
        Forest forest = Forest.buildForest(input);
        System.out.println(forest.getVisible());
        System.out.println(forest.getMaxScenicScore());
    }
}
