package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.MockModelImpl;
import model.portfolio.Portfolio;
import model.portfolio.PortfolioFlexImpl;
import model.portfolio.PortfolioImpl;
import model.stocks.Stock;
import model.stocks.StockFlexImpl;
import model.stocks.StockImpl;

import static org.junit.Assert.assertEquals;

/**
 * A test class to verify the appropriate functioning
 * of the controller .
 */
public class ControlImplTest {
  private final List<Portfolio> Portfolio1 = new ArrayList<>();
  private final List<Stock> Stocks1 = new ArrayList<>();
  private final List<Portfolio> Portfolio2 = new ArrayList<>();
  private final List<Stock> Stocks2 = new ArrayList<>();

  @Before
  public void setUp() {
    Stock st = new StockImpl("abc", 10, 20);
    Stock stock = new StockFlexImpl("abc", 10, 88, LocalDate.parse("2022-11-08"), 10);
    Stocks1.add(st);
    Portfolio p = new PortfolioImpl("ABC", Stocks1);
    Portfolio1.add(p);
    Stocks2.add(stock);
    Portfolio port = new PortfolioFlexImpl("XYZ", Stocks2, Stocks2);
    Portfolio2.add(port);
  }

  /**
   * A test to check the functioning of the portfolio for name.
   */
  @Test
  public void testNamePortfolio() {
    Reader in = new StringReader("1 1 1 IBM 10 88 f abc 2 q");
    StringBuilder out = new StringBuilder();
    StringBuilder log = new StringBuilder();

    Control control = new ControlImpl(in, out);
    control.execute(new MockModelImpl(log, Portfolio1, Stocks1, 10, true));
    assertEquals("The stock to be added in inflexible portfolio: IBM\n" +
            "The Number of share added: 10.0\n" +
            "The price of each stock: 88.0\n" +
            "The name of the portfolio: ABC\n" +
            "Returns the list of portfolios!\n", log.toString());
    assertEquals("Enter the type of portfolio to be created:\n" +
            "1 - Inflexible Portfolio\n" +
            "2 - Flexible portfolio\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio\n" +
            "2 - Show all portfolio\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total cost of portfolio on a given date\n" +
            "6 - Check total value of a portfolio\n" +
            "7 - Check total value of a portfolio on a given date\n" +
            "8 - Save a portfolio\n" +
            "9 - Retrieve a portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Enter the stock symbol: \n" +
            "Enter the Number of Shares: \n" +
            "Enter the price of each share: \n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Creating Portfolio....\n" +
            "Enter the name for the Portfolio: \n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio\n" +
            "2 - Show all portfolio\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total cost of portfolio on a given date\n" +
            "6 - Check total value of a portfolio\n" +
            "7 - Check total value of a portfolio on a given date\n" +
            "8 - Save a portfolio\n" +
            "9 - Retrieve a portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Portfolio Name: ABC\n" +
            "Stock name: abc\n" +
            "Number Of Shares: 10.0\n" +
            "Price Per Share: $20.0\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio\n" +
            "2 - Show all portfolio\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total cost of portfolio on a given date\n" +
            "6 - Check total value of a portfolio\n" +
            "7 - Check total value of a portfolio on a given date\n" +
            "8 - Save a portfolio\n" +
            "9 - Retrieve a portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Quiting....\n", out.toString());
  }

