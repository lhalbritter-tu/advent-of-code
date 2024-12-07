package AOC24.DaySeven.Objects;

public class Concatenation implements Operator {
    @Override
    public long operate(long left, long right) {
        String concat = "" + left + right;
        return Long.parseLong(concat);
    }
}
