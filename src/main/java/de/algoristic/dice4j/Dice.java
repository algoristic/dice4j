package de.algoristic.dice4j;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

public interface Dice<T> extends Iterable<WeightedRandom<T>> {

  T roll();

  default Stream<T> stream() {
    return Stream.generate(this.supplyValue());
  }

  default Supplier<T> supplyValue() {
    return () -> this.roll();
  }

  public static <T> Dice<T> getTraditionalDice(Stream<T> items) {
    return items.collect(toTraditionalDice());
  }

  public static <T> Dice<T> getTraditionalDice(Set<T> items) {
    return getTraditionalDice(items.stream());
  }

  public static <T> Dice<T> getTraditionalDice(List<T> items) {
    return getTraditionalDice(items.stream());
  }

  public static <T> Dice<T> getTraditionalDice(T[] items) {
    return getTraditionalDice(Arrays.asList(items));
  }

  public static <T> Collector<T, ?, Dice<T>> toTraditionalDice() {
    return new TraditionalDiceCollector<T>();
  }

  public static <T> Dice<T> getLoadedDice(Stream<WeightedRandom<T>> items) {
    return items.collect(toLoadedDice());
  }

  public static <T> Dice<T> getLoadedDice(Map<Double, T> weightMap) {
    return getLoadedDice(weightMap.entrySet().stream().map(WeightedRandom::of));
  }

  public static <T> Dice<T> getLoadedDice(Set<WeightedRandom<T>> items) {
    return getLoadedDice(items.stream());
  }

  public static <T> Dice<T> getLoadedDice(List<WeightedRandom<T>> items) {
    return getLoadedDice(items.stream());
  }

  public static <T> Dice<T> getLoadedDice(WeightedRandom<T>[] items) {
    return getLoadedDice(Arrays.asList(items));
  }

  public static <T> Collector<WeightedRandom<T>, ?, Dice<T>> toLoadedDice() {
    return new LoadedDiceCollector<T>();
  }
}
