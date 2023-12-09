package AOC23.DayNine.Objects;

import java.util.*;

public class History {
  private List<Long> changes;
  private List<List<Long>> predictions;

  public History(String line) {
    changes = Arrays.stream(line.split(" ")).map((num) -> Long.parseLong(num.strip())).toList();
    predictions = new ArrayList<>();
    buildPredictions();
  }

  public void buildPredictions() {
    predictions.add(new ArrayList<>());
    List<Long> first = predictions.get(0);
    for (int i = 0; i < changes.size() - 1; i++) {
      first.add(changes.get(i + 1) - changes.get(i));
    }

    List<Long> lastPrediction;
    while (!(lastPrediction = predictions.get(predictions.size() - 1)).stream().allMatch((num) -> num == 0)) {
      List<Long> next = new ArrayList<>();
      for (int i = 0; i < lastPrediction.size() - 1; i++) {
        next.add(lastPrediction.get(i + 1) - lastPrediction.get(i));
      }
      predictions.add(next);
    }
  }

  public Long nextChange(boolean add) {
    List<List<Long>> predictionsCopy = new ArrayList<>(predictions);

    long lastNum = 0;
    for (int i = predictionsCopy.size() - 1; i >= 0; i--) {
      List<Long> prediction = predictionsCopy.get(i);
      lastNum = prediction.get(prediction.size() - 1) + lastNum;
      prediction.add(lastNum);
    }

    return this.changes.get(this.changes.size() - 1)  + predictionsCopy.get(0).get(predictionsCopy.get(0).size() - 1);
  }

  public Long previousChange(boolean add) {
    List<List<Long>> predictionsCopy = new ArrayList<>(predictions);

    long lastNum = 0;
    for (int i = predictionsCopy.size() - 1; i >= 0; i--) {
      List<Long> prediction = predictionsCopy.get(i);
      lastNum = prediction.get(0) - lastNum;
      prediction.add(0, lastNum);
    }

    return this.changes.get(0)  - predictionsCopy.get(0).get(0);
  }
}
