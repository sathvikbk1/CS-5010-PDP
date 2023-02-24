package bignumber;

import java.util.Scanner;

/**
 * This class of the program implements the BigNumber interface.
 */

public class BigNumberImpl implements BigNumber {
  public Digit head;

  /**
   * The following is the constructor which takes in a string argument for input.
   *
   * @param input is of type string and takes in the input
   */
  public BigNumberImpl(String input) {
    head = new Digit(0);
    Scanner scan = new Scanner(input).useDelimiter("");
    while (scan.hasNext()) {
      shiftLeft(1);
      int digit = scan.next().toCharArray()[0] - '0';
      if (digit < 0) {
        throw new IllegalArgumentException("input should not be less than 0");
      } else if (digit > 9) {
        throw new IllegalArgumentException("input should be less than 10");
      }
      this.addDigit(digit);
    }
  }

  public BigNumberImpl() {
    this("0");
  }

  @Override
  public int length() {
    return this.count(head);
  }

  private int count(Digit d) {
    if (d == null) {
      return 0;
    }
    return count(d.next) + 1;
  }

  @Override
  public void shiftLeft(int noOfShifts) {
    if (noOfShifts == 0) {
      return;
    }
    if (noOfShifts < 0) {
      this.shiftRight(noOfShifts * -1);
      return;
    }
    if (this.head.value == 0 && this.head.next == null) {
      return;
    }
    Digit head = new Digit(0);
    head.next = this.head;
    this.head = head;
    this.head.next.prev = this.head;
    this.shiftLeft(noOfShifts - 1);
  }

  @Override
  public void shiftRight(int noOfShifts) {
    if (noOfShifts == 0) {
      return;
    }
    if (noOfShifts < 0) {
      this.shiftLeft(noOfShifts * -1);
      return;
    }
    if (this.head.next == null) {
      this.head.value = 0;
      return;
    }
    this.head = head.next;
    this.head.prev = null;
    this.shiftRight(noOfShifts - 1);
  }

  @Override
  public void addDigit(int digit) {
    if (digit < 0 || digit > 9) {
      throw new IllegalArgumentException("Input should be between 0 and 9");
    }
    addDigit(head, digit);
  }

  private void addDigit(Digit pointer, int value) {
    int result = pointer.value + value;
    if (result < 10) {
      pointer.value = result;
      return;
    }
    pointer.value = result % 10;
    if (pointer.next == null) {
      pointer.next = new Digit(0);
    }
    addDigit(pointer.next, 1);
  }

  @Override
  public int getDigitAt(int index) {
    if (index < 0) {
      throw new IllegalArgumentException("Invalid index");
    }
    return getDigitAt(head, index);
  }

  private int getDigitAt(Digit pointer, int counter) {
    if (pointer == null) {
      return 0;
    }
    if (counter == 0) {
      return pointer.value;
    }
    return getDigitAt(pointer.next, counter - 1);
  }

  @Override
  public BigNumber copy() {
    return new BigNumberImpl(this.toString());
  }

  private void addHelper(Digit p1, Digit p2, Digit acc) {
    int result = p1.value + p2.value + acc.value;
    acc.value = result % 10;
    if (p1.next == null && p2.next == null) {
      if (result > 9) {
        acc.next = new Digit(1);
      }
      return;
    }
    acc.next = new Digit(result > 9 ? 1 : 0);
    if (p1.next == null) {
      addHelper(new Digit(0), p2.next, acc.next);
    } else if (p2.next == null) {
      addHelper(p1.next, new Digit(0), acc.next);
    } else {
      addHelper(p1.next, p2.next, acc.next);
    }
  }

  @Override
  public BigNumber add(BigNumber number) {
    BigNumberImpl result = new BigNumberImpl();
    addHelper(this.head, ((BigNumberImpl) number).head, result.head);
    return result;
  }

  private int compareHelper(Digit p1, Digit p2) {
    if (p1.next == null && p2.next == null) {
      return compareByDigit(p1, p2);
    } else if (p1.next == null) {
      return -1;
    } else if (p2.next == null) {
      return 1;
    } else {
      return compareHelper(p1.next, p2.next);
    }
  }

  private int compareByDigit(Digit p1, Digit p2) {
    if (p1.value > p2.value) {
      return 1;
    } else if (p1.value < p2.value) {
      return -1;
    }
    if (p1.prev == null && p2.prev == null) {
      return 0;
    }
    if (p1.prev == null) {
      throw new IllegalStateException("Invalid State");
    }
    return compareByDigit(p1.prev, p2.prev);
  }

  @Override
  public int compareTo(BigNumber input) {
    return compareHelper(this.head, ((BigNumberImpl) input).head);
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    Digit temp = head;
    while (temp != null) {
      result = new StringBuilder().append(temp.value).append(result);
      temp = temp.next;
    }
    return result.toString();
  }

  private static class Digit {
    private Digit prev;
    private Digit next;

    private int value;

    Digit(int value) {
      this.value = value;
      this.prev = null;
      this.next = null;
    }

  }


}
