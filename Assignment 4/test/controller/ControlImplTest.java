package controller;

import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import model.portfolio.MockModelPortfolio;
import model.stocks.MockModelStock;
import model.stocks.Stock;
import model.stocks.StockImpl;

import static org.junit.Assert.assertEquals;

/**
 * A test class to verify the appropriate functioning
 * of the controller .
 */
public class ControlImplTest {
  /**
   * A test to check the functioning of the portfolio for name.
   */
  @Test
  public void testNamePortfolio() {
    Reader in = new StringReader("1 1 googl 10 5 f abc 2 q");
    StringBuilder out = new StringBuilder();
    StringBuilder log = new StringBuilder();
    List<Stock> temp = new ArrayList<>();
    temp.add(new StockImpl("test", 10, 3));
    Control control = new ControlImpl(in, out);
    control.execute(new MockModelPortfolio(log, "1231", 51231, temp));
    assertEquals("Created portfolio: abc\n"
            + "The name of the portfolio: 1231\n"
            + "The current stocks in portfolio: test\n", log.toString());
    assertEquals("Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio.\n" +
            "2 - Show all portfolio.\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total value of a portfolio\n" +
            "6 - Check total value of a portfolio by a given date\n" +
            "7 - Save a portfolio\n" +
            "8 - Retrieve a portfolio\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock.\n" +
            "f/F - close portfolio.\n" +
            "\n" +
            "Enter the stock symbol, number of shares and cost per stock: \n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock.\n" +
            "f/F - close portfolio.\n" +
            "\n" +
            "Creating Portfolio....\n" +
            "Enter the name for the Portfolio: \n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio.\n" +
            "2 - Show all portfolio.\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total value of a portfolio\n" +
            "6 - Check total value of a portfolio by a given date\n" +
            "7 - Save a portfolio\n" +
            "8 - Retrieve a portfolio\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Portfolio Name: 1231\n" +
            "Stock name: test\n" +
            "Number Of Shares: 10.0\n" +
            "Price Per Share: 3.0\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio.\n" +
            "2 - Show all portfolio.\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total value of a portfolio\n" +
            "6 - Check total value of a portfolio by a given date\n" +
            "7 - Save a portfolio\n" +
            "8 - Retrieve a portfolio\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Quiting....\n", out.toString());
  }

  /**
   * A test to check the functioning of the portfolio for getting the stock list.
   */
  @Test
  public void testGetStockList() {
    Reader in = new StringReader("1 1 googl 10 5 f abc 3 abc q");
    StringBuilder out = new StringBuilder();
    StringBuilder log = new StringBuilder();
    List<Stock> temp = new ArrayList<>();
    temp.add(new StockImpl("test", 10, 3));
    Control control = new ControlImpl(in, out);
    control.execute(new MockModelPortfolio(log, "abc", 51231, temp));
    assertEquals("Created portfolio: abc\n" +
            "The name of the portfolio: abc\n" +
            "The name of the portfolio: abc\n" +
            "The current stocks in portfolio: test\n", log.toString());
    assertEquals("Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio.\n" +
            "2 - Show all portfolio.\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total value of a portfolio\n" +
            "6 - Check total value of a portfolio by a given date\n" +
            "7 - Save a portfolio\n" +
            "8 - Retrieve a portfolio\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock.\n" +
            "f/F - close portfolio.\n" +
            "\n" +
            "Enter the stock symbol, number of shares and cost per stock: \n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock.\n" +
            "f/F - close portfolio.\n" +
            "\n" +
            "Creating Portfolio....\n" +
            "Enter the name for the Portfolio: \n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio.\n" +
            "2 - Show all portfolio.\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total value of a portfolio\n" +
            "6 - Check total value of a portfolio by a given date\n" +
            "7 - Save a portfolio\n" +
            "8 - Retrieve a portfolio\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter the name for the Portfolio: \n" +
            "Portfolio Name: abc\n" +
            "Stock name: test\n" +
            "Number Of Shares: 10.0\n" +
            "Price Per Share: 3.0\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio.\n" +
            "2 - Show all portfolio.\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total value of a portfolio\n" +
            "6 - Check total value of a portfolio by a given date\n" +
            "7 - Save a portfolio\n" +
            "8 - Retrieve a portfolio\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Quiting....\n", out.toString());
  }

