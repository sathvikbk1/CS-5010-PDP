import org.junit.Before;
import org.junit.Test;

import calculator.Calculator;
import calculator.SimpleCalculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is the test class for the Simple Calculator.
 */

public class SimpleCalctest {
  Calculator s1;
  Calculator s2;

  @Before
  public void setup() {
    s1 = new SimpleCalculator();
    s2 = new SimpleCalculator();
  }


  @Test
  public void testDouble() {
    boolean b = false;
    try {
      s1 = s1.input('3');
      s1 = s1.input('2');
      s1 = s1.input('+');
      s1 = s1.input('=');
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);
  }

  @Test
  public void equal() {
    boolean b = false;
    try {
      s1 = s1.input('=');
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);
  }

  @Test
  public void digitEqual() {
    s1 = s1.input('3');
    s1 = s1.input('=');
    assertEquals("3", s1.getResult());
  }

  @Test
  public void addition() {
    s1 = s1.input('1');
    s1 = s1.input('2');
    s1 = s1.input('+');
    s1 = s1.input('1');
    s1 = s1.input('2');
    s1 = s1.input('=');
    assertEquals("24", s1.getResult());
  }

  @Test
  public void MultiEqual() {
    s1 = s1.input('1');
    s1 = s1.input('2');
    s1 = s1.input('+');
    s1 = s1.input('1');
    s1 = s1.input('2');
    s1 = s1.input('=');
    s1 = s1.input('=');
    s1 = s1.input('=');
    assertEquals("24", s1.getResult());
  }

  @Test
  public void firstExpression() {
    boolean b = false;
    try {
      s1 = s1.input('+');
      s1 = s1.input('2');
      s1 = s1.input('+');
      s1 = s1.input('2');
      s1 = s1.input('=');
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);
  }

  @Test
  public void EqualAfteroperation() {
    s1 = s1.input('3');
    s1 = s1.input('+');
    s1 = s1.input('3');
    s1 = s1.input('=');
    s1 = s1.input('3');
    assertEquals("3", s1.getResult());
  }

  @Test
  public void doubleOperator() {
    boolean b = false;
    try {
      s1 = s1.input('3');
      s1 = s1.input('3');
      s1 = s1.input('-');
      s1 = s1.input('+');
      s1 = s1.input('3');
      s1 = s1.input('=');
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);

  }

  @Test
  public void clearCalled() {
    s1 = s1.input('3');
    s1 = s1.input('2');
    s1 = s1.input('+');
    s1 = s1.input('4');
    s1 = s1.input('3');
    s1 = s1.input('C');
    assertEquals("", s1.getResult());
  }

  @Test
  public void zeroFollowedByDigit() {
    s1 = s1.input('0');
    s1 = s1.input('0');
    s1 = s1.input('0');
    s1 = s1.input('2');
    s1 = s1.input('+');
    s1 = s1.input('0');
    s1 = s1.input('6');
    s1 = s1.input('=');
    assertEquals("8", s1.getResult());
  }

  @Test
  public void plusInTheBeginning() {
    boolean b = false;
    try {
      s1 = s1.input('+');
      s1 = s1.input('3');
      s1 = s1.input('2');
      s1 = s1.input('+');
      s1 = s1.input('3');
      s1 = s1.input('=');
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);
  }

  @Test
  public void NooverflowCheck() {
    s1 = s1.input('2');
    s1 = s1.input('1');
    s1 = s1.input('4');
    s1 = s1.input('7');
    s1 = s1.input('4');
    s1 = s1.input('8');
    s1 = s1.input('3');
    s1 = s1.input('6');
    s1 = s1.input('4');
    s1 = s1.input('7');
    assertEquals("2147483647", s1.getResult());
  }

  @Test
  public void overflowCheckMultiply() {

    s1 = s1.input('9');
    s1 = s1.input('9');
    s1 = s1.input('9');
    s1 = s1.input('9');
    s1 = s1.input('9');
    s1 = s1.input('*');
    s1 = s1.input('9');
    s1 = s1.input('9');
    s1 = s1.input('9');
    s1 = s1.input('9');
    s1 = s1.input('9');
    s1 = s1.input('=');
    assertEquals("0", s1.getResult());

  }


  @Test
  public void overflowCheck1() {
    boolean b = false;
    try {
      s1 = s1.input('2');
      s1 = s1.input('1');
      s1 = s1.input('4');
      s1 = s1.input('7');
      s1 = s1.input('4');
      s1 = s1.input('8');
      s1 = s1.input('3');
      s1 = s1.input('6');
      s1 = s1.input('4');
      s1 = s1.input('8');
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);
  }

