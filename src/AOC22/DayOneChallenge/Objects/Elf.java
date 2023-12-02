package AOC22.DayOneChallenge.Objects;

import java.util.ArrayList;
import java.util.List;

public class Elf {
    private List<Long> inventory;
    private Long total;

    public Elf() {
        this.inventory = new ArrayList();
        this.total = 0L;
    }

    public boolean addFood(Long calories) {
        total += calories;
        return this.inventory.add(calories);
    }

    public Long getTotal() {
        return total;
    }

    public List<Long> getInventory() {
        return inventory;
    }
}
