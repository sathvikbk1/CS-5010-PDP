package calculator;

/**
 * This is the abstract class of the program.
 */

public abstract class AbstractCalculator implements Calculator {

  protected String exp1;
  protected String exp2;
  protected String operator;

  protected Character prev;

  /**
   * This is the Abstract class of the program, abstraction is used by the SmartCalculator
   * and SimpleCalculator classes from this class.
   *
   * @param exp1     is used to store the first expression
   * @param exp2     is used to store the second expression
   * @param operator is used to store the operator
   * @throws IllegalArgumentException throws an exception
   */

  AbstractCalculator(String exp1, String exp2, String operator, Character prev)
          throws IllegalArgumentException {
    this.exp1 = exp1;
    this.exp2 = exp2;
    this.operator = operator;
    this.prev = prev;
  }

  @Override
  public String getResult() {
    return exp1 + operator + exp2;
  }

  protected String getExpOne() {
    return this.exp1;
  }

  protected String getExpTwo() {
    return this.exp2;
  }

  protected String getOperator() {
    return this.operator;
  }

  protected boolean isExpOneEmpty() {
    return this.exp1.isEmpty();
  }

  protected boolean isExpTwoEmpty() {
    return this.exp2.isEmpty();
  }

  protected boolean isOperatorEmpty() {
    return this.operator.isEmpty();
  }

  protected String checkinput(String exp, Character c) {
    String operand = exp + c;

    if (Long.parseLong(operand) > Integer.MAX_VALUE || Long.parseLong(operand)
            < Integer.MIN_VALUE) {
      throw new IllegalArgumentException("");
    }
    return operand;
  }
}
