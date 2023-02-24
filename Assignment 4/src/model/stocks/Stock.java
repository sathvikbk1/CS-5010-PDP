package model.stocks;

import java.time.LocalDate;

/**
 * An interface to represent the calculations
 * which can be performed on the stock, including calculation
 * of cost, current price, etc.
 */
public interface Stock {
  /**
   * A method to return the name of the stock/ticker.
   *
   * @return String format of the ticker.
   */
  String getStockName();

  /**
   * A method to return the number of shares of the stock held.
   *
   * @return number of shares.
   */
  double getNoOfShares();

  /**
   * A method to get the cost of the ticker per stock.
   *
   * @return cost per stock.
   */
  double getCost();

  /**
   * A method to identify the current price of the stock.
   *
   * @return current price per stock.
   */
  double getPrice();

  /**
   * /**
   * A method to identify the price of the stock at a particular date.
   *
   * @param date the date for which the price is to be retrieved.
   * @return the price per stock for the required date.
   * @throws IllegalArgumentException when date is invalid.
   */
  double getPriceAtDate(LocalDate date) throws IllegalArgumentException;

  /**
   * A method to update the cost per share of the ticker.
   *
   * @param shares the number of new shares added.
   * @param price  the new price the shares were bought at.
   */
  void updatePrice(double shares, double price);

  /**
   * A method to add extra shares bought on an existing stock.
   *
   * @param shares extra shares added.
   */
  void addShares(double shares);
}
