import org.junit.Before;
import org.junit.Test;

import bignumber.BigNumber;
import bignumber.BigNumberImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class is used for testing.
 */

public class BigNumberImplTest {

  BigNumber number;
  BigNumber number2;

  BigNumber number3;

  @Before
  public void setUp() {
    // default 32411
    number = new BigNumberImpl("32411");
    number2 = new BigNumberImpl("99999");
    number3 = new BigNumberImpl("0");
  }

  @Test
  public void testlenghtregular() {
    assertEquals(5, number.length());
  }

  @Test
  public void testlenghtL1() {
    number.shiftLeft(1);
    assertEquals(6, number.length());
  }

  @Test
  public void testlenghtL2() {
    number.shiftLeft(2);
    assertEquals(7, number.length());
  }

  @Test
  public void testlenghtL10() {
    number.shiftLeft(10);
    assertEquals(15, number.length());
  }

  @Test
  public void testlenghtR1() {
    number.shiftRight(1);
    assertEquals(4, number.length());
  }

  @Test
  public void testlenghtR2() {
    number.shiftRight(2);
    assertEquals(3, number.length());
  }

  @Test
  public void testlenghtR5() {
    number.shiftRight(5);
    assertEquals(1, number.length());
  }

  @Test
  public void testlenghtR10() {
    number.shiftRight(10);
    assertEquals(1, number.length());
  }

  @Test
  public void testlenghtL2R2() {
    number.shiftRight(2);
    number.shiftLeft(2);
    assertEquals(5, number.length());
  }

