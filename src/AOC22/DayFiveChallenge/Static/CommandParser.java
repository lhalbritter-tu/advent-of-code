package AOC22.DayFiveChallenge.Static;

import AOC22.DayFiveChallenge.Objects.Command;
import AOC22.DayFiveChallenge.Objects.MultipleCommand;

public class CommandParser {
  public static Command parse(String text, int type) {
    String[] comm = text.split(" ");
    int move = -1;
    int from = -1;
    int to = -1;
    for (int i = 0; i < comm.length; i += 2) {
      String c = comm[i];
      switch (c) {
        case "move":
          move = Integer.parseInt(comm[i + 1]);
          break;
        case "from":
          from = Integer.parseInt(comm[i + 1]);
          break;
        case "to":
          to = Integer.parseInt(comm[i + 1]);
          break;
        default:
          break;
      }
    }
    return type == 0 ? new Command(move, from, to) : new MultipleCommand(move, from, to);
  }
}
