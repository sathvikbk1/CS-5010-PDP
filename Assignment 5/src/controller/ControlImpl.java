package controller;

import java.io.Reader;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.Model;
import model.ModelImpl;
import model.portfolio.Portfolio;
import view.CommandLine;
import view.CommandLineImpl;

/**
 * The class that helps in controlling the
 * entire flow of the program, walking through
 * various operations that could be performed.
 */
public class ControlImpl implements Control {
  private final CommandLine commandLine;
  private final Scanner scanner;
  private Model model;

  /**
   * A constructor to initialize the objects of the controller.
   */
  public ControlImpl(Reader in, Appendable out) {
    this.commandLine = new CommandLineImpl(out);
    this.scanner = new Scanner(in);
    this.model = new ModelImpl();
  }

  /**
   * A method to start the execution of the program and
   * begin by printing the command line menu items.
   */
  @Override
  public void execute(Model model) {
    this.model = model;
    boolean type = getTypeOfPortfolio();
    if (type) {
      this.model.setType();
    }
    while (true) {
      if (type) {
        this.commandLine.commandPromptFlex();
      } else {
        this.commandLine.commandPrompt();
      }
      switch (this.getInput().toLowerCase()) {
        case "q":
          this.commandLine.appendMessage("Quiting....");
          return;
        case "1":
          if (type) {
            this.createPortfolioFlex();
          } else {
            this.createPortfolio();
          }
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
          this.getCostOfPortfolioAtDate();
          break;
        case "6":
          this.getValueOfPortfolio();
          break;
        case "7":
          this.getValueOfPortfolioAtDate();
          break;
        case "8":
          this.savePortfolio();
          break;
        case "9":
          this.retrievePortfolio();
          break;
        case "10":
          if (type) {
            this.loadPortfolio();
            break;
          }
          this.commandLine.invalidCommand();
          break;
        case "11":
          if (type) {
            this.removePortfolio();
            break;
          }
          this.commandLine.invalidCommand();
          break;
        case "12":
          if (type) {
            this.visualizePortfolio();
            break;
          }
          this.commandLine.invalidCommand();
          break;
        default:
          this.commandLine.invalidCommand();

      }
    }
  }

  /**
   * A helper function that returns the type of portfolio to be created.
   *
   * @return integer indicating the corresponding type of portfolio.
   */
  private boolean getTypeOfPortfolio() {
    this.commandLine.typeOfPortfolio();
    try {
      int val = Integer.parseInt(readNext());
      if (val == 1) {
        return false;
      } else if (val == 2) {
        return true;
      } else {
        this.commandLine.invalidCommand();

      }
    } catch (Exception e) {
      this.commandLine.invalidCommand();
    }
    return getTypeOfPortfolio();
  }

  /**
   * A helper function to create the inflexible portfolio once all stocks
   * have been added.
   */
  private void createPortfolio() throws IllegalArgumentException {
    while (true) {
      this.commandLine.createStock();
      switch (this.getInput().toLowerCase()) {
        case "f":
          this.commandLine.appendMessage("Creating Portfolio....");
          finalisePortfolio();
          return;
        case "1":
          this.createStock();
          break;
        default:
          this.commandLine.appendMessage("Invalid command!");
          break;
      }
    }
  }

  /**
   * A helper function to create the flexible portfolio once all stocks
   * have been added.
   */
  private void createPortfolioFlex() throws IllegalArgumentException {
    this.model.setType();
    while (true) {
      this.commandLine.createStockFlex();
      switch (this.getInput().toLowerCase()) {
        case "f":
          this.commandLine.appendMessage("Finalising Portfolio....");
          finalisePortfolio();
          return;
        case "1":
          this.createStockFlex();
          break;
        case "2":
          this.sellStock();
          break;
        default:
          this.commandLine.appendMessage("Invalid command!");
          break;
      }
    }
  }

  /**
   * A helper function tp validate the portfolio name and append to list.
   */
  private void finalisePortfolio() {
    if (!this.model.finalisePortfolio(getPortfolioName())) {
      this.commandLine.invalidPortfolioName();
      this.finalisePortfolio();
    }
  }

  /**
   * A helper function to load the portfolio to modify it.
   */
  private void loadPortfolio() {
    String name = getPortfolioName();
    int i = 0;
    for (Portfolio p : this.model.getMPortfolio()) {
      if (p.getName().equals(name)) {
        this.model.loadPortfolio(p.getName(), p.getStockList(), p.getTransactionHistory());
        this.model.removePortfolio(i);
        break;
      }
      i++;
    }
    createPortfolioFlex();
  }

  private void removePortfolio() {
    String name = getPortfolioName();
    int i = 0;
    for (Portfolio p : this.model.getMPortfolio()) {
      if (p.getName().equals(name)) {
        this.model.removePortfolio(i);
        break;
      }
      i++;
    }
  }

  /**
   * A helper function to create each stock added to the inflexible portfolio.
   */
  private void createStock() {
    if (!this.model.createStock(appendStockSymbol(), Double.parseDouble(shareCheck() + ""),
            getSharePrice())) {
      this.commandLine.invalidStock();
      this.createStock();
    }
  }

  /**
   * A helper function to create each stock added to the flexible portfolio.
   */
  private void createStockFlex() {
    if (!this.model.createStockFlex(appendStockSymbol(), Double.parseDouble(shareCheck() + ""),
            getSharePrice(), appendDate(), getCommission(), false)) {
      this.commandLine.invalidStock();
      this.createStockFlex();
    }
  }

