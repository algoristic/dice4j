package de.algoristic.dice4j;

final class DefaultWeightedRandom<T> implements WeightedRandom<T> {

  final double weight;
  final T value;

  public DefaultWeightedRandom(final double weight, final T value) {
    this.weight = weight;
    this.value = value;
  }

  @Override
  public double getWeight() {
    return weight;
  }

  @Override
  public T getValue() {
    return value;
  }
}
