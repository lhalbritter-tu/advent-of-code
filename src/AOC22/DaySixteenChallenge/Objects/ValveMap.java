package AOC22.DaySixteenChallenge.Objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValveMap {

    private List<Valve> valves;

    private Valve current = null;
    private Valve last = null;

    private float releasedFlow;

    public static ValveMap Map;

    public static void init(Valve[] valves, String start) {
        if (Map != null)
            return;
        Map = new ValveMap(valves);
        Map.current = Map.get(start);
    }

    private ValveMap(Valve[] valves) {
        this.valves = Arrays.stream(valves).toList();
    }

    public int open(String name) {
        Valve valve = get(name);
        if (valve == null)
            return 0;
        if (valve.isOpen())
            return 0;
        valve.open();
        return 1;
    }

    public int proceed(String name) {
        Valve valve = get(name);
        if (Arrays.toString(current.getNeighbours()).contains(name)) {
            this.last = this.current;
            this.current = valve;
            return 1;
        }
        return 0;
    }

    public List<Valve> getOptions() {
        List<Valve> ret = new ArrayList<>();
        for (String neighbour :
                current.getNeighbours()) {
            Valve n = get(neighbour);
            if (n == null) continue;
            if (!n.equals(last))
                ret.add(get(neighbour));
        }
        return ret;
    }

    public Valve get(String name) {
        for (Valve valve : valves) {
            if (valve.getName().equals(name))
                return valve;
        }
        return null;
    }

    public Valve getCurrent() {
        return current;
    }

    public void flow() {
        for (Valve valve : valves) {
            releasedFlow += valve.release();
        }
    }

    public String toString() {
        String ret = "";
        for (Valve valve : valves) {
            ret += valve.toString() + "\n";
        }

        ret += "\nReleased Flow: " + releasedFlow;
        return ret;
    }

}
