package AOC22.DayTenChallenge.Objects;

public class Clock {
    private long cycles;

    public Clock() {
        this.cycles = 0;
    }

    public void advance(CPU cpu) {
        this.cycles += 1;
        System.out.println("Start cycle " + this.cycles);
        cpu.executeNext();
    }

    public long time() {
        return cycles;
    }
}
