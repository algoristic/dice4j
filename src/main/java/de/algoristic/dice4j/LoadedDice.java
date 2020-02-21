package de.algoristic.dice4j;

import java.util.List;
import java.util.Random;

public final class LoadedDice<T> extends AbstractDice<T> {

  public LoadedDice() {
    super();
  }

  public LoadedDice(Random rnd) {
    super(rnd);
  }

  @Override
  public LoadedDice<T> add(final double weight, final T value) {
    return (LoadedDice<T>) super.add(weight, value);
  }

  @Override
  public LoadedDice<T> add(final WeightedValue<T> value) {
    return (LoadedDice<T>) super.add(value);
  }

  public LoadedDice<T> add(final List<WeightedValue<T>> values) {
    return (LoadedDice<T>) super.addAll(values);
  }

  public LoadedDice<T> addAll(final LoadedDice<T> other) {
    return (LoadedDice<T>) super.addAll(other);
  }
}
