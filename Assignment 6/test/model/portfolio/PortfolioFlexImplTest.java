package model.portfolio;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.stocks.Stock;
import model.stocks.StockFlexImpl;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit class to test the functioning of the Flexible Portfolio class.
 */
public class PortfolioFlexImplTest {
  /**
   * A test to verify the retrieval of name of the portfolio.
   */
  @Test
  public void testPortfolioFlexName() {
    Portfolio p = new PortfolioFlexImpl("name", new ArrayList<>(),
            new ArrayList<>(), new ArrayList<>());
    assertEquals("name", p.getName());
  }

  /**
   * A test to verify the retrieval of the list of stocks in the portfolio.
   */

  @Test
  public void testPortfolioFlexList() {
    List<Stock> listOfStocks = new ArrayList<>();
    Portfolio p1 = new PortfolioFlexImpl("name", listOfStocks, new ArrayList<>(),
            new ArrayList<>());
    listOfStocks.add(new StockFlexImpl("GOOGL", 1, 57.50, LocalDate.parse("2021-11-02"), 10));
    listOfStocks.add(new StockFlexImpl("TSLA", 100, 195.97, LocalDate.parse("2022-11-11"), 3));
    assertEquals(listOfStocks, p1.getStockList());
  }

  /**
   * A test to verify the cost of the portfolio.
   */

  @Test
  public void testGetFlexPortfolioCost2() {
    List<Stock> listOfStocks = new ArrayList<>();
    Portfolio p1 = new PortfolioFlexImpl("name", listOfStocks, listOfStocks, new ArrayList<>());
    listOfStocks.add(new StockFlexImpl("GOOGL", 1, 90.37, LocalDate.parse("2022-11-01"), 10));
    listOfStocks.add(new StockFlexImpl("TSLA", 1, 195.97, LocalDate.parse("2022-11-11"), 3));
    assertEquals(299.34000000000003, p1.getPortfolioCost(), 0.00);

  }

  /**
   * A test to verify the price of a stock from the API call for a particular date .
   */

  @Test
  public void valueFlexOnDateTest() {
    List<Stock> listOfStocks = new ArrayList<>();
    listOfStocks.add(new StockFlexImpl("TSLA", 1, 195.97, LocalDate.parse("2022-11-11"), 3));
    Portfolio p1 = new PortfolioImpl("name", listOfStocks);
    assertEquals(194.42, p1.getValueOfStockAtDate(LocalDate.parse("2022-11-15")),
            0.00);
  }

  /**
   * A test to verify the price of the portfolio from the API call for a particular date.
   */

  @Test
  public void valueFlexOnDateTest2() {
    List<Stock> listOfStocks = new ArrayList<>();
    listOfStocks.add(new StockFlexImpl("GOOGL", 1, 90.37, LocalDate.parse("2022-11-01"), 10));
    listOfStocks.add(new StockFlexImpl("TSLA", 1, 195.97, LocalDate.parse("2022-11-11"), 3));
    Portfolio p1 = new PortfolioFlexImpl("name", listOfStocks, listOfStocks, new ArrayList<>());
    assertEquals(286.65, p1.getValueOfStockAtDate(LocalDate.parse("2022-11-14")),
            0.00);
  }

  /**
   * A test to verify the number of stocks in the portfolio.
   */

  @Test
  public void noOfFlexStocks2() {
    List<Stock> listOfStocks = new ArrayList<>();
    listOfStocks.add(new StockFlexImpl("GOOGL", 1, 90.37, LocalDate.parse("2022-11-01"), 10));
    listOfStocks.add(new StockFlexImpl("TSLA", 1, 195.97, LocalDate.parse("2022-11-11"), 3));
    listOfStocks.add(new StockFlexImpl("GE", 300, 53.60, LocalDate.parse("2020-10-09"), 10));
    Portfolio p1 = new PortfolioFlexImpl("name", listOfStocks, new ArrayList<>(),
            new ArrayList<>());
    assertEquals(302, p1.getNoOfStocks(), 0.00);
  }

}