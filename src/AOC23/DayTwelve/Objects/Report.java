package AOC23.DayTwelve.Objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Report {

    private List<Integer> toPlace;
    private String placeString;

    private List<Integer> replacements;
    private List<List<Integer>> replacementOptions;

    public Report(String line) {
        String[] split = line.split(" ");
        placeString = split[0];
        toPlace = new ArrayList<>(Arrays.stream(split[1].split(",")).map(Integer::parseInt).toList());
        replacements = new ArrayList<>();

        for (int i = 0; i < placeString.length(); i++) {
            if (placeString.charAt(i) == '?')
                replacements.add(i);
        }

        permuations();

        /**int firstRecord = toPlace.get(0);
        int lastRecord = toPlace.get(toPlace.size() - 1);
        if (placeString.substring(0, firstRecord).replaceAll("#", "").isEmpty()) {
            toPlace.remove(0);
        }
        if (placeString.substring(placeString.length() - lastRecord).replaceAll("#", "").isEmpty()) {
            toPlace.remove(toPlace.size() - 1);
        }*/
    }

    public void permuations() {
        replacementOptions = new ArrayList<>();
        replacementOptions.add(new ArrayList<>(replacements));
        int[] indexes = new int[replacements.size()];
        for (int i = 0; i < replacements.size(); i++) {
            indexes[i] = 0;
        }


        int i = 0;
        while (i < replacements.size()) {
            if (indexes[i] < i) {
                swap(replacements, i % 2 == 0 ?  0: indexes[i], i);
                replacementOptions.add(new ArrayList<>(replacements));
                indexes[i]++;
                i = 0;
            }
            else {
                indexes[i] = 0;
                i++;
            }
        }

        for (List<Integer> option : replacementOptions) {
            System.out.println(option);
        }
    }

    public void swap(List<Integer> elements, int a, int b) {
        Integer temp = elements.get(a);
        elements.set(a, elements.get(b));
        elements.set(b, temp);
    }

    public boolean isValid() {
        int i = 0;
        int currentRecord;
        for (int j = 0; j < placeString.length(); j++) {
            if (i >= toPlace.size()) {
                if (placeString.substring(j).contains("#"))
                    return false;
                break;
            }
            currentRecord = toPlace.get(i);
            if (placeString.charAt(j) != '#')
                continue;
            int recorded = 1;
            while ((j + 1) < placeString.length() && placeString.charAt(++j) == '#') {
                recorded++;
                if (recorded > currentRecord)
                    return false;
            }
            if (recorded < currentRecord)
                return false;

            i++;
        }

        return i >= toPlace.size() - 1;
    }

    public boolean isValid(String record) {
        String temp = placeString;
        placeString = record;
        boolean result = isValid();
        placeString = temp;
        return result;
    }

    public long place() {
        long result = 0;
        List<String> checked = new ArrayList<>();
        for (List<Integer> permutation : replacementOptions) {
            String record = String.copyValueOf(placeString.toCharArray());
            int j = 0;
            int recorded = 0;
            int currentRecord;
            boolean point = false;
            for (Integer i : permutation) {
                if (j >= toPlace.size())
                    break;
                if (point) {
                    record = record.substring(0, i) + "." + record.substring(i + 1);
                    point = false;
                    continue;
                }
                currentRecord = toPlace.get(j);
                record = record.substring(0, i) + "#" + record.substring(i + 1);
                recorded++;
                if (recorded == currentRecord) {
                    j++;
                    recorded = 0;
                    point = true;
                }
            }
            record = record.replaceAll("\\?", ".");
            // System.out.println(record);
            if (checked.contains(record))
                continue;
            checked.add(record);
            result += isValid(record) ? 1 : 0;
        }
        return result;
    }

    @Override
    public String toString() {
        return placeString + " " + toPlace;
    }
}
