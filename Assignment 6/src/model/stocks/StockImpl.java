package model.stocks;

import java.time.LocalDate;

/**
 * A class to represent the implementation of stock's name, number of shares
 * and price per share of the stock, besides the calculations that
 * can be performed on the stocks.
 */
public class StockImpl extends StockAbs implements Stock {
  /**
   * A constructor to initialise the stock object.
   *
   * @param name   name of the stock.
   * @param shares number of shares of the stock held.
   * @param price  cost per share.
   */
  public StockImpl(String name, double shares, double price) {
    super(name, shares, price);
  }

  /**
   * A method to get the last modified date of the stock.
   *
   * @return date of last modification.
   */
  @Override
  public LocalDate getDate() {
    return null;
  }

  /**
   * A method to get the commission costed to the user.
   *
   * @return commission for the stock.
   */
  @Override
  public double getCommission() {
    return 0;
  }

  /**
   * A method to check if the stock is of type SELL or BUY.
   *
   * @return true is stock is of type SELL, else false.
   */
  @Override
  public boolean isSell() {
    return false;
  }

  /**
   * A method to set that the stock is of type SELL or BUY.
   */
  @Override
  public void setSell() {
    //Irrelevant to this instance of the stock.
  }

  /**
   * A method to update the date to the latest modification date.
   *
   * @param date date to be modified to.
   */
  @Override
  public void updateDate(LocalDate date) {
    //Irrelevant to this instance of the stock.
  }

  /**
   * A method to add the commission when the stock is modified.
   *
   * @param com new commission to be added to the existing commission.
   */
  @Override
  public void addCommission(double com) {
    //Irrelevant to this instance of the stock.
  }

  /**
   * A method create a copy of the stock.
   *
   * @param isSell to reference if the stock is of BUY type or SELL.
   * @return new formed Stock object.
   */
  @Override
  public Stock copy(boolean isSell) {
    return new StockImpl(this.getStockName(), this.getNoOfShares(), this.getCost());
  }
}
