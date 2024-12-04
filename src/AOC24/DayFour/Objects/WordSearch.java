package AOC24.DayFour.Objects;

import Utility.Position;

import java.util.List;

public class WordSearch {
    private List<List<String>> map;

    public WordSearch(List<List<String>> map) {
        this.map = map;
    }

    public int search(String word) {
        char[] characters = word.toCharArray();
        char first = characters[0];

        int matches = 0;

        for (int x = 0; x < map.size(); x++) {
            for (int y = 0; y < map.get(x).size(); y++) {
                if (map.get(x).get(y).charAt(0) == first) {
                    matches += matchWord(characters, x + 1, y, 1, new Position(1, 0))
                    + matchWord(characters, x - 1, y, 1, new Position(-1, 0))
                    + matchWord(characters, x, y + 1, 1, new Position(0, 1))
                    + matchWord(characters, x, y - 1, 1, new Position(0, -1))
                    + matchWord(characters, x + 1, y + 1, 1, new Position(1, 1))
                    + matchWord(characters, x - 1, y + 1, 1, new Position(-1, 1))
                    + matchWord(characters, x + 1, y - 1, 1, new Position(1, -1))
                    + matchWord(characters, x - 1, y - 1, 1, new Position(-1, -1));
                }
            }
        }

        return matches;
    }

    public int searchCrossMas() {
        char[] characters = new char[] {'M', 'A', 'S'};
        char first = characters[0];

        int matches = 0;

        for (int x = 0; x < map.size(); x++) {
            for (int y = 0; y < map.get(x).size(); y++) {
                if (map.get(x).get(y).charAt(0) == first) {
                    // right, up options: (x - 2, y), (x, y + 2)
                    if (matchWord(characters, x - 1, y + 1, 1, new Position(-1, 1)) > 0) {
                        matches += (matchWord(characters, x - 2, y, 0, new Position(1, 1))
                                + matchWord(characters, x, y + 2, 0, new Position(-1, -1))) > 0 ? 1 : 0;
                    }
                    // right, down options: (x + 2, y), (x, y + 2)
                    if (matchWord(characters, x + 1, y + 1, 1, new Position(1, 1)) > 0) {
                        matches += (matchWord(characters, x + 2, y, 0, new Position(-1, 1))
                                + matchWord(characters, x, y + 2, 0, new Position(1, -1))) > 0 ? 1 : 0;
                    }
                    // left, up options: (x - 2, y), (x, y - 2)
                    if (matchWord(characters, x - 1, y - 1, 1, new Position(-1, -1)) > 0) {
                        matches += (matchWord(characters, x - 2, y, 0, new Position(1, -1))
                                + matchWord(characters, x, y - 2, 0, new Position(-1, 1))) > 0 ? 1 : 0;
                    }
                    // left, down options: (x + 2, y), (x, y + 2)
                    if (matchWord(characters, x + 1, y - 1, 1, new Position(1, -1)) > 0) {
                        matches += (matchWord(characters, x + 2, y, 0, new Position(-1, -1))
                                + matchWord(characters, x, y - 2, 0, new Position(1, 1))) > 0 ? 1 : 0;
                    }
                }
            }
        }

        return matches / 2;
    }

    public int matchWord(char[] characters, int x, int y, int depth, Position direction) {
        if (depth >= characters.length)
            return 1;
        if (x < 0 || y < 0 || x >= map.size() || y >= map.get(0).size())
            return 0;
        if (map.get(x).get(y).charAt(0) != characters[depth])
            return 0;

        return matchWord(characters, x + direction.x(), y + direction.y(), depth + 1, direction);
    }
}
