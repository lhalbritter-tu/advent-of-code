package AOC22.DayThirteenChallenge.Objects;

public class PacketInteger extends PacketContent {

  public PacketInteger(int value) {
    this.value = value;
  }
  @Override
  public int compareTo(PacketContent other) {
    System.out.println("  - " + this.value + " vs " + other.value);
    if (other.getClass() == PacketListEnd.class) return 1;
    return this.value - other.value;
  }

  @Override
  public String toString() {
    return (this.previous == null ? "" : (this.previous.getClass() == PacketList.class ? "" : ",")) + this.value + (this.next == null ? "" : this.next);
  }
}