  /**
   * A test to check the functioning of the portfolio for name.
   */
  @Test
  public void testNamePortfolioFlex() {
    Reader in = new StringReader("2 1 1 IBM 10 139 2022-11-09 10 f abc 2 q");
    StringBuilder out = new StringBuilder();
    StringBuilder log = new StringBuilder();

    Control control = new ControlImpl(in, out);
    control.execute(new MockModelImpl(log, Portfolio2, Stocks2, 10, true));
    assertEquals("Setting type to Flexible Portfolio\n" +
            "Setting type to Flexible Portfolio\n" +
            "The stock to be added in the flexible portfolio: IBM\n" +
            "The Number of share added: 10.0\n" +
            "The price of each stock: 139.0\n" +
            "Date of transaction: 2022-11-09\n" +
            "Commission of broker: 10.0\n" +
            "The name of the portfolio: XYZ\n" +
            "Returns the list of portfolios!\n", log.toString());
    assertEquals("Enter the type of portfolio to be created:\n" +
            "1 - Inflexible Portfolio\n" +
            "2 - Flexible portfolio\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1   - Create a new portfolio\n" +
            "2   - Show all portfolio\n" +
            "3   - Show detail of a portfolio\n" +
            "4   - Check total cost of portfolio\n" +
            "5   - Check total cost of portfolio on a given date\n" +
            "6   - Check total value of a portfolio\n" +
            "7   - Check total value of a portfolio on a given date\n" +
            "8   - Save a portfolio\n" +
            "9   - Retrieve a portfolio\n" +
            "10  - Load a portfolio\n" +
            "11  - Remove a portfolio\n" +
            "12  - Plot the portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "2 - Sell an existing stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Enter the stock symbol: \n" +
            "Enter the Number of Shares: \n" +
            "Enter the price of each share: \n" +
            "Enter the custom date in yyyy-mm-dd format: \n" +
            "Enter the commission: \n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "2 - Sell an existing stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Finalising Portfolio....\n" +
            "Enter the name for the Portfolio: \n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1   - Create a new portfolio\n" +
            "2   - Show all portfolio\n" +
            "3   - Show detail of a portfolio\n" +
            "4   - Check total cost of portfolio\n" +
            "5   - Check total cost of portfolio on a given date\n" +
            "6   - Check total value of a portfolio\n" +
            "7   - Check total value of a portfolio on a given date\n" +
            "8   - Save a portfolio\n" +
            "9   - Retrieve a portfolio\n" +
            "10  - Load a portfolio\n" +
            "11  - Remove a portfolio\n" +
            "12  - Plot the portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Portfolio Name: XYZ\n" +
            "Stock name: abc\n" +
            "Number Of Shares: 10.0\n" +
            "Price Per Share: $88.0\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1   - Create a new portfolio\n" +
            "2   - Show all portfolio\n" +
            "3   - Show detail of a portfolio\n" +
            "4   - Check total cost of portfolio\n" +
            "5   - Check total cost of portfolio on a given date\n" +
            "6   - Check total value of a portfolio\n" +
            "7   - Check total value of a portfolio on a given date\n" +
            "8   - Save a portfolio\n" +
            "9   - Retrieve a portfolio\n" +
            "10  - Load a portfolio\n" +
            "11  - Remove a portfolio\n" +
            "12  - Plot the portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Quiting....\n", out.toString());
  }

  /**
   * A test to check the functioning of the portfolio for getting the stock list.
   */
  @Test
  public void testGetStockList() {
    Reader in = new StringReader("1 1 1 googl 10 5 f abc 3 abc q");
    StringBuilder out = new StringBuilder();
    StringBuilder log = new StringBuilder();
    Control control = new ControlImpl(in, out);
    control.execute(new MockModelImpl(log, Portfolio1, Stocks1, 10, true));
    assertEquals("The stock to be added in inflexible portfolio: GOOGL\n" +
            "The Number of share added: 10.0\n" +
            "The price of each stock: 5.0\n" +
            "The name of the portfolio: ABC\n" +
            "Returns the list of portfolios!\n", log.toString());
    assertEquals("Enter the type of portfolio to be created:\n" +
            "1 - Inflexible Portfolio\n" +
            "2 - Flexible portfolio\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio\n" +
            "2 - Show all portfolio\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total cost of portfolio on a given date\n" +
            "6 - Check total value of a portfolio\n" +
            "7 - Check total value of a portfolio on a given date\n" +
            "8 - Save a portfolio\n" +
            "9 - Retrieve a portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Enter the stock symbol: \n" +
            "Enter the Number of Shares: \n" +
            "Enter the price of each share: \n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Creating Portfolio....\n" +
            "Enter the name for the Portfolio: \n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio\n" +
            "2 - Show all portfolio\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total cost of portfolio on a given date\n" +
            "6 - Check total value of a portfolio\n" +
            "7 - Check total value of a portfolio on a given date\n" +
            "8 - Save a portfolio\n" +
            "9 - Retrieve a portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Enter the name for the Portfolio: \n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio\n" +
            "2 - Show all portfolio\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total cost of portfolio on a given date\n" +
            "6 - Check total value of a portfolio\n" +
            "7 - Check total value of a portfolio on a given date\n" +
            "8 - Save a portfolio\n" +
            "9 - Retrieve a portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Quiting....\n", out.toString());
  }

