package AOC24.DaySeven.Objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calibration {
    private long result;
    private List<Long> operands;
    
    public static Operator ADD = new Add();
    public static Operator MULTIPLY = new Multiply();
    public static Operator CONCATENATION = new Concatenation();
    
    public Calibration(String calibration) {
        String[] split = calibration.split(":");
        result = Long.parseLong(split[0]);
        operands = new ArrayList<>();
        for (String operand : split[1].strip().split(" ")) {
            operands.add(Long.parseLong(operand));
        }
    }

    public List<Integer[]> getBinaries(int n, Integer[] array, int i, List<Integer[]> current)
    {
        if (i == n)
        {
            if (!current.contains(array))
                current.add(Arrays.copyOf(array, n));
            return current;
        }
        
        array[i] = 0;
        getBinaries(n, array, i + 1, current);
        
        array[i] = 1;
        getBinaries(n, array, i + 1, current);
        
        return current;
    }
    
    public List<Integer[]> getTrinaries(int n, Integer[] array, int i, List<Integer[]> current) {
        if (i == n) {
            current.add(Arrays.copyOf(array, n));
            return current;
        }
        
        array[i] = 0;
        getTrinaries(n, array, i + 1, current);
        
        array[i] = 1;
        getTrinaries(n, array, i + 1, current);
        
        array[i] = 2;
        getTrinaries(n, array, i + 1, current);
        
        return current;
    }
    
    public List<Integer[]> possibilities() {
        List<Integer[]> result = new ArrayList<>();
        int size = operands.size() - 1;
        
        Integer[] array = new Integer[size];
        result = getBinaries(size, array, 0, result);
        
        // System.out.println("Size: " + size);
        // for (Integer[] res : result) {
        //     System.out.println(String.join(", ", Arrays.stream(res).map(r -> r.toString()).toList()));
        // }
        
        return result;
    }
    
    public List<Integer[]> trinaryPossibilities() {
        List<Integer[]> result = new ArrayList<>();
        int size = operands.size() - 1;

        Integer[] array = new Integer[size];
        result = getTrinaries(size, array, 0, result);

        return result;
    }
    
    public long testCalibration() {
        List<Integer[]> possibilities = possibilities();
        
        for (Integer[] possibility : possibilities) {
            long left = operands.getFirst();
            int possibilityInd = 0;
            for (int i = 1; i < operands.size(); i++) {
                long right = operands.get(i);
                left = new Calculation(left, right, possibility[possibilityInd++] == 0 ? ADD : MULTIPLY).calculate();
            }
            
            if (left == result)
                return result;
        }
        
        return 0;
    }

    public long testCalibrationTrinary() {
        List<Integer[]> possibilities = trinaryPossibilities();

        for (Integer[] possibility : possibilities) {
            long left = operands.getFirst();
            int possibilityInd = 0;
            for (int i = 1; i < operands.size(); i++) {
                long right = operands.get(i);
                left = new Calculation(left, right, possibility[possibilityInd] == 0 ? ADD :
                        (possibility[possibilityInd] == 1 ? MULTIPLY : CONCATENATION)).calculate();
                possibilityInd++;
            }

            if (left == result)
                return result;
        }

        return 0;
    }
}
