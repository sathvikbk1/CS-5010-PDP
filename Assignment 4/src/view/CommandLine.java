package view;

import model.portfolio.Portfolio;

/**
 * An interface to communicate with the display medium.
 */
public interface CommandLine {
  /**
   * A method to initialise the menu.
   */
  void commandPrompt();

  /**
   * A method to request the user for the portfolio name.
   */
  void appendPortfolioName();

  /**
   * A method to add the ticker symbol.
   */
  void appendStockSymbol();

  /**
   * A method to request the existing portfolio name.
   */
  void getPortfolioName();

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
   * A method to display the options when create stock is invoked.
   */
  void createStock();

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
}
