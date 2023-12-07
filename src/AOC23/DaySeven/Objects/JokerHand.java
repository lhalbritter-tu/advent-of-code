package AOC23.DaySeven.Objects;

import java.util.ArrayList;
import java.util.List;

public class JokerHand extends Hand {

    public JokerHand(String handLine) {
        super(handLine, false);
        cards.replaceAll((card) -> card == 11 ? 1 : card);
        this.winCondition = this.winCondition();
    }

    @Override
    public WinCondition winCondition() {
        // Poker logic
        List<Integer> handCopy = new ArrayList<>(cards);
        int jokerCount = 0;
        while (handCopy.remove(new Integer(1))) {
            jokerCount++;
        }

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
            return jokerCount == 0 ? WinCondition.HIGH :
                    (jokerCount == 1 ? WinCondition.PAIR :
                            (jokerCount == 2 ? WinCondition.THREE :
                                    (jokerCount == 3 ? WinCondition.FOUR : WinCondition.FIVE)));
        if (full)
            return WinCondition.FULL;

        if (five)
            return WinCondition.FIVE;
        if (four)
            return jokerCount == 0 ? WinCondition.FOUR : WinCondition.FIVE;
        if (three)
            return jokerCount == 0 ? WinCondition.THREE : (jokerCount == 1 ? WinCondition.FOUR : WinCondition.FIVE);
        if (two)
            return jokerCount == 0 ? WinCondition.TWO : WinCondition.FULL;
        return jokerCount == 0 ? WinCondition.PAIR : (jokerCount == 1 ? WinCondition.THREE : (jokerCount == 2 ? WinCondition.FOUR : WinCondition.FIVE));
    }
}
