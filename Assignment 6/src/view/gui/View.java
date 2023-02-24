package view.gui;

import java.awt.event.ActionListener;
import java.time.LocalDate;

/**
 * An interface to describe the actions which could be performed in views.
 */
public interface View {

  void addActionListener(ActionListener listener);

  void setVisible(boolean b);

  void dispose();

  String getStockAbbreviation();

  int getStockQuantity();

  double getStockPrice();

  double getStockCommission();

  LocalDate getStockDate();

  LocalDate getStockDate2();

  String getPortfolioName();

  void promptErrorMessage(String message);

  double getPortfolioCost(String name);

  double getPortfolioCostAtDate(LocalDate date);

  double getPortfolioValue(String name);

  double getPortfolioValueAtDate(LocalDate date);

  void startVisualization(String name, LocalDate startDate, LocalDate endDate);

  void getValueOfPortfolioAtDate(LocalDate date);

  void retrievePortfolio(String name);

  void removePortfolio(String name);

  void savePortfolio();

  String getStrategyName();

  LocalDate getStrategyStartDate();

  int getStockInterval();

  double getStockCost();
}

