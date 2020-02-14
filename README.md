# dice4j

## Overview
**dice4j** is a lightweight collection of utilities for the purpose of choosing random values from a given set of values. I found the image of rolling an _n_-sided dice quite fitting for this. Right now there are two features:

1. `Traditional dice`: Retrieve values from an equally distributed set of values.
1. `Loaded dice`: Retrieve values from a set of weighted values.

## Code examples
### Traditional dice
```java
// instantiate by static method...
List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6);
Dice<Integer> dice = Dice.getTraditionalDice(values);
// ...and retrieve a value
int value = dice.roll();

// ...or do it more classic...
Dice<Integer> anotherDice =
    new TraditionalDice<Integer>()
        .add(1)
        .add(2)
        .add(3)
        .add(4)
        .add(5)
        .add(6);

// ...that works also fine with stream api
Dice<Integer> traditionalDice =
    IntStream.rangeClosed(1, 6)
        .mapToObj((i) -> i)
        .collect(Dice.toTraditionalDice());

// ...of course you can also stream the dice to retrieve a potentially infinite stream of random values
List<Integer> values =
    traditionalDice.stream()
        .limit(10)
        .collect(Collectors.toList());
```
### Loaded Dice
The `LoadedDice` is a little bit less straightforward, since it deals with values that have a weight assigned to it. To handle this, it can only be constructed out of objects, that implement the `WeightedValue`-interface.
```java
int weight = 1, value = 2;
WeightedValue<Integer> val = WeightedValue.of(weight, value);
```
So you can also imagine a structure like this:
```java
class Person implements WeightedValue<Person> {
    private String name;
    private Date birthday;
    private double height;
    private double weight;

    @Override
    public double getWeight() {
      return weight;
    }

    @Override
    public Person getValue() {
      return this;
    }
  }

  Supplier<Person> personFactory = //whatever...
  Dice<Person> dice =
    Stream.generate(personFactory)
        .limit(100)
        .collect(Dice.toLoadedDice());
```
