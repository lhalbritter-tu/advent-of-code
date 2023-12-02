package AOC22.DaySevenChallenge;

import AOC22.DaySevenChallenge.Objects.Directory;
import AOC22.DaySevenChallenge.Objects.File;
import Utility.InputManager;

import java.util.List;

public class PuzzleSeven {

  private static final long MAX_SIZE = 100000;
  private static final long TOTAL_SPACE = 70000000;
  private static final long REQUIRED = 30000000;

  public static void main(String[] args) {
    List<String> input =
            InputManager.INSTANCE.getAdventOfCodeInput(7, 2022);
            // List.of(InputManager.INSTANCE.getFile(InputManager.DEFAULT_INPUT_PATH + "/resources/input/dayseven_example"));

    InputManager.INSTANCE.writeWebToFile(InputManager.AOC_DEFAULT_URL.replace("?", "7"),
            InputManager.DEFAULT_INPUT_PATH + "/resources/input/dayseven_input");
    Directory dir = Directory.ROOT;

    for (String line : input) {
      if (line.startsWith("$")) {
        // COMMAND
        String[] command = line.substring(2).split(" ");
        if (command[0].equals("cd")) {
          if (command.length < 2) continue;
          String name = command[1];
          dir = (Directory) dir.navigateSequence(name);
        }
      } else {
        String[] file = line.split(" ");
        if (file.length < 2) continue;
        File next;
        if (file[0].equals("dir")) {
          next = new Directory(file[1]);
        } else {
          next = new File(file[1], Long.parseLong(file[0]));
        }
        dir.add(next);
        next.setParent(dir);
      }
    }

    long total = 0;
    for (Directory directory : Directory.ROOT.directories()) {
      long size = directory.getSize();
      if (size <= MAX_SIZE) total += size;
    }

    System.out.println(total);

    long free = TOTAL_SPACE - Directory.ROOT.getSize();
    long needed = REQUIRED - free;

    long min_dir = Long.MAX_VALUE;
    for (Directory directory : Directory.ROOT.directories()) {
      long size = directory.getSize();
      if (size >= needed) {
        if (size < min_dir) {
          min_dir = size;
        }
      }
    }

    System.out.println("DELETE " + min_dir);
  }
}
