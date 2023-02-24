package model.stocks;

import java.time.LocalDate;
import java.util.HashMap;

import model.api.ApiCallImpl;
import model.api.ApiCall;

/**
 * A class to represent the implementation of stock's name, number of shares
 * and price per share of the stock, besides the calculations that
 * can be performed on the stocks.
 */
public class StockImpl implements Stock {
  private final String stockName;
  private double noOfShares;
  private double price;

  /**
   * A constructor to initialise the stock object.
   *
   * @param name   name of the stock.
   * @param shares number of shares of the stock held.
   * @param price  cost per share.
   */
  public StockImpl(String name, double shares, double price) {
    if (name == null || name.length() == 0 || shares <= 0.0 || price <= 0.0) {
      throw new IllegalArgumentException("Invalid Stock name!!");
    }
    this.stockName = name;
    this.noOfShares = shares;
    this.price = price;
  }

  /**
   * A constructor to initialise the stock object.
   *
   * @param name   name of the stock.
   * @param shares number of shares of the stock bought, on current date.
   */
  public StockImpl(String name, double shares) {
    if (name == null || name.length() == 0) {
      throw new IllegalArgumentException("Invalid Stock name!!");
    }
    this.stockName = name;
    this.noOfShares = shares;
    this.price = this.getPrice();
  }

  /**
   * A method to return the name of the stock/ticker.
   *
   * @return String format of the ticker.
   */
  @Override
  public String getStockName() {
    return this.stockName;
  }

  /**
   * A method to return the number of shares of the stock held.
   *
   * @return number of shares.
   */
  @Override
  public double getNoOfShares() {
    return this.noOfShares;
  }

  /**
   * A method to get the cost of the ticker per stock.
   *
   * @return cost per stock.
   */
  @Override
  public double getCost() {
    return this.price;
  }

  /**
   * A method to identify the current price of the stock.
   *
   * @return current price per stock.
   */
  @Override
  public double getPrice() {
    return this.getPriceAtDate(LocalDate.now());
  }

  /**
   * /**
   * A method to identify the price of the stock at a particular date.
   *
   * @param date the date for which the price is to be retrieved.
   * @return the price per stock for the required date.
   * @throws IllegalArgumentException when date is invalid.
   */
  @Override
  public double getPriceAtDate(LocalDate date) throws IllegalArgumentException {
    if (date == null) {
      throw new IllegalArgumentException("Date cannot be null.");
    }
    try {
      return this.getHistory().get(date);
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("Invalid Date!");
    }
  }

  /**
   * A Helper function perform the API call and retrieve the data.
   *
   * @return hash map of all closing prices for the ticker.
   */
  private HashMap<LocalDate, Double> getHistory() {
    ApiCall ap = new ApiCallImpl(this.stockName);
    return ap.getData();
  }

  /**
   * A method to update the cost per share of the ticker.
   *
   * @param shares the number of new shares added.
   * @param price  the new price the shares were bought at.
   */
  @Override
  public void updatePrice(double shares, double price) {
    this.price = ((this.noOfShares * this.price) + (shares * price)) / (shares + this.noOfShares);
  }

  /**
   * A method to add extra shares bought on an existing stock.
   *
   * @param shares extra shares added.
   */
  @Override
  public void addShares(double shares) {
    this.noOfShares = this.getNoOfShares() + shares;
  }
}
