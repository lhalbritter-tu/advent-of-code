package AOC24.DayThree;

import AOC24.DayThree.Objects.Memory;
import Utility.InputManager;

public class Puzzle {
    public static void main(String[] args) {
        String test = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";
        String input = InputManager.INSTANCE.getAdventOfCodeInput1D(3, 2024);
        // input = test;
        Memory memory = new Memory(input);
        System.out.println("Multiplication sum: " + memory.multiplicationSum());

        Memory level2 = new Memory(input, "do\\(\\)", "don't\\(\\)");
        System.out.println("Level 2 sum: " + level2.multiplicationSum());
    }
}