  /**
   * A test to check the functioning of the portfolio for checking cost of portfolio.
   */
  @Test
  public void testGetPortfolioCost() {
    Reader in = new StringReader("1 1 1 googl 10 10 f abc 4 abc q");
    StringBuilder out = new StringBuilder();
    StringBuilder log = new StringBuilder();
    List<Stock> temp = new ArrayList<>();
    temp.add(new StockImpl("test", 10, 3));
    Control control = new ControlImpl(in, out);
    control.execute(new MockModelImpl(log, Portfolio1, temp, 10, true));
    assertEquals("The stock to be added in inflexible portfolio: GOOGL\n" +
            "The Number of share added: 10.0\n" +
            "The price of each stock: 10.0\n" +
            "The name of the portfolio: ABC\n" +
            "The cost of portfolio: abc\n", log.toString());
    assertEquals("Enter the type of portfolio to be created:\n" +
            "1 - Inflexible Portfolio\n" +
            "2 - Flexible portfolio\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio\n" +
            "2 - Show all portfolio\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total cost of portfolio on a given date\n" +
            "6 - Check total value of a portfolio\n" +
            "7 - Check total value of a portfolio on a given date\n" +
            "8 - Save a portfolio\n" +
            "9 - Retrieve a portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Enter the stock symbol: \n" +
            "Enter the Number of Shares: \n" +
            "Enter the price of each share: \n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Creating Portfolio....\n" +
            "Enter the name for the Portfolio: \n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio\n" +
            "2 - Show all portfolio\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total cost of portfolio on a given date\n" +
            "6 - Check total value of a portfolio\n" +
            "7 - Check total value of a portfolio on a given date\n" +
            "8 - Save a portfolio\n" +
            "9 - Retrieve a portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Enter the name for the Portfolio: \n" +
            "Total Cost of Stocks in Portfolio abc: $10.0\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio\n" +
            "2 - Show all portfolio\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total cost of portfolio on a given date\n" +
            "6 - Check total value of a portfolio\n" +
            "7 - Check total value of a portfolio on a given date\n" +
            "8 - Save a portfolio\n" +
            "9 - Retrieve a portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Quiting....\n", out.toString());
  }

  /**
   * A test to check the functioning of the portfolio for total value.
   */
  @Test
  public void testTotalVal() {
    Reader in = new StringReader("1 1 1 googl 10 10 f kwj 7 kwj 2022-11-10 q");
    StringBuilder out = new StringBuilder();
    StringBuilder log = new StringBuilder();
    Control control = new ControlImpl(in, out);
    control.execute(new MockModelImpl(log, Portfolio1, Stocks1, 10, true));
    assertEquals("The stock to be added in inflexible portfolio: GOOGL\n" +
            "The Number of share added: 10.0\n" +
            "The price of each stock: 10.0\n" +
            "The name of the portfolio: ABC\n" +
            "The value of portfolio: kwj on date: 2022-11-10\n", log.toString());
    assertEquals("Enter the type of portfolio to be created:\n" +
            "1 - Inflexible Portfolio\n" +
            "2 - Flexible portfolio\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio\n" +
            "2 - Show all portfolio\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total cost of portfolio on a given date\n" +
            "6 - Check total value of a portfolio\n" +
            "7 - Check total value of a portfolio on a given date\n" +
            "8 - Save a portfolio\n" +
            "9 - Retrieve a portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Enter the stock symbol: \n" +
            "Enter the Number of Shares: \n" +
            "Enter the price of each share: \n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Creating Portfolio....\n" +
            "Enter the name for the Portfolio: \n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio\n" +
            "2 - Show all portfolio\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total cost of portfolio on a given date\n" +
            "6 - Check total value of a portfolio\n" +
            "7 - Check total value of a portfolio on a given date\n" +
            "8 - Save a portfolio\n" +
            "9 - Retrieve a portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Enter the name for the Portfolio: \n" +
            "Enter the custom date in yyyy-mm-dd format: \n" +
            "Total Cost of Stocks in Portfolio kwj: $10.0\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio\n" +
            "2 - Show all portfolio\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total cost of portfolio on a given date\n" +
            "6 - Check total value of a portfolio\n" +
            "7 - Check total value of a portfolio on a given date\n" +
            "8 - Save a portfolio\n" +
            "9 - Retrieve a portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Quiting....\n", out.toString());
  }

