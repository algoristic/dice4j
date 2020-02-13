package de.algoristic.dice4j;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

  public static <T> Dice<T> getNormalDice(Stream<T> items) {
    return items.collect(toNormalDice());
  }

  public static <T> Dice<T> getNormalDice(List<T> items) {
    return getNormalDice(items.stream());
  }

  public static <T> Dice<T> getNormalDice(T[] items) {
    return getNormalDice(Arrays.asList(items));
  }

  public static <T> Collector<T, ?, Dice<T>> toNormalDice() {
    return new EquallyDistributedCollector<T>();
  }

  public static <T> Dice<T> getWeightedDice(Stream<WeightedRandom<T>> items) {
    return items.collect(toWeightedDice());
  }

  public static <T> Dice<T> getWeightedDice(Map<Double, T> weightMap) {
    return getWeightedDice(weightMap.entrySet().stream().map(WeightedRandom::of));
  }

  public static <T> Dice<T> getWeightedDice(List<WeightedRandom<T>> items) {
    return getWeightedDice(items.stream());
  }

  public static <T> Dice<T> getWeightedDice(WeightedRandom<T>[] items) {
    return getWeightedDice(Arrays.asList(items));
  }

  public static <T> Collector<WeightedRandom<T>, ?, Dice<T>> toWeightedDice() {
    return new WeightedCollector<T>();
  }
}
