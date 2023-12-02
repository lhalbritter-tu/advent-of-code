package AOC22.DayFiveChallenge.Objects;

public class MultipleCommand extends Command {

  public MultipleCommand(int move, int from, int to) {
    super(move, from, to);
  }

  @Override
  public void execute(Stack[] stacks) {
    if (!valid(stacks)) return;
    Stack from = stacks[this.from - 1];
    Stack to = stacks[this.to - 1];
    to.put(from.take(move));
  }
}
