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
   * A method to add shares to an existing stock.
   */
  void addShares(double shares);

  /**
   * A method to get the last modified date of the stock.
   *
   * @return date of last modification.
   */
  LocalDate getDate();

  /**
   * A method to get the commission costed to the user.
   *
   * @return commission for the stock.
   */
  double getCommission();

  /**
   * A method to check if the stock is of type SELL or BUY.
   *
   * @return true is stock is of type SELL, else false.
   */
  boolean isSell();

  /**
   * A method to set that the stock is of type SELL or BUY.
   */
  void setSell();

  /**
   * A method to update the date to the latest modification date.
   *
   * @param date date to be modified to.
   */
  void updateDate(LocalDate date);

  /**
   * A method to add the commission when the stock is modified.
   *
   * @param com new commission to be added to the existing commission.
   */
  void addCommission(double com);

  /**
   * A method create a copy of the stock.
   *
   * @param isSell to reference if the stock is of BUY type or SELL.
   * @return new formed Stock object.
   */
  Stock copy(boolean isSell);

  double getCostAtDate(LocalDate date) throws IllegalArgumentException;
}
