package AOC22.DayTwelveChallenge.Objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Map {
    private Node[][] map;

    private HashMap<Node, Long> discoveredRoutes = new HashMap<>();
    private HashMap<Node, Long> distances = new HashMap<>();
    private HashMap<Node, Node> discovered = new HashMap<>();

    private Node start, end;

    public Map(List<List<String>> map2D) {
        map = new Node[map2D.size()][map2D.get(0).size()];
        for (int i = 0; i < map2D.size(); i++) {
            for (int j = 0; j < map2D.get(i).size(); j++) {
                char c = map2D.get(i).get(j).charAt(0);
                if (c == 'S') {
                    start = new Node(c, i, j);
                    map[i][j] = start;
                    discoveredRoutes.put(map[i][j], 0L);
                    continue;
                }
                if (c == 'E') {
                    end = new Node(c, i, j);
                    map[i][j] = end;
                    discoveredRoutes.put(map[i][j], Long.MAX_VALUE);
                    continue;
                }
                map[i][j] = new Node(c, i, j);
                discoveredRoutes.put(map[i][j], Long.MAX_VALUE);
                distances.put(map[i][j], Long.MAX_VALUE);
            }
        }
    }

    public void setStart(Node start) {
        int[] tmpPos = this.start.position();
        this.start = start;
        map[tmpPos[0]][tmpPos[1]] = new Node('a', tmpPos[0], tmpPos[1]);
        map[this.start.position()[0]][this.start.position()[1]] = this.start;
    }

    private List<Node> getValidMoves(Node n) {
        List<Node> moves = new ArrayList<>();
        moves.add(getValidMove(n, n.position()[0] + 1, n.position()[1]));
        moves.add(getValidMove(n, n.position()[0] - 1, n.position()[1]));
        moves.add(getValidMove(n, n.position()[0], n.position()[1] + 1));
        moves.add(getValidMove(n, n.position()[0], n.position()[1] - 1));
        moves.remove(null);
        moves.remove(null);
        moves.remove(null);
        moves.remove(null);

        return moves;
    }

    private Node getValidMove(Node start, int x, int y) {
        if (!inBounds(x, y)) return null;
        if (start.validMove(map[x][y])) return map[x][y];
        return null;
    }

    private boolean inBounds(int x, int y) {
        return x >= 0 && x < map.length && y >= 0 && y < map[x].length;
    }

    private int distanceTo(Node start, Node target) {
        int[] startPos = start.position();
        int[] endPos = target.position();
        return (int) (Math.pow(startPos[0] - endPos[0], 2) + Math.pow(startPos[1] - endPos[1], 2));
    }

    private Node getMinimumDistanceNode(List<Node> nodes) {
        int minDistance = Integer.MAX_VALUE;
        Node minNode = null;

        for (Node node : nodes) {
            if (distanceTo(node, end) < minDistance) {
                minDistance = distanceTo(node, end);
                minNode = node;
            }
        }

        return minNode;
    }

    private long routeLength(Node node) {
        long length = 0;
        Node next = node;
        System.out.println(next);
        while ((next = discovered.get(next)) != null) {
            System.out.println(next);
            length++;
        }

        return length;
    }

    private Node getLowestScoreNode(List<Node> openNodes) {
        long minScore = Long.MAX_VALUE;
        Node minNode = null;
        for (Node node : openNodes) {
            long score = discoveredRoutes.get(node);
            if (score < minScore) {
                minScore = score;
                minNode = node;
            }
        }
        return minNode;
    }

    public long getRouteLength(Node start) {
        List<Node> openNodes = new ArrayList<>();
        openNodes.add(start);
        long f = distanceTo(start, end);
        distances.put(start, f);

        while (!openNodes.isEmpty()) {
            //System.out.println(openNodes.size());
            Node currentNode = getLowestScoreNode(openNodes);
            long g = discoveredRoutes.get(currentNode);
            f = distanceTo(currentNode, end);
            System.out.println("Looking at " + currentNode + " with f score " + f);
            if (currentNode.equals(end)) {
                return routeLength(currentNode);
            }

            openNodes.remove(currentNode);
            List<Node> validNodes = getValidMoves(currentNode);
            for (Node node : validNodes) {
                long score = g + distanceTo(currentNode, node);

                if (score < discoveredRoutes.get(node)) {
                    discovered.put(node, currentNode);
                    discoveredRoutes.put(node, score);
                    distances.put(node, score + distanceTo(node, end));
                    if (!openNodes.contains(node)) openNodes.add(node);
                }
            }
        }

        return Long.MAX_VALUE;
    }

    public long getBestRouteNaive(Node start, List<Node> visited, long steps) {
        System.out.println("Looking at " + start + ", visited: " + visited.size());
        if (start.equals(end)) return steps;
        if (visited.contains(start)) return Long.MAX_VALUE;
        long minRoute = Long.MAX_VALUE;
        Node node = start;
        visited.add(node);
        List<Node> validMoves = getValidMoves(node);
        if (validMoves.isEmpty()) return Long.MAX_VALUE;
        while (!validMoves.isEmpty()) {
            Node move = getMinimumDistanceNode(validMoves);
            validMoves.remove(move);
            if (visited.contains(move)) continue;
            long route = getBestRouteNaive(move, new ArrayList<>(visited), steps + 1);
            if (route < minRoute) {
                minRoute = route;
                node = move;
            }
        }

        return minRoute;
    }

    public long getBestRouteLength() {
        // return getBestRouteNaive(start, new ArrayList<>(), 0);
        return getRouteLength(start);
    }

    @Override
    public String toString() {
        String res = "";
        for (Node[] row : map) {
            for (Node col : row) {
                res += col.symbol();
            }
            res += "\n";
        }
        return res;
    }

    public List<List<String>> getString() {
        List<List<String>> result = new ArrayList<>();
        for (Node[] row : map) {
            result.add(new ArrayList<>());
            for (Node col : row) {
                result.get(result.size() - 1).add("" + col);
            }
        }
        return result;
    }

    public List<int[]> getPositionsByChar(char c) {
        List<int[]> positions = new ArrayList<>();
        for (Node[] row : map) {
            for (Node col : row) {
                if (col.symbol().charAt(0) == c) {
                    positions.add(col.position());
                }
            }
        }
        return positions;
    }

    public Node start() {
        return start.copy();
    }

    public Node getNode(int[] position) {
        return map[position[0]][position[1]];
    }

    public Map copy() {
        return new Map(getString());
    }
}
