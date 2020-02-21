package de.algoristic.dice4j;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class WeightedValueTests {

  @ParameterizedTest
  @CsvSource(value = {"1, 2", "3, 4", "5, 6"})
  void testDefaultWeightedValue(int weight, int value) {
    WeightedValue<Integer> weightedValue = WeightedValue.of(weight, value);
    assertEquals(weight, weightedValue.getWeight());
    assertEquals(value, weightedValue.getValue());
  }
}
