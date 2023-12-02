package AOC22.DayThirteenChallenge.Objects;

public abstract class PacketContent implements Comparable<PacketContent> {
  protected PacketContent next;
  protected PacketContent previous;
  protected int value;

  public void add(PacketContent content) {
    if (next == null) {
      this.next = content;
      this.next.previous = this;
    }
    else this.next.add(content);
  }

  public PacketContent get(int i) {
    PacketContent content = this;
    for (int j = 0; j < i; j++) {
      if (content == null) return null;
      content = content.getNext();
    }

    return content;
  }

  public PacketContent getNext() {
    return next;
  }

  public PacketContent getPrevious() {
    return previous;
  }

  public int value() {
    return value;
  }

  public abstract int compareTo(PacketContent other);

  public void setPrevious(PacketList temp) {
    this.previous = temp;
  }
}
