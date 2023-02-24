package view;

import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import controller.Control;
import controller.ControlImpl;
import model.portfolio.MockModelPortfolio;
import model.stocks.Stock;
import model.stocks.StockImpl;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit class to test the functioning of the view class.
 */
public class CommandLineImplTest {
  /**
   * A test to check the functioning of the view for total value on a date.
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
   * A test to check the functioning of the view for saving and retrieving.
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
}