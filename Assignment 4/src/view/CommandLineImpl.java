package view;

import java.io.IOException;

import model.portfolio.Portfolio;
import model.stocks.Stock;

/**
 * A class that represents the way the user views the
 * results when displayed on the output medium.
 */
public class CommandLineImpl implements CommandLine {
  private final Appendable out;

  /**
   * A constructor to initialise the output console.
   *
   * @param out output console.
   * @throws IllegalArgumentException when out is null.
   */
  public CommandLineImpl(Appendable out) throws IllegalArgumentException {
    if (out == null) {
      throw new IllegalArgumentException("out cannot be null");
    }
    this.out = out;
  }

  /**
   * A method to initialise the menu.
   */
  @Override
  public void commandPrompt() {
    StringBuilder sb = new StringBuilder();
    sb.append("Enter the number corresponding to the operation to be performed:\n");
    sb.append("1 - Create a new portfolio\n");
    sb.append("2 - Show all portfolio\n");
    sb.append("3 - Show detail of a portfolio\n");
    sb.append("4 - Check total cost of portfolio\n");
    sb.append("5 - Check total value of a portfolio\n");
    sb.append("6 - Check total value of a portfolio by a given date\n");
    sb.append("7 - Save a portfolio\n");
    sb.append("8 - Retrieve a portfolio\n");
    sb.append("q/Q - quit the system\n");
    appendMessage(sb.toString());
  }

  /**
   * A method to request the user for the portfolio name.
   */
  @Override
  public void appendPortfolioName() {
    appendMessage("Enter the name for the Portfolio: ");
  }

  /**
   * A method to add the ticker symbol.
   */
  @Override
  public void appendStockSymbol() {
    appendMessage("Enter the stock symbol, number of shares and cost per stock: ");
  }

  /**
   * A method to display invalid values when an exception is thrown.
   */
  @Override
  public void invalidStock() {
    appendMessage("Invalid values.\n");
  }

  /**
   * A method to print the appended message into the display screen.
   *
   * @param message the string to be displayed.
   */
  @Override
  public void appendMessage(String message) {
    try {
      out.append(message);
      out.append("\n");
    } catch (IOException e) {
      throw new IllegalStateException("Append Fail");
    }
  }

  /**
   * A method to display the options when create stock is invoked.
   */
  @Override
  public void createStock() {
    StringBuilder sb = new StringBuilder();
    sb.append("Enter the corresponding to the operation to be performed:\n");
    sb.append("1 - Add a new stock\n");
    sb.append("f/F - close portfolio\n");
    appendMessage(sb.toString());
  }

  /**
   * A method to display the contents of the portfolio.
   *
   * @param p the portfolio whose contents are to be displayed.
   */
  @Override
  public void showPortfolio(Portfolio p) {
    appendMessage("Portfolio Name: " + p.getName());
    for (Stock st : p.getStockList()) {
      appendMessage("Stock name: " + st.getStockName()
              + "\nNumber Of Shares: " + st.getNoOfShares()
              + "\nPrice Per Share: " + st.getCost() + "\n");
    }
  }

  /**
   * A method to request the existing portfolio name.
   */
  @Override
  public void getPortfolioName() {
    appendMessage("Enter the name of the portfolio to be retrieved: ");
  }

  /**
   * A method to display that the portfolio name already exists.
   */
  @Override
  public void invalidPortfolioName() {
    appendMessage("Portfolio already exists.");
  }

  /**
   * A method to request the date for which the price is to be calculated.
   */
  @Override
  public void getDate() {
    appendMessage("Enter the custom date in yyyy-mm-dd format: ");
  }

  /**
   * A method to display the total cost of the portfolio.
   *
   * @param pName name of the portfolio.
   * @param cost  total cost of the contents of the portfolio.
   */
  @Override
  public void showPortfolioCost(String pName, double cost) {
    appendMessage("Total Cost of Stocks in Portfolio "
            + pName + ": " + cost + "\n");
  }

  /**
   * A method to display that the file format/location is invalid.
   */
  @Override
  public void invalidFile() {
    appendMessage("Invalid File format/location!");
  }

  /**
   * A method to display that the date entered is in the wrong format.
   */
  @Override
  public void invalidDate() {
    appendMessage("Invalid date entered!");
  }

  /**
   * A method to display that the number of share entered is not allowed.
   */
  @Override
  public void invalidShare() {
    appendMessage("Cannot enter fractional number of shares!\n"
            + "Enter number of shares again: ");
  }
}
