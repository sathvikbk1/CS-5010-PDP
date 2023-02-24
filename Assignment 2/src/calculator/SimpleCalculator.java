// for 1+2=4  should reset
// for double operator throw exception

package calculator;

/**
 * This is the SimpleCalculator class of the program.
 * This class inherits the parent class through abstraction which is the AbstractCalculator.
 */

public class SimpleCalculator extends AbstractCalculator {

  private SimpleCalculator(String exp1, String exp2, String operator, Character prev)
          throws IllegalArgumentException {
    super(exp1, exp2, operator, prev);
  }

  /**
   * The following is a constructor of SimpleCalculator it passes the three parameters:
   * exp1, exp2 and operator.
   */
  public SimpleCalculator() {
    super("", "", "", 'q');
  }

  /**
   * This constructor inherits the attributes through super from the parent class.
   *
   * @param ch is the input character.
   */

  @Override
  public SimpleCalculator input(char ch) {
    // Used to clear the result
    if ((ch == 'C')) {
      return new SimpleCalculator();
    }

    // Exception if first input is not a digit
    else if ((ch == '+' || ch == '-' || ch == '*' || ch == '=') && this.isExpOneEmpty()
            && !Character.isDigit(ch)) {
      throw new IllegalArgumentException("");
    }

    // When the prev. is = and new is number
    else if (prev == '=') {
      if (Character.isDigit(ch)) {
        return new SimpleCalculator(Character.toString(ch), "", "", ch);
      } else if (ch == '+' || ch == '-' || ch == '*') {
        return new SimpleCalculator(this.getExpOne(), "", Character.toString(ch), ch);
      } else {
        return this;
      }
    }

    //clear output after '=' is called
    else if (Character.isDigit(ch) && !this.isExpOneEmpty() && this.operator.equals("=")) {
      return new SimpleCalculator(Character.toString(ch), "", "", ch);
    }

    // Populate the first expression
    else if (Character.isDigit(ch) && this.isExpTwoEmpty() && this.isOperatorEmpty()) {
      String inp = this.checkinput(this.getExpOne(), ch);
      return new SimpleCalculator(inp, "", "", ch);
    }

    //Populate the operation
    else if ((ch == '+' || ch == '-' || ch == '*') && this.isExpTwoEmpty()
            && this.isOperatorEmpty()) {
      return new SimpleCalculator(this.getExpOne(), "", Character.toString(ch), ch);
    }

    //Do this after an equal to operation
    else if ((ch == '+' || ch == '-' || ch == '*') && this.isExpTwoEmpty()
            && this.operator.equals("=")) {
      return new SimpleCalculator(this.getExpOne(), "", Character.toString(ch), ch);
    } else if (Character.isDigit(ch) && this.operator.equals('=')) {
      return new SimpleCalculator(Character.toString(ch), "", "", ch);
    }

    //Populate the second digit
    else if (Character.isDigit(ch) && !this.isExpOneEmpty() && !this.isOperatorEmpty()) {
      String inp = this.checkinput(this.getExpTwo(), ch);
      return new SimpleCalculator(this.getExpOne(), inp, this.getOperator(), ch);
    }

    //Calling calculate if exp1 exp2 and operation is already populated
    else if (!this.isExpOneEmpty() && !this.isExpTwoEmpty() && !this.isOperatorEmpty()
            && (ch == '+' || ch == '-' || ch == '*')) {
      String result = calculate();
      return new SimpleCalculator(result, "", String.valueOf(ch), ch);
    }

    // Conditions when input is '='
    else if (ch == '=') {
      //Calling calculate if exp1 exp2 and operation is already populated
      if (!this.isExpOneEmpty() && !this.isExpTwoEmpty() && !this.isOperatorEmpty()) {
        String result = calculate();
        return new SimpleCalculator(result, "", "", ch);
      } else {
        return new SimpleCalculator(this.getExpOne(), this.getExpTwo(), this.operator, this.prev);
      }
    }

    // throws an exception for any other input
    throw new IllegalArgumentException("");
  }

  private String calculate() {
    long result;
    switch (this.getOperator()) {
      case "+":
        result = Long.parseLong(this.getExpOne()) + Long.parseLong(this.getExpTwo());
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
          result = 0;
        }
        break;
      case "-":
        result = Long.parseLong(this.getExpOne()) - Long.parseLong(this.getExpTwo());
        if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) {
          result = 0;
        }
        break;
      case "*":
        result = Long.parseLong(this.getExpOne()) * Long.parseLong(this.getExpTwo());
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
          result = 0;
        }
        break;
      default:
        throw new IllegalStateException("Invalid operator");
    }
    return Long.toString(result);
  }
}


