package AOC23.DayFive.Objects;

import java.util.Map;

public class SeedMap {

    private long sourceStart, destinationStart;
    private long range;

    public SeedMap(long source, long destination, long range) {
        this.sourceStart = source;
        this.destinationStart = destination;
        this.range = range;
    }

    public long convert(long source) {
        if (source >= sourceStart + range || source < sourceStart)
            return source;
        long delta = Math.abs(sourceStart - source);
        return destinationStart + delta;
    }

    public long revert(long converted) {
        if (converted >= destinationStart + range || converted < destinationStart)
            return converted;
        long delta = Math.abs(destinationStart - converted);
        return sourceStart + delta;
    }

}