  /**
   * A test to check the functioning of the portfolio for checking cost of portfolio.
   */
  @Test
  public void testGetPortfolioCost() {
    Reader in = new StringReader("1 1 googl 10 10 f abc 4 abc q");
    StringBuilder out = new StringBuilder();
    StringBuilder log = new StringBuilder();
    List<Stock> temp = new ArrayList<>();
    temp.add(new StockImpl("test", 10, 3));
    Control control = new ControlImpl(in, out);
    control.execute(new MockModelPortfolio(log, "abc", 100, temp));
    assertEquals("Created portfolio: abc\n"
            + "The name of the portfolio: abc\n"
            + "Cost of current portfolio: 100.0\n", log.toString());
    assertEquals("Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio.\n" +
            "2 - Show all portfolio.\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total value of a portfolio\n" +
            "6 - Check total value of a portfolio by a given date\n" +
            "7 - Save a portfolio\n" +
            "8 - Retrieve a portfolio\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock.\n" +
            "f/F - close portfolio.\n" +
            "\n" +
            "Enter the stock symbol, number of shares and cost per stock: \n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock.\n" +
            "f/F - close portfolio.\n" +
            "\n" +
            "Creating Portfolio....\n" +
            "Enter the name for the Portfolio: \n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio.\n" +
            "2 - Show all portfolio.\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total value of a portfolio\n" +
            "6 - Check total value of a portfolio by a given date\n" +
            "7 - Save a portfolio\n" +
            "8 - Retrieve a portfolio\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter the name for the Portfolio: \n" +
            "Total Cost of Stocks in Portfolio abc: 100.0\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio.\n" +
            "2 - Show all portfolio.\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total value of a portfolio\n" +
            "6 - Check total value of a portfolio by a given date\n" +
            "7 - Save a portfolio\n" +
            "8 - Retrieve a portfolio\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Quiting....\n", out.toString());
  }

  /**
   * A test to check the functioning of the portfolio for total value.
   */
  @Test
  public void testTotalVal() {
    Reader in = new StringReader("1 1 googl 10 10 f abc 5 abc q");
    StringBuilder out = new StringBuilder();
    StringBuilder log = new StringBuilder();
    List<Stock> temp = new ArrayList<>();
    temp.add(new StockImpl("test", 10, 3));
    Control control = new ControlImpl(in, out);
    control.execute(new MockModelPortfolio(log, "abc", 100, temp));
    assertEquals("Created portfolio: abc\n"
            + "The name of the portfolio: abc\n"
            + "Value of current portfolio: 100.0\n", log.toString());
    assertEquals("Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio.\n" +
            "2 - Show all portfolio.\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total value of a portfolio\n" +
            "6 - Check total value of a portfolio by a given date\n" +
            "7 - Save a portfolio\n" +
            "8 - Retrieve a portfolio\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock.\n" +
            "f/F - close portfolio.\n" +
            "\n" +
            "Enter the stock symbol, number of shares and cost per stock: \n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock.\n" +
            "f/F - close portfolio.\n" +
            "\n" +
            "Creating Portfolio....\n" +
            "Enter the name for the Portfolio: \n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio.\n" +
            "2 - Show all portfolio.\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total value of a portfolio\n" +
            "6 - Check total value of a portfolio by a given date\n" +
            "7 - Save a portfolio\n" +
            "8 - Retrieve a portfolio\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter the name for the Portfolio: \n" +
            "Total Cost of Stocks in Portfolio abc: 100.0\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio.\n" +
            "2 - Show all portfolio.\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total value of a portfolio\n" +
            "6 - Check total value of a portfolio by a given date\n" +
            "7 - Save a portfolio\n" +
            "8 - Retrieve a portfolio\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Quiting....\n", out.toString());
  }

