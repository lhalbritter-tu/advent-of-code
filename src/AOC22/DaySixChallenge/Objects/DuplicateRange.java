package AOC22.DaySixChallenge.Objects;

import java.util.ArrayList;
import java.util.List;

public class DuplicateRange {
  private List<Integer[]> range;

  public DuplicateRange() {
    range = new ArrayList<>();
  }

  public void add(Integer pos1, Integer pos2) {
    range.add(new Integer[] { pos1, pos2 });
  }

  public boolean containsAny(int start, int end) {
    for (Integer[] positions : range) {
      if (positions[0] >= start && positions[1] <= end) return true;
    }
    return false;
  }
}
