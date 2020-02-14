package de.algoristic.dice4j;

import java.util.Map.Entry;

public interface WeightedValue<T> {

  public double getWeight();

  public T getValue();

  public static <T> WeightedValue<T> of(final Entry<Double, T> entry) {
    double weight = entry.getKey();
    T value = entry.getValue();
    return of(weight, value);
  }

  public static <T> WeightedValue<T> of(final double weight, final T value) {
    return new DefaultWeightedValue<>(weight, value);
  }
}
