package AOC22.DayTwelveChallenge.Objects;

import java.util.ArrayList;
import java.util.List;

public class MapManager {
    private static List<Map> maps;

    public static void buildMaps(List<List<String>> input) {
        Map original = new Map(input);
        maps = new ArrayList<>();
        maps.add(original.copy());
        Node originalStart = original.start();
        List<int[]> possiblePositions = original.getPositionsByChar('a');
        for (int[] position : possiblePositions) {
            Node node = original.getNode(position).copy();
            node.setSymbol('S');
            original.setStart(node);
            maps.add(original.copy());
        }
    }

    public static void printMaps() {
        for (Map map : maps) {
            System.out.println(map.toString());
        }
    }

    public static long getBestRoute() {
        long minRoute = Long.MAX_VALUE;
        for (Map map : maps) {
            long route = map.getBestRouteLength();
            if (route < minRoute)
                minRoute = route;
        }

        return minRoute;
    }
}
