package de.algoristic.dice4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LoadedDiceTests {

  @Mock Random random;

  @InjectMocks LoadedDice<Integer> dice;

  WeightedValue<Integer> NO_WEIGHT = WeightedValue.of(0, 1337);

  @Test
  void testTraditionalDiceArrayConstruction() {
    // the construction via list gets handed down through all other instantiation methods
    List<WeightedValue<Integer>> items =
        Arrays.asList(
            WeightedValue.of(1, 1),
            WeightedValue.of(2, 2),
            WeightedValue.of(3, 3),
            WeightedValue.of(4, 4));
    Dice<Integer> dice = Dice.getLoadedDice(items);
    assertTrue(Arrays.asList(1, 2, 3, 4).contains(dice.roll()));
  }

  @Test
  void testEmptyArrayConstruction() {
    Dice<Integer> dice = Dice.getLoadedDice(new ArrayList<>());
    assertThrows(
        EmptyDiceException.class,
        () -> {
          dice.roll();
        });
  }

  @ParameterizedTest
  @CsvSource(value = {"1, 2", "3, 4", "4, 5"})
  void testAddSingleValue(int weight, int value) {
    LoadedDice<Integer> loadedDice = new LoadedDice<>();
    loadedDice.add(weight, value);
    assertEquals(value, loadedDice.roll());
    assertEquals(1, loadedDice.map.size());
    assertEquals(weight, loadedDice.total);
  }

  @ParameterizedTest
  @CsvSource(value = {"1, 2", "3, 4", "4, 5"})
  void testValueWeighting(int weight, int value) {
    LoadedDice<Integer> loadedDice = new LoadedDice<>();
    loadedDice.add(NO_WEIGHT);
    loadedDice.add(weight, value);
    loadedDice.add(NO_WEIGHT);
    assertEquals(value, loadedDice.roll());
    assertEquals(1, loadedDice.map.size());
    assertEquals(weight, loadedDice.total);
  }

  @Test
  void testAddList() {
    List<Integer> ls = IntStream.rangeClosed(1, 3).mapToObj((i) -> i).collect(Collectors.toList());
    LoadedDice<Integer> loadedDice = new LoadedDice<>();
    loadedDice.add(ls.stream().map((i) -> WeightedValue.of(i, i)).collect(Collectors.toList()));
    assertTrue(ls.contains(loadedDice.roll()));
    assertEquals(3, loadedDice.map.size());
    assertEquals(6, loadedDice.total);
  }

  @Test
  void testAddCompleteDice() {
    List<Integer> ls = IntStream.rangeClosed(1, 3).mapToObj((i) -> i).collect(Collectors.toList());
    LoadedDice<Integer> loadedDice = new LoadedDice<>();
    loadedDice.add(ls.stream().map((i) -> WeightedValue.of(i, i)).collect(Collectors.toList()));
    dice.addAll(loadedDice);
    dice.add(4, 4);
    assertEquals(4, dice.map.size());
    assertEquals(10, dice.total);
    Mockito.when(random.nextDouble()).thenReturn(.75d);
    assertEquals(4, dice.roll());
  }

  @ParameterizedTest
  @CsvSource(value = {"0.1,  1", "0.25, 2", "0.5,  3"})
  void testValueWeightingLogic(double rnd, int expectedResult) {
    dice.add(NO_WEIGHT);
    dice.add(1, 1);
    dice.add(2, 2);
    dice.add(3, 3);
    Mockito.when(random.nextDouble()).thenReturn(rnd);
    assertEquals(expectedResult, dice.roll());
  }
}
