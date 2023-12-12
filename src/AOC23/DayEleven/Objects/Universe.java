package AOC23.DayEleven.Objects;

import Utility.Position;

import java.util.ArrayList;
import java.util.List;

public class Universe {
    private List<List<String>> starChart;
    private List<Galaxy> galaxies = new ArrayList<>();

    private List<Integer> emptyRows = new ArrayList<>(), emptyCols = new ArrayList<>();

    private long expansion = 0;

    public Universe(List<List<String>> input) {
        this.starChart = input;
        this.expand();

        for (int x = 0; x < starChart.size(); x++) {
            for (int y = 0; y < starChart.get(x).size(); y++) {
                if (this.starChart.get(x).get(y).equals("#")) {
                    galaxies.add(new Galaxy(new Position(x, y)));
                }
            }
        }
    }

    public void expand() {
        for (int i = 0; i < starChart.size(); i++) {
            List<String> row = starChart.get(i);
            if (row.stream().allMatch((symbol) -> symbol.equals("."))) {
                emptyRows.add(i);
            }
        }

        for (int i = 0; i < starChart.get(0).size(); i++) {
            boolean isEmpty = true;
            for (int j = 0; j < starChart.size(); j++) {
                if (!starChart.get(j).get(i).equals(".")) {
                    isEmpty = false;
                    break;
                }
            }
            if (isEmpty) {
                emptyCols.add(i);
            }
        }

        printUniverse();
    }

    public void expand(long expansion) {
        this.expansion = expansion;
    }

    public void printUniverse() {
        for (List<String> line : starChart) {
            System.out.println(String.join("", line));
        }
    }

    public long totalSteps() {
        long total = 0;
        for (int i = 0; i < galaxies.size(); i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                long steps = steps(i, j);
                System.out.println("Galaxy " + (i + 1) + " -> Galaxy " + (j + 1) + ": "  + steps);
                total += steps;
            }
        }
        return total;
    }

    public double distance(int galaxyOne, int galaxyTwo) {
        return galaxies.get(galaxyOne).distanceTo(galaxies.get(galaxyTwo));
    }

    public long steps(int galaxyOne, int galaxyTwo) {
        Galaxy one = galaxies.get(galaxyOne);
        Galaxy two = galaxies.get(galaxyTwo);

        boolean bottom = one.x() > two.x();
        boolean left = one.y() < two.y();

        long extra = 0;

        if (galaxyOne == 3 && galaxyTwo == 4)
            System.out.println("HERE");

        for (Integer i : emptyRows) {
            if (bottom) {
                if (i <= one.x() && i >= two.x()) {
                    extra += expansion;
                }
            } else {
                if (i >= one.x() && i <= two.x()) {
                    extra += expansion;
                }
            }
        }

        for (Integer i : emptyCols) {
            if (left) {
                if (i >= one.y() && i <= two.y()) {
                    extra += expansion;
                }
            } else {
                if (i >= two.y() && i <= one.y()) {
                    extra += expansion;
                }
            }
        }

        return extra + one.stepsTo(two);
    }
}
