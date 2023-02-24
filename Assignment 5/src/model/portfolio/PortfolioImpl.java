package model.portfolio;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import model.stocks.Stock;

/**
 * A class to represent the implementation of the portfolio
 * wherein name of the portfolio and it's contents are stored
 * along with performing related calculations.
 */
public class PortfolioImpl extends PortfolioAbs implements Portfolio {
  /**
   * A constructor to initialize the portfolio object.
   *
   * @param name  holds the name of the portfolio.
   * @param stock holds the list of stocks in the portfolio.
   */
  public PortfolioImpl(String name, List<Stock> stock) {
    super(name, stock);
  }

  /**
   * A method to return the transaction history.
   *
   * @return list of stocks based on the order of transaction.
   */
  @Override
  public List<Stock> getTransactionHistory() {
    return null;
  }

  /**
   * A method to get the total cost of the portfolio.
   *
   * @return total cost of tickers in the portfolio.
   */
  @Override
  public double getPortfolioCost() {
    double num = 0.0;
    for (Stock stock : this.getStockList()) {
      num += stock.getCost()
              * stock.getNoOfShares();
    }
    return num;
  }

  /**
   * A method to get the total cost of the portfolio on a particular date.
   *
   * @return total cost of tickers in the portfolio on a particular date.
   */
  @Override
  public double getPortfolioCostAtDate(LocalDate date) {
    return 0;
  }

  /**
   * A method to get the value of the portfolio on a particular day.
   *
   * @param date date for which the value is required.
   * @return total value of the portfolio for the given date.
   */
  @Override
  public double getValueOfStockAtDate(LocalDate date) {
    if (date == null) {
      throw new IllegalArgumentException("Date cannot be null.");
    }
    if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
      date = date.minusDays(1);
    } else if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
      date = date.minusDays(2);
    }
    double num = 0.0;
    for (Stock stock : this.getStockList()) {
      num += stock.getPriceAtDate(date)
              * stock.getNoOfShares();
    }
    return num;
  }

  /**
   * A method to get the total number of stocks in the portfolio.
   *
   * @return number of stocks in the portfolio.
   */
  @Override
  public double getNoOfStocks() {
    double num = 0;
    for (Stock stock : this.getStockList()) {
      num += stock.getNoOfShares();
    }
    return num;
  }

}
