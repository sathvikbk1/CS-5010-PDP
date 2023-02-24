package controller;

import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.portfolio.Portfolio;
import model.file.FileOperation;
import model.file.FileOperationImpl;
import model.stocks.Stock;
import model.stocks.StockImpl;
import view.CommandLine;
import view.CommandLineImpl;

/**
 * The class that helps in controlling the
 * entire flow of the program, walking through
 * various operations that could be performed.
 */
public class ControlImpl implements Control {
  private List<Portfolio> mPortfolios;
  private List<Stock> mStocks;
  private CommandLine commandLine;
  private Scanner scanner;

  /**
   * A constructor to initialize the objects of the controller.
   */
  public ControlImpl(Reader in, Appendable out) {
    this.mPortfolios = new ArrayList<>();
    this.mStocks = new ArrayList<>();
    this.commandLine = new CommandLineImpl(out);
    this.scanner = new Scanner(in);
  }

  /**
   * A method to start the execution of the program and
   * begin by printing the command line menu items.
   */
  @Override
  public void execute(Portfolio portfolio) {
    while (true) {
      this.commandLine.commandPrompt();
      String input = this.getInput().toLowerCase();
      switch (input) {
        case "q":
          this.commandLine.appendMessage("Quiting....");
          return;
        case "1":
          this.createPortfolio(portfolio);
          break;
        case "2":
          this.showPortfolios();
          break;
        case "3":
          this.showOnePortfolio();
          break;
        case "4":
          this.getCostOfPortfolio();
          break;
        case "5":
          this.getValueOfPortfolio();
          break;
        case "6":
          this.getValueOfPortfolioAtDate();
          break;
        case "7":
          this.savePortfolio();
          break;
        case "8":
          this.retrievePortfolio();
          break;
        default:
          this.commandLine.appendMessage("Invalid command!");
      }
    }
  }

  /**
   * A helper function to create the portfolio once all stocks
   * have been added.
   */
  private void createPortfolio(Portfolio portfolio) throws IllegalArgumentException {
    while (true) {
      this.commandLine.createStock();
      String input = this.getInput().toLowerCase();
      switch (input) {
        case "f":
          this.commandLine.appendMessage("Creating Portfolio....");
          finalisePortfolio(portfolio);
          return;
        case "1":
          this.createStock();
          break;
        default:
          this.commandLine.appendMessage("Invalid command!");
      }
    }
  }

  /**
   * A helper function validate the portfolio name and append to list.
   */
  private void finalisePortfolio(Portfolio temp) {
    temp = temp.createPortfolio(getPortfolioName(), this.mStocks);
    for (Portfolio p : this.mPortfolios) {
      if (p.getName().equals(temp.getName())) {
        this.commandLine.invalidPortfolioName();
        this.finalisePortfolio(temp);
        return;
      }
    }
    this.mPortfolios.add(temp);
    this.mStocks = new ArrayList<>();
  }

  /**
   * A helper function to create each stock entered.
   */
  private void createStock() {
    Stock st;
    try {
      st = new StockImpl(appendStockSymbol(), Double.parseDouble(shareCheck() + ""),
              Double.parseDouble(readNext()));
    } catch (Exception e) {
      this.commandLine.invalidStock();
      this.createStock();
      return;
    }
    for (Stock stock : this.mStocks) {
      if (stock.getStockName().equals(st.getStockName())) {
        stock.updatePrice(st.getNoOfShares(), st.getCost());
        stock.addShares(stock.getNoOfShares());
        return;
      }
    }
    this.mStocks.add(st);
  }

  private int shareCheck() {
    int temp;
    try {
      String st = readNext();
      temp = Integer.parseInt(st);
    } catch (NumberFormatException e) {
      this.commandLine.invalidShare();
      temp = shareCheck();
    }
    return temp;
  }

  /**
   * A helper function to show all the portfolios present.
   */
  private void showPortfolios() {
    for (Portfolio p : this.mPortfolios) {
      this.commandLine.showPortfolio(p);
    }
  }

  /**
   * A helper function to show a specific portfolio.
   */
  private void showOnePortfolio() {
    String portfolio = this.getPortfolioName();
    for (Portfolio p : this.mPortfolios) {
      if (p.getName().equals(portfolio)) {
        this.commandLine.showPortfolio(p);
      }
    }
  }

  /**
   * A helper function to show the total cost of a portfolio.
   */
  private void getCostOfPortfolio() {
    double value = 0.0;
    String portfolio = this.getPortfolioName();
    for (Portfolio p : this.mPortfolios) {
      if (p.getName().equals(portfolio)) {
        value = p.getPortfolioCost();
      }
    }
    this.commandLine.showPortfolioCost(portfolio, value);
  }

  /**
   * A helper function to show the current value of the portfolio.
   */
  private void getValueOfPortfolio() {
    double value = 0.0;
    String portfolio = this.getPortfolioName();
    for (Portfolio p : this.mPortfolios) {
      if (p.getName().equals(portfolio)) {
        value = p.getValueOfStock();
      }
    }
    this.commandLine.showPortfolioCost(portfolio, value);
  }

  /**
   * A helper function to show the value of the portfolio at a particular date.
   */
  private void getValueOfPortfolioAtDate() {
    double value = 0.0;
    String portfolio = this.getPortfolioName();
    LocalDate date;
    this.commandLine.getDate();
    try {
      date = LocalDate.parse(readNext());
    } catch (Exception e) {
      this.commandLine.invalidDate();
      this.getValueOfPortfolioAtDate();
      return;
    }
    for (Portfolio p : this.mPortfolios) {
      if (p.getName().equals(portfolio)) {
        value = p.getValueOfStockAtDate(date);
      }
    }
    this.commandLine.showPortfolioCost(portfolio, value);
  }

  /**
   * A helper function to return the value entered by the user.
   *
   * @return String format of the value entered.
   */
  private String getInput() {
    return readNext();
  }

  /**
   * A helper function to read the user entered values.
   *
   * @return user entered value.
   * @throws IllegalStateException when an invalid value is entered.
   */
  private String readNext() throws IllegalStateException {
    try {
      return this.scanner.next();
    } catch (Exception e) {
      throw new IllegalStateException("Appendable Fail.\n");
    }
  }

  /**
   * A Helper function to check if the ticker symbol entered is valid
   * or not and convert it to upper case.
   *
   * @return valid ticker symbols.
   */
  private String appendStockSymbol() {
    while (true) {
      this.commandLine.appendStockSymbol();
      String transInput = readNext();
      if (transInput.matches("[A-Za-z]+")) {
        return transInput.toUpperCase();
      }
      this.commandLine.invalidStock();
    }
  }

  /**
   * A helper function to read the name of the portfolio.
   *
   * @return name of the portfolio.
   */
  private String getPortfolioName() {
    this.commandLine.appendPortfolioName();
    return this.readNext();
  }

  /**
   * A helper function to save the portfolio.
   */
  private void savePortfolio() {
    FileOperation fp = new FileOperationImpl();
    for (Portfolio p : this.mPortfolios) {
      fp.saveFile(p);
    }
  }

  /**
   * A helper function to read a saved portfolio.
   */
  private void retrievePortfolio() {
    this.commandLine.getPortfolioName();
    String name = this.readNext();
    FileOperation fp = new FileOperationImpl();
    try {
      this.mPortfolios.add(fp.retrieveFile(name));
    } catch (Exception e) {
      this.commandLine.invalidFile();
      retrievePortfolio();
    }
  }
}
