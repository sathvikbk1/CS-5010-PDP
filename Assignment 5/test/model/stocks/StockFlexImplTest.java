package model.stocks;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * A JUnit test class to check the functioning of the stock
 * belonging to the flexible portfolio.
 */
public class StockFlexImplTest {
  Stock s1;
  Stock s3;
  Stock s4;

  @Before
  public void setUp() {
    s3 = new StockFlexImpl("GOOGL", 5, 90.37, LocalDate.parse("2022-11-01"), 10);
    s4 = new StockFlexImpl("TSLA", 5, 195.97, LocalDate.parse("2022-11-11"), 3);
  }

  /**
   * A test to verify the functioning of the stock class.
   */
  @Test
  public void createStockFlexTest() {
    assertEquals("GOOGL", s3.getStockName());
  }


  /**
   * Testing the appropriate functioning of the stock creation.
   */
  @Test(expected = IllegalArgumentException.class)
  public void createStockFlexTestException2() {
    s1 = new StockFlexImpl("GOOGL", -2, -5, LocalDate.parse("2022-11-01"), 10);
    s1.getNoOfShares();
  }

  /**
   * Testing the appropriate functioning of the stock creation.
   */
  @Test(expected = IllegalArgumentException.class)
  public void createStockFlexTestException3() {
    s1 = new StockFlexImpl("", 87, 05, LocalDate.parse("2022-11-01"), 10);
    s1.getNoOfShares();
  }

  /**
   * Testing the appropriate functioning of the stock creation.
   */
  @Test(expected = IllegalArgumentException.class)
  public void createStockFlexTestException4() {
    s1 = new StockFlexImpl("IBM", 0, 8, LocalDate.parse("2022-11-01"), 10);
    s1.getNoOfShares();
  }

  /**
   * Testing the appropriate functioning of the stock creation.
   */
  @Test(expected = IllegalArgumentException.class)
  public void createStockFlexTestException5() {
    s1 = new StockFlexImpl("", -5, -5, LocalDate.parse("2022-11-01"), 10);
    s1.getStockName();
  }

  /**
   * Testing the appropriate functioning of the stock creation.
   */
  @Test(expected = IllegalArgumentException.class)
  public void createStockFlexTestException6() {
    s1 = new StockFlexImpl("", 0, -5, LocalDate.parse("2022-11-01"), 10);
    s1.getNoOfShares();
  }

  /**
   * Testing the appropriate functioning of the stock creation.
   */
  @Test(expected = IllegalArgumentException.class)
  public void createStockFlexTestException7() {
    s1 = new StockFlexImpl(null, 0, 0, LocalDate.parse("2022-11-01"), 10);
    s1.getCost();
  }

  /**
   * Testing the function to get the number of shares stored.
   */
  @Test
  public void quantityStockFlexTest() {

    assertEquals(5.0, s3.getNoOfShares(), 0.00);
  }


  /**
   * Testing the price of the ticker on a particular day.
   */
  @Test
  public void FlexpriceAtDate() {
    assertEquals(190.95, s4.getPriceAtDate(LocalDate.parse("2022-11-14")), 0.00);
  }

  /**
   * Testing the name of the stock.
   */
  @Test
  public void tickerStockFlexTest() {

    assertEquals("TSLA", s4.getStockName());
  }

  /**
   * Testing the price fetching from the API call.
   */
  @Test
  public void testFlexGetPrice() {
    boolean check = true;
    try {
      s4.getPrice();
    } catch (Exception e) {
      check = false;
    }
    assertTrue(check);
  }

  /**
   * Testing the storage of the cost of the stock.
   */
  @Test
  public void getFlexCost() {

    assertEquals(90.37, s3.getCost(), 0.00);
  }
}