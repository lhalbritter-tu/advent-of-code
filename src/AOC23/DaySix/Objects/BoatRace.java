package AOC23.DaySix.Objects;

public class BoatRace {
    private long time, timeRemaining;
    private long distance;
    private long acceleration;

    public BoatRace(long time, long distance) {
        this.time = time;
        this.timeRemaining = time;
        this.distance = distance;
        this.acceleration = 0;
    }

    public void accelerate() {
        this.timeRemaining--;
        this.acceleration++;
    }

    public void decelerate() {
        this.timeRemaining++;
        this.acceleration--;
    }

    public long reachedDistance() {
        return this.acceleration * this.timeRemaining;
    }

    public boolean isFastEnough() {
        return this.reachedDistance() > this.distance;
    }

    public void setAcceleration(long acceleration) {
        if (acceleration > time)
            return;
        this.acceleration = acceleration;
        this.timeRemaining = this.time - this.acceleration;
    }

    public void predict() {
        this.setAcceleration((long) Math.ceil((double) this.distance / this.time));
    }

    @Override
    public String toString() {
        return "BoatRace{" +
                "time=" + time +
                ", timeRemaining=" + timeRemaining +
                ", distance=" + distance +
                ", acceleration=" + acceleration +
                ", reached=" + reachedDistance() +
                '}';
    }
}
