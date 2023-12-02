package AOC22.DayThreeChallenge;

import AOC22.DayThreeChallenge.Objects.Compartment;
import AOC22.DayThreeChallenge.Objects.Items;
import AOC22.DayThreeChallenge.Objects.Rucksack;
import Utility.InputManager;

public class PuzzleThree {
    public static void main(String[] args) {
        String[] lines = InputManager.INSTANCE.getFile(
                InputManager.DEFAULT_INPUT_PATH + "/resources/input/daythree_input");
        int total = 0;
        for (String line : lines) {
            Rucksack r = new Rucksack(line);
            total += r.getSameItemPriority();
        }
        System.out.println(total);

        total = 0;
        for (int i = 0; i < lines.length; i += 3) {
            Compartment elf1 = new Compartment(lines[i]);
            Compartment elf2 = new Compartment(lines[i + 1]);
            Compartment elf3 = new Compartment(lines[i + 2]);

            total += Items.getPriority(elf1.equal(elf2, elf3));
        }

        System.out.println(total);
    }
}
