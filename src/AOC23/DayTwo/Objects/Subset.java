package AOC23.DayTwo.Objects;

public class Subset {
  private int red, green, blue;

  public void addRed(int r) {
    red += r;
  }

  public void addGreen(int g) {
    green += g;
  }

  public void addBlue(int b) {
    blue += b;
  }

  public int getRed() {
    return red;
  }

  public int getGreen() {
    return green;
  }

  public int getBlue() {
    return blue;
  }

  public int power() {
    return red * green * blue;
  }

  public String toString() {
    return red + " red, " + green + " green, " + blue + " blue";
  }
}
