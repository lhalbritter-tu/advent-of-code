package AOC25.DayOne.Objects;

public class Rotation {
    public static enum Direction {
        LEFT,
        RIGHT,
    }
    
    private Direction direction;
    private int count;
    
    public Rotation(Direction direction, int count) {
        this.direction = direction;
        this.count = count;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public void turn(Dial dial) {
        int number = dial.getCurrentNumber();
        
        for (int i = 0; i < count; i++) {
            switch (direction) {
                case LEFT -> {
                    number--;
                    if (number < 0)
                        number = 99;
                }
                case RIGHT -> {
                    number++;
                    if (number > 99)
                        number = 0;
                }
            }
            if (number == 0) dial.fireDialAtZero();
        }
        
        dial.setCurrentNumber(number);
    }
}
