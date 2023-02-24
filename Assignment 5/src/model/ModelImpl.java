package model;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.xpath.XPathExpressionException;

import model.file.FileOperation;
import model.file.FileOperationFlexImpl;
import model.file.FileOperationImpl;
import model.portfolio.Portfolio;
import model.portfolio.PortfolioFlexImpl;
import model.portfolio.PortfolioImpl;
import model.stocks.Stock;
import model.stocks.StockFlexImpl;
import model.stocks.StockImpl;

/**
 * The main model of the system, used to perform all the necessary operation.
 */
public class ModelImpl implements Model {
  private final List<Portfolio> mPortfolios;
  private List<Stock> mStocks;
  private boolean type;
  private List<Stock> transactionHistory;

  /**
   * A constructor to initialize the object instance to default values.
   */
  public ModelImpl() {
    this.mPortfolios = new ArrayList<>();
    this.mStocks = new ArrayList<>();
    this.transactionHistory = new ArrayList<>();
    this.type = false;
  }

  /**
   * A method to set the type of portfolio being created.
   */
  @Override
  public void setType() {
    this.type = true;
  }

  /**
   * A function to finalize the portfolio.
   *
   * @param name name of the portfolio.
   * @return if the portfolio was created successfully or not.
   */
  @Override
  public boolean finalisePortfolio(String name) {
    Portfolio temp;
    if (this.type) {
      temp = new PortfolioFlexImpl(name, this.mStocks, this.transactionHistory);
    } else {
      temp = new PortfolioImpl(name, this.mStocks);
    }
    for (Portfolio p : this.mPortfolios) {
      if (p.getName().equals(temp.getName())) {
        return false;
      }
    }
    this.mPortfolios.add(temp);
    this.mStocks = new ArrayList<>();
    this.transactionHistory = new ArrayList<>();
    return true;
  }

  /**
   * A method to load a portfolio to memory to modify.
   *
   * @param name               name of the portfolio to be loaded.
   * @param stocks             the stocks in the portfolio.
   * @param transactionHistory the transaction history of the portfolio.
   */
  @Override
  public void loadPortfolio(String name, List<Stock> stocks, List<Stock> transactionHistory) {
    this.mStocks = stocks;
    this.transactionHistory = transactionHistory;
  }

  /**
   * A method to delete a portfolio.
   *
   * @param i the location of the portfolio in the list.
   */
  @Override
  public void removePortfolio(int i) {
    this.mPortfolios.remove(i);
  }

  /**
   * A method to create a stock of inflexible type.
   *
   * @param name   name of share(ticker value).
   * @param shares number of shares.
   * @param price  cost of each share.
   * @return if the stock was successfully created or not.
   */
  @Override
  public boolean createStock(String name, double shares, double price) {
    Stock st;
    try {
      st = new StockImpl(name, shares, price);
    } catch (Exception e) {
      return false;
    }
    for (Stock stock : this.mStocks) {
      if (stock.getStockName().equals(st.getStockName())) {
        stock.updatePrice(st.getNoOfShares(), st.getCost());
        stock.addShares(st.getNoOfShares());
        return true;
      }
    }
    this.mStocks.add(st);
    return true;
  }

  /**
   * A method to create a stock of flexible type.
   *
   * @param name   name of share(ticker value).
   * @param shares number of shares.
   * @param price  cost of each share.
   * @param date   date of transaction.
   * @param com    commission paid to the broker.
   * @param val    if the stock is of buy type or sell type.
   * @return if the stock was successfully created or not.
   */
  @Override
  public boolean createStockFlex(String name, double shares, double price,
                                 LocalDate date, double com, boolean val)
          throws IllegalArgumentException {
    Stock st;
    try {
      st = new StockFlexImpl(name, shares, price, date, com);
    } catch (Exception e) {
      return false;
    }
    for (Stock stock : this.mStocks) {
      if (stock.getStockName().equals(st.getStockName())) {
        if (val) {
          stock.addShares(-st.getNoOfShares());
          stock.addCommission(st.getCommission());
          st.setSell();
        } else {
          stock.updatePrice(st.getNoOfShares(), st.getCost());
          stock.updateDate(st.getDate());
          stock.addShares(st.getNoOfShares());
          stock.addCommission(st.getCommission());
        }
        this.transactionHistory.add(st.copy(val));
        return true;
      }
    }
    if (val) {
      throw new IllegalArgumentException();
    }
    this.mStocks.add(st);
    this.transactionHistory.add(st.copy(false));
    return true;
  }

  /**
   * A method to fetch the portfolios in memory thus far.
   *
   * @return list of portfolios.
   */
  @Override
  public List<Portfolio> getMPortfolio() {
    return this.mPortfolios;
  }

  /**
   * A method to return the cost of a portfolio.
   *
   * @param name name of the portfolio.
   * @return cost of the portfolio.
   */
  @Override
  public double getPortfolioCost(String name) {
    double value = 0;
    for (Portfolio p : this.mPortfolios) {
      if (p.getName().equals(name)) {
        value = p.getPortfolioCost();
      }
    }
    return value;
  }

