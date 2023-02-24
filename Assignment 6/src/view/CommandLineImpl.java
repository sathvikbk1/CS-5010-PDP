package view;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

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
   * A method to initialise the menu for the inflexible portfolio.
   */
  @Override
  public void commandPrompt() {
    StringBuilder sb = new StringBuilder();
    sb.append("Enter the number corresponding to the operation to be performed:\n");
    sb.append("1 - Create a new portfolio\n");
    sb.append("2 - Show all portfolio\n");
    sb.append("3 - Show detail of a portfolio\n");
    sb.append("4 - Check total cost of portfolio\n");
    sb.append("5 - Check total cost of portfolio on a given date\n");
    sb.append("6 - Check total value of a portfolio\n");
    sb.append("7 - Check total value of a portfolio on a given date\n");
    sb.append("8 - Save a portfolio\n");
    sb.append("9 - Retrieve a portfolio\n");
    sb.append("q/Q - quit the system\n");
    appendMessage(sb.toString());
  }

  /**
   * A method to initialize the menu for the flexible portfolio.
   */
  @Override
  public void commandPromptFlex() {
    StringBuilder sb = new StringBuilder();
    sb.append("Enter the number corresponding to the operation to be performed:\n");
    sb.append("1   - Create a new portfolio\n");
    sb.append("2   - Show all portfolio\n");
    sb.append("3   - Show detail of a portfolio\n");
    sb.append("4   - Check total cost of portfolio\n");
    sb.append("5   - Check total cost of portfolio on a given date\n");
    sb.append("6   - Check total value of a portfolio\n");
    sb.append("7   - Check total value of a portfolio on a given date\n");
    sb.append("8   - Save a portfolio\n");
    sb.append("9   - Retrieve a portfolio\n");
    sb.append("10  - Load a portfolio\n");
    sb.append("11  - Remove a portfolio\n");
    sb.append("12  - Plot the portfolio\n");
    sb.append("q/Q - quit the system\n");
    appendMessage(sb.toString());
  }

  @Override
  public void startStrategy() {
    StringBuilder sb = new StringBuilder();
    sb.append("Enter the number corresponding to the operation to be performed: \n");
    sb.append("1 - Add new stock to strategy\n");
    sb.append("f/F - finalize strategy\n");
    appendMessage(sb.toString());
  }

  @Override
  public void appendWeight() {
    appendMessage("Enter the weight of the stock in the strategy in terms of percentage(%): ");
  }

  /**
   * A method to request the user for the portfolio name.
   */
  @Override
  public void appendPortfolioName() {
    appendMessage("Enter the name for the Portfolio: ");
  }

  /**
   * A method to request the user for the name of the strategy.
   */
  @Override
  public void appendStrategyName() {
    appendMessage("Enter a name for the plan: ");
  }

  /**
   * A method to add the ticker symbol.
   */
  @Override
  public void appendStockSymbol() {
    appendMessage("Enter the stock symbol: ");
  }

  /**
   * A method to request the user to enter the commission.
   */
  @Override
  public void appendCommission() {
    appendMessage("Enter the commission: ");
  }

  /**
   * A method to request the user to enter the number of shares.
   */
  @Override
  public void appendShares() {
    appendMessage("Enter the Number of Shares: ");
  }

  /**
   * A method to request the user to enter the cost of the stock per share.
   */
  @Override
  public void appendCost() {
    appendMessage("Enter the cost: ");
  }

  /**
   * A method to display invalid values when an exception is thrown.
   */
  @Override
  public void invalidStock() {
    appendMessage("Invalid stock name or price.\n");
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
   * A method to display the options when create stock for an
   * inflexible portfolio is invoked.
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
   * A method to display the options when create stock for an
   * flexible portfolio is invoked.
   */
  @Override
  public void createStockFlex() {
    StringBuilder sb = new StringBuilder();
    sb.append("Enter the corresponding to the operation to be performed:\n");
    sb.append("1 - Add a new stock\n");
    sb.append("2 - Sell an existing stock\n");
    sb.append("3 - Create new strategy\n");
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
              + "\nPrice Per Share: $" + st.getCost() + "\n");
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
   * A method to intimate that the portfolio name entered doesn't exist.
   */
  @Override
  public void wrongPortfolioName() {
    appendMessage("Portfolio does not exist!");
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
            + pName + ": $" + cost + "\n");
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

  /**
   * A method to display that the command entered is invalid.
   */
  @Override
  public void invalidCommand() {
    appendMessage("Invalid Command.\n");
  }

  /**
   * A method to display that the stock entered is invalid.
   */
  @Override
  public void nonExistingStock() {
    appendMessage("Stock does not exist in Portfolio to be sold!\n");
  }

  /**
   * A method to request the type of portfolio to be created.
   */
  @Override
  public void typeOfPortfolio() {
    StringBuilder sb = new StringBuilder();
    sb.append("Enter the type of portfolio to be created:\n");
    sb.append("1 - Inflexible Portfolio\n");
    sb.append("2 - Flexible portfolio\n");
    appendMessage(sb.toString());
  }

  /**
   * A method to display that the data provided is insufficient.
   */
  @Override
  public void insufficientData() {
    appendMessage("Insufficient Data!");
  }

  /**
   * A method to begin the visualisation of the portfolio.
   */
  @Override
  public void startVisualization(String name, LocalDate startDate, LocalDate endDate) {
    appendMessage(String.format("\nPerformance of portfolio %s from %s to %s\n",
            name,
            startDate.toString(),
            endDate.toString()));
  }

  /**
   * A method to visualise the portfolio.
   *
   * @param data the data to be visualized.
   */
  @Override
  public void printGraph(Map<Object, Double> data) {
    if (data.size() == 0) {
      this.insufficientData();
      return;
    }
    double max = data.values().stream().max(Double::compareTo).get();
    int scale = 10;
    while (max / scale > 50) {
      scale += 10;
    }

    for (Map.Entry<Object, Double> e : data.entrySet()) {
      int count = (int) Math.ceil(e.getValue() / scale);
      String bar = "*".repeat(count);
      this.appendMessage(String.format("%s: %s", e.getKey(), bar));
    }
    this.appendMessage(String.format("\nScale: * = $%d\n", scale));
  }
}
