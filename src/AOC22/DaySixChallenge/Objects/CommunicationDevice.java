package AOC22.DaySixChallenge.Objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommunicationDevice {
  private HashMap<Character, List<Duplicate>> characters = new HashMap<>();

  public CommunicationDevice(String sequence) {
    for (int i = 0; i < sequence.length(); i++) {
      char c = sequence.charAt(i);
      Duplicate d = new Duplicate(c, i + 1);
      if (characters.containsKey(c)) {
        characters.get(c).add(d);
      } else {
        characters.put(c, new ArrayList<>());
        characters.get(c).add(d);
      }
    }

    List<Character> toRemove = new ArrayList<>();
    for (Character c : characters.keySet()) {
      if (characters.get(c).size() <= 1) {
        toRemove.add(c);
      }
    }
    for (Character c : toRemove) {
      characters.remove(c);
    }
  }

  private DuplicateRange initDuplicateRange(int minDist) {
    DuplicateRange duplicateRange = new DuplicateRange();

    for (Character c : characters.keySet()) {
      List<Duplicate> duplicates = characters.get(c);
      for (int i = 0; i < duplicates.size() - 1; i++) {
        Duplicate d1 = duplicates.get(i);
        Duplicate d2 = duplicates.get(i + 1);
        int distance = d1.distanceTo(d2);
        if (distance < minDist) {
          duplicateRange.add(d1.position(), d2.position());
        }
      }
    }
    return duplicateRange;
  }

  public int getStartOfPacket(int minDist) {
    int minPos = Integer.MAX_VALUE;
    char minC = '*';
    DuplicateRange duplicateRange = initDuplicateRange(minDist);
    for (Character c : characters.keySet()) {
      List<Duplicate> duplicates = characters.get(c);
      for (int i = 0; i < duplicates.size() - 1; i++) {
        Duplicate d1 = duplicates.get(i);
        Duplicate d2 = duplicates.get(i + 1);
        int distance = d1.distanceTo(d2);
        if (distance >= minDist && !duplicateRange.containsAny(d1.position(), d1.position() + (minDist - 1))) {
          if (d1.position() < minPos) {
            minPos = d1.position();
            minC = c;
          }
        }
      }
    }
    return minPos == Integer.MAX_VALUE ? -1 : minPos + (minDist - 1);
  }
}
