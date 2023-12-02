package AOC22.DayElevenChallenge;

import AOC22.DayElevenChallenge.Objects.Monke;
import AOC22.DayElevenChallenge.Objects.MonkeManager;
import Utility.InputManager;

import java.util.ArrayList;
import java.util.List;

public class PuzzleEleven {

    public static void main(String[] args) {
        List<List<String>> input =
                InputManager.INSTANCE.getAdventOfCodeInputSeperated(11, 2022);
                // InputManager.INSTANCE.getFileSeperated(InputManager.DEFAULT_INPUT_PATH + "/resources/input/dayeleven_example");
        List<Monke> monkes = new ArrayList<>();
        for (List<String> line : input) {
            monkes.add(getMonke(line));
            // System.out.println(monkes.get(monkes.size() - 1));
        }

        MonkeManager manager = new MonkeManager(monkes);

        int i = 0;
        for (Monke monke : monkes) {
            monke.setManager(manager);
            monke.setId(i++);
        }

        int rounds = 0;
        while (rounds < 10000) {
            for (Monke monke : monkes) {
                monke.takeTurn();
            }
            if (rounds % 1000 == 0) {
                System.out.println("== After round " + rounds + " ==");
                for (Monke monke : monkes) {
                    System.out.println(monke + "\n" + monke.getInspected());
                }
            }
            rounds++;
        }

        for (Monke monke : monkes) {
            System.out.println(monke.getInspected());
        }

        System.out.println(manager.monkeBusiness());
    }

    public static Monke getMonke(List<String> monkeInput) {
        List<Long> items = new ArrayList<>();
        int operation = -1;
        Monke.Operator operator = Monke.Operator.PLUS;
        int testPositive = -1, testNegative = -1;
        int test = -1;

        for (int i = 0; i < monkeInput.size(); i++) {
            if (monkeInput.get(i).startsWith("Monkey")) continue;
            String[] splitted = monkeInput.get(i).split(":");
            String id = splitted[0];
            String content = splitted[1];
            switch (id.strip()) {
                case "Starting items":
                    String[] inputItems = content.split(",");
                    for (String item : inputItems) {
                        items.add(Long.parseLong(item.strip()));
                    }
                    break;
                case "Operation":
                    String relevant = content.split("=")[1].substring(4).strip();
                    String[] relevantSplit = relevant.split(" ");
                    operator = Monke.parseOperator(relevantSplit[0]);
                    if (Character.isDigit(relevantSplit[1].strip().charAt(0))) {
                        operation = Integer.parseInt(relevantSplit[1].strip());
                    }
                    break;
                case "Test":
                    String[] inputTest = content.split(" ");
                    test = Integer.parseInt(inputTest[inputTest.length - 1].strip());
                    break;
                case "If true":
                    String[] inputTestPos = content.split(" ");
                    testPositive = Integer.parseInt(inputTestPos[inputTestPos.length - 1].strip());
                    break;
                case "If false":
                    String[] inputTestNeg = content.split(" ");
                    testNegative = Integer.parseInt(inputTestNeg[inputTestNeg.length - 1].strip());
                    break;
                default:
                    break;
            }
        }

        return Monke.MonkeBuilder.aMonke()
                .withItems(items)
                .withTest(test)
                .withTestResult(true, testPositive)
                .withTestResult(false, testNegative)
                .withOperation(operation)
                .withOperator(operator)
                .build();

    }
}
