package AOC22.DayNineChallenge;

import AOC22.DayNineChallenge.Objects.Actor;
import AOC22.DayNineChallenge.Objects.Follower;
import Utility.InputManager;
import Utility.Position;

import java.util.List;

public class PuzzleNine {

    public static void printMap(int sizeX, int sizeY, Actor[] a) {
        String[][] map = new String[sizeX][sizeY];
        for (Actor actor : a) {
            map[actor.getPosition().x()][actor.getPosition().y()] = actor.toString();
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] != null ? map[i][j] : (a[a.length - 1].hasVisited(new Position(i, j)) ? "#" : "."));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<String> lines =
                InputManager.INSTANCE.getAdventOfCodeInput(9, 2022);
                //List.of(InputManager.INSTANCE.getFile(InputManager.DEFAULT_INPUT_PATH + "/resources/input/daynine_example"));
        /*--------- Part 1 ---------*/
        System.out.println("------- Part 1 --------");
        Actor head = new Actor(500, 500);
        head.setDisplay("H");
        Follower tail = new Follower(500, 500);
        tail.setDisplay("T");
        tail.follow(head);

        for (String line : lines) {
            String[] splitted = line.split(" ");
            String move = splitted[0];
            int x = move.equals("L") ? -1 : (move.equals("R") ? 1 : 0);
            int y = move.equals("U") ? -1 : (move.equals("D") ? 1 : 0);
            int steps = Integer.parseInt(splitted[1]);

            for (int i = 0; i < steps; i++) {
                head.move(x, y);
            }
        }

        System.out.println("Head: " + head.totalVisited() + ", Tail: " + tail.totalVisited());
        System.out.println();

        /*---------- Part 2 ------------*/
        System.out.println("------- Part 2 --------");
        head = new Actor(12, 16);
        head.setDisplay("H");
        Actor[] actors = new Actor[10];
        actors[0] = head;
        for (int i = 1; i < 10; i++) {
            actors[i] = new Follower(12, 16);
            actors[i].setDisplay("" + i);
            ((Follower) actors[i]).follow(actors[i - 1]);
        }

        for (String line : lines) {
            String[] splitted = line.split(" ");
            String move = splitted[0];
            int x = move.equals("L") ? -1 : (move.equals("R") ? 1 : 0);
            int y = move.equals("U") ? -1 : (move.equals("D") ? 1 : 0);
            int steps = Integer.parseInt(splitted[1]);

            for (int i = 0; i < steps; i++) {
                head.move(x, y);
            }
        }

        System.out.println("Head: " + head.totalVisited() + ", Tail: " + actors[actors.length - 1].totalVisited());
    }
}