  /**
   * A test to check the functioning of the portfolio for total value on a date.
   */
  @Test
  public void testGetDateVal() {
    Reader in = new StringReader("1 1 1 googl 10 10 f abc 7 abc 2022-11-01 q");
    StringBuilder out = new StringBuilder();
    StringBuilder log = new StringBuilder();
    List<Stock> temp = new ArrayList<>();
    temp.add(new StockImpl("test", 10, 3));
    Control control = new ControlImpl(in, out);
    control.execute(new MockModelImpl(log, Portfolio1, temp, 10, true));
    assertEquals("The stock to be added in inflexible portfolio: GOOGL\n" +
            "The Number of share added: 10.0\n" +
            "The price of each stock: 10.0\n" +
            "The name of the portfolio: ABC\n" +
            "The value of portfolio: abc on date: 2022-11-01\n", log.toString());
    assertEquals("Enter the type of portfolio to be created:\n" +
            "1 - Inflexible Portfolio\n" +
            "2 - Flexible portfolio\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio\n" +
            "2 - Show all portfolio\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total cost of portfolio on a given date\n" +
            "6 - Check total value of a portfolio\n" +
            "7 - Check total value of a portfolio on a given date\n" +
            "8 - Save a portfolio\n" +
            "9 - Retrieve a portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Enter the stock symbol: \n" +
            "Enter the Number of Shares: \n" +
            "Enter the price of each share: \n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Creating Portfolio....\n" +
            "Enter the name for the Portfolio: \n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio\n" +
            "2 - Show all portfolio\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total cost of portfolio on a given date\n" +
            "6 - Check total value of a portfolio\n" +
            "7 - Check total value of a portfolio on a given date\n" +
            "8 - Save a portfolio\n" +
            "9 - Retrieve a portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Enter the name for the Portfolio: \n" +
            "Enter the custom date in yyyy-mm-dd format: \n" +
            "Total Cost of Stocks in Portfolio abc: $10.0\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio\n" +
            "2 - Show all portfolio\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total cost of portfolio on a given date\n" +
            "6 - Check total value of a portfolio\n" +
            "7 - Check total value of a portfolio on a given date\n" +
            "8 - Save a portfolio\n" +
            "9 - Retrieve a portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Quiting....\n", out.toString());
  }

