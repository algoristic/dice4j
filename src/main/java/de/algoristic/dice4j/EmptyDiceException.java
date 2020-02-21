package de.algoristic.dice4j;

public class EmptyDiceException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public EmptyDiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
