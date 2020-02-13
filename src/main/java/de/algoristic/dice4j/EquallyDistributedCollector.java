package de.algoristic.dice4j;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

final class EquallyDistributedCollector<T>
    implements Collector<T, EquallyDistributedDice<T>, Dice<T>> {

  @Override
  public Supplier<EquallyDistributedDice<T>> supplier() {
    return EquallyDistributedDice::new;
  }

  @Override
  public BiConsumer<EquallyDistributedDice<T>, T> accumulator() {
    return (dice, value) -> dice.add(value);
  }

  @Override
  public BinaryOperator<EquallyDistributedDice<T>> combiner() {
    return (left, right) -> left.addAll(right);
  }

  @Override
  public Function<EquallyDistributedDice<T>, Dice<T>> finisher() {
    return (dice) -> dice;
  }

  @Override
  public Set<Characteristics> characteristics() {
    return Collections.singleton(Characteristics.UNORDERED);
  }
}
