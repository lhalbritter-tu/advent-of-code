package AOC24.DayFive.Objects;

import Utility.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rule {

    private static Map<Integer, Rule> allRules = new HashMap<>();

    public static Rule getRule(int key) {
        if (allRules.containsKey(key))
            return allRules.get(key);
        return new Rule(key);
    }

    private int key;
    private List<Integer> values;

    public Rule(int key) {
        this.key = key;
        this.values = new ArrayList<>();
        Rule.allRules.put(key, this);
    }

    public void addValue(int value) {
        this.values.add(value);
    }

    public boolean applyRule(List<Integer> beforeKey) {
        return beforeKey.stream().anyMatch(value -> values.contains(value));
    }

    public Position getRuleBreak(List<Integer> beforeKey) {
        for (int i = 0; i < beforeKey.size(); i++) {
            int value = beforeKey.get(i);
            if (values.contains(value)) {
                return new Position(i, value);
            }
        }

        return null;
    }

    public static boolean anyRuleBreak(List<Integer> page) {
        List<Integer> visited = new ArrayList<>();
        for (int num : page) {
            visited.add(num);
            if (getRule(num).applyRule(visited)) {
                return true;
            }
        }
        return false;
    }
}
