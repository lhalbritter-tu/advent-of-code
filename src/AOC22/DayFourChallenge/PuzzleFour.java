package AOC22.DayFourChallenge;

import AOC22.DayFourChallenge.Objects.Job;
import Utility.InputManager;

public class PuzzleFour {

    public static void main(String[] args) {
        String[] lines = InputManager.INSTANCE.getFile(
                InputManager.DEFAULT_INPUT_PATH + "/resources/input/dayfour_input");
        int total = 0;
        for (String line : lines) {
            String[] splitted = line.split(",");
            String[] jobRange1 = splitted[0].split("-");
            String[] jobRange2 = splitted[1].split("-");
            Job job1 = new Job(jobRange1);
            Job job2 = new Job(jobRange2);
            total += job1.contains(job2) || job2.contains(job1) ? 1 : 0;
        }

        System.out.println(total);

        total = 0;
        for (String line : lines) {
            String[] splitted = line.split(",");
            String[] jobRange1 = splitted[0].split("-");
            String[] jobRange2 = splitted[1].split("-");
            Job job1 = new Job(jobRange1);
            Job job2 = new Job(jobRange2);
            total += job1.overlaps(job2) || job2.overlaps(job1) ? 1 : 0;
        }

        System.out.println(total);
    }
}
