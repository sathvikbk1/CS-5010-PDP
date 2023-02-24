package controller.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import controller.StockPortfolioCommand;
import model.FlexibleModelImplementation;
import model.ModelInterface;
import model.Share;
import view.View;

/**
 * A class to implement the Re-Balancing of the portfolio.
 */
public class BalancePortfolio implements StockPortfolioCommand {
  /**
   * A method to start the balancing of the portfolio.
   *
   * @param view    view object passed to command method if it needs access to user input
   * @param scanner scanner object passed to command method if it needs any input from the user
   * @param model   model object passed to command method if it needs data from storage applications
   */
  @Override
  public void process(View view, Scanner scanner, ModelInterface model) {
    boolean flag;
    boolean fixed = false;
    do {
      view.selectPortfolio();
      String selectedId = scanner.next().trim();
      flag = model.idIsPresent(selectedId);
      if (!flag) {
        view.printInvalidInputMessage();
        continue;
      }
      if (flag) {
        fixed = checkIfPortfolioMutable(selectedId, model);
      }
      if (!fixed) {
        flag = false;
        view.alertFixedPortfolio();
      } else {
        callPortfolio(view, scanner, model, selectedId);
      }
    }
    while (!flag);
  }

  private void callPortfolio(View view, Scanner scanner, ModelInterface model, String portfolioId) {
    model = new FlexibleModelImplementation();
    boolean isValidCompany;
    Set<Share> listOfStocks = model.getListOfShares(portfolioId);
    HashMap<Share, Float> weights = new HashMap<>();
    double sum = 0;
    boolean invalidDate;
    LocalDate date;
    String stockDate;
    do {
      while (true) {
        try {
          view.askForDate();
          stockDate = scanner.next();
          invalidDate = !(Pattern.matches("\\d{4}-\\d{2}-\\d{2}", stockDate));
          date = LocalDate.parse(stockDate);
          break;
        } catch (DateTimeParseException dtp) {
          view.printInvalidDateError();
        }
      }
      if (date.isAfter(LocalDate.of(1949, 12, 31))
              && (date.isBefore(LocalDate.now()) || date.isEqual(LocalDate.now()))) {
        invalidDate = false;
      } else {
        invalidDate = true;
      }
      if (invalidDate) {
        view.printInvalidInputMessage();
      }
    }
    while (invalidDate);
    for (Share share : listOfStocks) {
      if (share.getPurchaseDate().isBefore(date)
              || share.getPurchaseDate().isEqual(date)) {
        view.showStockForWeight(share.getCompanyName());
        float weight = Float.parseFloat(scanner.next().trim());
        sum += weight;
        weights.put(share, weight);
      }
    }
    if (sum > 99.99 && sum < 100.001) {
      model.balancePortfolio(portfolioId, weights, date);
    }
  }

  private boolean checkIfPortfolioMutable(String portfolioId, ModelInterface model) {
    model = new FlexibleModelImplementation();
    List<String> portfolioList = model.getPortfolio();
    if (portfolioList.size() == 0) {
      return false;
    } else {
      for (String portfolio : portfolioList) {
        String[] portfolioRecords = portfolio.split("\\|\\|");
        if (portfolioId.equals(portfolioRecords[2].trim())) {
          if (portfolioRecords.length == 5) {
            return true;
          }
        }
      }
    }
    return false;
  }

  @Override
  public void undo(ModelInterface model) {
    //For future implementation.
  }
}
