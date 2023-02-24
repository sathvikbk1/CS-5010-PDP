package model.portfolio;

import java.time.LocalDate;
import java.util.List;

import model.stocks.Stock;

/**
 * A mock model to verify the proper input and
 * functioning of the portfolio class.
 */
public class MockModelPortfolio implements Portfolio {
  private StringBuilder log;
  private final String uniqueName;
  private final double uniqueVal;
  private final List<Stock> uniqueStock;

  /**
   * A constructor to initialise the check variables.
   *
   * @param log         to keep track of the functions called in the portfolio class.
   * @param uniqueName  a name to verify if it remains the same.
   * @param uniqueVal   a value to verify if it is not manipulated.
   * @param uniqueStock a list of stocks to verify if it is not manipulated.
   */
  public MockModelPortfolio(StringBuilder log, String uniqueName,
                            double uniqueVal, List<Stock> uniqueStock) {
    this.log = log;
    this.uniqueName = uniqueName;
    this.uniqueVal = uniqueVal;
    this.uniqueStock = uniqueStock;
  }

  /**
   * A method to check if the values entering and
   * returned by the function are manipulated or not.
   *
   * @param name   name of the portfolio.
   * @param stocks the list of stocks to be held in the portfolio.
   * @return portfolio after check.
   */
  @Override
  public Portfolio createPortfolio(String name, List<Stock> stocks) {
    log.append("Created portfolio: " + name + "\n");
    return new MockModelPortfolio(this.log, this.uniqueName, this.uniqueVal, this.uniqueStock);
  }

  /**
   * A method to check if the name of the portfolio is
   * manipulated by the controller or not.
   *
   * @return the check name value.
   */
  @Override
  public String getName() {
    log.append("The name of the portfolio: " + this.uniqueName + "\n");
    return uniqueName;
  }

  /**
   * A method to check if the list of stocks is manipulated
   * by the controller or not.
   *
   * @return the unique stock for verification.
   */
  @Override
  public List<Stock> getStockList() {
    log.append("The current stocks in portfolio: " + this.uniqueStock.get(0).getStockName() + "\n");
    return uniqueStock;
  }

  /**
   * A method to check the portfolio cost is
   * manipulated by the controller or not.
   *
   * @return the unique value to verify.
   */
  @Override
  public double getPortfolioCost() {
    log.append("Cost of current portfolio: " + this.uniqueVal + "\n");
    return uniqueVal;
  }

  /**
   * A method to check the portfolio price is
   * manipulated by the controller or not.
   *
   * @return the unique value to verify.
   */
  @Override
  public double getValueOfStock() {
    log.append("Value of current portfolio: " + this.uniqueVal + "\n");
    return uniqueVal;
  }

  /**
   * A method to check the portfolio price at a particular date is
   * manipulated by the controller or not.
   *
   * @return the unique value to verify.
   */
  @Override
  public double getValueOfStockAtDate(LocalDate date) {
    log.append("The Date to check for: " + date + "\n");
    return uniqueVal;
  }

  /**
   * A method to check whether the number of shares in a portfolio is
   * manipulated by the controller or not.
   *
   * @return the unique value to verify.
   */
  @Override
  public double getNoOfStocks() {
    log.append("The number of stocks in portfolio: " + this.uniqueVal + "\n");
    return uniqueVal;
  }
}
