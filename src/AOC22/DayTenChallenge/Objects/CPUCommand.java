package AOC22.DayTenChallenge.Objects;

public class CPUCommand {
    private int cycleLength;
    private int value;

    public CPUCommand(int cycleLength, int value) {
        this.cycleLength = cycleLength;
        this.value = value;
    }

    public void execute(CPU cpu) {
        if (cycleLength <= 0) {
            // System.out.println("Executing command, adds: " + this.value);
            cpu.addRegister(this.value);
            cpu.pollNext();
        }
    }

    public void advance() {
        cycleLength--;
        System.out.println("Executing command add " + value + " at " + cycleLength);
    }
}
