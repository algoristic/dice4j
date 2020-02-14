package de.algoristic.dice4j;

public final class TraditionalDice<T> extends AbstractDice<T> {

  public TraditionalDice<T> add(final T value) {
    return (TraditionalDice<T>) super.add(1, value);
  }

  public TraditionalDice<T> addAll(final TraditionalDice<T> other) {
    return (TraditionalDice<T>) super.addAll(other);
  }
}
