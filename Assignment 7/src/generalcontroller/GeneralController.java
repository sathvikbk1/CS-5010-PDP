package generalcontroller;

import static utility.Constants.FILE_SEPARATOR;

import gui.GUIView;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

import model.FlexibleModelImplementation;
import model.ModelImplementation;
import model.ModelInterface;
import model.Periodicity;
import model.Share;

/**
 * Generalized controller which implements the features that support the stocker application.
 */
public class GeneralController implements Features {

  private final ModelInterface model;
  private GUIView view;

  /**
   * Parameterize constructor that initiallizes the controller.
   *
   * @param model Model to work with controller.
   */
  public GeneralController(ModelInterface model) {
    this.model = model;

  }

  /**
   * Sets View of given interface.
   *
   * @param view View to set of interface.
   */
  public void setView(GUIView view) {
    this.view = view;
    this.view.addFeatures(this);
    this.updatePortfolioList();
    this.view.showView();
  }

  private void updatePortfolioList() {
    this.view.listAllPortfolios(getPortfolios(PortfolioType.ALL));
    this.view.listAllMutablePortfolios(getPortfolios(PortfolioType.FLEXIBLE));
  }

  @Override
  public boolean checkPortfolioNameExists(String portfolioName) {
    return model.idIsPresent(portfolioName);
  }

  @Override
  public boolean checkTickerExists(String ticker) {
    return this.model.checkTicker(ticker);
  }

  @Override
  public List<String> getShareTickerInPortfolio(String portfolioName) {
    return this.model.getShareTickerInPortfolio(portfolioName);
  }

  @Override
  public Set<Share> getShareTicker(String portfolioName) {
    return new FlexibleModelImplementation().getListOfShares(portfolioName);
  }

  @Override
  public boolean setBalancePortfolio(String portfolioName, HashMap<Share,
          Float> weights, LocalDate date) {
    return new FlexibleModelImplementation().balancePortfolio(portfolioName, weights, date);
  }

  private List<String> getPortfolios(PortfolioType pType) {
    List<String> portfolios = new FlexibleModelImplementation().getPortfolio();
    if (pType == PortfolioType.ALL) {
      return portfolios.stream().map((inp) -> inp.split("\\|\\|")[2].trim())
              .collect(Collectors.toList());
    } else if (pType == PortfolioType.FIXED) {
      // Add logic for fixed if required
    } else if (pType == PortfolioType.FLEXIBLE) {
      return portfolios.stream().filter(inp -> inp.split("\\|\\|").length >= 5)
              .map((inp) -> inp.split("\\|\\|")[2].trim()).collect(Collectors.toList());
    }
    return null;
  }

  @Override
  public boolean createPortfolio(String portfolioName, PortfolioType pType) {
    if (!checkPortfolioNameExists(portfolioName)) {
      if (pType == PortfolioType.FLEXIBLE) {
        new FlexibleModelImplementation((ModelImplementation) this.model).createPortfolio(
                portfolioName, LocalDate.now());
      } else {
        this.model.createPortfolio(portfolioName, LocalDate.now());
      }
      this.updatePortfolioList();
      return true;
    }
    return false;
  }

  @Override
  public int uploadPortfolio(String filePath) {
    boolean validPath = false;
    int errorCode = 0;
    int idx = filePath.lastIndexOf(FILE_SEPARATOR);
    String folderName = filePath.substring(0, idx);
    String[] file = filePath.substring(idx + 1).split("\\.");
    idx = folderName.lastIndexOf(FILE_SEPARATOR);
    String root = filePath.substring(0, idx);
    String folder = filePath.substring(idx + 1);
    idx = folder.lastIndexOf(FILE_SEPARATOR);
    folder = folder.substring(0, idx);
    try {
      validPath = model.addPortfolioByUpload(root, folder, file[0], file[1]);
    } catch (DataFormatException dataFormatException) {
      errorCode = 1;
    } catch (FileNotFoundException fileNotFoundException) {
      errorCode = 2;
    }
    return validPath ? 0 : errorCode;
  }

  @Override
  public boolean purchaseShare(String shareName, int numShares, LocalDate date) {
    if (numShares <= 0) {
      return false;
    }
    try {
      return model.addShareToModel(shareName, date, numShares, -1);
    } catch (IllegalArgumentException invalidTicker) {
      return false;
    }
  }

  @Override
  public double purchaseShare(String portfolioName, String shareName, int numShares,
                              LocalDate date) {
    if (!checkTickerExists(shareName)) {
      return -1.0;
    }
    try {
      return new FlexibleModelImplementation().appendPortfolio(portfolioName, shareName, numShares,
              date);
    } catch (IllegalArgumentException invalidTicker) {
      return -1.0;
    }
  }

  @Override
  public double sellShare(String portfolioName, String shareName, int numShares, LocalDate date) {
    if (!checkTickerExists(shareName) || numShares <= 0.0) {
      return -1.0;
    }
    return new FlexibleModelImplementation().sellStocks(portfolioName, shareName, numShares, date);
  }

  @Override
  public double generateCostBasis(String id, LocalDate date) {
    return this.model.getCostBasis(id, date);
  }

  @Override
  public String generateComposition(String id) {
    return this.model.getPortfolioById(id);
  }

  @Override
  public List<Double> generatePerformanceGraph(String portfolioName, LocalDate from, LocalDate to,
                                               Periodicity group) {
    return this.model.getPortfolioPerformance(portfolioName, from, to, group);
  }

  @Override
  public double getValuation(String id, LocalDate date) {
    return this.model.getValuationGivenDate(id, date);
  }

  @Override
  public boolean createStrategy(String portfolioName, String investmentAmount, LocalDate date,
                                LocalDate endDate, ArrayList<String> shares,
                                ArrayList<Integer> weightage) {
    return new FlexibleModelImplementation().createStrategy(portfolioName, investmentAmount, date,
            endDate, shares, weightage, 0);
  }

  @Override
  public boolean createPortfolioStrategy(String portfolioName, String investmentAmount,
                                         LocalDate date, LocalDate endDate,
                                         ArrayList<String> shares, ArrayList<Integer> weightage,
                                         String frequency) {
    return new FlexibleModelImplementation().createPortfolioStrategy(portfolioName,
            investmentAmount, date, endDate, shares, weightage, Integer.parseInt(frequency));
  }
}
