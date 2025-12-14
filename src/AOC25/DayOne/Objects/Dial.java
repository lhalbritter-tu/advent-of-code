package AOC25.DayOne.Objects;

import java.util.ArrayList;
import java.util.List;

public class Dial {
    private int currentNumber;
    private List<Rotation> rotations;
    
    private int zeroCount;
    
    public Dial(List<String> rotations) {
        this.rotations = new ArrayList<>();
        for (String line : rotations) {
            String direction = line.substring(0, 1);
            String rest = line.substring(1);
            
            this.rotations.add(new Rotation(
                    direction.equals("L") ? Rotation.Direction.LEFT : Rotation.Direction.RIGHT,
                    Integer.parseInt(rest)
            ));
        }
        
        currentNumber = 50;
    }
    
    public int executeRotationsAndGetZeroCount() {
        return executeRotationsAndGetZeroCount(this.rotations.size());
    }
    
    public int executeRotationsAndGetZeroCount(int endIndex) {
        currentNumber = 50;
        zeroCount = 0;
        for (int i = 0; i < endIndex; i++) {
            rotations.get(i).turn(this);
        }
        return zeroCount;
    }
    
    public void setCurrentNumber(int number) {
        currentNumber = number;
    }
    
    public int getCurrentNumber() {
        return currentNumber;
    }
    
    public void fireDialAtZero() {
        zeroCount++;
    }
}
