package AOC22.DayTenChallenge.Objects;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CPU {
    private Queue<CPUCommand> commands;

    CPUCommand currentCommand;

    private Clock clock;
    private CRT crt;

    private long register;

    public CPU() {
        this.commands = new LinkedList<>();
        this.clock = new Clock();
        this.register = 1;
        this.crt = new CRT();
    }

    public CPU(Clock clock) {
        this.clock = clock;
    }

    public void executeNext() {
        if (currentCommand != null) currentCommand.execute(this);
    }

    public void pollNext() {
        currentCommand = commands.poll();
    }

    public void put(CPUCommand command) {
        this.commands.add(command);
        if (currentCommand == null)
            currentCommand = commands.poll();
    }

    public void addRegister(long value) {
        this.register += value;
        this.crt.moveSprite(value);
    }

    public long getRegister() {
        return this.register;
    }

    public void advance(long time) {
        for (int i = 0; i < time; i++) {
            clock.advance(this);
            if (currentCommand != null) currentCommand.advance();
            crt.advance();
        }
    }

    public void reset(List<CPUCommand> commands) {
        this.register = 0;
        this.clock = new Clock();
        this.commands = new LinkedList<>();
        this.commands.addAll(commands);
    }

    public long signalStrength() {
        return this.register * this.clock.time();
    }

    public long clockTime() {
        return clock.time();
    }

    public void display() {
        this.crt.display();
    }

    public boolean finished() {
        return this.currentCommand == null;
    }
}
