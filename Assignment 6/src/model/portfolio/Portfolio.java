package model.portfolio;

import java.time.LocalDate;
import java.util.List;

import model.plans.Strategy;
import model.stocks.Stock;

/**
 * An interface to represent a portfolio that is
 * capable of holding a list of stocks and a portfolio name.
 */
public interface Portfolio {

  /**
   * A method to get the name of the portfolio.
   *
   * @return name of portfolio.
   */
  String getName();

  /**
   * A method that returns the transaction history.
   *
   * @return list of stocks based on the order of transaction.
   */

  List<Stock> getTransactionHistory();

  /**
   * A method to get the list of stocks in the portfolio.
   *
   * @return list of all stocks added to the portfolio.
   */
  List<Stock> getStockList();

  /**
   * A method to get the total cost of the portfolio.
   *
   * @return total cost of tickers in the portfolio.
   */
  double getPortfolioCost();

  /**
   * A method to get the total cost of the portfolio on a particular date.
   *
   * @return total cost of tickers in the portfolio on a particular date.
   */
  double getPortfolioCostAtDate(LocalDate date);

  /**
   * A method to get the current value of the portfolio.
   *
   * @return total value of the portfolio on the present day.
   */
  double getValueOfStock();

  /**
   * A method to get the value of the portfolio on a particular day.
   *
   * @param date date for which the value is required.
   * @return total value of the portfolio for the given date.
   */
  double getValueOfStockAtDate(LocalDate date);

  List<Strategy> getStrategies();

  /**
   * A method to get the total number of stocks in the portfolio.
   *
   * @return number of stocks in the portfolio.
   */
  double getNoOfStocks();
}
