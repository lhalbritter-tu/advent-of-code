package AOC22.DaySixteenChallenge;

import AOC22.DaySixteenChallenge.Objects.Valve;
import AOC22.DaySixteenChallenge.Objects.ValveMap;
import Utility.InputManager;

import java.util.List;
import java.util.Optional;

public class PuzzleSixteen {

    public static void main(String[] args) {
        String[] input =
            // InputManager.INSTANCE.getAdventOfCodeInput(16, 2022);
            InputManager.INSTANCE.getFile(InputManager.DEFAULT_INPUT_PATH + "/resources/input/daysixteen_example");
        Valve[] valves = new Valve[input.length];
        for (int i = 0; i < input.length; i++) {
            valves[i] = new Valve(input[i]);
        }

        int remaining_minutes = 30;
        ValveMap.init(valves, valves[0].getName());

        while (remaining_minutes > 0) {
            ValveMap.Map.flow();
            List<Valve> options = ValveMap.Map.getOptions();
            if (!ValveMap.Map.getCurrent().isOpen()) {
                options.add(ValveMap.Map.getCurrent());
            }
            Optional<Valve> choice = options.stream().max((Valve::compareTo));
            Valve valve = null;
            if (choice.isPresent()) {
                valve = choice.get();
            } else {
                remaining_minutes -= 1;
                continue;
            }

            if (valve.equals(ValveMap.Map.getCurrent())) {
                System.out.println("You open Valve " + valve.getName() + ", remaining: " + remaining_minutes);
                remaining_minutes -= ValveMap.Map.open(valve.getName());
            } else {
                System.out.println("You move to Valve " + valve.getName() + ", remaining: " + remaining_minutes);
                remaining_minutes -= ValveMap.Map.proceed(valve.getName());
            }
        }

        System.out.println(ValveMap.Map);
    }

}
