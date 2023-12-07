package AOC23.DaySeven.Objects;

import java.util.ArrayList;
import java.util.List;

public class Hand implements Comparable {

    protected List<Integer> cards = new ArrayList<>();

    protected int highCard;

    protected int bid;

    protected WinCondition winCondition;

    public Hand(String handLine) {
        new Hand(handLine, true);
    }

    public Hand(String handLine, boolean setWin) {
        String hand = handLine.split(" ")[0];
        bid = Integer.parseInt(handLine.split(" ")[1]);
        highCard = -1;

        for (char card : hand.toCharArray()) {
            if (Character.isDigit(card))
                cards.add(Integer.parseInt("" + card));
            else {
                switch(card) {
                    case 'T' -> cards.add(10);
                    case 'J' -> cards.add(11);
                    case 'Q' -> cards.add(12);
                    case 'K' -> cards.add(13);
                    case 'A' -> cards.add(14);
                }
            }
            if (cards.get(cards.size() - 1) > highCard) {
                highCard = cards.get(cards.size() - 1);
            }
        }

        if (setWin)
            this.winCondition = winCondition();
    }

    public int winValue(int rank) {
        return bid * rank;
    }

    public WinCondition winCondition() {
        // Poker logic
        List<Integer> handCopy = new ArrayList<>(cards);
        boolean five = false, four = false, full = false, three = false, two = false, one = false, high = false;
        int currentCard;
        while (handCopy.size() > 0) {
            currentCard = handCopy.get(0);

            int dupeCount = 0;
            for (int i = 1; i < handCopy.size(); i++) {
                if (currentCard == handCopy.get(i))
                    dupeCount++;
            }

            switch(dupeCount) {
                case 1:
                    if (one)
                        two = true;
                    else
                        one = true;
                    break;
                case 2:
                    three = true;
                    break;
                case 3:
                    four = true;
                    break;
                case 4:
                    five = true;
                    break;
            }

            for (int i = 0; i < dupeCount + 1; i++) {
                handCopy.remove(new Integer(currentCard));
            }
        }

        full = one && three;
        high = !(five || four || full || three || two || one);
        if (high)
            return WinCondition.HIGH;
        if (full)
            return WinCondition.FULL;

        if (five)
            return WinCondition.FIVE;
        if (four)
            return WinCondition.FOUR;
        if (three)
            return WinCondition.THREE;
        if (two)
            return WinCondition.TWO;
        return WinCondition.PAIR;
    }

    public List<Integer> getCards() {
        return cards;
    }

    public WinCondition getWinCondition() {
        return winCondition;
    }

    @Override
    public int compareTo(Object o) {
        Hand other = (Hand) o;
        WinCondition win = getWinCondition();
        WinCondition otherWin = other.getWinCondition();
        if (win == otherWin) {
            for (int i = 0; i < cards.size(); i++) {
                int myCard = cards.get(i);
                int otherCard = other.getCards().get(i);
                if (myCard != otherCard)
                    return myCard - otherCard;
            }
        }

        return win.ordinal() - otherWin.ordinal();
    }

    @Override
    public String toString() {
        return cards.toString() + " wins with " + getWinCondition();
    }

    public enum WinCondition {
        HIGH(),
        PAIR(),
        TWO(),
        THREE(),
        FULL(),
        FOUR(),
        FIVE();
    }
}
