package AOC24.DaySeven.Objects;

public class Add implements Operator {
    @Override
    public long operate(long left, long right) {
        return left + right;
    }
}
