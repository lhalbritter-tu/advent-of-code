package AOC22.DayFiveChallenge.Objects;

import java.util.List;

public class Stack {
  private String top;
  private Stack next;

  public Stack() { }

  public boolean put(String element) {
    String temp = top;
    this.top = element;
    if (temp == null) return true;
    if (next == null) {
      next = new Stack();
    }
    return next.put(temp);
  }
  
  public boolean put(String[] elements) {
    for (int i = elements.length - 1; i >= 0; i--) {
      this.put(elements[i]);
    }
    return true;
  }

  public String take() {
    String temp = top;
    this.top = (next == null ? null : next.take());
    return temp;
  }

  public String[] take(int count) {
    String[] result = new String[count];
    for (int i = 0; i < count; i++) {
      result[i] = this.top;
      this.top = (next == null ? null : next.take());
    }
    return result;
  }

  public String peek() {
    return this.top;
  }

  public String peek(int i) {
    if (i <= 0) return this.top;
    return next == null ? "" : next.peek(i - 1);
  }

  @Override
  public String toString() {
    return (this.top == null ? "" : this.top) + (next == null ? "" : "\n" + next.toString());
  }

  public static class StackHelper {
    public static Stack[] buildStacks(List<String> input) {
      String[] stackNums = input.get(input.size() - 1).split(" ");
      int numberStacks = Integer.parseInt(stackNums[stackNums.length - 1].strip());
      if (numberStacks < stackNums.length) {
        String[] copy = stackNums.clone();
        stackNums = new String[numberStacks];
        int j = 0;
        for (int i = 0; i < copy.length; i++) {
          if (copy[i].strip().isEmpty()) continue;
          stackNums[j++] = copy[i];
        }
      }

      Stack[] stacks = new Stack[numberStacks];
      for (int j = input.size() - 2; j >= 0; j--) {
        String line = input.get(j);
        String[] crates = line.split(" ");
        int cur_stack = 0;
        int skip = 0;
        for (int i = 0; i < crates.length; i++) {
          if (cur_stack >= numberStacks) break;
          String crate = crates[i];
          if (crate.strip().isEmpty()) {
            skip++;
            if (skip >= 4) {
              cur_stack++;
              skip = 0;
            }
            continue;
          }
          if (stacks[cur_stack] == null) stacks[cur_stack] = new Stack();
          stacks[cur_stack++].put(crate.strip());
        }
      }
      return stacks;
    }

    public static String itemize(String item) {
      if (item == null) return " ";
      return item.substring(1, 2);
    }
  }
}
