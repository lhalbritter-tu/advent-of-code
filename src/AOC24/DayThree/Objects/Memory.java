package AOC24.DayThree.Objects;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Memory {

    private List<Mul> multiplications = new ArrayList<Mul>();

    public Memory(String memory) {
        Matcher matcher = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)").matcher(memory);
        while (matcher.find()) {
            String mul = matcher.group();
            System.out.println(mul);
            String[] nums = mul.substring(4, mul.length() - 1).split(",");
            multiplications.add(new Mul(Integer.parseInt(nums[0]), Integer.parseInt(nums[1])));
        }
    }

    public Memory(String memory, String enabler, String disabler) {
        String[] enabled = memory.split(enabler);
        for (int i = 0; i < enabled.length; i++) {
            String actualEnabled = enabled[i].split(disabler)[0];
            if (actualEnabled.isEmpty()) {
                actualEnabled = enabled[i];
            }
            System.out.println("Actual enabled: " + actualEnabled);
            Matcher matcher = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)").matcher(actualEnabled);
            while (matcher.find()) {
                String mul = matcher.group();
                System.out.println(mul);
                String[] nums = mul.substring(4, mul.length() - 1).split(",");
                multiplications.add(new Mul(Integer.parseInt(nums[0]), Integer.parseInt(nums[1])));
            }
        }
    }

    public int multiplicationSum() {
        return multiplications.stream().map(Mul::apply).reduce(0, Integer::sum);
    }
}
