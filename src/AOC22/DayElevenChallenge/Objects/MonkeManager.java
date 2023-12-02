package AOC22.DayElevenChallenge.Objects;

import java.util.List;

public class MonkeManager {
    private List<Monke> monkes;

    public MonkeManager (List<Monke> monkes) {
        this.monkes = monkes;
    }

    public Monke get(int i) {
        if (i < 0 || i >= monkes.size()) return null;
        return monkes.get(i);
    }

    public long monkeBusiness() {
        long maxOne = Long.MIN_VALUE, maxTwo = Long.MIN_VALUE;

        for (Monke monke : monkes) {
            if (monke.getInspected() > maxOne) {
                maxOne = monke.getInspected();
            }
        }

        for (Monke monke : monkes) {
            if (monke.getInspected() > maxTwo && monke.getInspected() != maxOne) {
                maxTwo = monke.getInspected();
            }
        }

        return maxOne * maxTwo;
    }

    public long reduceWorry(long item) {
        // Chinese remainder theorem: https://en.wikipedia.org/wiki/Chinese_remainder_theorem
        long m = 1;
        for (Monke monke : monkes) {
            m *= monke.getTest();
        }

        long productRemainder = item % m;

        return productRemainder;
    }

    public long reduceWorry(long item, int thrower, int catcher) {
        int throwerTest = monkes.get(thrower).getTest();
        int catcherTest = monkes.get(catcher).getTest();

        long productRemainder = item % ((long) throwerTest * catcherTest);

        return item > ((long) throwerTest * catcherTest) ? item : productRemainder;
    }

    public long gcd(long first, long second) {
        long a = Math.max(first, second);
        long b = Math.min(first, second);

        while (b > 0) {
            a %= b;
            if (a == 0) return b;
            b %= a;
        }

        return a;
    }
}
