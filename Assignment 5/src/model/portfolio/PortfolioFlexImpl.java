package model.portfolio;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import model.stocks.Stock;

/**
 * A class that represents the flexible portfolio implementation.
 */
public class PortfolioFlexImpl extends PortfolioAbs implements Portfolio {
  private final List<Stock> transactionHistory;

  /**
   * A constructor to initialize the flexible portfolio object instance.
   */
  public PortfolioFlexImpl(String name, List<Stock> stocks, List<Stock> transactionHistory) {
    super(name, stocks);
    this.transactionHistory = transactionHistory;
  }

  /**
   * A method to return the transaction history.
   *
   * @return list of stocks based on the order of transaction.
   */
  @Override
  public List<Stock> getTransactionHistory() {
    return this.transactionHistory;
  }

  /**
   * A method to get the total cost of the portfolio.
   *
   * @return total cost of the ticker in the portfolio.
   */
  @Override
  public double getPortfolioCost() {
    return this.getPortfolioCostAtDate(LocalDate.now());
  }

  /**
   * A method to get the total cost of the portfolio on a particular date.
   *
   * @return total cost of tickers in the portfolio on a particular date.
   */
  @Override
  public double getPortfolioCostAtDate(LocalDate date) {
    double num = 0.0;
    for (Stock stock : this.transactionHistory) {
      if (stock.getDate().isBefore(date)) {
        if (!stock.isSell()) {
          num += (stock.getCost()
                  * stock.getNoOfShares()) + stock.getCommission();
        } else {
          num += stock.getCommission();
        }
      }
    }
    return num;
  }

  @Override
  public double getValueOfStock() {
    return this.getValueOfStockAtDate(LocalDate.now());
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
    for (Stock stock : this.transactionHistory) {
      if (stock.getDate().isBefore(date)) {
        if (stock.isSell()) {
          num -= stock.getPriceAtDate(date)
                  * stock.getNoOfShares();
        } else {
          num += stock.getPriceAtDate(date)
                  * stock.getNoOfShares();
        }
      }
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
