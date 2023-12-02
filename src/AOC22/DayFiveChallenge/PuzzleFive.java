package AOC22.DayFiveChallenge;

import AOC22.DayFiveChallenge.Objects.Command;
import AOC22.DayFiveChallenge.Objects.Stack;
import AOC22.DayFiveChallenge.Static.CommandParser;
import Utility.InputManager;

import java.util.List;

public class PuzzleFive {
  public static void main(String[] args) {
    List<String> stacks = InputManager.INSTANCE.getFileUntil(
            InputManager.DEFAULT_INPUT_PATH + "/resources/input/dayfive_input", "");
    Stack[] stacked = Stack.StackHelper.buildStacks(stacks);
    // System.out.println("\n\n\n");
    List<String> commands = InputManager.INSTANCE.getFileFrom(InputManager.DEFAULT_INPUT_PATH + "/resources/input/dayfive_input", "");
    for (String comm : commands) {
      // System.out.println(comm);
      Command command = CommandParser.parse(comm, 0);
      command.execute(stacked);
    }

    for (Stack stack : stacked) {
      //System.out.println(stack);
      System.out.print(Stack.StackHelper.itemize(stack.peek()));
    }
    System.out.println("\n");
    stacked = Stack.StackHelper.buildStacks(stacks);
    for (String comm : commands) {
      Command command = CommandParser.parse(comm, 1);
      command.execute(stacked);
    }

    for (Stack stack : stacked) {
      System.out.print(Stack.StackHelper.itemize(stack.peek()));
    }
  }
}
