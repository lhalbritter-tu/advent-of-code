package AOC24.DayFive.Objects;

import Utility.Position;

import java.util.ArrayList;
import java.util.List;

public class PrintPages {

    private String[] pages;

    public PrintPages(String pages) {
        this.pages = pages.split(",");
    }

    public int correctPage() {
        List<Integer> visited = new ArrayList<Integer>();

        for (String page : pages) {
            int pageNumber = Integer.parseInt(page);
            visited.add(pageNumber);
            if (Rule.getRule(pageNumber).applyRule(visited)) {
                return 0;
            }
        }

        return Integer.parseInt(pages[pages.length / 2]);
    }

    public int incorrectPage() {
        List<Integer> visited = new ArrayList<>();
        List<Integer> newOrder = new ArrayList<>();
        boolean rulesBroken = false;
        for (String page : pages) {
            int pageNumber = Integer.parseInt(page);
            visited.add(pageNumber);
            Position ruleBreak = Rule.getRule(pageNumber).getRuleBreak(visited);
            if (ruleBreak != null) {
                rulesBroken = true;
                newOrder.add(ruleBreak.x(), pageNumber);
            } else {
                newOrder.add(pageNumber);
            }
        }

        while (Rule.anyRuleBreak(newOrder)) {
            visited.clear();
            for (int i = 0; i < newOrder.size(); i++) {
                int pageNumber = newOrder.remove(i);
                visited.add(pageNumber);
                Position ruleBreak = Rule.getRule(pageNumber).getRuleBreak(visited);
                if (ruleBreak != null) {
                    rulesBroken = true;
                    newOrder.add(ruleBreak.x(), pageNumber);
                } else {
                    newOrder.add(i, pageNumber);
                }
            }
        }

        if (rulesBroken)
            return newOrder.get(newOrder.size() / 2);
        return 0;
    }
}