  /**
   * A test to check the functioning of the portfolio for total value on a date.
   */
  @Test
  public void testGetDateVal() {
    Reader in = new StringReader("1 1 googl 10 10 f abc 6 abc 2022-11-01 q");
    StringBuilder out = new StringBuilder();
    StringBuilder log = new StringBuilder();
    List<Stock> temp = new ArrayList<>();
    temp.add(new StockImpl("test", 10, 3));
    Control control = new ControlImpl(in, out);
    control.execute(new MockModelPortfolio(log, "abc", 90.47, temp));
    assertEquals("Created portfolio: abc\n"
            + "The name of the portfolio: abc\n"
            + "The Date to check for: 2022-11-01\n", log.toString());
    assertEquals("Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio.\n" +
            "2 - Show all portfolio.\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total value of a portfolio\n" +
            "6 - Check total value of a portfolio by a given date\n" +
            "7 - Save a portfolio\n" +
            "8 - Retrieve a portfolio\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock.\n" +
            "f/F - close portfolio.\n" +
            "\n" +
            "Enter the stock symbol, number of shares and cost per stock: \n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock.\n" +
            "f/F - close portfolio.\n" +
            "\n" +
            "Creating Portfolio....\n" +
            "Enter the name for the Portfolio: \n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio.\n" +
            "2 - Show all portfolio.\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total value of a portfolio\n" +
            "6 - Check total value of a portfolio by a given date\n" +
            "7 - Save a portfolio\n" +
            "8 - Retrieve a portfolio\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter the name for the Portfolio: \n" +
            "Enter the custom date in yyyy-mm-dd format: \n" +
            "Total Cost of Stocks in Portfolio abc: 90.47\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio.\n" +
            "2 - Show all portfolio.\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total value of a portfolio\n" +
            "6 - Check total value of a portfolio by a given date\n" +
            "7 - Save a portfolio\n" +
            "8 - Retrieve a portfolio\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Quiting....\n", out.toString());
  }

  /**
   * A test to check the functioning of the portfolio for saving and retrieving.
   */
  @Test
  public void testSaveAndRetrieve() {
    Reader in = new StringReader("1 1 googl 10 10 f xyz 7 8 xyz 2 q");
    StringBuilder out = new StringBuilder();
    StringBuilder log = new StringBuilder();
    List<Stock> temp = new ArrayList<>();
    temp.add(new StockImpl("test", 10, 3));
    Control control = new ControlImpl(in, out);
    control.execute(new MockModelPortfolio(log, "xyz", 90.47, temp));
    assertEquals("Created portfolio: xyz\n"
            + "The current stocks in portfolio: test\n"
            + "The name of the portfolio: xyz\n"
            + "The name of the portfolio: xyz\n"
            + "The current stocks in portfolio: test\n", log.toString());
    assertEquals("Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio.\n" +
            "2 - Show all portfolio.\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total value of a portfolio\n" +
            "6 - Check total value of a portfolio by a given date\n" +
            "7 - Save a portfolio\n" +
            "8 - Retrieve a portfolio\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock.\n" +
            "f/F - close portfolio.\n" +
            "\n" +
            "Enter the stock symbol, number of shares and cost per stock: \n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock.\n" +
            "f/F - close portfolio.\n" +
            "\n" +
            "Creating Portfolio....\n" +
            "Enter the name for the Portfolio: \n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio.\n" +
            "2 - Show all portfolio.\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total value of a portfolio\n" +
            "6 - Check total value of a portfolio by a given date\n" +
            "7 - Save a portfolio\n" +
            "8 - Retrieve a portfolio\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio.\n" +
            "2 - Show all portfolio.\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total value of a portfolio\n" +
            "6 - Check total value of a portfolio by a given date\n" +
            "7 - Save a portfolio\n" +
            "8 - Retrieve a portfolio\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter the name of the portfolio to be retrieved: \n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio.\n" +
            "2 - Show all portfolio.\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total value of a portfolio\n" +
            "6 - Check total value of a portfolio by a given date\n" +
            "7 - Save a portfolio\n" +
            "8 - Retrieve a portfolio\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Portfolio Name: xyz\n" +
            "Stock name: test\n" +
            "Number Of Shares: 10.0\n" +
            "Price Per Share: 3.0\n" +
            "\n" +
            "Portfolio Name: xyz\n" +
            "Stock name: test\n" +
            "Number Of Shares: 10.0\n" +
            "Price Per Share: 3.0\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio.\n" +
            "2 - Show all portfolio.\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total value of a portfolio\n" +
            "6 - Check total value of a portfolio by a given date\n" +
            "7 - Save a portfolio\n" +
            "8 - Retrieve a portfolio\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Quiting....\n", out.toString());
  }