  /**
   * A test to check the functioning of the portfolio for saving and retrieving.
   */
  @Test
  public void testSaveAndRetrieve() {
    Reader in = new StringReader("1 1 1 googl 10 10 f xyz 8 9 xyz 2 q");
    StringBuilder out = new StringBuilder();
    StringBuilder log = new StringBuilder();
    Control control = new ControlImpl(in, out);
    control.execute(new MockModelImpl(log, Portfolio1, Stocks1, 10, true));
    assertEquals("The stock to be added in inflexible portfolio: GOOGL\n" +
            "The Number of share added: 10.0\n" +
            "The price of each stock: 10.0\n" +
            "The name of the portfolio: ABC\n" +
            "Saves all portfolios in loaded in memory.\n" +
            "Retrieves portfolios in storage onto memory, if exists.\n" +
            "Returns the list of portfolios!\n", log.toString());
    assertEquals("Enter the type of portfolio to be created:\n" +
            "1 - Inflexible Portfolio\n" +
            "2 - Flexible portfolio\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio\n" +
            "2 - Show all portfolio\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total cost of portfolio on a given date\n" +
            "6 - Check total value of a portfolio\n" +
            "7 - Check total value of a portfolio on a given date\n" +
            "8 - Save a portfolio\n" +
            "9 - Retrieve a portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Enter the stock symbol: \n" +
            "Enter the Number of Shares: \n" +
            "Enter the price of each share: \n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Creating Portfolio....\n" +
            "Enter the name for the Portfolio: \n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio\n" +
            "2 - Show all portfolio\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total cost of portfolio on a given date\n" +
            "6 - Check total value of a portfolio\n" +
            "7 - Check total value of a portfolio on a given date\n" +
            "8 - Save a portfolio\n" +
            "9 - Retrieve a portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio\n" +
            "2 - Show all portfolio\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total cost of portfolio on a given date\n" +
            "6 - Check total value of a portfolio\n" +
            "7 - Check total value of a portfolio on a given date\n" +
            "8 - Save a portfolio\n" +
            "9 - Retrieve a portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Enter the name of the portfolio to be retrieved: \n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio\n" +
            "2 - Show all portfolio\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total cost of portfolio on a given date\n" +
            "6 - Check total value of a portfolio\n" +
            "7 - Check total value of a portfolio on a given date\n" +
            "8 - Save a portfolio\n" +
            "9 - Retrieve a portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Portfolio Name: ABC\n" +
            "Stock name: abc\n" +
            "Number Of Shares: 10.0\n" +
            "Price Per Share: $20.0\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1 - Create a new portfolio\n" +
            "2 - Show all portfolio\n" +
            "3 - Show detail of a portfolio\n" +
            "4 - Check total cost of portfolio\n" +
            "5 - Check total cost of portfolio on a given date\n" +
            "6 - Check total value of a portfolio\n" +
            "7 - Check total value of a portfolio on a given date\n" +
            "8 - Save a portfolio\n" +
            "9 - Retrieve a portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Quiting....\n", out.toString());
  }