  /**
   * A helper function to handle the stocks sold, which must previously exist in the portfolio.
   */
  private void sellStock() {
    try {
      if (!this.model.createStockFlex(appendStockSymbol(), Double.parseDouble(shareCheck() + ""),
              getSharePrice(), appendDate(), getCommission(), true)) {
        this.commandLine.invalidStock();
        this.createStockFlex();
      }
    } catch (Exception e) {
      this.commandLine.nonExistingStock();
      this.createStockFlex();
    }
  }

  /**
   * A helper function to check if the shares entered are non-fractional.
   *
   * @return an integer of the number of shares.
   */
  private int shareCheck() {
    this.commandLine.appendShares();
    try {
      return Integer.parseInt(readNext());
    } catch (NumberFormatException e) {
      this.commandLine.invalidShare();
      return shareCheck();
    }
  }

  /**
   * A helper function to enter the commission of the broker.
   *
   * @return commission value paid by the user.
   */
  private double getCommission() {
    this.commandLine.appendCommission();
    try {
      return Double.parseDouble(readNext());
    } catch (NumberFormatException e) {
      this.commandLine.invalidCommand();
      return getSharePrice();
    }
  }

  /**
   * A helper function to get the price of the share.
   *
   * @return price at which the share was bought/sold.
   */
  private double getSharePrice() {
    this.commandLine.appendCost();
    try {
      return Double.parseDouble(readNext());
    } catch (NumberFormatException e) {
      this.commandLine.invalidShare();
      return getSharePrice();
    }
  }

  /**
   * A helper function to show all the portfolios present.
   */
  private void showPortfolios() {
    List<Portfolio> portfolios = this.model.getMPortfolio();
    for (Portfolio p : portfolios) {
      this.commandLine.showPortfolio(p);
    }
  }

  /**
   * A helper function to show a specific portfolio.
   */
  private void showOnePortfolio() {
    String portfolio = this.getPortfolioName();
    List<Portfolio> portfolios = this.model.getMPortfolio();
    for (Portfolio p : portfolios) {
      if (p.getName().equals(portfolio)) {
        this.commandLine.showPortfolio(p);
      }
    }
  }

  /**
   * A helper function to show the total cost of a portfolio.
   */
  private void getCostOfPortfolio() {
    String portfolio = getPortfolioName();
    double value = this.model.getPortfolioCost(portfolio);
    this.commandLine.showPortfolioCost(portfolio, value);
  }

  /**
   * A helper function to get the cost of a portfolio on a particular date.
   */
  private void getCostOfPortfolioAtDate() {
    String portfolio = this.getPortfolioName();
    LocalDate date = this.appendDate();
    this.commandLine.showPortfolioCost(portfolio,
            this.model.getPortfolioCostAtDate(portfolio, date));
  }

  /**
   * A helper function to show the current value of the portfolio.
   */
  private void getValueOfPortfolio() {
    String portfolio = this.getPortfolioName();
    double value = this.model.getPortfolioValue(portfolio);
    this.commandLine.showPortfolioCost(portfolio, value);
  }

  /**
   * A helper function to show the value of the portfolio at a particular date.
   */
  private void getValueOfPortfolioAtDate() {
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
    this.commandLine.showPortfolioCost(portfolio,
            this.model.getPortfolioValueAtDate(portfolio, date));
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
   * A helper function to enter the date and validate the date.
   *
   * @return a valid date value.
   */
  private LocalDate appendDate() {
    this.commandLine.getDate();
    try {
      LocalDate date = LocalDate.parse(readNext());
      if (date.isAfter(LocalDate.now())) {
        throw new IllegalArgumentException();
      }
      return date;
    } catch (Exception e) {
      this.commandLine.invalidDate();
      return this.appendDate();
    }
  }

  /**
   * A helper function to save the portfolio.
   */
  private void savePortfolio() {
    this.model.savePortfolio();
  }

  /**
   * A helper function to read a saved portfolio.
   */
  private void retrievePortfolio() {
    this.commandLine.getPortfolioName();
    String name = this.readNext();
    try {
      this.model.retrievePortfolio(name);
    } catch (Exception e) {
      this.commandLine.invalidFile();
      retrievePortfolio();
    }
  }

  /**
   * A helper function to map a chart for the portfolio.
   */
  private void visualizePortfolio() {
    String name = getPortfolioName();
    Portfolio portfolio = null;
    for (Portfolio p : this.model.getMPortfolio()) {
      if (p.getName().equals(name)) {
        portfolio = p;
        break;
      }
    }
    if (portfolio == null) {
      this.commandLine.wrongPortfolioName();
      return;
    }
    this.commandLine.appendMessage("Enter start date as");
    LocalDate startDate = appendDate();
    this.commandLine.appendMessage("Enter end date as");
    LocalDate endDate = appendDate();
    this.commandLine.startVisualization(name, startDate, endDate);
    if ((int) ChronoUnit.DAYS.between(startDate, endDate) < 5) {
      this.commandLine.insufficientData();
      return;
    }
    Map<Object, Double> prints = this.model.visualize(portfolio, startDate, endDate);
    if (prints == null) {
      this.commandLine.insufficientData();
      this.visualizePortfolio();
      return;
    }
    this.commandLine.printGraph(prints);
  }

}
