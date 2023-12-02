package AOC23.DayOne.Objects;

import java.util.*;

public class Calibration {

  public static final Map<Integer, String> DIGITS = Map.of(
      1, "one", 2, "two", 3, "three", 4, "four", 5, "five", 6,
      "six", 7, "seven", 8, "eight", 9, "nine"
  );

  public static long parse(List<String> document) {
    long calibration = 0L;

    for (String line : document) {
      calibration += parse(line);
    }

    return calibration;
  }

  public static long parse(String calibrationLine) {
    long calibration = 0L;

    int firstDigit = 0;
    int secondDigit = 0;

    for (int i = 0; i < calibrationLine.length(); i++) {
      if (Character.isDigit(calibrationLine.charAt(i))) {
        firstDigit = Integer.parseInt("" + calibrationLine.charAt(i));
        break;
      }

      int digit = matchesAnyDigit(calibrationLine, i);
      if (digit != -1) {
        firstDigit = digit;
        break;
      }
    }

    for (int i = calibrationLine.length() - 1; i > -1; i--) {
      if (Character.isDigit(calibrationLine.charAt(i))) {
        secondDigit = Integer.parseInt("" + calibrationLine.charAt(i));
        break;
      }

      int digit = matchesAnyDigitReversed(calibrationLine, i);
      if (digit != -1) {
        secondDigit = digit;
        break;
      }
    }
    
    calibration = Long.parseLong(firstDigit + "" + secondDigit);
    System.out.println("Calibration of " + calibrationLine + " is " + calibration);
    return calibration;
  }

  public static String reverse(String original) {
    String reversed = "";
    for (int i = original.length() - 1; i > -1; i--) {
      reversed += original.charAt(i);
    }
    return reversed;
  }

  public static boolean matchesDigit(String line, int start, int digit) {
    String digitString = DIGITS.get(digit);
    for (int i = 0; i < digitString.length(); i++) {
      if (line.charAt(start + i) != digitString.charAt(i))
        return false;
    }
    return true;
  }

  public static boolean matchesDigitReversed(String line, int start, int digit) {
    String digitString = reverse(DIGITS.get(digit));
    for (int i = 0; i < digitString.length(); i++) {
      if (line.charAt(start - i) != digitString.charAt(i))
        return false;
    }
    return true;
  }

  public static int matchesAnyDigit(String line, int start) {
    for (int i = 1; i < 10; i++) {
      if (matchesDigit(line, start, i))
        return i;
    }
    return -1;
  }

  public static int matchesAnyDigitReversed(String line, int start) {
    for (int i = 1; i < 10; i++) {
      if (matchesDigitReversed(line, start, i))
        return i;
    }
    return -1;
  }
}