  @Test
  public void overflowCheck2() {
    s1 = s1.input('1');
    s1 = s1.input('+');
    s1 = s1.input('2');
    s1 = s1.input('1');
    s1 = s1.input('4');
    s1 = s1.input('7');
    s1 = s1.input('4');
    s1 = s1.input('8');
    s1 = s1.input('3');
    s1 = s1.input('6');
    s1 = s1.input('4');
    s1 = s1.input('7');
    s1 = s1.input('=');
    assertEquals("0", s1.getResult());

  }

  @Test
  public void overflowCheck3() {
    boolean b = false;
    try {
      s1 = s1.input('2');
      s1 = s1.input('+');
      s1 = s1.input('2');
      s1 = s1.input('1');
      s1 = s1.input('4');
      s1 = s1.input('7');
      s1 = s1.input('4');
      s1 = s1.input('8');
      s1 = s1.input('3');
      s1 = s1.input('6');
      s1 = s1.input('4');
      s1 = s1.input('8');
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);
  }


  @Test
  public void overflowCheck5() {

    s1 = s1.input('1');
    s1 = s1.input('+');
    s1 = s1.input('2');
    s1 = s1.input('1');
    s1 = s1.input('4');
    s1 = s1.input('7');
    s1 = s1.input('4');
    s1 = s1.input('8');
    s1 = s1.input('3');
    s1 = s1.input('6');
    s1 = s1.input('4');
    s1 = s1.input('7');
    s1 = s1.input('-');
    s1 = s1.input('1');
    s1 = s1.input('0');
    s1 = s1.input('=');

    assertEquals("-10", s1.getResult());
  }

  @Test
  public void overflowCheck6() {
    boolean b = false;
    try {
      s1.input('0');
      s1.input('-');
      s1.input('2');
      s1.input('1');
      s1.input('4');
      s1.input('7');
      s1.input('4');
      s1.input('8');
      s1.input('3');
      s1.input('6');
      s1.input('4');
      s1.input('7');
      s1.input('-');
      s1.input('1');
      s1.input('0');
      s1.input('+');
      s1.input('1');
      s1.input('0');
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);
  }

  @Test
  public void overflowCheck7() {
    boolean b = false;
    try {
      s1.input('-');
      s1.input('2');
      s1.input('1');
      s1.input('4');
      s1.input('7');
      s1.input('4');
      s1.input('8');
      s1.input('3');
      s1.input('6');
      s1.input('4');
      s1.input('7');
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);
  }

  @Test
  public void additionMultipleValues() {
    s1.input('2');
    s1.input('4');
    s1.input('+');
    s1.input('5');
    s1.input('+');
    s1.input('1');
    s1.input('=');

    assertEquals("30", s1.getResult());
  }

  @Test
  public void doubleOperatorException() {
    boolean b = false;
    try {
      s1.input('2');
      s1.input('1');
      s1.input('+');
      s1.input('+');

    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);
  }

  @Test
  public void doubleEqual() {
    s1.input('3');
    s1.input('=');
    s1.input('=');
    assertEquals("3", s1.getResult());
  }


  @Test
  public void MultiEquals() {
    s1.input('3');
    s1.input('3');
    s1.input('+');
    s1.input('2');
    s1.input('=');
    s1.input('=');
    s1.input('=');
    s1.input('=');
    assertEquals("35", s1.getResult());
  }

  @Test
  public void AdditionandSubtraction() {
    s1 = s1.input('3');
    s1 = s1.input('5');
    s1 = s1.input('+');
    s1 = s1.input('5');
    s1 = s1.input('-');
    s1 = s1.input('1');
    s1 = s1.input('0');
    s1 = s1.input('=');
    assertEquals("30", s1.getResult());
  }

  @Test
  public void Equalsto1() {
    s1 = s1.input('3');
    s1 = s1.input('+');
    s1 = s1.input('3');
    s1 = s1.input('=');
    s1 = s1.input('3');
    s1 = s1.input('=');
    assertEquals("3", s1.getResult());
  }

  @Test
  public void Equalsto2() {
    boolean b = false;
    try {
      s1 = s1.input('=');
      s1 = s1.input('3');
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);
  }

  @Test
  public void Exception() {
    boolean b = false;
    try {
      s1.input('3');
      s1.input('=');
      s1.input('-');
      s1.input('2');
      s1.input('1');
      s1.input('4');
      s1.input('7');
      s1.input('4');
      s1.input('8');
      s1.input('3');
      s1.input('6');
      s1.input('4');
      s1.input('8');
      assertEquals('3', s1.getResult());
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);
  }

