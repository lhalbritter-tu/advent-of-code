package AOC22.DayTenChallenge;

import AOC22.DayTenChallenge.Objects.CPU;
import AOC22.DayTenChallenge.Objects.CPUCommand;
import Utility.InputManager;

import java.util.ArrayList;
import java.util.List;

public class PuzzleTen {
    public static void main(String[] args) {
        List<String> commands =
                //InputManager.INSTANCE.getAdventOfCodeInput(10, 2022);
                List.of(InputManager.INSTANCE.getFile(InputManager.DEFAULT_INPUT_PATH + "/resources/input/dayten_example"));
        List<CPUCommand> comms = new ArrayList<>();
        CPU cpu = new CPU();

        for (String command : commands) {
            String[] splitCommand = command.split(" ");
            CPUCommand comm = new CPUCommand(
                    splitCommand[0].equals("noop") ? 1 : splitCommand[0].equals("addx") ? 2 : 0,
                    splitCommand.length > 1 ? Integer.parseInt(splitCommand[1]) : 0);
            cpu.put(comm);
            comms.add(comm);
        }
        long total = 0;
        cpu.advance(20);
        System.out.println(20 + ": " + cpu.signalStrength());
        total += cpu.signalStrength();
        //cpu.display();
        while (cpu.clockTime() < 240) {
            cpu.advance(20);
            System.out.println(cpu.clockTime() + ": " + cpu.signalStrength());
            total += cpu.signalStrength();
        }

        System.out.println("Total: " + total);
        cpu.display();
    }
}
