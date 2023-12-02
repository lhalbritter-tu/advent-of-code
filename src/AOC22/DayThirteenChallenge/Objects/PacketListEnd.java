package AOC22.DayThirteenChallenge.Objects;

public class PacketListEnd extends PacketContent {
  @Override
  public int compareTo(PacketContent other) {
    System.out.println("My list ended, other's list is at: " + other);
    return other.getClass() == PacketListEnd.class ? 0 : -1;
  }

  @Override
  public String toString() {
    return "]" + (this.next == null ? "" : this.next);
  }
}