  @Test
  public void Exception2() {
    boolean b = false;
    try {
      s1 = s1.input('-');
      s1 = s1.input('3');
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);
  }

  @Test
  public void Exception3() {
    boolean b = false;
    try {
      s1 = s1.input('*');
      s1 = s1.input('3');
      assertEquals('3', s1.getResult());
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);
  }

  @Test
  public void Exception4() {
    boolean b = false;
    try {
      s1 = s1.input('2');
      s1 = s1.input('+');
      s1 = s1.input('-');
      s1 = s1.input('*');
      s1 = s1.input('3');
      assertEquals('3', s1.getResult());
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);
  }

  @Test
  public void Exception5() {
    boolean b = false;
    try {
      s1 = s1.input('3');
      s1 = s1.input('+');
      s1 = s1.input('=');
      s1 = s1.input('*');
      s1 = s1.input('3');
      s1 = s1.input('=');
      assertEquals('3', s1.getResult());
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);
  }

  @Test
  public void Exception6() {
    boolean b = false;
    try {
      s1 = s1.input('3');
      s1 = s1.input('2');
      s1 = s1.input('+');
      s1 = s1.input('-');
      assertEquals('3', s1.getResult());
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);
  }

  @Test
  public void Exception7() {
    boolean b = false;
    try {
      s1 = s1.input('+');
      s1 = s1.input('-');
      s1 = s1.input('+');
      s1 = s1.input('2');
      s1 = s1.input('-');
      s1 = s1.input('1');
      s1 = s1.input('6');
      s1 = s1.input('=');
      assertEquals('3', s1.getResult());
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);
  }

  @Test
  public void Exception8() {
    boolean b = false;
    try {
      s1 = s1.input('+');
      assertEquals('3', s1.getResult());
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);
  }

  @Test
  public void Exception9() {
    boolean b = false;
    try {
      s1 = s1.input('9');
      s1 = s1.input('/');
      s1 = s1.input('/');
      assertEquals('3', s1.getResult());
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);
  }

  @Test
  public void Exception10() {
    boolean b = false;
    try {
      s1 = s1.input('a');
      assertEquals('3', s1.getResult());
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);
  }

  @Test
  public void Exception11() {
    boolean b = false;
    try {
      s1 = s1.input(' ');

      assertEquals('3', s1.getResult());
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);
  }

  @Test
  public void Exception12() {
    boolean b = false;
    try {
      s1 = s1.input('+');
      s1 = s1.input('+');
      s1 = s1.input('+');
      s1 = s1.input('2');
      s1 = s1.input('+');
      s1 = s1.input('2');
      s1 = s1.input('=');
      assertEquals('3', s1.getResult());
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);
  }

  @Test
  public void Exception13() {
    boolean b = false;
    try {
      s1 = s1.input('+');
      s1 = s1.input('3');
      s1 = s1.input('2');
      s1 = s1.input('-');
      s1 = s1.input('2');
      s1 = s1.input('4');
      s1 = s1.input('=');
      assertEquals('3', s1.getResult());
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);
  }

  @Test
  public void RegularExpression() {
    s1 = s1.input('3');
    s1 = s1.input('2');
    s1 = s1.input('+');
    s1 = s1.input('8');
    s1 = s1.input('=');
    s1 = s1.input('-');
    s1 = s1.input('5');
    s1 = s1.input('=');

    assertEquals("35", s1.getResult());
  }

  @Test
  public void RegularExpression2() {
    s1 = s1.input('3');
    s1 = s1.input('2');
    s1 = s1.input('-');
    s1 = s1.input('8');
    s1 = s1.input('=');
    s1 = s1.input('-');
    s1 = s1.input('4');
    s1 = s1.input('=');

    assertEquals("20", s1.getResult());
  }

  @Test
  public void RegularExpression3() {
    s1 = s1.input('3');
    s1 = s1.input('2');
    s1 = s1.input('-');
    s1 = s1.input('2');
    s1 = s1.input('4');
    s1 = s1.input('=');

    assertEquals("8", s1.getResult());
  }

  @Test
  public void RegularExpression4() {
    s1 = s1.input('3');
    s1 = s1.input('2');
    s1 = s1.input('+');
    s1 = s1.input('0');
    s1 = s1.input('0');
    s1 = s1.input('0');
    s1 = s1.input('+');
    s1 = s1.input('8');
    s1 = s1.input('=');

    assertEquals("40", s1.getResult());
  }