  /**
   * A test to check the functioning of the stock for all its functionalities.
   */
  @Test
  public void testStockCreationAndSaveRetreive() {
    Reader in = new StringReader("2 1 1 googl 10 89 2022-11-09 10 f xyz 8 9 xyz 2 q");
    StringBuilder out = new StringBuilder();
    StringBuilder otherLog = new StringBuilder();
    Control control = new ControlImpl(in, out);
    control.execute(new MockModelImpl(otherLog, Portfolio2, Stocks2, 10, true));
    assertEquals("Setting type to Flexible Portfolio\n" +
                    "Setting type to Flexible Portfolio\n" +
                    "The stock to be added in the flexible portfolio: GOOGL\n" +
                    "The Number of share added: 10.0\n" +
                    "The price of each stock: 89.0\n" +
                    "Date of transaction: 2022-11-09\n" +
                    "Commission of broker: 10.0\n" +
                    "The name of the portfolio: XYZ\n" +
                    "Saves all portfolios in loaded in memory.\n" +
                    "Retrieves portfolios in storage onto memory, if exists.\n" +
                    "Returns the list of portfolios!\n"
            , otherLog.toString());
    assertEquals("Enter the type of portfolio to be created:\n" +
            "1 - Inflexible Portfolio\n" +
            "2 - Flexible portfolio\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1   - Create a new portfolio\n" +
            "2   - Show all portfolio\n" +
            "3   - Show detail of a portfolio\n" +
            "4   - Check total cost of portfolio\n" +
            "5   - Check total cost of portfolio on a given date\n" +
            "6   - Check total value of a portfolio\n" +
            "7   - Check total value of a portfolio on a given date\n" +
            "8   - Save a portfolio\n" +
            "9   - Retrieve a portfolio\n" +
            "10  - Load a portfolio\n" +
            "11  - Remove a portfolio\n" +
            "12  - Plot the portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "2 - Sell an existing stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Enter the stock symbol: \n" +
            "Enter the Number of Shares: \n" +
            "Enter the price of each share: \n" +
            "Enter the custom date in yyyy-mm-dd format: \n" +
            "Enter the commission: \n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "2 - Sell an existing stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Finalising Portfolio....\n" +
            "Enter the name for the Portfolio: \n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1   - Create a new portfolio\n" +
            "2   - Show all portfolio\n" +
            "3   - Show detail of a portfolio\n" +
            "4   - Check total cost of portfolio\n" +
            "5   - Check total cost of portfolio on a given date\n" +
            "6   - Check total value of a portfolio\n" +
            "7   - Check total value of a portfolio on a given date\n" +
            "8   - Save a portfolio\n" +
            "9   - Retrieve a portfolio\n" +
            "10  - Load a portfolio\n" +
            "11  - Remove a portfolio\n" +
            "12  - Plot the portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1   - Create a new portfolio\n" +
            "2   - Show all portfolio\n" +
            "3   - Show detail of a portfolio\n" +
            "4   - Check total cost of portfolio\n" +
            "5   - Check total cost of portfolio on a given date\n" +
            "6   - Check total value of a portfolio\n" +
            "7   - Check total value of a portfolio on a given date\n" +
            "8   - Save a portfolio\n" +
            "9   - Retrieve a portfolio\n" +
            "10  - Load a portfolio\n" +
            "11  - Remove a portfolio\n" +
            "12  - Plot the portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Enter the name of the portfolio to be retrieved: \n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1   - Create a new portfolio\n" +
            "2   - Show all portfolio\n" +
            "3   - Show detail of a portfolio\n" +
            "4   - Check total cost of portfolio\n" +
            "5   - Check total cost of portfolio on a given date\n" +
            "6   - Check total value of a portfolio\n" +
            "7   - Check total value of a portfolio on a given date\n" +
            "8   - Save a portfolio\n" +
            "9   - Retrieve a portfolio\n" +
            "10  - Load a portfolio\n" +
            "11  - Remove a portfolio\n" +
            "12  - Plot the portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Portfolio Name: XYZ\n" +
            "Stock name: abc\n" +
            "Number Of Shares: 10.0\n" +
            "Price Per Share: $88.0\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1   - Create a new portfolio\n" +
            "2   - Show all portfolio\n" +
            "3   - Show detail of a portfolio\n" +
            "4   - Check total cost of portfolio\n" +
            "5   - Check total cost of portfolio on a given date\n" +
            "6   - Check total value of a portfolio\n" +
            "7   - Check total value of a portfolio on a given date\n" +
            "8   - Save a portfolio\n" +
            "9   - Retrieve a portfolio\n" +
            "10  - Load a portfolio\n" +
            "11  - Remove a portfolio\n" +
            "12  - Plot the portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Quiting....\n", out.toString());
  }

