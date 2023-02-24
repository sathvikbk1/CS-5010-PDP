package model.stocks;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;

import model.api.ApiCall;
import model.api.ApiCallImpl;

/**
 * An abstract class that represents the
 * implementation of the stock in the portfolio.
 */
public abstract class StockAbs implements Stock {
  private final String stockName;
  private double noOfShares;
  private double price;

  /**
   * A constructor to initialize the common attributes of the stock object.
   *
   * @param name   name of the stock.
   * @param shares number of shares.
   * @param price  cost of the stock.
   */
  public StockAbs(String name, double shares, double price) {
    if (name == null || name.length() == 0 || shares <= 0.0) {
      throw new IllegalArgumentException("Invalid Stock name!!");
    }
    this.stockName = name;
    this.noOfShares = shares;
    this.price = price;
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
   * A method to update the cost per share of the ticker.
   *
   * @param shares the number of new shares added.
   * @param price  the new price the shares were bought at.
   */
  @Override
  public void updatePrice(double shares, double price) {
    this.price = ((this.getNoOfShares() * this.getCost())
            + (shares * price)) / (shares + this.getNoOfShares());
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
    double result = 0;
    if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
      date = date.minusDays(1);
    } else if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
      date = date.minusDays(2);
    }
    try {
      result = this.getHistory().get(date)[3];
    } catch (NullPointerException e) {
      return this.getPriceAtDate(date.minusDays(1));
    }
    return result;
  }

  /**
   * A Helper function perform the API call and retrieve the data.
   *
   * @return hash map of all closing prices for the ticker.
   */
  public HashMap<LocalDate, Double[]> getHistory() {
    ApiCall ap = new ApiCallImpl(this.stockName);
    return ap.getData();
  }

  private double checkCost(double cost) throws IllegalArgumentException {
    HashMap<LocalDate, Double[]> history = getHistory();
    if (cost <= history.get(this.getDate())[1] && cost >= history.get(this.getDate())[2]) {
      return cost;
    }
    throw new IllegalArgumentException();
  }
}
