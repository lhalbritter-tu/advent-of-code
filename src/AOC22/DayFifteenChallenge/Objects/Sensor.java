package AOC22.DayFifteenChallenge.Objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Sensor {

  private long range;
  private Position position;
  private Position closestBeacon;

  public Sensor(Position position, Position closestBeacon) {
    this.position = position;
    this.closestBeacon = closestBeacon;

    this.range = this.position.distance(this.closestBeacon);
  }

  public synchronized void floodFill(Position pos, Map<Position, String> map, List<Position> visited) {
    if (visited.contains(pos)) return;
    // System.out.println("Filling " + pos);
    visited.add(pos);
    // if (!map.containsKey(pos)) return;
    if (map.containsKey(pos) && map.get(pos).equals("#")) return;
    if (this.position.distance(pos) > range) return;
    String symbol = ".";
    if (map.containsKey(pos)) symbol = map.get(pos);
    map.put(pos, (symbol.equals(".") ? "#" : symbol));

    floodFill(pos.copy(1, 0), map, visited);
    floodFill(pos.copy(-1, 0), map, visited);
    floodFill(pos.copy(0, 1), map, visited);
    floodFill(pos.copy(0, -1), map, visited);
  }

  public Position getSensorPosition() {
    return position;
  }

  public Position getBeaconPosition() {
    return closestBeacon;
  }

  @Override
  public String toString() {
    return "Sensor at " + this.position + ": closest beacon is at " + this.closestBeacon + ", manhattan distance: " + range;
  }

  public void startFloodFill(Map<Position, String> map) {
    List<Position> visited = new ArrayList<>();
    this.floodFill(this.position.copy(1, 0), map, visited);
    this.floodFill(this.position.copy(-1, 0), map, visited);
    this.floodFill(this.position.copy(0, 1), map, visited);
    this.floodFill(this.position.copy(0, -1), map, visited);
  }

  public boolean isInRange(Position test) {
    return this.position.distance(test) <= range;
  }

  public long distance(Position test) {
    return this.position.distance(test);
  }

  public int getOrientation(Position test) {
    return test.x() - this.position.x() < 0 ? -1 : 1;
  }

  public long getRange() {
    return range;
  }

  public Position checkEdges(List<Sensor> others, long min, long max) {
    System.out.println("Checking Edge 1/4");
    Position pos = checkAlongAxis(others, min, max, 1, -1, this.position.copy(-range - 1, 0));
    if (pos != null) return pos;
    System.out.println("Checking Edge 2/4");
    pos = checkAlongAxis(others, min, max, 1, 1, this.position.copy(0, -range - 1));
    if (pos != null) return pos;
    System.out.println("Checking Edge 3/4");
    pos = checkAlongAxis(others, min, max, -1, 1, this.position.copy(range + 1, 0));
    if (pos != null) return pos;
    System.out.println("Checking Edge 4/4");
    pos = checkAlongAxis(others, min, max, -1, -1, this.position.copy(0, range + 1));
    return pos;
  }

  public Position checkAlongAxis(List<Sensor> others, long min, long max, int ax, int ay, Position checkPosition) {
    while (checkPosition.distance(this.position) == range + 1) {
      boolean free = true;
      if (checkPosition.x() < min || checkPosition.x() > max || checkPosition.y() < min || checkPosition.y() > max) {
        checkPosition.add(ax, ay);
        free = false;
      }
      if (!free) continue;
      for (Sensor sensor : others) {
        if (sensor.isInRange(checkPosition)) {
          checkPosition.add(ax, ay);
          free = false;
          break;
        }
      }
      if (free)
        return checkPosition;
    }
    return null;
  }
}
