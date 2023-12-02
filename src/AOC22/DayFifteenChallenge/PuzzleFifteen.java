package AOC22.DayFifteenChallenge;

import AOC22.DayFifteenChallenge.Objects.Position;
import AOC22.DayFifteenChallenge.Objects.Sensor;
import Utility.InputManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PuzzleFifteen {
  public static void main(String[] args) {
    List<String> input =
            InputManager.INSTANCE.getAdventOfCodeInput(15, 2022);
            //List.of(InputManager.INSTANCE.getFile(InputManager.DEFAULT_INPUT_PATH + "/resources/input/dayfifteen_example"));

    long minX = Long.MAX_VALUE;
    long minY = Long.MAX_VALUE;
    long maxX = Long.MIN_VALUE;
    long maxY = Long.MIN_VALUE;
    Map<Position, String> map = new HashMap<>();
    Map<Position, Sensor> sensors = new HashMap<>();
    System.out.println("Parsing input...");
    for (String line : input) {
      String[] data = line.split(":");
      String[] sensorData = data[0].split(",");
      String[] beaconData = data[1].split(",");

      long sensorY = Long.parseLong(sensorData[1].substring(3));
      long beaconY = Long.parseLong(beaconData[1].substring(3));

      long sensorX = Long.parseLong(sensorData[0].substring("Sensor at x=".length()));
      long beaconX = Long.parseLong(beaconData[0].substring(" closest beacon is at x=".length()));

      if (sensorY < minY) {
        minY = sensorY;
      }
      if (beaconY < minY) {
        minY = beaconY;
      }
      if (sensorY > maxY) {
        maxY = sensorY;
      }
      if (beaconY > maxY) {
        maxY = beaconY;
      }

      if (sensorX < minX) {
        minX = sensorX;
      }
      if (beaconX < minX) {
        minX = beaconX;
      }
      if (sensorX > maxX) {
        maxX = sensorX;
      }
      if (beaconX > maxX) {
        maxX = beaconX;
      }

      Sensor sensor = new Sensor(new Position(sensorX, sensorY), new Position(beaconX, beaconY));
      map.put(sensor.getSensorPosition(), "S");
      map.put(sensor.getBeaconPosition(), "B");

      sensors.put(sensor.getSensorPosition(), sensor);
      //System.out.println(sensor);
    }
    System.out.println("Done.");

    long maxRange = Long.MIN_VALUE;
    for (Sensor sensor : sensors.values()) {
      long range = sensor.getRange();
      if (range > maxRange) {
        maxRange = range;
      }
    }

    /**System.out.println("Creating map...");
    for (long x = minX; x <= maxX; x++) {
      System.out.println(x + "/" + maxX);
      for (long y = minY; y <= maxY; y++) {
        System.out.println(y + "/" + maxY);
        Position pos = new Position(x, y);
        if (map.containsKey(pos)) continue;
        map.put(pos, ".");
      }
    }
    System.out.println("Done.");*/

    /**System.out.println("Start flood fill...");
    //sensors.get(new Position(8, 7)).startFloodFill(map);
    int i = 1;
    int count = sensors.size();
    for (Sensor sensor : sensors.values()) {
      System.out.println("Filling Sensor " + (i++) + "/" + count + ": " + sensor);
      sensor.startFloodFill(map);
    }
    System.out.println("Done.");

    printMap(map, new Position(minX, minY), new Position(maxX, maxY));*/
    System.out.println(countUnavailable(sensors, minX - maxRange, maxX + maxRange, 2000000, map));
    System.out.println(getDistressBeaconSignal(sensors, 4000000, map));
  }

  public static void printMap(Map<Position, String> map, Position minPos, Position maxPos) {
    for (long y = minPos.y(); y <= maxPos.y(); y++) {
      for (long x = minPos.x(); x <= maxPos.x(); x++) {
        System.out.print(map.get(new Position(x, y)) == null ? "." : map.get(new Position(x, y)));
      }
      System.out.println();
    }
  }

  public static long countUnavailable(Map<Position, Sensor> sensors, long minX, long maxX, int y, Map<Position, String> map) {
    long count = 0;
    for (long x = minX; x <= maxX; x++) {
      //System.out.println("Checking " + x + "/" + maxX);
      Position toTest = new Position(x, y);
      if (map.containsKey(toTest) && map.get(toTest).equals("B")) continue;
      boolean free = true;
      for (Sensor sensor : sensors.values()) {
        if (sensor.isInRange(toTest)) {
          free = false;
          break;
        }
      }
      count += !free ? 1 : 0;
    }
    return count;
  }

  public static Position getAvailableBeacon(Map<Position, Sensor> sensors, long minX, long maxX, long y, Map<Position, String> map) {
    long minDistance = Long.MAX_VALUE;
    Sensor minSensor = null;
    for (long x = minX; x <= maxX; x++) {
      //System.out.println("Checking " + x + "/" + maxX);
      Position toTest = new Position(x, y);
      if (map.containsKey(toTest) && map.get(toTest).equals("B")) continue;
      boolean free = true;
      for (Sensor sensor : sensors.values()) {
        long distance = sensor.distance(toTest);
        if (distance <= sensor.getRange()) {
          x += (sensor.getOrientation(toTest) < 0 ? distance - (distance == 0 ? 0 : 1) : 0);
          if (distance < minDistance) {
            minDistance = distance;
            minSensor = sensor;
          }
          free = false;
          break;
        }
      }
      if (free) return toTest;
    }
    //if (minSensor != null && minSensor.getOrientation(y) < 0)
    //  return new Position(-1, minDistance);
    return new Position(-1, 0);
  }

  public static long getDistressBeaconSignal(Map<Position, Sensor> sensors, long range, Map<Position, String> map) {
    Position beacon = null;
    int i = 1;
    int size = sensors.size();
    for (Sensor sensor : sensors.values()) {
      System.out.println("Sensor " + i++ + "/" + size);
      beacon = sensor.checkEdges(sensors.values().stream().toList(), 0, range);
      if (beacon != null) {
        System.out.println(beacon);
        return beacon.getDistressSignal(4000000);
      }
    }

    return beacon.x() >= 0 ? beacon.getDistressSignal(4000000) : -1;
  }
}
