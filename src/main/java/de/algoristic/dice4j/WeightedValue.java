package de.algoristic.dice4j;

public interface WeightedValue<T> {

  public double getWeight();

  public T getValue();

  public static <T> WeightedValue<T> of(final double weight, final T value) {
    return new DefaultWeightedValue<>(weight, value);
  }
}
