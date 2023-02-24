package model.stocks;

import java.time.LocalDate;

/**
 * A mock model to verify the proper input and
 * functioning of the stock class.
 */
public class MockModelStock implements Stock {
  private StringBuilder log;
  private String uniqueName;
  private double uniqueVals;

  /**
   * A constructor to initialise the check variables.
   *
   * @param log        to keep a track of the functions called in the stock class.
   * @param uniqueName a name to verify if it remains the same.
   * @param uniqueVals a value to verify if it is not manipulated.
   */
  public MockModelStock(StringBuilder log, String uniqueName, double uniqueVals) {
    this.log = log;
    this.uniqueName = uniqueName;
    this.uniqueVals = uniqueVals;
  }

  /**
   * A method to check if the name of the stock is
   * manipulated by the controller or not.
   *
   * @return the check name value.
   */
  @Override
  public String getStockName() {
    this.log.append("Adding stock name: " + this.uniqueName + "\n");
    return this.uniqueName;
  }

  /**
   * A method to check if the number of shares of a stock is
   * manipulated by the controller or not.
   *
   * @return the check name value.
   */
  @Override
  public double getNoOfShares() {
    this.log.append("Adding number of shares: " + this.uniqueVals + "\n");
    return this.uniqueVals;
  }

  /**
   * A method to check if the cost of the stock is
   * manipulated by the controller or not.
   *
   * @return the check name value.
   */
  @Override
  public double getCost() {
    this.log.append("Calculating total cost: " + this.uniqueVals + "\n");
    return this.uniqueVals;
  }

  /**
   * A method to check if the current price of the stock is
   * manipulated by the controller or not.
   *
   * @return the check name value.
   */
  @Override
  public double getPrice() {
    this.log.append("Calculate price of stock: " + this.uniqueVals + "\n");
    return this.uniqueVals;
  }

  /**
   * A method to check if the price of the stock on a particular day is
   * manipulated by the controller or not.
   *
   * @return the check name value.
   */
  @Override
  public double getPriceAtDate(LocalDate date) {
    this.log.append("Fetching price for date: " + date + "\n");
    return this.uniqueVals;
  }

  /**
   * A method to check if the update price of the stock is
   * called properly by the controller or not.
   */
  @Override
  public void updatePrice(double shares, double price) {
    this.log.append("Change of price: " + this.uniqueVals + "\n");
  }

  /**
   * A method to check if the new shares of the stock is
   * added appropriately by the controller or not.
   */
  @Override
  public void addShares(double shares) {
    this.log.append("Additional shares are added here\n");
  }
}