  /**
   * A test to check the functioning of the stock for loading a portfolio and removing a portfolio.
   */
  @Test
  public void testLoadAndRemove() {
    Reader in = new StringReader("2 1 1 googl 10 89 2022-11-09 10 f xyz 10 xyz " +
            "9 xyz 1 1 googl 10 90 2022-11-10 10 f 2 q");
    StringBuilder out = new StringBuilder();
    StringBuilder otherLog = new StringBuilder();
    Control control = new ControlImpl(in, out);
    control.execute(new MockModelImpl(otherLog, Portfolio2, Stocks2, 10, true));
    assertEquals("Setting type to Flexible Portfolio\n" +
                    "Setting type to Flexible Portfolio\n" +
                    "The stock to be added in the flexible portfolio: GOOGL\n" +
                    "The Number of share added: 10.0\n" +
                    "The price of each stock: 89.0\n" +
                    "Date of transaction: 2022-11-09\n" +
                    "Commission of broker: 10.0\n" +
                    "The name of the portfolio: XYZ\n" +
                    "Returns the list of portfolios!\n" +
                    "Setting type to Flexible Portfolio\n" +
                    "The stock to be added in the flexible portfolio: GOOGL\n" +
                    "The Number of share added: 10.0\n" +
                    "The price of each stock: 90.0\n" +
                    "Date of transaction: 2022-11-10\n" +
                    "Commission of broker: 10.0\n" +
                    "The name of the portfolio: XYZ\n"
            , otherLog.toString());
    assertEquals("Enter the type of portfolio to be created:\n" +
            "1 - Inflexible Portfolio\n" +
            "2 - Flexible portfolio\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1   - Create a new portfolio\n" +
            "2   - Show all portfolio\n" +
            "3   - Show detail of a portfolio\n" +
            "4   - Check total cost of portfolio\n" +
            "5   - Check total cost of portfolio on a given date\n" +
            "6   - Check total value of a portfolio\n" +
            "7   - Check total value of a portfolio on a given date\n" +
            "8   - Save a portfolio\n" +
            "9   - Retrieve a portfolio\n" +
            "10  - Load a portfolio\n" +
            "11  - Remove a portfolio\n" +
            "12  - Plot the portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "2 - Sell an existing stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Enter the stock symbol: \n" +
            "Enter the Number of Shares: \n" +
            "Enter the price of each share: \n" +
            "Enter the custom date in yyyy-mm-dd format: \n" +
            "Enter the commission: \n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "2 - Sell an existing stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Finalising Portfolio....\n" +
            "Enter the name for the Portfolio: \n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1   - Create a new portfolio\n" +
            "2   - Show all portfolio\n" +
            "3   - Show detail of a portfolio\n" +
            "4   - Check total cost of portfolio\n" +
            "5   - Check total cost of portfolio on a given date\n" +
            "6   - Check total value of a portfolio\n" +
            "7   - Check total value of a portfolio on a given date\n" +
            "8   - Save a portfolio\n" +
            "9   - Retrieve a portfolio\n" +
            "10  - Load a portfolio\n" +
            "11  - Remove a portfolio\n" +
            "12  - Plot the portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Enter the name for the Portfolio: \n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "2 - Sell an existing stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Invalid command!\n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "2 - Sell an existing stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Invalid command!\n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "2 - Sell an existing stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Enter the stock symbol: \n" +
            "Invalid stock name or price.\n" +
            "\n" +
            "Enter the stock symbol: \n" +
            "Enter the Number of Shares: \n" +
            "Enter the price of each share: \n" +
            "Enter the custom date in yyyy-mm-dd format: \n" +
            "Enter the commission: \n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "2 - Sell an existing stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Finalising Portfolio....\n" +
            "Enter the name for the Portfolio: \n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1   - Create a new portfolio\n" +
            "2   - Show all portfolio\n" +
            "3   - Show detail of a portfolio\n" +
            "4   - Check total cost of portfolio\n" +
            "5   - Check total cost of portfolio on a given date\n" +
            "6   - Check total value of a portfolio\n" +
            "7   - Check total value of a portfolio on a given date\n" +
            "8   - Save a portfolio\n" +
            "9   - Retrieve a portfolio\n" +
            "10  - Load a portfolio\n" +
            "11  - Remove a portfolio\n" +
            "12  - Plot the portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Quiting....\n", out.toString());
  }

