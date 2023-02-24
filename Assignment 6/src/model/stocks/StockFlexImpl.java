package model.stocks;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * A class to represent the stock implementation in the flexible portfolio.
 */
public class StockFlexImpl extends StockAbs implements Stock {
  private LocalDate date;
  private boolean sell;
  private double commission;

  /**
   * A constructor to initialize the stock object instance.
   *
   * @param name       name of the stock(ticker value).
   * @param noOfShares number of shares.
   * @param price      cost of each share.
   * @param date       date of transaction.
   * @param com        commission paid for the transaction.
   */
  public StockFlexImpl(String name, double noOfShares, double price,
                       LocalDate date, double com) {
    super(name, noOfShares, price);
    this.date = validateDate(date);
    this.sell = false;
    this.commission = com;
    validateStock();
  }

  /**
   * A constructor to initialize the common attributes of the stock object.
   *
   * @param name          name of the stock.
   * @param priceAllotted price allotted for the purchase.
   * @param date          date pf purchase.
   * @param com           commission for the transaction.
   */
  public StockFlexImpl(String name, double priceAllotted, LocalDate date, double com) {
    super(name, priceAllotted, date);
    this.date = validateDate(date);
    this.commission = com;
    this.sell = false;
  }

  /**
   * A method to get the last modified date of the stock.
   *
   * @return date of last modification.
   */
  @Override
  public LocalDate getDate() {
    return this.date;
  }

  /**
   * A method to get the commission costed to the user.
   *
   * @return commission for the stock.
   */
  @Override
  public double getCommission() {
    return this.commission;
  }

  /**
   * A method to check if the stock is of type SELL or BUY.
   *
   * @return true is stock is of type SELL, else false.
   */
  @Override
  public boolean isSell() {
    return this.sell;
  }

  /**
   * A method to set that the stock is of type SELL or BUY.
   */
  @Override
  public void setSell() {
    this.sell = true;
  }

  /**
   * A method to update the date to the latest modification date.
   *
   * @param date date to be modified to.
   */
  @Override
  public void updateDate(LocalDate date) {
    this.date = this.date.isAfter(date) ? this.date : date;
  }

  /**
   * A method to add the commission when the stock is modified.
   *
   * @param com new commission to be added to the existing commission.
   */
  @Override
  public void addCommission(double com) {
    this.commission += commission;
  }

  private void validateStock() throws IllegalArgumentException {
    HashMap<LocalDate, Double[]> history = this.getHistory();
    System.out.println(history.get(this.getDate())[2]);
    if (this.getCost() <= history.get(this.getDate())[1]
            && this.getCost() >= history.get(this.getDate())[2]) {
      return;
    }
    throw new IllegalArgumentException();
  }

  private LocalDate validateDate(LocalDate date) {
    if (date == null) {
      throw new IllegalArgumentException("Date cannot be null.");
    }
    if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
      date = date.plusDays(2);
    } else if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
      date = date.plusDays(1);
    }
    try {
      double result = this.getHistory().get(date)[3];
    } catch (NullPointerException e) {
      return this.validateDate(date.plusDays(1));
    }
    return date;
  }

  /**
   * A method create a copy of the stock.
   *
   * @param isSell to reference if the stock is of BUY type or SELL.
   * @return new formed Stock object.
   */
  @Override
  public Stock copy(boolean isSell) {
    Stock st = new StockFlexImpl(this.getStockName(), this.getNoOfShares(),
            this.getCost(), this.date, this.commission);
    if (isSell) {
      st.setSell();
    }
    return st;
  }
}
