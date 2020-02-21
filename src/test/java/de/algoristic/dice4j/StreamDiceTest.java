package de.algoristic.dice4j;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

public class StreamDiceTest {

  @Test
  void traditionalDiceStreamTest() {
    List<Integer> ls = Arrays.asList(1, 2, 3, 4, 5, 6);
    Dice<Integer> dice = Dice.getTraditionalDice(ls);
    dice.stream().limit(10).forEach((i) -> assertTrue(ls.contains(i)));
  }

  @Test
  void toTraditionalRandomStreamTest() {
    IntStream.rangeClosed(1, 6)
        .mapToObj((i) -> i)
        .collect(Dice.toTraditionalRandomStream())
        .limit(10)
        .forEach((i) -> assertTrue(Arrays.asList(1, 2, 3, 4, 5, 6).contains(i)));
  }

  @Test
  void loadedDiceStreamTest() {
    List<WeightedValue<Integer>> items =
        Arrays.asList(
            WeightedValue.of(1, 1),
            WeightedValue.of(2, 2),
            WeightedValue.of(3, 3),
            WeightedValue.of(4, 4));
    Dice<Integer> dice = Dice.getLoadedDice(items);
    dice.stream().limit(10).forEach((i) -> assertTrue(Arrays.asList(1, 2, 3, 4).contains(i)));
  }

  @Test
  void toWeightedRandomStreamTest() {
    Arrays.asList(
            WeightedValue.of(1, 1),
            WeightedValue.of(2, 2),
            WeightedValue.of(3, 3),
            WeightedValue.of(4, 4))
        .stream()
        .collect(Dice.toLoadedRandomStream())
        .limit(10)
        .forEach((i) -> assertTrue(Arrays.asList(1, 2, 3, 4).contains(i)));
  }
}
