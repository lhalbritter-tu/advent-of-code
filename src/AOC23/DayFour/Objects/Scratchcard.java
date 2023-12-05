package AOC23.DayFour.Objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Scratchcard implements Comparable {

  private String card;
  private int id;
  private Map<String, Boolean> winning = new HashMap<>();
  private List<String> numbers = new ArrayList<>();

  public Scratchcard(String cardText) {
    card = cardText.split(":")[0];
    String[] cardSplit = card.split(" ");
    id = Integer.parseInt(cardSplit[cardSplit.length - 1]);
    String numbers = cardText.split(":")[1].strip();
    String winningNumbers = numbers.split("\\|")[0].strip();
    this.numbers = Arrays.stream(numbers.split("\\|")[1].strip().split(" ")).toList();

    for (String win : winningNumbers.split(" ")) {
      if (win.strip().isEmpty())
        continue;
      winning.put(win.strip(), true);
    }

    System.out.println(card + " has winning numbers " + winning.keySet());
  }

  public long value() {
    long value = 0;
    for (String number : numbers) {
      number = number.strip();
      if (winning.getOrDefault(number, false)) {
        winning.replace(number, false);
        value = value > 0 ? value * 2 : 1;
      }
    }
    return value;
  }

  public void real(Map<Scratchcard, Long> scratchcards, List<Scratchcard> scratchcardList, long executions) {
    long value = 0;
    for (String number : numbers) {
      number = number.strip();
      if (winning.getOrDefault(number, false)) {
        winning.replace(number, false);
        value++;
      }
    }
    reset();

    for (long i = 1; i <= value; i++) {
      Scratchcard scratchcard = scratchcardList.get((int) (id - 1 + i));
      scratchcards.put(
          scratchcard,
          scratchcards.get(scratchcard) + executions
      );
    }
  }

  public void reset() {
    winning.replaceAll((key, value) -> true);
  }

  public String card() {
    return card;
  }

  @Override
  public int compareTo(Object o) {
    Scratchcard other = (Scratchcard) o;
    return this.id - other.id;
  }
}
