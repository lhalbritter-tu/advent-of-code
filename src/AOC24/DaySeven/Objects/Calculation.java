package AOC24.DaySeven.Objects;

public class Calculation {
    private long left;
    private long right;
    private Operator operator;
    
    public Calculation(long left, long right, Operator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }
    
    public long calculate() {
        return operator.operate(left, right);
    }
    
    public void setOperator(Operator operator) {
        this.operator = operator;
    }
    
    public void setLeft(long left) {
        this.left = left;
    }

    public void setRight(long right) {
        this.right = right;
    }
}
