package de.algoristic.dice4j;

import java.util.Iterator;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;

abstract class AbstractDice<T> implements Dice<T> {

  protected final Random rnd = new Random();
  protected final NavigableMap<Double, T> map = new TreeMap<Double, T>();
  protected double total;

  protected AbstractDice<T> add(final double weight, final T value) {
    if (weight > 0) {
      total += weight;
      map.put(total, value);
    }
    return this;
  }

  protected AbstractDice<T> add(final WeightedRandom<T> rnd) {
    double weight = rnd.getWeight();
    T value = rnd.getValue();
    return add(weight, value);
  }

  protected AbstractDice<T> addAll(final Dice<T> other) {
    for (WeightedRandom<T> rnd : other) {
      this.add(rnd);
    }
    return this;
  }

  @Override
  public T roll() {
    double rndValue = rnd.nextDouble() * total;
    return map.higherEntry(rndValue).getValue();
  }

  @Override
  public Iterator<WeightedRandom<T>> iterator() {
    return map.entrySet().stream().map(WeightedRandom::of).collect(Collectors.toList()).iterator();
  }
}
