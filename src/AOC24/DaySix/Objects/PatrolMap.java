package AOC24.DaySix.Objects;

import Utility.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatrolMap {
    private List<List<String>> map;
    private List<List<String>> patrol;
    private Guard guard;
    
    public PatrolMap(List<List<String>> map) {
        this.map = map;
        this.patrol = new ArrayList<>();
        this.patrol.addAll(map);
        getGuard();
    }
    
    public boolean inBounds(Position position) {
        return position.x() >= 0 && position.x() < map.size() && position.y() >= 0 && position.y() < map.getFirst().size();
    }
    
    public char get(Position position) {
        return map.get(position.x()).get(position.y()).charAt(0);
    }
    
    public char getPatrol(Position position) {
        return patrol.get(position.x()).get(position.y()).charAt(0);
    }
    
    public Guard getGuard() {
        if (guard == null) {
            for (int x = 0; x < map.size(); x++) {
                for (int y = 0; y < map.get(x).size(); y++) {
                    char c = map.get(x).get(y).charAt(0);
                    if (c == '^' || c == 'v' || c == '>' || c == '<') {
                        guard = new Guard(new Position(x, y), c);
                        break;
                    }
                }
            }
        }
        return guard;
    }
    
    public void resetGuard() {
        guard = null;
        getGuard();
    }
    
    public List<Position> getObstacles() {
        List<Position> obstacles = new ArrayList<>();
        
        for (int x = 0; x < map.size(); x++) {
            for (int y = 0; y < map.get(x).size(); y++) {
                if (map.get(x).get(y).equals("#")) {
                    obstacles.add(new Position(x, y));
                }
            }
        }
        
        return obstacles;
    }
    
    public int patrol() {
        int fieldsPatrolled = 0;
        while (inBounds(guard.getPosition())) {
            Position current = guard.getPosition();
            Position next = guard.nextMove(this);
            if (!current.equals(next)) {
                if (getPatrol(current) != 'X') {
                    fieldsPatrolled++;
                    // patrol.get(current.x()).set(current.y(), "X");
                }
            }
        }
        
        return fieldsPatrolled;
    }
    
    public boolean isPatrolLoop() {
        Position start = guard.getPosition();
        Map<Position, List<Position>> directedPath = new HashMap<>();
        while (inBounds(guard.getPosition())) {
            Position current = guard.getPosition();
            Position currentDirection = guard.getDirection();
            
            if (directedPath.containsKey(currentDirection)) {
                if (directedPath.get(currentDirection).contains(current)) return true;
                directedPath.get(currentDirection).add(current);
            } else {
                directedPath.put(currentDirection, new ArrayList<>(List.of(current)));
            }
            
            guard.nextMove(this);
        }
        return false;
    }
    
    public int loopPositions() {
        int totalLoops = 0;
        // List<Position> obstacles = getObstacles();
        
        for (int x = 0; x < map.size(); x++) {
            for (int y = 0; y < map.get(x).size(); y++) {
                Position pos = new Position(x, y);
                if (get(pos) == '#' || get(pos) == getGuard().getDirectionChar()) continue;
                Position start = guard.getPosition().copy();
                Position direction = guard.getDirection().copy();
                map.get(x).set(y, "#");
                if (isPatrolLoop()) {
                    totalLoops++;
                    // System.out.println("LOOP!");
                    // printPatrol();
                    // System.out.println();
                }
                map.get(x).set(y, ".");
                guard.setPosition(start);
                guard.setDirection(direction);
            }
        }
        
        return totalLoops;
    }
    
    public void printPatrol() {
        for (int x = 0; x < map.size(); x++) {
            System.out.println(String.join("", map.get(x)));
        }
    }
}
