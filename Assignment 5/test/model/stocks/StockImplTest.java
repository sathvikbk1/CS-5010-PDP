package model.stocks;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * A test class to test the functionalities and
 * operations performed by the stock class.
 */
public class StockImplTest {
  Stock s1;
  Stock s2;

  @Before
  public void setUp() {
    s1 = new StockImpl("GOOGL", 5, 10);
    s2 = new StockImpl("ibm", 5.25, 10.58);
  }

  /**
   * A test to verify the functioning of the stock class.
   */
  @Test
  public void createStockTest() {
    assertEquals("GOOGL", s1.getStockName());
  }

  /**
   * Testing the appropriate functioning of the stock creation.
   */
  @Test(expected = IllegalArgumentException.class)
  public void createStockTestException2() {
    s1 = new StockImpl("GOOGL", -2, -5);
    s1.getNoOfShares();
  }

  /**
   * Testing the appropriate functioning of the stock creation.
   */
  @Test(expected = IllegalArgumentException.class)
  public void createStockTestException3() {
    s1 = new StockImpl("", 87, 05);
    s1.getNoOfShares();
  }

  /**
   * Testing the appropriate functioning of the stock creation.
   */
  @Test(expected = IllegalArgumentException.class)
  public void createStockTestException4() {
    s1 = new StockImpl("IBM", 0, 8);
    s1.getNoOfShares();
  }

  /**
   * Testing the appropriate functioning of the stock creation.
   */
  @Test(expected = IllegalArgumentException.class)
  public void createStockTestException5() {
    s1 = new StockImpl("", -5, -5);
    s1.getStockName();
  }

  /**
   * Testing the appropriate functioning of the stock creation.
   */
  @Test(expected = IllegalArgumentException.class)
  public void createStockTestException6() {
    s1 = new StockImpl("", 0, -5);
    s1.getNoOfShares();
  }

  /**
   * Testing the appropriate functioning of the stock creation.
   */
  @Test(expected = IllegalArgumentException.class)
  public void createStockTestException7() {
    s1 = new StockImpl(null, 0, 0);
    s1.getCost();
  }

  /**
   * Testing the function to get the number of shares stored.
   */
  @Test
  public void quantityStockTest() {
    assertEquals(5.0, s1.getNoOfShares(), 0.00);
  }

  /**
   * Testing the function to get a fractional quantity of shares stored.
   */
  @Test
  public void fractionalQuantityStockTest() {
    assertEquals(5.25, s2.getNoOfShares(), 0.00);
  }

  /**
   * Testing the price of the ticker on a particular day.
   */
  @Test
  public void priceAtDate() {
    assertEquals(86.97, s1.getPriceAtDate(LocalDate.parse("2022-11-02")), 0.00);
  }

  /**
   * Testing the name of the stock.
   */
  @Test
  public void tickerStockTest() {
    assertEquals("GOOGL", s1.getStockName());
  }

  /**
   * Testing the price fetching from the API call.
   */
  @Test
  public void testGetPrice() {
    boolean check = true;
    try {
      s2.getPrice();
    } catch (Exception e) {
      check = false;
    }
    assertTrue(check);
  }

  /**
   * Testing the storage of the cost of the stock.
   */
  @Test
  public void getCost() {
    assertEquals(10.58, s2.getCost(), 0.00);
  }
}