  /**
   * A method to return the cost of a portfolio on a particular date.
   *
   * @param name name of the portfolio.
   * @param date date for which cost is to be calculated for.
   * @return cost of the portfolio.
   */
  @Override
  public double getPortfolioCostAtDate(String name, LocalDate date) {
    double value = 0;
    for (Portfolio p : this.mPortfolios) {
      if (p.getName().equals(name)) {
        value = p.getPortfolioCostAtDate(date);
      }
    }
    return value;
  }

  /**
   * A method to return the value of a portfolio.
   *
   * @param name name of the portfolio.
   * @return value of the portfolio as of current day.
   */
  @Override
  public double getPortfolioValue(String name) {
    double value = 0;
    for (Portfolio p : this.mPortfolios) {
      if (p.getName().equals(name)) {
        value = p.getValueOfStock();
      }
    }
    return value;
  }

  /**
   * A method to return the value of a portfolio on a particular date.
   *
   * @param name name of the portfolio.
   * @param date date for which cost is to be calculated for.
   * @return value of the portfolio as of the date mentioned.
   */
  @Override
  public double getPortfolioValueAtDate(String name, LocalDate date) {
    double value = 0;
    for (Portfolio p : this.mPortfolios) {
      if (p.getName().equals(name)) {
        value = p.getValueOfStockAtDate(date);
      }
    }
    return value;
  }

  /**
   * A method to save the portfolio.
   */
  @Override
  public void savePortfolio() {
    FileOperation fp;
    if (this.type) {
      fp = new FileOperationFlexImpl();
    } else {
      fp = new FileOperationImpl();
    }
    for (Portfolio p : this.mPortfolios) {
      fp.saveFile(p);
    }
  }

  /**
   * A method to retrieve the portfolio from storage.
   *
   * @param name name of the portfolio in storage.
   * @throws XPathExpressionException in case of an error in path.
   */
  @Override
  public void retrievePortfolio(String name) throws XPathExpressionException {
    FileOperation fp;
    if (this.type) {
      fp = new FileOperationFlexImpl();
    } else {
      fp = new FileOperationImpl();
    }
    this.mPortfolios.add(fp.retrieveFile(name));
  }

  /**
   * A methid to visualize the portfolio in the form of a bar chart.
   *
   * @param portfolio portfolio to be visualized.
   * @param startDate start date of visualization.
   * @param endDate   end date of visualization.
   * @return a mapped object of the data to be displayed.
   */
  @Override
  public Map<Object, Double> visualize(Portfolio portfolio,
                                       LocalDate startDate, LocalDate endDate) {
    Map<Object, Double> dataDaily = new TreeMap<>();
    for (LocalDate i = startDate; i.isBefore(endDate); i = i.plusDays(1)) {
      double value = 0.0;
      for (Stock s : portfolio.getStockList()) {
        if (!i.isBefore(s.getDate())) {
          double price = s.getPriceAtDate(i);
          if (price == 0.0) {
            price = s.getPriceAtDate(i.minusDays(1));
          }
          if (price == 0.0) {
            price = s.getPriceAtDate(i.minusDays(2));
          }
          value += (price * s.getNoOfShares()) * (1 - (s.getCommission() / 100));
        }
      }
      if (value != 0.0) {
        dataDaily.put(i, value);
      }
    }

    if (dataDaily.values().size() < 5) {
      return null;
    }

    if (dataDaily.size() <= 30) {
      return dataDaily;
    }

    Map<Object, Double> dataMonthly = new TreeMap<>();
    for (Map.Entry<Object, Double> e : dataDaily.entrySet()) {
      LocalDate date = (LocalDate) e.getKey();
      YearMonth key = YearMonth.of(date.getYear(), date.getMonth());
      Double value = dataMonthly.getOrDefault(key, 0.0);
      value += e.getValue();
      dataMonthly.put(key, value);
    }

    if (dataMonthly.size() <= 30) {
      return dataMonthly;
    }

    Map<Object, Double> dataMonthly3 = new TreeMap<>();

    int counter = 0;
    double val = 0;

    for (Map.Entry<Object, Double> e : dataMonthly.entrySet()) {
      if (counter == 2) {
        dataMonthly3.put(e.getKey(), val);
        counter = 0;
        val = 0;
      } else {
        val += e.getValue();
        counter++;
      }
    }

    if (dataMonthly3.size() <= 30) {
      return dataMonthly3;
    }

    Map<Object, Double> dataYearly = new TreeMap<>();
    for (Map.Entry<Object, Double> e : dataDaily.entrySet()) {
      LocalDate date = (LocalDate) e.getKey();
      String key = String.valueOf(date.getYear());
      Double value = dataYearly.getOrDefault(key, 0.0);
      value += e.getValue();
      dataYearly.put(key, value);
    }

    if (dataYearly.size() <= 30) {
      return dataYearly;
    }
    return null;
  }
}
