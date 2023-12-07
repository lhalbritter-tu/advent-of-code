package AOC23.DaySeven;

import AOC23.DaySeven.Objects.Hand;
import AOC23.DaySeven.Objects.JokerHand;
import Utility.InputManager;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Puzzle {
    public static void main(String[] args) {
        List<String> input = InputManager.INSTANCE.getAdventOfCodeInput(7, 2023);
        List<String> test = List.of(("32T3K 765\n" +
                "T55J5 684\n" +
                "KK677 28\n" +
                "KTJJT 220\n" +
                "QQQJA 483").split("\n"));

        // input = test;

        SortedSet<Hand> hands = new TreeSet<>();

        for (String hand : input) {
            System.out.println("Initializing " + hand + "...");
            Hand h = new Hand(hand, true);
            hands.add(h);
        }

        int currentRank = 1;
        int total = 0;
        for (Hand hand : hands) {
            System.out.println(hand + ", ranking: " + currentRank);
            total += hand.winValue(currentRank++);
            System.out.println(hand + " has winValue of " + hand.winValue(currentRank - 1));
        }
        System.out.println(total);

        // -- PART TWO -- \\
        SortedSet<JokerHand> jokerHands = new TreeSet<>();

        for (String hand : input) {
            jokerHands.add(new JokerHand(hand));
        }

        currentRank = 1;
        total = 0;
        for (JokerHand hand : jokerHands) {
            System.out.println(hand + ", ranking: " + currentRank);
            total += hand.winValue(currentRank++);
            System.out.println(hand + " has winValue of " + hand.winValue(currentRank - 1));
        }
        System.out.println(total);
    }
}
