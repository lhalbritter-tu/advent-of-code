package AOC22.DayFourteenChallenge.Objects;

import Utility.Position;

public class SandGenerator extends Entity {

  private boolean finished;
  private Sand currentSand;
  private int spawned = 0;


  public SandGenerator(int x, int y) {
    this.position = new Position(x, y);
    finished = false;
  }

  @Override
  public void advance(Cave cave) {
    if (currentSand == null) currentSand = new Sand(this.position.x(), this.position.y());
    if (isFinished()) return;
    if (!currentSand.isFinished()) {
      Position oldPos = currentSand.position.copy();
      currentSand.advance(cave);
      cave.updateEntity(oldPos, currentSand.position.copy());

      if (currentSand.isVoided()) {
        this.finished = true;
        // Voided sand is not of interest
        spawned--;
      }

      if (currentSand.position.equals(this.position)) {
        System.out.println(currentSand.position);
        this.finished = true;
      }
      return;
    }
    currentSand = new Sand(this.position.x(), this.position.y());
    cave.add(currentSand);
    spawned++;
  }

  public boolean isFinished() {
    return finished;
  }

  public int spawned() {
    return spawned;
  }

  @Override
  public String toString() {
    return "+";
  }
}
