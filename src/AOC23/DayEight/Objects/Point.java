package AOC23.DayEight.Objects;

import java.util.*;

public class Point {

  public static final Map<String, Point> MAP = new HashMap<>();

  private String name;

  private Point left, right;

  public Point(String name) {
    this.name = name;
  }

  public Point(String[] instruction) {
    name = instruction[0];
    String[] neighbours = instruction[1].split(",");

    setNeighbours(neighbours);
  }

  public void setNeighbours(String[] neighbours) {
    left = MAP.getOrDefault(
        neighbours[0].substring(1),
        null
    );
    if (left == null) {
      left = new Point(neighbours[0].substring(1));
      MAP.put(neighbours[0].substring(1), left);
    }

    right = MAP.getOrDefault(
        neighbours[1].replace(')', ' ').strip(),
        null
    );
    if (right == null) {
      right = new Point(neighbours[1].replace(')', ' ').strip());
      MAP.put(neighbours[1].replace(')', ' ').strip(), right);
    }
  }

  public Point left() {
    return left;
  }

  public Point right() {
    return right;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Point{" +
        "name='" + name + '\'' +
        ", left=" + left.name +
        ", right=" + right.name +
        '}';
  }
}
