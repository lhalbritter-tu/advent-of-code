package AOC22.DayOneChallenge;

import AOC22.DayOneChallenge.Objects.Elf;
import AOC22.DayOneChallenge.Objects.ElfTree;
import Utility.InputManager;

import java.util.List;

public class PuzzleOne {
    public static void main(String[] args) {
        List<List<String>> input = InputManager.INSTANCE.getFileSeperated(
                InputManager.DEFAULT_INPUT_PATH + "/resources/input/dayone_input");
        ElfTree root = new ElfTree();

        int i = 1;
        for (List<String> inventory : input) {
            Elf elf = new Elf();
            for (String ration : inventory) {
                elf.addFood(Long.parseLong(ration.strip()));
            }
            root.add(elf);
        }

        Elf strongest = root.max();
        System.out.println("Strong Elf: " + strongest.getTotal());
        System.out.printf("Top 3: " + root.top(3));
    }
}
