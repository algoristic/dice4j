package de.algoristic.dice4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

abstract class AbstractDice<T> implements Dice<T> {

  protected final Random rnd;
  protected final NavigableMap<Double, T> map = new TreeMap<Double, T>();
  protected double total = 0d;

  private final List<WeightedValue<T>> rawValues = new ArrayList<>();

  protected AbstractDice() {
    this(new Random());
  }

  protected AbstractDice(Random rnd) {
    this.rnd = rnd;
  }

  protected AbstractDice<T> add(final double weight, final T value) {
    if (weight > 0) {
      total += weight;
      map.put(total, value);
      rawValues.add(new DefaultWeightedValue<T>(weight, value));
    }
    return this;
  }

  protected AbstractDice<T> add(final WeightedValue<T> value) {
    double weight = value.getWeight();
    T val = value.getValue();
    return add(weight, val);
  }

  protected AbstractDice<T> addAll(final List<WeightedValue<T>> values) {
    values.forEach(this::add);
    return this;
  }

  protected AbstractDice<T> addAll(final Dice<T> other) {
    for (WeightedValue<T> rnd : other) {
      this.add(rnd);
    }
    return this;
  }

  @Override
  public T roll() {
    double rndValue = rnd.nextDouble() * total;
    try {
      return map.higherEntry(rndValue).getValue();
    } catch (NullPointerException e) {
      throw new EmptyDiceException("the dice contains no values; add values before rolling", e);
    }
  }

  @Override
  public Iterator<WeightedValue<T>> iterator() {
    return rawValues.iterator();
  }
}
