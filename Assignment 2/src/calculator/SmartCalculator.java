package calculator;

import java.util.Objects;

/**
 * This is the SmartCalculator class of the program.
 * This class inherits the parent class through abstraction which is the AbstractCalculator.
 */
public class SmartCalculator extends AbstractCalculator {

  private String stored;
  private String operStore;

  private SmartCalculator(String exp1, String exp2, String operator, String stored,
                          String operator2, Character prev) throws IllegalArgumentException {
    super(exp1, exp2, operator, prev);
    this.stored = stored;
    this.operStore = operator2;
  }

  /**
   * the following is a constructor of SmartCalculator.
   */
  public SmartCalculator() {
    super("", "", "", 'q');
  }

  @Override
  public SmartCalculator input(char ch) {
    // Used to clear the result
    if ((ch == 'C')) {
      return new SmartCalculator();
    }

    // Exception if first input is not a digit
    else if ((ch == '-' || ch == '*') && this.isExpOneEmpty()
            && !Character.isDigit(ch)) {
      throw new IllegalArgumentException("");
    } else if (!(ch == '+' || ch == '-' || ch == '*' || ch == '=' || Character.isDigit(ch))) {
      throw new IllegalArgumentException("");
    }

    //when first input is a +, +23
    else if (Character.isDigit(ch) && this.isExpTwoEmpty() && this.operator.equals("+")
            && this.isExpOneEmpty()) {
      String inp = this.checkinput(this.getExpOne(), ch);
      return new SmartCalculator(this.operator + inp, "", "",
              this.stored, this.operStore, ch);
    }

    // When the prev. is = and new is number
    else if (prev == '=') {
      if (Character.isDigit(ch)) {
        return new SmartCalculator(Character.toString(ch), "", "",
                "", "", ch);
      } else if (ch == '+' || ch == '-' || ch == '*') {
        return new SmartCalculator(this.getExpOne(), "", Character.toString(ch), "",
                Character.toString(ch), ch);
      } else {
        String res = calculate(this.getExpOne(), this.getExpTwo(), this.operStore, this.stored);
        return new SmartCalculator(res, "", this.operator, this.stored,
                this.operStore, this.prev);
      }
    }

    //Populate the first expression
    else if (Character.isDigit(ch) && this.isExpTwoEmpty() && this.isOperatorEmpty()) {
      String inp = this.checkinput(this.getExpOne(), ch);
      return new SmartCalculator(inp, "", "", this.stored, this.operStore, ch);
    }

    // check if the prev is an operator
    else if (prev == '+' || prev == '-' || prev == '*') {
      if (ch == '+' || ch == '-' || ch == '*') {
        return new SmartCalculator(this.getExpOne(), this.getExpTwo(), Character.toString(ch),
                this.stored, "", ch);
      } else if (Character.isDigit(ch) && !this.isExpOneEmpty() && !this.isOperatorEmpty()) {
        String inp2 = this.checkinput(this.getExpTwo(), ch);
        return new SmartCalculator(this.getExpOne(), inp2, this.getOperator(), inp2,
                this.operStore, ch);
      } else {
        String res = calculate(this.getExpOne(), this.getExpTwo(), this.operStore, this.stored);
        return new SmartCalculator(res, this.getExpTwo(), "", this.stored,
                this.operStore, this.prev);
      }
    }

    // populate the operator
    else if ((ch == '+' || ch == '-' || ch == '*')
            && this.isExpTwoEmpty()) {
      return new SmartCalculator(this.exp1,
              "", Character.toString(ch), this.getExpOne(), Character.toString(ch), ch);
    }

    // populate the second expression
    else if (Character.isDigit(ch) && !this.isExpOneEmpty() && !this.isOperatorEmpty()) {
      String inp2 = this.checkinput(this.getExpTwo(), ch);
      return new SmartCalculator(this.getExpOne(), inp2,
              this.getOperator(), inp2, this.operStore, ch);
    }

    // Calling calculate if exp1 exp2 and operation is already populated
    else if ((ch == '+' || ch == '-' || ch == '*') && !this.isExpOneEmpty()
            && !this.isOperatorEmpty() && !this.isExpTwoEmpty()) {
      String inp3 = calculate(this.getExpOne(), this.getExpTwo(), this.getOperator(), this.stored);
      return new SmartCalculator(inp3, "", Character.toString(ch),
              inp3, Character.toString(ch), ch);
    }
    //call calculate when another operator is called
    else if ((ch == '+' || ch == '-' || ch == '*') && this.isOperatorEmpty()
            && !this.isExpOneEmpty() && this.isExpTwoEmpty()) {
      return new SmartCalculator(this.getExpOne(), this.getExpTwo(),
              Character.toString(ch), this.stored, Character.toString(ch), ch);
    } else if (ch == '=') {
      String res;
      if (Objects.equals(this.getExpTwo(), "") && !this.stored.equals("")) {
        res = calculate(this.getExpOne(), this.getExpTwo(), this.operStore, this.stored);
      } else if (Objects.equals(this.getExpTwo(), "")) {
        res = calculate(this.getExpOne(), this.getExpOne(), this.operStore, this.stored);
      }

      // call calculate
      else {
        res = calculate(this.getExpOne(), this.getExpTwo(), this.getOperator(), this.stored);
        return new SmartCalculator(res, "", "",
                this.getExpTwo(), this.operStore, ch);
      }
      return new SmartCalculator(res, "", "", this.stored, this.operStore, ch);
    }

    // calling calculate when all variables are populated
    else if (!this.isExpOneEmpty() && !this.isExpTwoEmpty() && !this.isOperatorEmpty()) {
      String result = calculate(this.getExpOne(), this.getExpTwo(),
              this.getOperator(), this.stored);
      return new SmartCalculator(result, "", "", this.stored, this.operStore, ch);
    }

    // Exception cases
    else if (!this.isExpTwoEmpty() && this.isOperatorEmpty()) {
      if ((ch == '+' || ch == '-' || ch == '*') && Objects.equals(this.getExpOne(), "")) {
        throw new IllegalArgumentException("invalid input cannot start with an operator");
      }
    }
    throw new IllegalArgumentException("");
  }

  private String calculate(String exp1, String exp2, String operator, String stored)
          throws IllegalArgumentException {
    String calcExp2;
    long result;
    if (!exp2.equals("")) {
      calcExp2 = exp2;
    } else {
      calcExp2 = stored;
    }
    switch (operator) {
      case "+":
        result = Long.parseLong(exp1) + Long.parseLong(calcExp2);
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
          result = 0;
        }
        return Long.toString(result);
      case "-":
        result = Long.parseLong(exp1) - Long.parseLong(calcExp2);
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
          result = 0;
        }
        return Long.toString(result);
      case "*":
        result = Long.parseLong(exp1) * Long.parseLong(calcExp2);
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
          result = 0;
        }
        return Long.toString(result);

      default:
        throw new IllegalArgumentException("");
    }
  }
}