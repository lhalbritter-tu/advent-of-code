package AOC22.DayThirteenChallenge.Objects;

public class Packet {
  private PacketContent root;

  public Packet(String line) {
    PacketList currentInner = null;
    PacketList currentOuter = null;

    String[] split = line.split(",");

    for (String element : split) {
      while (element.startsWith("[")) {
        if (root == null) {
          root = new PacketList();
          currentInner = (PacketList) root;
        }
        else {
          PacketList next = new PacketList();
          currentInner.add(next);
          PacketList temp = currentOuter;
          currentOuter = currentInner;
          if (temp != null) currentOuter.setPrevious(temp);
          currentInner = next;
        }
        element = element.substring(1);
      }

      String toAdd = element.replaceAll("]", "");
      if (!toAdd.isEmpty()) {
        if (root == null) {
          root = new PacketInteger(Integer.parseInt(toAdd));
        }
        else {
          if (currentInner == null)
            root.add(new PacketInteger(Integer.parseInt(toAdd)));
          else
            currentInner.add(new PacketInteger(Integer.parseInt(toAdd)));
        }
      }

      while (element.endsWith("]")) {
        currentInner.add(new PacketListEnd());
        if (currentOuter == null) {
          currentInner = null;
        }
        else {
          currentInner = currentOuter;
          currentOuter = (PacketList) currentOuter.getPrevious();
        }
        element = element.substring(0, element.length() - 1);
      }
    }
  }

  public boolean compare(Packet other) {
    return this.root.compareTo(other.root) < 0;
  }

  public void printList() {
    System.out.println(root.toString());
  }

  public PacketContent root() {
    return root;
  }
}
