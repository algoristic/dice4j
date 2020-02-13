package de.algoristic.dice4j;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

final class WeightedCollector<T> implements Collector<WeightedRandom<T>, WeightedDice<T>, Dice<T>> {

  @Override
  public Supplier<WeightedDice<T>> supplier() {
    return WeightedDice::new;
  }

  @Override
  public BiConsumer<WeightedDice<T>, WeightedRandom<T>> accumulator() {
    return (dice, rnd) -> dice.add(rnd);
  }

  @Override
  public BinaryOperator<WeightedDice<T>> combiner() {
    return (left, right) -> left.addAll(right);
  }

  @Override
  public Function<WeightedDice<T>, Dice<T>> finisher() {
    return (dice) -> dice;
  }

  @Override
  public Set<Characteristics> characteristics() {
    return Collections.singleton(Characteristics.UNORDERED);
  }
}
