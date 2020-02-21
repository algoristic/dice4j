package de.algoristic.dice4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class TraditionalDiceTests {

  @Test
  void testTraditionalDiceArrayConstruction() {
    // the construction via array gets handed down through all other instantiation methods
    Integer[] items = new Integer[] {1, 2, 3, 4};
    Dice<Integer> dice = Dice.getTraditionalDice(items);
    assertTrue(Arrays.asList(items).contains(dice.roll()));
  }

  @Test
  void testEmptyArrayConstruction() {
    Dice<Integer> dice = Dice.getTraditionalDice(new Integer[] {});
    assertThrows(
        EmptyDiceException.class,
        () -> {
          dice.roll();
        });
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 2, 3, 4, 5, 6})
  void testAddSingleValue(int singleValue) {
    TraditionalDice<Integer> traditionalDice = new TraditionalDice<>();
    traditionalDice.add(singleValue);
    assertEquals(singleValue, traditionalDice.roll());
    assertEquals(1, traditionalDice.map.size());
    assertEquals(1, traditionalDice.total);
  }

  @ParameterizedTest
  @CsvSource(value = {"1, 2, 3", "4, 5, 6", "7, 8, 9,"})
  void testAddList(int first, int second, int third) {
    List<Integer> ls = Arrays.asList(first, second, third);
    TraditionalDice<Integer> traditionalDice = new TraditionalDice<>();
    traditionalDice.add(ls);
    assertTrue(ls.contains(traditionalDice.roll()));
    assertEquals(3, traditionalDice.map.size());
    assertEquals(3, traditionalDice.total);
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 2, 3, 4, 5, 6})
  void testAddCompleteDice(int numberOfValues) {
    Random numberGenerator = new Random();
    List<Integer> ls = new ArrayList<>();
    for (int i = 0; i < numberOfValues; i++) {
      ls.add(numberGenerator.nextInt(7));
    }
    TraditionalDice<Integer> traditionalDice = new TraditionalDice<>();
    traditionalDice.add(10);
    TraditionalDice<Integer> anotherDice = new TraditionalDice<>();
    anotherDice.add(ls);
    traditionalDice.addAll(anotherDice);
    List<Integer> allValues = new ArrayList<>();
    allValues.addAll(ls);
    allValues.add(10);
    assertTrue(allValues.contains(traditionalDice.roll()));
    assertEquals((numberOfValues + 1), traditionalDice.map.size());
    assertEquals((numberOfValues + 1), traditionalDice.total);
  }
}
