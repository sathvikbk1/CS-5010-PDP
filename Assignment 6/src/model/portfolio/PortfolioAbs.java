package model.portfolio;

import java.time.LocalDate;
import java.util.List;

import model.stocks.Stock;

/**
 * An abstract class that represents a portfolio.
 */
public abstract class PortfolioAbs implements Portfolio {
  private String portfolioName;
  private List<Stock> stock;

  /**
   * Constructor to initialize the object instance.
   *
   * @param name   name of the portfolio.
   * @param stocks list of stocks in the portfolio.
   */
  public PortfolioAbs(String name, List<Stock> stocks) {
    if (name == null || name.length() == 0) {
      throw new IllegalArgumentException("Invalid Portfolio Name!");
    }
    this.portfolioName = name;
    this.stock = stocks;
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
    return this.stock;
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
