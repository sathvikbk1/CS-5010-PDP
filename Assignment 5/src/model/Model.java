package model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.xml.xpath.XPathExpressionException;

import model.portfolio.Portfolio;
import model.stocks.Stock;

/**
 * An interface that replicates the model which is responsible
 * for performing all the operations.
 */
public interface Model {
  /**
   * A method to set the type of portfolio being created.
   */
  void setType();

  /**
   * A function to finalize the portfolio.
   *
   * @param name name of the portfolio.
   * @return if the portfolio was created successfully or not.
   */
  boolean finalisePortfolio(String name);

  /**
   * A method to load a portfolio to memory to modify.
   *
   * @param name               name of the portfolio to be loaded.
   * @param stocks             the stocks in the portfolio.
   * @param transactionHistory the transaction history of the portfolio.
   */
  void loadPortfolio(String name, List<Stock> stocks, List<Stock> transactionHistory);

  /**
   * A method to delete a portfolio.
   *
   * @param i the location of the portfolio in the list.
   */
  void removePortfolio(int i);

  /**
   * A method to create a stock of inflexible type.
   *
   * @param name   name of share(ticker value).
   * @param shares number of shares.
   * @param price  cost of each share.
   * @return if the stock was successfully created or not.
   */
  boolean createStock(String name, double shares, double price);

  /**
   * A method to create a stock of flexible type.
   *
   * @param name   name of share(ticker value).
   * @param shares number of shares.
   * @param price  cost of each share.
   * @param date   date of transaction.
   * @param com    commission paid to the broker.
   * @param val    if the stock is of buy type or sell type.
   * @return if the stock was successfully created or not.
   */
  boolean createStockFlex(String name, double shares, double price,
                          LocalDate date, double com, boolean val);

  /**
   * A method to fetch the portfolios in memory thus far.
   *
   * @return list of portfolios.
   */
  List<Portfolio> getMPortfolio();

  /**
   * A method to return the cost of a portfolio.
   *
   * @param name name of the portfolio.
   * @return cost of the portfolio.
   */
  double getPortfolioCost(String name);

  /**
   * A method to return the cost of a portfolio on a particular date.
   *
   * @param name name of the portfolio.
   * @param date date for which cost is to be calculated for.
   * @return cost of the portfolio.
   */
  double getPortfolioCostAtDate(String name, LocalDate date);

  /**
   * A method to return the value of a portfolio.
   *
   * @param name name of the portfolio.
   * @return value of the portfolio as of current day.
   */
  double getPortfolioValue(String name);

  /**
   * A method to return the value of a portfolio on a particular date.
   *
   * @param name name of the portfolio.
   * @param date date for which cost is to be calculated for.
   * @return value of the portfolio as of the date mentioned.
   */
  double getPortfolioValueAtDate(String name, LocalDate date);

  /**
   * A method to save the portfolio.
   */
  void savePortfolio();

  /**
   * A method to retrieve the portfolio from storage.
   *
   * @param name name of the portfolio in storage.
   * @throws XPathExpressionException in case of an error in path.
   */
  void retrievePortfolio(String name) throws XPathExpressionException;

  /**
   * A methid to visualize the portfolio in the form of a bar chart.
   *
   * @param portfolio portfolio to be visualized.
   * @param startDate start date of visualization.
   * @param endDate   end date of visualization.
   * @return a mapped object of the data to be displayed.
   */
  Map<Object, Double> visualize(Portfolio portfolio, LocalDate startDate, LocalDate endDate);
}
