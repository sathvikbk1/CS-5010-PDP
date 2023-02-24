package model.portfolio;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.stocks.Stock;
import model.stocks.StockImpl;

import static org.junit.Assert.assertEquals;

/**
 * A test class to test the functionalities and
 * operations performed by the portfolio class.
 */
public class PortfolioImplTest {
  /**
   * A test to validate the name of the portfolio.
   */
  @Test
  public void testPortfolioName() {
    Portfolio p1 = new PortfolioImpl("name", new ArrayList<>());
    assertEquals("name", p1.getName());
  }

  /**
   * A test to verify the portfolio list.
   */
  @Test
  public void testPortfolioList() {
    List<Stock> listOfStocks = new ArrayList<>();
    Portfolio p1 = new PortfolioImpl("name", listOfStocks);
    listOfStocks.add(new StockImpl("GOOGL", 1, 1));
    listOfStocks.add(new StockImpl("IBM", 1, 1));
    assertEquals(listOfStocks, p1.getStockList());
  }

  /**
   * A test to get the total cost of the portfolio.
   */
  @Test
  public void testGetPortfolioCost() {
    List<Stock> listOfStocks = new ArrayList<>();
    Portfolio p1 = new PortfolioImpl("name", listOfStocks);
    listOfStocks.add(new StockImpl("GOOGL", 1, 1));
    listOfStocks.add(new StockImpl("IBM", 1, 1));
    assertEquals(2, p1.getPortfolioCost(), 0.00);
  }

  /**
   * A test to check if invalid date is appropriately handled.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDate() {
    List<Stock> listOfStocks = new ArrayList<>();
    listOfStocks.add(new StockImpl("TSLA", 1, 10));
    Portfolio p1 = new PortfolioImpl("name", listOfStocks);
    p1.getValueOfStockAtDate(LocalDate.parse("2023-11-02"));
  }

  /**
   * A test to get the cost of the portfolio with multiple stocks.
   */
  @Test
  public void testGetPortfolioCost2() {
    List<Stock> listOfStocks = new ArrayList<>();
    Portfolio p1 = new PortfolioImpl("name", listOfStocks);
    listOfStocks.add(new StockImpl("GOOGL", 3, 1));
    listOfStocks.add(new StockImpl("IBM", 1, 1));
    assertEquals(4, p1.getPortfolioCost(), 0.00);

  }

  /**
   * A test to verify the price of a stock from the API call.
   */
  @Test
  public void valueOnDateTest() {
    List<Stock> listOfStocks = new ArrayList<>();
    listOfStocks.add(new StockImpl("TSLA", 1, 1));
    Portfolio p1 = new PortfolioImpl("name", listOfStocks);
    assertEquals(214.98, p1.getValueOfStockAtDate(LocalDate.parse("2022-11-02")),
            0.00);
  }

  /**
   * A test to verify the price of the portfolio from the
   * API call for a particular date.
   */
  @Test
  public void valueOnDateTest2() {
    List<Stock> listOfStocks = new ArrayList<>();
    listOfStocks.add(new StockImpl("MSFT", 1, 1));
    listOfStocks.add(new StockImpl("IBM", 1, 1));
    Portfolio p1 = new PortfolioImpl("name", listOfStocks);
    assertEquals(356.93, p1.getValueOfStockAtDate(LocalDate.parse("2022-11-02")),
            0.00);
  }

  /**
   * A test to verify the price of the portfolio with multiple stocks from the
   * API call for a particular date.
   */
  @Test
  public void valueOnDateTest3() {
    List<Stock> listOfStocks = new ArrayList<>();
    listOfStocks.add(new StockImpl("MSFT", 1, 1));
    listOfStocks.add(new StockImpl("IBM", 1, 1));
    listOfStocks.add(new StockImpl("GOOGL", 1, 1));
    Portfolio p1 = new PortfolioImpl("name", listOfStocks);
    assertEquals(443.9, p1.getValueOfStockAtDate(LocalDate.parse("2022-11-02")),
            0.00);
  }

  /**
   * A test to verify the retrieval of number of stocks.
   */
  @Test
  public void noOfStocks1() {
    List<Stock> listOfStocks = new ArrayList<>();
    listOfStocks.add(new StockImpl("CITI", 3, 1));
    Portfolio p1 = new PortfolioImpl("name", listOfStocks);
    assertEquals(3, p1.getNoOfStocks(), 0.00);
  }

  /**
   * A test to verify the retrieval of total number of stocks in the portfolio.
   */
  @Test
  public void noOfStocks2() {
    List<Stock> listOfStocks = new ArrayList<>();
    listOfStocks.add(new StockImpl("CITI", 1, 1));
    listOfStocks.add(new StockImpl("IBM", 1, 1));
    listOfStocks.add(new StockImpl("GOOGL", 2, 1));
    Portfolio p1 = new PortfolioImpl("name", listOfStocks);
    assertEquals(4, p1.getNoOfStocks(), 0.00);
  }
}