package AOC22.DayElevenChallenge.Objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Monke {

    private int id;

    private List<Long> items;
    private int operation;
    private Operator operator;
    private int test;

    private HashMap<Boolean, Integer> testResult;

    private MonkeManager manager;

    private long inspected = 0;

    private Monke() {
    }

    public void setManager(MonkeManager manager) {
        this.manager = manager;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void takeTurn() {
        while (!items.isEmpty()) {
            long currentItem = items.get(0);
            inspected++;
            //System.out.println("  Monkey inspects an item with worry level of " + currentItem);
            //System.out.print("    Worry level is multiplied by " + operation);
            currentItem = applyWorryOperation(currentItem);
            //System.out.println(" to " + currentItem);
            // currentItem = reduceWorry(currentItem);
            boolean test = testItem(currentItem);
            //System.out.println("    Current worry level is " + (test ? "" : "not ") + "divisible by " + this.test);
            currentItem = manager.reduceWorry(currentItem);
            //System.out.println("    Monkey gets bored with item. Worry level is reduced to " + currentItem);
            manager.get(testResult.get(test)).addItem(currentItem);
            //System.out.println("    Item with worry level " + currentItem + " is thrown to monkey " + testResult.get(test));
            items.remove(0);
        }
    }

    public boolean testItem(long item) {
        return item % test == 0;
    }

    public long operation(long item) {
        return operation == -1 ? item : operation;
    }

    public long applyWorryOperation(long item) {
        switch (operator) {
            case PLUS -> {
                return item + operation(item);
            }
            case MINUS -> {
                return item - operation(item);
            }
            case TIMES -> {
                return item * operation(item);
            }
            case DIVIDE -> {
                return item / operation(item);
            }
        }

        return -2;
    }

    public long reduceWorry(long item) {
        return (item / 3);
    }

    public boolean addItem(long item) {
        return this.items.add(item);
    }

    public long getInspected() {
        return inspected;
    }

    public int getTest() {
        return test;
    }

    @Override
    public String toString() {
        return "Monke{" +
                "items=" + Arrays.toString(items.toArray()) +
                ", operation=" + operation +
                ", operator=" + operator +
                ", test=" + test +
                ", testResult if true=" + testResult.get(true) +
                ", testResult if false=" + testResult.get(false) +
                '}';
    }

    public static class MonkeBuilder {

        private List<Long> items;
        private int operation;
        private Operator operator;
        private int test;

        private HashMap<Boolean, Integer> testResult;

        private MonkeBuilder() {
            this.items = new ArrayList<>();
            this.testResult = new HashMap<>();
        }

        public static MonkeBuilder aMonke () {
            return new MonkeBuilder();
        }

        public MonkeBuilder withItem(long item) {
            this.items.add(item);
            return this;
        }

        public MonkeBuilder withOperation(int op) {
            operation = op;
            return this;
        }

        public MonkeBuilder withOperator(Operator operator) {
            this.operator = operator;
            return this;
        }

        public MonkeBuilder withTest(int test) {
            this.test = test;
            return this;
        }

        public MonkeBuilder withTestResult(boolean result, Integer next) {
            this.testResult.put(result, next);
            return this;
        }

        public MonkeBuilder withItems(List<Long> items) {
            this.items = items;
            return this;
        }

        public Monke build() {
            Monke res = new Monke();
            res.testResult = this.testResult;
            res.test = this.test;
            res.operation = this.operation;
            res.operator = this.operator;
            res.items = this.items;
            return res;
        }
    }

    public static Operator parseOperator(String op) {
        switch (op) {
            case "+": return Operator.PLUS;
            case "-": return Operator.MINUS;
            case "*": return Operator.TIMES;
            case "/": return Operator.DIVIDE;
            default: return null;
        }
    }

    public enum Operator {
        PLUS,
        MINUS,
        TIMES,
        DIVIDE,
    }
}
