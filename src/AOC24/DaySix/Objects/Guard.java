package AOC24.DaySix.Objects;

import Utility.Position;

import java.util.List;

public class Guard {
    private Position position;
    private Position direction;
    
    public Guard(Position position, char direction) {
        this.position = position;
        switch (direction) {
            case '^':
                this.direction = new Position(-1, 0);
                break;
            case 'v':
                this.direction = new Position(1, 0);
                break;
            case '<':
                this.direction = new Position(0, -1);
                break;
            case '>':
                this.direction = new Position(0, 1);
                break;
        }
    }
    
    public Position getPosition() {
        return position;
    }
    
    public Position getDirection() {
        return direction;
    }
    
    public char getDirectionChar() {
        if (this.direction.x() == -1) {
            return '^';
        } else if (this.direction.y() == -1) {
            return '<';
        } else if (this.direction.x() == 1) {
            return 'v';
        }
        return '>';
    }
    
    public void rotate() {
        if (this.direction.x() == -1) {
            this.direction = new Position(0, 1);
        } else if (this.direction.y() == -1) {
            this.direction = new Position(-1, 0);
        } else if (this.direction.x() == 1) {
            this.direction = new Position(0, -1);
        } else {
            this.direction = new Position(1, 0);
        }
    }
    
    public Position nextMove(PatrolMap patrolMap) {
        Position nextPosition = this.position.copy(this.direction.x(), this.direction.y());
        if (!patrolMap.inBounds(nextPosition)) {
            this.position = nextPosition;
            return nextPosition;
        } else {
            char field = patrolMap.get(nextPosition);
            if (field == '#') {
                rotate();
                return position;
            }
            this.position = nextPosition;
            return position;
        }
    }

    public void setPosition(Position start) {
        this.position = start;
    }
    
    public void setDirection(Position direction) {
        this.direction = direction;
    }
}
