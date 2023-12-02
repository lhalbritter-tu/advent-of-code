package AOC23.DayTwo.Objects;

public class Game {

  private int id;
  private Subset[] subsets;

  public Game(String record) {
    String[] split = record.split(":");
    id = Integer.parseInt(split[0].split(" ")[1].strip());

    String[] subsetStrings = split[1].split(";");
    subsets = new Subset[subsetStrings.length];

    for (int i = 0; i < subsetStrings.length; i++) {
      String sub = subsetStrings[i].strip();
      subsets[i] = new Subset();
      Subset subset = subsets[i];

      String[] colors = sub.split(", ");
      for (String color : colors) {
        int amount = Integer.parseInt(color.split(" ")[0].strip());
        String colorName = color.split(" ")[1].strip();

        switch (colorName) {
          case "red" -> subset.addRed(amount);
          case "green" -> subset.addGreen(amount);
          case "blue" -> subset.addBlue(amount);
        }
      }
    }
  }

  public boolean isValid(int maxRed, int maxGreen, int maxBlue) {
    for (Subset subset : subsets) {
      if (subset.getRed() > maxRed)
        return false;
      if (subset.getGreen() > maxGreen)
        return false;
      if (subset.getBlue() > maxBlue)
        return false;
    }
    return true;
  }

  public Subset minSet() {
    int minRed = -1;
    int minGreen = -1;
    int minBlue = -1;
    Subset minSet = new Subset();

    for (Subset subset : subsets) {
      int red = subset.getRed();
      int green = subset.getGreen();
      int blue = subset.getBlue();

      if (red > minRed)
        minRed = red;
      if (green > minGreen)
        minGreen = green;
      if (blue > minBlue)
        minBlue = blue;
    }

    minSet.addRed(minRed == -1 ? 0 : minRed);
    minSet.addGreen(minGreen == -1 ? 0 : minGreen);
    minSet.addBlue(minBlue == -1 ? 0 : minBlue);

    return minSet;
  }

  public String toString() {
    String ret = "Game " + id + "\n";
    for (Subset subset : subsets) {
      ret += " " + subset.toString() + "\n";
    }
    return ret;
  }

  public int getId() {
    return id;
  }
}