  @Test
  public void testlenghtadd() {
    number.addDigit(2);
    assertEquals(5, number.length());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testlenghtaddException() {
    number.addDigit(70000);
    assertEquals(6, number.length());
  }

  @Test
  public void testlenghtaddcarry() {
    number2.addDigit(2);
    assertEquals(6, number2.length());
  }

  @Test
  public void shiftLeft1() {
    number.shiftLeft(8);
    assertEquals(13, number.length());
    assertEquals("3241100000000", number.toString());
  }

  @Test
  public void shiftLeft2() {
    number2.addDigit(2);
    number2.shiftLeft(8);
    assertEquals(14, number2.length());
    assertEquals("10000100000000", number2.toString());
  }

  @Test
  public void shiftLeft3() {
    number2.addDigit(2);
    number2.shiftRight(2);
    number2.shiftLeft(8);
    assertEquals(12, number2.length());
    assertEquals("100000000000", number2.toString());
  }

  @Test
  public void shiftLeft4() {
    number3.shiftLeft(8);
    assertEquals(1, number3.length());
    assertEquals("0", number3.toString());
  }

  @Test
  public void shiftLeft5() {
    number3.addDigit(2);
    number3.shiftRight(2);
    number3.shiftLeft(8);
    assertEquals(1, number3.length());
    assertEquals("0", number3.toString());
  }

  @Test
  public void shiftLeft6() {
    number3.shiftLeft(-8);
    assertEquals(1, number3.length());
    assertEquals("0", number3.toString());
  }

  @Test
  public void shiftLeft7() {
    number3.addDigit(2);
    number3.shiftLeft(-8);
    assertEquals(1, number3.length());
    assertEquals("0", number3.toString());
  }

  @Test
  public void shiftLeftNegative() {
    number2.shiftLeft(-1);
    assertEquals(4, number2.length());
    assertEquals("9999", number2.toString());
  }

  @Test
  public void testMultipleShiftsOnZero() {
    for (int i = 0; i < 100; i++) {
      number.shiftRight(100);
      number.shiftRight(13);
    }
    assertEquals("0", number.toString());
  }

  @Test
  public void shiftRight1() {
    number.shiftRight(8);
    assertEquals(1, number.length());
    assertEquals("0", number.toString());
  }

  @Test
  public void shiftRight2() {
    number.shiftRight(1000);
    assertEquals(1, number.length());
    assertEquals("0", number.toString());
  }

  @Test
  public void shiftRight3() {
    number.shiftLeft(2);
    number.shiftRight(1);
    assertEquals(6, number.length());
    assertEquals("324110", number.toString());
  }

  @Test
  public void shiftRight4() {
    number2.addDigit(2);
    number2.shiftRight(1);
    assertEquals(5, number2.length());
    assertEquals("10000", number2.toString());
  }

  @Test
  public void shiftRight5() {
    number2.addDigit(2);
    number2.shiftLeft(2);
    number2.shiftRight(1);
    assertEquals(7, number2.length());
    assertEquals("1000010", number2.toString());
  }

  @Test
  public void shiftRight6() {
    number3.shiftRight(1);
    assertEquals(1, number3.length());
    assertEquals("0", number3.toString());
  }

  @Test
  public void shiftRight7() {
    number3.addDigit(2);
    number3.shiftLeft(2);
    number3.shiftRight(1);
    assertEquals(2, number3.length());
    assertEquals("20", number3.toString());
  }

  @Test
  public void shiftRight8() {
    number3.shiftRight(-10);
    assertEquals(1, number3.length());
    assertEquals("0", number3.toString());
  }

  @Test
  public void shiftRight9() {
    number3.addDigit(2);
    number3.shiftRight(-10);
    assertEquals(11, number3.length());
    assertEquals("20000000000", number3.toString());
  }

  @Test
  public void shiftRightNegative() {
    number2.shiftRight(-3);
    assertEquals(8, number2.length());
    assertEquals("99999000", number2.toString());
  }

  @Test(timeout = 1000)
  public void multiShift() {
    StringBuilder num = new StringBuilder();
    for (int i = 0; i < 2000; i++) {
      num.append(Integer.toString((int) (Math.random() * 9 + 1)));
    }
    BigNumber b = new BigNumberImpl(num.toString());
    for (int j = 0; j < 50; j++) {


      for (int i = 0; i < 3000; i++) {
        b.shiftLeft(5);
        b.shiftRight(5);
        b.addDigit(9);
      }
    }
    assertEquals("0", number3.toString());
  }

  @Test
  public void testMultipleShifts2() {
    for (int i = 0; i < 100; i++) {
      number3.shiftRight(100);
      number3.shiftRight(13);
    }
    assertEquals("0", number3.toString());
  }


  @Test
  public void emptyCreation1() {
    assertEquals("0", number3.toString());
  }

  @Test
  public void stringCreation() {
    assertEquals("99999", number2.toString());
  }

  @Test
  public void stringCreation2() {
    BigNumber number4 = new BigNumberImpl("0982");
    assertEquals("982", number4.toString());
  }

  @Test
  public void stringCreation3() {
    BigNumber number5 = new BigNumberImpl("0000000000000000");
    assertEquals("0", number5.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void stringConstructorCreationException() {
    BigNumber number6 = new BigNumberImpl("erierebqihf");
  }

  @Test(timeout = 1000)
  public void addDigit1() {
    StringBuilder num = new StringBuilder();
    for (int i = 0; i < 2000; i++) {
      num.append(Integer.toString(9));
    }
    BigNumber a = new BigNumberImpl(num.toString());
    a.addDigit(1);

    StringBuilder result = new StringBuilder("1");
    for (int i = 0; i < 2000; i++) {
      result.append(Integer.toString(0));
    }
    assertEquals(result.toString(), a.toString());
  }

  @Test(timeout = 1000)
  public void testLen() {
    StringBuilder num = new StringBuilder();
    for (int i = 0; i < 2000; i++) {
      num.append(Integer.toString((int) (Math.random() * 9 + 1)));
    }
    BigNumber b = new BigNumberImpl(num.toString());
    assertEquals(2000, b.length());
  }

  @Test
  public void addDigit2() {
    number.addDigit(8);
    assertEquals("32419", number.toString());
  }

  @Test
  public void addDigit3() {
    number2.addDigit(2);
    assertEquals("100001", number2.toString());
  }

  @Test
  public void getDigitAt() {
    assertEquals(3, number.getDigitAt(4));
    assertEquals(1, number.getDigitAt(1));
    assertEquals(2, number.getDigitAt(3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void getDigitAtInvalidIndex() {
    number.getDigitAt(-1);
  }

  @Test
  public void copy() {
    BigNumber copy = number.copy();
    assertEquals(5, copy.length());
  }

  @Test
  public void add1() {
    BigNumberImpl bigNumber = new BigNumberImpl("3453456");
    BigNumber result = number.add(bigNumber);
    assertEquals("3485867", result.toString());
  }

  @Test
  public void add2() {
    BigNumberImpl bigNumber2 = new BigNumberImpl("92411");
    BigNumber result2 = number.add(bigNumber2);
    assertEquals("124822", result2.toString());
  }

  @Test
  public void add3() {

    BigNumberImpl bigNumber3 = new BigNumberImpl("819");
    BigNumber result3 = number.add(bigNumber3);
    assertEquals("33230", result3.toString());

  }

  @Test
  public void add4() {


    BigNumberImpl bigNumber4 = new BigNumberImpl("819");
    number.shiftRight(1);
    BigNumber result4 = number.add(bigNumber4);
    assertEquals("4060", result4.toString());

  }

  @Test
  public void add5() {

    BigNumberImpl bigNumber5 = new BigNumberImpl("819");
    number.shiftLeft(1);
    BigNumber result5 = number.add(bigNumber5);
    assertEquals("324929", result5.toString());
  }

  @Test
  public void add6() {

    BigNumberImpl bigNumber6 = new BigNumberImpl("819");
    number.addDigit(9);
    BigNumber result6 = number.add(bigNumber6);
    assertEquals("33239", result6.toString());

  }

  @Test
  public void compareTo1() {

    BigNumber bigNumber = new BigNumberImpl("32222");
    assertEquals(1, number.compareTo(bigNumber));

  }

  @Test
  public void compareTo2() {

    BigNumber bigNumber2 = new BigNumberImpl("32411");
    assertEquals(0, number.compareTo(bigNumber2));

  }

  @Test
  public void compareTo3() {

    BigNumber bigNumber3 = new BigNumberImpl("1");
    assertEquals(1, number.compareTo(bigNumber3));

  }

  @Test
  public void compareTo4() {

    BigNumber bigNumber4 = new BigNumberImpl("324114343");
    assertEquals(-1, number.compareTo(bigNumber4));

  }

  @Test
  public void compareTo5() {

    BigNumber bigNumber4 = new BigNumberImpl("324114343");
    bigNumber4.addDigit(9);
    assertEquals(-1, number.compareTo(bigNumber4));

  }

  @Test
  public void compareTo6() {

    BigNumber bigNumber4 = new BigNumberImpl("324114343");
    bigNumber4.shiftLeft(2);
    assertEquals(-1, number.compareTo(bigNumber4));

  }

  @Test
  public void compareTo7() {

    BigNumber bigNumber4 = new BigNumberImpl("324114343");
    bigNumber4.shiftRight(1);
    assertEquals(-1, number.compareTo(bigNumber4));

  }
}