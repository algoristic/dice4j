package de.algoristic.dice4j;

public final class LoadedDice<T> extends AbstractDice<T> {

  @Override
  public LoadedDice<T> add(final double weight, final T value) {
    return (LoadedDice<T>) super.add(weight, value);
  }

  @Override
  public LoadedDice<T> add(final WeightedRandom<T> rnd) {
    return (LoadedDice<T>) super.add(rnd);
  }

  public LoadedDice<T> addAll(final LoadedDice<T> other) {
    return (LoadedDice<T>) super.addAll(other);
  }
}
