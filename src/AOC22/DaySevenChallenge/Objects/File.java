package AOC22.DaySevenChallenge.Objects;

public class File {
  private long size;
  private String name;

  protected Directory parent;

  public File() { }

  public File(String name, long size) {
    this.name = name;
    this.size = size;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setParent(Directory parent) {
    this.parent = parent;
  }

  public Directory getParent() {
    return this.parent;
  }

  @Override
  public String toString() {
    return "- " + this.getName();
  }
}
