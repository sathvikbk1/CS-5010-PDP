package calculator;

/**
 * This is the interface of the calculator program.
 */

public interface Calculator {
  /**
   * It contains two of the main methods,
   * the input method and the getResult method.
   *
   * @param op is the input character.
   * @return it returns a character.
   */
  Calculator input(char op);

  /**
   * the getResult is used to get the current result.
   *
   * @return it returns a string of the sequence of operations.
   */
  String getResult();
}
