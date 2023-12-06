package AOC23.DaySix;

import AOC23.DaySix.Objects.BoatRace;
import Utility.InputManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzle {
    public static void main(String[] args) {
        List<String> input = InputManager.INSTANCE.getAdventOfCodeInput(6, 2023);
        List<String> test = List.of(("Time:      7  15   30\n" +
                "Distance:  9  40  200").split("\n"));
        // input = test;

        List<String> time = new ArrayList<>(Arrays.stream(input.get(0).split(":")[1].strip().split(" ")).toList());
        List<String> distance = new ArrayList<>(Arrays.stream(input.get(1).split(":")[1].strip().split(" ")).toList());

        time.removeAll(Arrays.asList("", null));
        distance.removeAll(Arrays.asList("", null));

        List<BoatRace> races = new ArrayList<>();
        for (int i = 0; i < time.size(); i++) {
            String t = time.get(i);
            String d = distance.get(i);

            races.add(new BoatRace(Long.parseLong(t), Long.parseLong(d)));
        }

        long marginError = 1;
        for (BoatRace race : races) {
            long wins = determineWins(race);
            marginError *= wins;
        }

        System.out.println("I have a margin of error of " + marginError);

        // --- PART TWO --- \\
        BoatRace race = new BoatRace(Long.parseLong(String.join("", time)), Long.parseLong(String.join("", distance)));
        System.out.println(race);

        marginError = 1;
        long wins = determineWins(race);
        marginError *= wins;

        System.out.println("I have a margin of error of " + marginError);
    }

    public static long determineWins(BoatRace race) {
        race.predict();
        long lastDistance = race.reachedDistance();
        while (race.isFastEnough()) {
            race.decelerate();
        }

        while (!race.isFastEnough()) {
            race.accelerate();
            if (lastDistance > race.reachedDistance()) {
                race.setAcceleration(0);
            }
            lastDistance = race.reachedDistance();
        }

        long wins = 0;
        while (race.isFastEnough()) {
            race.accelerate();
            wins++;
        }
        System.out.println(race + " has " + wins + " wins.");
        return wins;
    }
}
