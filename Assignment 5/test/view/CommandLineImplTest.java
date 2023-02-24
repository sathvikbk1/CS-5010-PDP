package view;

import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controller.Control;
import controller.ControlImpl;
import model.MockModelImpl;
import model.portfolio.Portfolio;
import model.portfolio.PortfolioFlexImpl;
import model.portfolio.PortfolioImpl;
import model.stocks.Stock;
import model.stocks.StockFlexImpl;
import model.stocks.StockImpl;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit class to test the functioning of the view class.
 */
public class CommandLineImplTest {
  private List<Portfolio> portfolio1 = new ArrayList<>();
  private List<Stock> stocks1 = new ArrayList<>();
  private List<Portfolio> portfolio2 = new ArrayList<>();
  private List<Stock> stocks2 = new ArrayList<>();

  @Before
  public void setUp() {
    Stock st = new StockImpl("GOOGL", 10, 88);
    Stock stock = new StockFlexImpl("GOOGL", 10, 88, LocalDate.parse("2022-11-08"), 10);
    stocks1.add(st);
    Portfolio p = new PortfolioImpl("ABC", stocks1);
    portfolio1.add(p);
    stocks2.add(stock);
    Portfolio port = new PortfolioFlexImpl("XYZ", stocks2, stocks2);
    portfolio2.add(port);
  }

  /**
   * A test to check the functioning of the view for total value on a date.
   */
  @Test
  public void testGetDateVal() {
    Reader in = new StringReader("1 1 1 googl 10 10 f abc 6 abc 2022-11-01 q");
    StringBuilder out = new StringBuilder();
    StringBuilder log = new StringBuilder();
    List<Stock> temp = new ArrayList<>();
    temp.add(new StockImpl("test", 10, 3));
    Control control = new ControlImpl(in, out);
    control.execute(new MockModelImpl(log, portfolio1, stocks1, 10, true));
    assertEquals("The stock to be added in inflexible portfolio: GOOGL\n" +
            "The Number of share added: 10.0\n" +
            "The price of each stock: 10.0\n" +
            "The name of the portfolio: ABC\n" +
            "The value of portfolio: abc\n", log.toString());
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
            "Invalid Command.\n" +
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
   * A test to check the functioning of the view for saving and retrieving.
   */
  @Test
  public void testChart() {
    Reader in = new StringReader("2 1 1 ibm 10 139 2022-11-08 10 f " +
            "xyz 12 xyz 2022-11-05 2022-11-15 q");
    StringBuilder out = new StringBuilder();
    StringBuilder log = new StringBuilder();
    Control control = new ControlImpl(in, out);
    control.execute(new MockModelImpl(log, portfolio2, stocks2, 10, true));
    assertEquals("Setting type to Flexible Portfolio\n" +
            "Setting type to Flexible Portfolio\n" +
            "The stock to be added in the flexible portfolio: IBM\n" +
            "The Number of share added: 10.0\n" +
            "The price of each stock: 139.0\n" +
            "Date of transaction: 2022-11-08\n" +
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
            "Enter the name for the Portfolio: \n" +
            "Portfolio does not exist!\n" +
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
            "Invalid Command.\n" +
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
            "Invalid Command.\n" +
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
}