  /**
   * A test to check the functioning of the stock in case an invalid date is entered.
   */
  @Test
  public void testInvalidDate() {
    Reader in = new StringReader("2 1 1 googl 10 89 2023-11-09 2022-11-09 10 f xyz 10 xyz " +
            "9 xyz 1 1 googl 10 90 2022-11-10 10 f 2 q");
    StringBuilder out = new StringBuilder();
    StringBuilder otherLog = new StringBuilder();
    Control control = new ControlImpl(in, out);
    control.execute(new MockModelImpl(otherLog, Portfolio2, Stocks2, 10, true));
    assertEquals("Setting type to Flexible Portfolio\n" +
                    "Setting type to Flexible Portfolio\n" +
                    "The stock to be added in the flexible portfolio: GOOGL\n" +
                    "The Number of share added: 10.0\n" +
                    "The price of each stock: 89.0\n" +
                    "Date of transaction: 2022-11-09\n" +
                    "Commission of broker: 10.0\n" +
                    "The name of the portfolio: XYZ\n" +
                    "Returns the list of portfolios!\n" +
                    "Setting type to Flexible Portfolio\n" +
                    "The stock to be added in the flexible portfolio: GOOGL\n" +
                    "The Number of share added: 10.0\n" +
                    "The price of each stock: 90.0\n" +
                    "Date of transaction: 2022-11-10\n" +
                    "Commission of broker: 10.0\n" +
                    "The name of the portfolio: XYZ\n"
            , otherLog.toString());
    assertEquals("Enter the type of portfolio to be created:\n" +
            "1 - Inflexible Portfolio\n" +
            "2 - Flexible portfolio\n" +
            "\n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1   - Create a new portfolio\n" +
            "2   - Show all portfolio\n" +
            "3   - Show detail of a portfolio\n" +
            "4   - Check total cost of portfolio\n" +
            "5   - Check total cost of portfolio on a given date\n" +
            "6   - Check total value of a portfolio\n" +
            "7   - Check total value of a portfolio on a given date\n" +
            "8   - Save a portfolio\n" +
            "9   - Retrieve a portfolio\n" +
            "10  - Load a portfolio\n" +
            "11  - Remove a portfolio\n" +
            "12  - Plot the portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "2 - Sell an existing stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Enter the stock symbol: \n" +
            "Enter the Number of Shares: \n" +
            "Enter the price of each share: \n" +
            "Enter the custom date in yyyy-mm-dd format: \n" +
            "Invalid date entered!\n" +
            "Enter the custom date in yyyy-mm-dd format: \n" +
            "Enter the commission: \n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "2 - Sell an existing stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Finalising Portfolio....\n" +
            "Enter the name for the Portfolio: \n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1   - Create a new portfolio\n" +
            "2   - Show all portfolio\n" +
            "3   - Show detail of a portfolio\n" +
            "4   - Check total cost of portfolio\n" +
            "5   - Check total cost of portfolio on a given date\n" +
            "6   - Check total value of a portfolio\n" +
            "7   - Check total value of a portfolio on a given date\n" +
            "8   - Save a portfolio\n" +
            "9   - Retrieve a portfolio\n" +
            "10  - Load a portfolio\n" +
            "11  - Remove a portfolio\n" +
            "12  - Plot the portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Enter the name for the Portfolio: \n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "2 - Sell an existing stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Invalid command!\n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "2 - Sell an existing stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Invalid command!\n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "2 - Sell an existing stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Enter the stock symbol: \n" +
            "Invalid stock name or price.\n" +
            "\n" +
            "Enter the stock symbol: \n" +
            "Enter the Number of Shares: \n" +
            "Enter the price of each share: \n" +
            "Enter the custom date in yyyy-mm-dd format: \n" +
            "Enter the commission: \n" +
            "Enter the corresponding to the operation to be performed:\n" +
            "1 - Add a new stock\n" +
            "2 - Sell an existing stock\n" +
            "f/F - close portfolio\n" +
            "\n" +
            "Finalising Portfolio....\n" +
            "Enter the name for the Portfolio: \n" +
            "Enter the number corresponding to the operation to be performed:\n" +
            "1   - Create a new portfolio\n" +
            "2   - Show all portfolio\n" +
            "3   - Show detail of a portfolio\n" +
            "4   - Check total cost of portfolio\n" +
            "5   - Check total cost of portfolio on a given date\n" +
            "6   - Check total value of a portfolio\n" +
            "7   - Check total value of a portfolio on a given date\n" +
            "8   - Save a portfolio\n" +
            "9   - Retrieve a portfolio\n" +
            "10  - Load a portfolio\n" +
            "11  - Remove a portfolio\n" +
            "12  - Plot the portfolio\n" +
            "q/Q - quit the system\n" +
            "\n" +
            "Quiting....\n", out.toString());
  }
}