package AOC22.DayThirteenChallenge;

import AOC22.DayThirteenChallenge.Objects.Packet;
import AOC22.DayThirteenChallenge.Objects.PacketContent;
import Utility.InputManager;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class PuzzleThirteen {
  public static void main(String[] args) {
    List<String> input =
            InputManager.INSTANCE.getAdventOfCodeInput(13, 2022);
            //List.of(InputManager.INSTANCE.getFile(InputManager.DEFAULT_INPUT_PATH + "/resources/input/daythirteen_example"));
    SortedSet<PacketContent> sortedPackets = new TreeSet<>();

    PacketContent dividerOne = new Packet("[[2]]").root();
    PacketContent dividerTwo = new Packet("[[6]]").root();

    int total = 0;
    int cur_pair = 1;
    for (int i = 0; i < input.size(); i += 3) {
      String packetOne = input.get(i);
      String packetTwo = input.get(i + 1);

      Packet one = new Packet(packetOne);
      Packet two = new Packet(packetTwo);

      sortedPackets.add(one.root());
      sortedPackets.add(two.root());

      one.printList();
      two.printList();
      boolean comp = one.compare(two);
      System.out.println((comp ? "RIGHT" : "WRONG") + " ORDER");
      System.out.println();

      if (comp) total += cur_pair;
      cur_pair++;
    }

    System.out.println(total); // 4643

    sortedPackets.add(dividerOne);
    sortedPackets.add(dividerTwo);
    for (PacketContent content : sortedPackets) {
      System.out.println(content);
    }

    Object[] packets = sortedPackets.toArray();
    int result = 1;
    for (int i = 0; i < packets.length; i++) {
      if (packets[i] == dividerOne) {
        result *= (i + 1);
      }
      if (packets[i] == dividerTwo) {
        result *= (i + 1);
      }
    }
    System.out.println(result); // 21614
  }
}
