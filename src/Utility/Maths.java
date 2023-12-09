package Utility;

public class Maths {
  public static long gcd(long a, long b) {
    while (b > 0) {
      long c = b;
      b = a % b;
      a = c;
    }

    return a;
  }

  public static long lcm(long a, long b) {
    return (a * b) / gcd(a, b);
  }
}
