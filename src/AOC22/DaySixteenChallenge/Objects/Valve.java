package AOC22.DaySixteenChallenge.Objects;

import java.util.Arrays;
import java.util.Objects;

public class Valve implements Comparable<Valve> {

    private String name;
    private float flowRate;
    private String[] neighbours;

    private boolean open;

    public Valve(String line) {
        String[] split = line.split(";");
        name = split[0].split(" ")[1].strip();
        flowRate = Float.parseFloat(split[0].split("=")[1].strip());
        neighbours = split[1].substring(" tunnels lead to valves".length()).split(", ");
        neighbours[0] = neighbours[0].strip();
        open = false;
    }

    public int open() {
        this.open = true;
        return 1;
    }

    public int move(int next) {
        return -1;
    }

    @Override
    public String toString() {
        return "Valve{" +
                "name='" + name + '\'' +
                ", flowRate=" + flowRate +
                ", neighbours=" + Arrays.toString(neighbours) +
                ", open=" + open +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getFlowRate() {
        return flowRate;
    }

    public void setFlowRate(float flowRate) {
        this.flowRate = flowRate;
    }

    public String[] getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(String[] neighbours) {
        this.neighbours = neighbours;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Valve valve = (Valve) o;
        return Float.compare(valve.flowRate, flowRate) == 0 && open == valve.open && Objects.equals(name, valve.name) && Arrays.equals(neighbours, valve.neighbours);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, flowRate, open);
        result = 31 * result + Arrays.hashCode(neighbours);
        return result;
    }

    @Override
    public int compareTo(Valve o) {
        return (int) (this.flowRate - o.flowRate);
    }

    public float release() {
        return isOpen() ? this.flowRate : 0;
    }
}
