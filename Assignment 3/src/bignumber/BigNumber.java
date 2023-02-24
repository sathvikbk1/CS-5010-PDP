package bignumber;

/**
 * This the interface of the program.
 */

public interface BigNumber {
  /**
   * In the interface we implement the following.
   * length is used to find the lenght of the bignumber.
   */

  int length();

  /**
   * The function shiftleft adds a zero to the bignumber, effectively multiplying it by 10.
   *
   * @param noofshifts is used to store the no of shifts to be performed
   */

  void shiftLeft(int noofshifts);

  /**
   * The function shiftRight adds a zero to the bignumber, effectively multiplying it by 10.
   *
   * @param noofshifts is used to store the no of shifts to be performed
   */

  void shiftRight(int noofshifts);

  /**
   * The function addDigit adds a number to the existing number.
   *
   * @param number holds the digit to be inserted
   * @throws IllegalArgumentException will be thrown if the entered number is invalid
   */

  void addDigit(int number) throws IllegalArgumentException;

  /**
   * The function returns the digit which is present at a given location.
   *
   * @param index holds the value of the index
   * @return the digit present a particular index
   * @throws IllegalArgumentException if the entered index is invalid
   */

  int getDigitAt(int index) throws IllegalArgumentException;

  /**
   * The function will return a copy of the current number.
   *
   * @return a copy of the number
   */

  BigNumber copy();

  /**
   * The function adds to bigNumbers.
   *
   * @param number holds the second BigNumber that is to be added.
   * @return a new BigNmber which is the sum of the other two BigNumbers
   */

  BigNumber add(BigNumber number);

  /**
   * The function compares two BigNumbers to see which is greater.
   *
   * @param number holds the second BigNumber
   * @return 1 if the first one is greater,-1 if the second one is greater and 0 if both are equal.
   */

  int compareTo(BigNumber number);
}


