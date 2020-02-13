package de.algoristic.dice4j;

public class WeightedDice<T> extends AbstractDice<T> {

  @Override
  public WeightedDice<T> add(double weight, T value) {
    return (WeightedDice<T>) super.add(weight, value);
  }

  @Override
  protected WeightedDice<T> add(WeightedRandom<T> rnd) {
    return (WeightedDice<T>) super.add(rnd);
  }

  public WeightedDice<T> addAll(WeightedDice<T> other) {
    return (WeightedDice<T>) super.addAll(other);
  }
}