  /**
   * A test to check the functioning of the stock for all its functionalities.
   */
  @Test
  public void testStockCreationAndSaveRetreive() {
    Reader in = new StringReader("1 1 googl 10 10 f xyz 7 8 xyz 2 q");
    StringBuilder out = new StringBuilder();
    StringBuilder log = new StringBuilder();
    StringBuilder otherLog = new StringBuilder();
    List<Stock> temp = new ArrayList<>();
    temp.add(new MockModelStock(log, "test", 10));
    Control control = new ControlImpl(in, out);
    control.execute(new MockModelPortfolio(otherLog, "xyz", 90.47, temp));
    assertEquals("Adding stock name: test\n" +
                    "Adding stock name: test\n" +
                    "Adding stock name: test\n" +
                    "Adding number of shares: 10.0\n" +
                    "Adding number of shares: 10.0\n" +
                    "Calculating total cost: 10.0\n" +
                    "Calculating total cost: 10.0\n" +
                    "Adding stock name: test\n" +
                    "Adding stock name: test\n" +
                    "Adding number of shares: 10.0\n" +
                    "Calculating total cost: 10.0\n"
            , log.toString());
    assertEquals("Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio.\n" +
            "2 - Show all portfolio.\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total value of a portfolio\n" +
            "6 - Check total value of a portfolio by a given date\n" +
            "7 - Save a portfolio\n" +
            "8 - Retrieve a portfolio\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock.\n" +
            "f/F - close portfolio.\n" +
            "\n" +
            "Enter the stock symbol, number of shares and cost per stock: \n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock.\n" +
            "f/F - close portfolio.\n" +
            "\n" +
            "Creating Portfolio....\n" +
            "Enter the name for the Portfolio: \n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio.\n" +
            "2 - Show all portfolio.\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total value of a portfolio\n" +
            "6 - Check total value of a portfolio by a given date\n" +
            "7 - Save a portfolio\n" +
            "8 - Retrieve a portfolio\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio.\n" +
            "2 - Show all portfolio.\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total value of a portfolio\n" +
            "6 - Check total value of a portfolio by a given date\n" +
            "7 - Save a portfolio\n" +
            "8 - Retrieve a portfolio\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Enter the name of the portfolio to be retrieved: \n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio.\n" +
            "2 - Show all portfolio.\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total value of a portfolio\n" +
            "6 - Check total value of a portfolio by a given date\n" +
            "7 - Save a portfolio\n" +
            "8 - Retrieve a portfolio\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Portfolio Name: xyz\n" +
            "Stock name: test\n" +
            "Number Of Shares: 10.0\n" +
            "Price Per Share: 10.0\n" +
            "\n" +
            "Portfolio Name: xyz\n" +
            "Stock name: test\n" +
            "Number Of Shares: 10.0\n" +
            "Price Per Share: 10.0\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio.\n" +
            "2 - Show all portfolio.\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total value of a portfolio\n" +
            "6 - Check total value of a portfolio by a given date\n" +
            "7 - Save a portfolio\n" +
            "8 - Retrieve a portfolio\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Quiting....\n", out.toString());
  }

  /**
   * A test to check the appropriate functioning of the portfolio for invalid entries.
   */
  @Test
  public void testInvalidPortfolio() {
    Reader in = new StringReader("32 q");
    StringBuilder out = new StringBuilder();
    StringBuilder log = new StringBuilder();
    List<Stock> temp = new ArrayList<>();
    temp.add(new StockImpl("test", 10, 3));
    Control control = new ControlImpl(in, out);
    control.execute(new MockModelPortfolio(log, "abc", 90.47, temp));
    assertEquals("", log.toString());
    assertEquals("Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio.\n" +
            "2 - Show all portfolio.\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total value of a portfolio\n" +
            "6 - Check total value of a portfolio by a given date\n" +
            "7 - Save a portfolio\n" +
            "8 - Retrieve a portfolio\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Invalid command!\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio.\n" +
            "2 - Show all portfolio.\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total value of a portfolio\n" +
            "6 - Check total value of a portfolio by a given date\n" +
            "7 - Save a portfolio\n" +
            "8 - Retrieve a portfolio\n" +
            "q/Q - quit the system.\n" +
            "\n" +
            "Quiting....\n", out.toString());
  }
}