package AOC22.DaySixChallenge;

import AOC22.DaySixChallenge.Objects.CommunicationDevice;
import Utility.InputManager;

import java.util.List;

public class PuzzleSix {
  public static void main(String[] args) {
    InputManager.INSTANCE.writeWebToFile(
            InputManager.AOC_DEFAULT_URL.replace("?", "6"),
            InputManager.DEFAULT_INPUT_PATH + "/resources/input/daysix_input"
    );
    List<String> input = InputManager.INSTANCE.getAdventOfCodeInput(6, 2022);
    for (String line : input) {
      CommunicationDevice device = new CommunicationDevice(line);
      System.out.println("Start of packet: " + device.getStartOfPacket(4));
      System.out.println("Start of message: " + device.getStartOfPacket(14) + "\n");
    }
  }
}
