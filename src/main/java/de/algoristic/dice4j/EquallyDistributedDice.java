package de.algoristic.dice4j;

public final class EquallyDistributedDice<T> extends AbstractDice<T> {

  public EquallyDistributedDice<T> add(final T value) {
    return (EquallyDistributedDice<T>) super.add(1, value);
  }

  public EquallyDistributedDice<T> addAll(EquallyDistributedDice<T> other) {
    return (EquallyDistributedDice<T>) super.addAll(other);
  }
}
