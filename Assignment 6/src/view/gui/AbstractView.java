package view.gui;

import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * An abstract class to include the functionalities the program
 * is capable of performing.
 */
public abstract class AbstractView implements View {
  @Override
  public String getStockAbbreviation() {
    promptErrorMessage("Invalid Stock Abbreviation");
    return null;
  }

  @Override
  public int getStockQuantity() {
    promptErrorMessage("Invalid Stock Quantity");
    return 0;
  }

  @Override
  public double getStockPrice() {
    promptErrorMessage("Invalid Stock Price");
    return 0;
  }

  @Override
  public double getStockCommission() {
    promptErrorMessage("Invalid Stock Commission");
    return 0;
  }

  @Override
  public LocalDate getStockDate() {
    promptErrorMessage("Invalid Stock Date");
    return null;
  }

  @Override
  public LocalDate getStockDate2() {
    promptErrorMessage("Invalid Stock Date");
    return null;
  }

  @Override
  public String getPortfolioName() {
    promptErrorMessage("Invalid Portfolio Name");
    return null;
  }

  @Override
  public void promptErrorMessage(String message) {
    JOptionPane.showMessageDialog(new JFrame(), message, "Error",
            JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public double getPortfolioCost(String name) {
    promptErrorMessage("Invalid Portfolio Name");
    return 0;
  }

  @Override
  public double getPortfolioCostAtDate(LocalDate date) {
    promptErrorMessage("Invalid Portfolio Date");
    return 0;
  }

  @Override
  public double getPortfolioValue(String name) {
    promptErrorMessage("Invalid Portfolio Name");
    return 0;
  }

  @Override
  public double getPortfolioValueAtDate(LocalDate date) {
    promptErrorMessage("Invalid Portfolio Date");
    return 0;
  }

  @Override
  public void startVisualization(String name, LocalDate startDate, LocalDate endDate) {
    promptErrorMessage("Unable to visualize");
  }

  @Override
  public void getValueOfPortfolioAtDate(LocalDate date) {
    promptErrorMessage("Unable to get value of portfolio at date");
  }

  @Override
  public void retrievePortfolio(String name) {
    promptErrorMessage("Unable to retrieve portfolio");
  }

  @Override
  public void removePortfolio(String name) {
    promptErrorMessage("Unable to remove portfolio");
  }

  @Override
  public void savePortfolio() {
    promptErrorMessage("Portfolio not saved");
  }

  @Override
  public String getStrategyName() {
    promptErrorMessage("Invalid Strategy Name");
    return null;
  }

  @Override
  public LocalDate getStrategyStartDate() {
    promptErrorMessage("Invalid Date");
    return null;
  }

  @Override
  public int getStockInterval() {
    promptErrorMessage("Interval not defined");
    return 0;
  }

  @Override
  public double getStockCost() {
    promptErrorMessage("Invalid cost");
    return 0;
  }
}