  @Test
  public void RegularExpression5() {
    s1 = s1.input('4');
    s1 = s1.input('=');
    s1 = s1.input('+');
    s1 = s1.input('2');
    s1 = s1.input('=');

    assertEquals("6", s1.getResult());
  }

  @Test
  public void RegularExpression6() {
    s1 = s1.input('2');
    s1 = s1.input('+');
    s1 = s1.input('2');
    s1 = s1.input('=');

    assertEquals("4", s1.getResult());
  }

  @Test
  public void RegularExpression7() {
    s1 = s1.input('3');
    s1 = s1.input('5');
    s1 = s1.input('-');
    s1 = s1.input('3');
    s1 = s1.input('2');
    s1 = s1.input('=');

    assertEquals("3", s1.getResult());
  }

  @Test
  public void RegularExpression8() {
    s1 = s1.input('3');
    s1 = s1.input('5');
    s1 = s1.input('-');
    s1 = s1.input('3');
    s1 = s1.input('2');
    s1 = s1.input('-');
    s1 = s1.input('3');
    s1 = s1.input('-');
    s1 = s1.input('5');
    s1 = s1.input('=');


    assertEquals("-5", s1.getResult());
  }


  @Test
  public void RegularExpression10() {
    s1 = s1.input('3');
    s1 = s1.input('5');
    s1 = s1.input('1');
    s1 = s1.input('3');
    s1 = s1.input('+');
    s1 = s1.input('1');
    s1 = s1.input('2');
    s1 = s1.input('3');
    s1 = s1.input('5');
    s1 = s1.input('=');
    s1 = s1.input('=');


    assertEquals("4748", s1.getResult());
  }

  @Test
  public void RegularExpression11() {
    s1 = s1.input('3');
    s1 = s1.input('5');
    s1 = s1.input('1');
    s1 = s1.input('3');
    s1 = s1.input('+');
    s1 = s1.input('1');
    s1 = s1.input('2');
    s1 = s1.input('3');
    s1 = s1.input('5');
    s1 = s1.input('=');
    s1 = s1.input('-');
    s1 = s1.input('2');
    s1 = s1.input('1');
    s1 = s1.input('2');
    s1 = s1.input('3');
    s1 = s1.input('=');
    s1 = s1.input('=');


    assertEquals("2625", s1.getResult());
  }

  @Test
  public void RegularExpression12() {
    s1 = s1.input('3');
    s1 = s1.input('5');
    s1 = s1.input('1');
    s1 = s1.input('3');
    s1 = s1.input('+');
    s1 = s1.input('1');
    s1 = s1.input('2');
    s1 = s1.input('3');
    s1 = s1.input('5');
    s1 = s1.input('=');
    s1 = s1.input('-');
    s1 = s1.input('2');
    s1 = s1.input('1');
    s1 = s1.input('2');
    s1 = s1.input('3');
    s1 = s1.input('=');
    s1 = s1.input('=');
    s1 = s1.input('=');


    assertEquals("2625", s1.getResult());
  }

  @Test
  public void RegularExpression13() {
    s1 = s1.input('3');
    s1 = s1.input('*');
    s1 = s1.input('1');
    s1 = s1.input('*');
    s1 = s1.input('2');
    s1 = s1.input('=');

    assertEquals("6", s1.getResult());
  }

  @Test
  public void minusObjectChange() {
    s1 = s1.input('4');
    Calculator smartCalculator2;
    s2 = s2.input('3');
    assertNotEquals(s1, s2);
  }

  @Test
  public void clear() {
    boolean b = false;
    try {
      s1 = s1.input('c');
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);
  }

  @Test
  public void invalidInput() {
    boolean b = false;
    try {
      s1 = s1.input('/');
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);
  }

  @Test
  public void invalidSecondInput() {
    boolean b = false;
    try {
      s1 = s1.input('1');
      s1 = s1.input('+');
      s1 = s1.input('a');
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);
  }

  @Test
  public void operandAfterequals() {

    s1 = s1.input('1');
    s1 = s1.input('+');
    s1 = s1.input('2');
    s1 = s1.input('=');
    s1 = s1.input('+');
    s1 = s1.input('2');
    s1 = s1.input('=');
    assertEquals("2", s1.getResult());
  }

  @Test
  public void ignorePlus() {
    s1 = s1.input('+');
    s1 = s1.input('1');
    s1 = s1.input('+');
    s1 = s1.input('2');
    s1 = s1.input('=');
    assertEquals("3", s1.getResult());
  }

}





