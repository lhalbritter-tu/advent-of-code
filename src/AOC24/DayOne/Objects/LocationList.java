package AOC24.DayOne.Objects;

import java.util.*;

public class LocationList {
    
    private List<Integer> left;
    private List<Integer> right;
    
    public LocationList(List<String> locations) {
        left = new ArrayList<>();
        right = new ArrayList<>();
        for (String location : locations) {
            String[] parts = location.split(" {3}");
            left.add(Integer.parseInt(parts[0]));
            right.add(Integer.parseInt(parts[1]));
        }
    }
    
    public void sortAscending() {
        Collections.sort(left);
        Collections.sort(right);
    }
    
    public void sortDescending() {
        left.sort(Collections.reverseOrder());
        right.sort(Collections.reverseOrder());
    }
    
    public int totalDistance() {
        if (left.size() != right.size()) {
            return -1;
        }
        int result = 0;
        for (int i = 0; i < left.size(); i++) {
            result += Math.abs(left.get(i) - right.get(i));
        }
        return result;
    }
    
    public int totalSimilarityScore() {
        int result = 0;
        for (int i = 0; i < left.size(); i++) {
            int id = left.get(i);
            result += (int) (id * right.stream().filter(elem -> elem == id).count());
        }
        
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        System.out.println(left.size());
        System.out.println(right.size());
        for (int i = 0; i < left.size(); i++) {
            result += left.get(i) + " " + right.get(i) + "\n";
        }
        return result;
    }
}
