package AOC22.DayThirteenChallenge.Objects;

public class PacketList extends PacketContent {

  public PacketList() {
    this.value = -1;
  }

  @Override
  public int compareTo(PacketContent other) {
    PacketList otherList;
    if (other.getClass() != PacketList.class) {
      if (other.getClass() == PacketListEnd.class) return 1;
      System.out.println("Mixed Types, converting other to list");
      otherList = new PacketList();
      otherList.add(other);
    } else {
      otherList = (PacketList) other;
    }

    PacketContent myNext = this.next;
    PacketContent otherNext = otherList.next;
    while (myNext != null) {
      if (otherNext == null) return 1;
      PacketContent tempMyNext = null;
      PacketContent tempOtherNext = null;
      if (myNext.getClass() != otherNext.getClass()) {
        if (myNext.getClass() == PacketInteger.class) {
          if (otherNext.getClass() == PacketList.class) {
            System.out.println("Mixed Types, converting this to list");
            PacketInteger val = new PacketInteger(myNext.value);
            tempMyNext = myNext.next;
            myNext = new PacketList();
            myNext.add(val);
          } else if (otherNext.getClass() == PacketListEnd.class) {
            System.out.println("Other List ran out of elements");
            return 1;
          }
        } else if (myNext.getClass() == PacketList.class) {
          if (otherNext.getClass() == PacketInteger.class) {
            System.out.println("Mixed Types, converting other to list");
            PacketContent val = new PacketInteger(otherNext.value);
            tempOtherNext = otherNext.next;
            otherNext = new PacketList();
            otherNext.add(val);
          } else if (otherNext.getClass() == PacketListEnd.class) {
            System.out.println("Other List ran out of elements");
            return 1;
          }
        }
      }
      int comp = myNext.compareTo(otherNext);
      if (comp < 0) return -1;
      if (comp > 0) return 1;
      myNext = myNext.next == null ? tempMyNext : myNext.next;
      otherNext = otherNext.next == null ? tempOtherNext : otherNext.next;
    }

    System.out.println("I ran out of elements, other: " + otherNext);
    return otherNext == null ? 0 : -1;
  }

  @Override
  public String toString() {
    return (this.previous == null ? "" : (this.previous.getClass() == PacketList.class ? "" : ",")) + "[" + (this.next == null ? "" : this.next);
  }
}
