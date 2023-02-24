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
public class PortfolioImpl implements Portfolio {
  private final String portfolioName;
  private final List<Stock> stocks;

  /**
   * A constructor to initialise the portfolio object to default values.
   */
  public PortfolioImpl() {
    this.portfolioName = "";
    this.stocks = null;
  }

  /**
   * A constructor to initialize the portfolio object.
   *
   * @param name  holds the name of the portfolio.
   * @param stock holds the list of stocks in the portfolio.
   */
  public PortfolioImpl(String name, List<Stock> stock) {
    if (name == null || name.length() == 0) {
      throw new IllegalArgumentException("Invalid Portfolio Name!");
    }
    this.portfolioName = name;
    this.stocks = stock;
  }

  /**
   * A method to create the portfolio object.
   *
   * @param name   name of the portfolio.
   * @param stocks the list of stocks to be held in the portfolio.
   * @return the portfolio object thus created.
   */
  @Override
  public Portfolio createPortfolio(String name, List<Stock> stocks) {
    Portfolio temp = new PortfolioImpl(name, stocks);
    return temp;
  }

  /**
   * A method to get the name of the portfolio.
   *
   * @return name of portfolio.
   */
  @Override
  public String getName() {
    return this.portfolioName;
  }

  /**
   * A method to get the list of stocks in the portfolio.
   *
   * @return list of all stocks added to the portfolio.
   */
  @Override
  public List<Stock> getStockList() {
    return this.stocks;
  }

  /**
   * A method to get the total cost of the portfolio.
   *
   * @return total cost of tickers in the portfolio.
   */
  @Override
  public double getPortfolioCost() {
    double num = 0.0;
    for (Stock stock : this.stocks) {
      num += stock.getCost()
              * stock.getNoOfShares();
    }
    return num;
  }

  /**
   * A method to get the current value of the portfolio.
   *
   * @return total value of the portfolio on the present day.
   */
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
    for (Stock stock : this.stocks) {
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
    for (Stock stock : this.stocks) {
      num += stock.getNoOfShares();
    }
    return num;
  }
}
