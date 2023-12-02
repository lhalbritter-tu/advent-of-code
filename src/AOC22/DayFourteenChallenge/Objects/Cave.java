package AOC22.DayFourteenChallenge.Objects;

import Utility.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cave {

  private Map<Position, Entity> entities;

  private SandGenerator sandGenerator;

  private Position voidStart, voidEnd;

  private boolean withFloor;
  private int floor;

  public Cave(List<String> input) {
    entities = new HashMap<>();
    int xMin = Integer.MAX_VALUE, yMin = Integer.MAX_VALUE;
    int xMax = Integer.MIN_VALUE, yMax = Integer.MIN_VALUE;
    for (String line : input) {
      String[] positions = line.split("-");
      for (int i = 0; i < positions.length - 1; i++) {
        String position = positions[i];
        String end = positions[i + 1];
        position = position.replace(">", "").strip();
        end = end.replace(">", "").strip();
        String[] coordinates = position.split(",");
        int x = Integer.parseInt(coordinates[0].strip());
        int y = Integer.parseInt(coordinates[1].strip());

        coordinates = end.split(",");
        int x1 = Integer.parseInt(coordinates[0].strip());
        int y1 = Integer.parseInt(coordinates[1].strip());

        Position from = new Position(x, y);
        Position to = new Position(x1, y1);

        rockTrail(from, to);

        xMin = Math.min(xMin, Math.min(from.x(), to.x()));
        xMax = Math.max(xMax, Math.max(from.x(), to.x()));
        yMin = Math.min(yMin, Math.min(from.y(), to.y()));
        yMax = Math.max(yMax, Math.max(from.y(), to.y()));
      }
    }

    voidStart = new Position(xMin, yMin);
    voidEnd = new Position(xMax, yMax);

    this.floor = yMax + 2;

    System.out.println("Void starts at " + voidStart + " and ends at " + voidEnd);

    sandGenerator = new SandGenerator(500, 0);
    // entities.put(sandGenerator.position.copy(), sandGenerator);
  }

  public boolean isFree(Position position) {
    if (withFloor && position.y() == floor) return false;
    return !entities.containsKey(position);
  }

  public boolean isVoid(Position position) {
    if (withFloor) return false;
    return position.x() - voidStart.x() <= 0 || position.x() - voidEnd.x() >= 0 || position.y() > voidEnd.y();
  }

  public int simulate() {
    while (!sandGenerator.isFinished()) {
      sandGenerator.advance(this);
    }

    return sandGenerator.spawned();
  }

  public int simulateWithFloor() {
    this.withFloor = true;
    return simulate();
  }

  public void add(Entity entity) {
    entities.put(entity.position, entity);
  }

  public void updateEntity(Position oldPosition, Position newPosition) {
    if (!entities.containsKey(oldPosition)) return;

    Entity removed = entities.remove(oldPosition);
    entities.put(newPosition, removed);
  }

  public void rockTrail(Position from, Position to) {
    int yMax = Math.max(from.y(), to.y());
    int xMax = Math.max(from.x(), to.x());
    int yMin = Math.min(from.y(), to.y());
    int xMin = Math.min(from.x(), to.x());

    for (int x = xMin; x <= xMax; x++) {
      for (int y = yMin; y <= yMax; y++) {
        Rock rock = new Rock(x, y);
        entities.put(rock.position.copy(), rock);
      }
    }
  }

  @Override
  public String toString() {
    String result = "";
    int yEnd = withFloor ? floor : voidEnd.y();
    for (int y = 0; y <= yEnd; y++) {
      for (int x = voidStart.x(); x < voidEnd.x(); x++) {
        result += entities.containsKey(new Position(x, y)) ? entities.get(new Position(x, y)) : ".";
      }
      result += "\n";
    }
    return result;
  }
}
