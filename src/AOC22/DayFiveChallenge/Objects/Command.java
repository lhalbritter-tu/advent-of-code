package AOC22.DayFiveChallenge.Objects;

public class Command {
  protected int move;
  protected int from;
  protected int to;

  public Command(int move, int from, int to) {
    this.move = move;
    this.from = from;
    this.to = to;
  }

  public void execute(Stack[] stacks) {
    if (!valid(stacks)) return;
    // System.out.println("Moving " + move + " from " + from + " to " + to);
    Stack from = stacks[this.from - 1];
    Stack to = stacks[this.to - 1];
    for (int i = 0; i < this.move; i++) {
      to.put(from.take());
    }
  }

  protected boolean valid(Stack[] stacks) {
    return !(this.from < 0 || this.from > stacks.length || this.to < 0 || this.to > stacks.length);
  }
}
