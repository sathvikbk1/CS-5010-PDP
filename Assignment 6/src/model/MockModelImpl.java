package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.xpath.XPathExpressionException;

import model.portfolio.Portfolio;
import model.stocks.Stock;

/**
 * A mock model to test that the controller isn't manipulating
 * the inputs before sending it to the model.
 */
public class MockModelImpl implements Model {
  private StringBuilder log;
  private List<Portfolio> mPortfolios;
  private boolean type;
  private double uniqueVal;

  /**
   * A constructor to initialize the mock model.
   *
   * @param log         to maintain log of methods traversed.
   * @param mPortfolios the portfolio list.
   * @param mStocks     stock list.
   * @param values      the unique value for verification.
   * @param type        the type of portfolio for validation.
   */
  public MockModelImpl(StringBuilder log, List<Portfolio> mPortfolios, List<Stock> mStocks,
                       double values, boolean type) {
    this.log = log;
    this.mPortfolios = mPortfolios;
    this.type = type;
    this.uniqueVal = values;
  }

  /**
   * A method to set the type of portfolio being created.
   */
  @Override
  public void setType() {
    log.append("Setting type to ");
    if (this.type) {
      log.append("Flexible Portfolio\n");
    }
  }

  /**
   * A function to finalize the portfolio.
   *
   * @param name name of the portfolio.
   * @return if the portfolio was created successfully or not.
   */
  @Override
  public boolean finalisePortfolio(String name) {
    for (Portfolio p : this.mPortfolios) {
      log.append(("The name of the portfolio: " + p.getName()) + "\n");
    }
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
    log.append("The name of the portfolio to be loaded: " + name);
    log.append("The stocks are ");
    for (Stock stock : stocks) {
      log.append(stock.getStockName());
    }
    log.append("\n");
  }

  /**
   * A method to delete a portfolio.
   *
   * @param i the location of the portfolio in the list.
   */
  @Override
  public void removePortfolio(int i) {
    log.append("The name of the portfolio to be removed: " + this.mPortfolios.get(i) + "\n");
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
    log.append("The stock to be added in inflexible portfolio: " + name
            + "\nThe Number of share added: " + shares + "\nThe price of each stock: "
            + price + "\n");
    return this.type;
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
                                 LocalDate date, double com, boolean val) {
    log.append("The stock to be added in the flexible portfolio: " + name
            + "\nThe Number of share added: "
            + shares + "\nThe price of each stock: " + price
            + "\nDate of transaction: " + date
            + "\nCommission of broker: " + com + "\n");
    return this.type;
  }

  @Override
  public void dollarCostAveraging(String name, HashMap<String, Double[]> values,
                                  LocalDate startDate, LocalDate endDate, LocalDate lastDate,
                                  int interval, double investmentPrice) {
    log.append("The strategy name: " + name
            + "\nStart Date: " + startDate
            + "\nEnd Date: " + endDate
            + "\nInterval: " + interval
            + "\nInvestment Price: " + investmentPrice + "\n");
  }

  /**
   * A method to fetch the portfolios in memory thus far.
   *
   * @return list of portfolios.
   */
  @Override
  public List<Portfolio> getMPortfolio() {
    log.append("Returns the list of portfolios!\n");
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
    log.append("The cost of portfolio: " + name + "\n");
    return this.uniqueVal;
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
    log.append("The cost of portfolio: " + name + " on date: " + date + "\n");
    return this.uniqueVal;
  }

  /**
   * A method to return the value of a portfolio.
   *
   * @param name name of the portfolio.
   * @return value of the portfolio as of current day.
   */
  @Override
  public double getPortfolioValue(String name) {
    log.append("The value of portfolio: " + name + "\n");
    return this.uniqueVal;
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
    log.append("The value of portfolio: " + name + " on date: " + date + "\n");
    return this.uniqueVal;
  }

  /**
   * A method to save the portfolio.
   */
  @Override
  public void savePortfolio() {
    log.append("Saves all portfolios in loaded in memory.\n");
  }

  /**
   * A method to retrieve the portfolio from storage.
   *
   * @param name name of the portfolio in storage.
   * @throws XPathExpressionException in case of an error in path.
   */
  @Override
  public void retrievePortfolio(String name) throws XPathExpressionException {
    log.append("Retrieves portfolios in storage onto memory, if exists.\n");
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
    log.append("Used to visualize the portfolio: " + portfolio.getName()
            + " between dates: " + startDate + " and " + endDate + "\n");
    return null;
  }
}
