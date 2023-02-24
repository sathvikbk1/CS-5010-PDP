package view;

import java.time.LocalDate;
import java.util.Map;

import model.portfolio.Portfolio;

/**
 * An interface to communicate with the display medium.
 */
public interface CommandLine {
  /**
   * A method to initialise the menu for the inflexible portfolio.
   */
  void commandPrompt();

  /**
   * A method to initialize the menu for the flexible portfolio.
   */
  void commandPromptFlex();

  void startStrategy();

  void appendWeight();

  /**
   * A method to request the user for the portfolio name.
   */
  void appendPortfolioName();

  void appendStrategyName();

  /**
   * A method to add the ticker symbol.
   */
  void appendStockSymbol();

  /**
   * A method to request the existing portfolio name.
   */
  void getPortfolioName();

  /**
   * A method to request the user to enter the commission.
   */
  void appendCommission();

  /**
   * A method to request the user to enter the number of shares.
   */
  void appendShares();

  /**
   * A method to request the user to enter the cost of the stock per share.
   */
  void appendCost();

  /**
   * A method to display invalid values when an exception is thrown.
   */
  void invalidStock();

  /**
   * A method to print the appended message into the display screen.
   *
   * @param message the string to be displayed.
   */
  void appendMessage(String message);

  /**
   * A method to display the options when create stock for an
   * inflexible portfolio is invoked.
   */
  void createStock();

  /**
   * A method to display the options when create stock for an
   * flexible portfolio is invoked.
   */
  void createStockFlex();

  /**
   * A method to display the contents of the portfolio.
   *
   * @param p the portfolio whose contents are to be displayed.
   */
  void showPortfolio(Portfolio p);

  /**
   * A method to display that the portfolio name already exists.
   */
  void invalidPortfolioName();

  /**
   * A method to intimate that the portfolio name entered doesn't exist.
   */
  void wrongPortfolioName();

  /**
   * A method to request the date for which the price is to be calculated.
   */
  void getDate();

  /**
   * A method to display the total cost of the portfolio.
   *
   * @param pName name of the portfolio.
   * @param cost  total cost of the contents of the portfolio.
   */
  void showPortfolioCost(String pName, double cost);

  /**
   * A method to display that the file name/format is invalid.
   */
  void invalidFile();

  /**
   * A method to display that the date format is invalid.
   */
  void invalidDate();

  /**
   * A method to display that the number of share entered is not allowed.
   */
  void invalidShare();

  /**
   * A method to display that the command entered is invalid.
   */
  void invalidCommand();

  /**
   * A method to display that the stock entered is invalid.
   */
  void nonExistingStock();

  /**
   * A method to request the type of portfolio to be created.
   */
  void typeOfPortfolio();

  /**
   * A method to display that the data provided is insufficient.
   */
  void insufficientData();

  /**
   * A method to begin the visualisation of the portfolio.
   */
  void startVisualization(String name, LocalDate startDate, LocalDate endDate);

  /**
   * A method to visualise the portfolio.
   *
   * @param data the data to be visualized.
   */
  void printGraph(Map<Object, Double> data);
}
