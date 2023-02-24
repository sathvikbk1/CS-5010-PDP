package model;

import static utility.Constants.BROKER_FEES;
import static utility.Constants.LINE_BREAKER;
import static utility.Constants.MUTABLE;
import static utility.Constants.PORTFOLIO_DIRECTORY;
import static utility.Constants.PORTFOLIO_FILENAME;
import static utility.Constants.RECORD_FIELD_SEPERATOR;
import static utility.Constants.RELATIVE_PATH;
import static utility.Constants.STRATEGY_FILENAME;
import static utility.Constants.VALUE_SEPERATOR;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class behaves as a gateway between model objects and operations and the controller and
 * provides all model level functionalities to the controller interface from higher level. It also
 * provides additional features creating flexible portfolio, adding and removing stocks from the
 * flexible portfolio.
 */
public class FlexibleModelImplementation extends ModelAbstract {

  protected Map<String, Share> shares;
  private HashMap<String, String> strategyMap;

  /**
   * Constructor that allows for processing of all strategies on startup.
   */
  public FlexibleModelImplementation() {
    super();
    strategyMap = new HashMap<>();
    this.getStrategyList();
    this.processStrategy();
  }

  /**
   * Copy constructor for flexible implementation class.
   *
   * @param other Model to copy.
   */
  public FlexibleModelImplementation(ModelAbstract other) {
    this.shares = other.shares;
  }

  protected FlexibleModelImplementation(FileInterface fileInterface) {
    super(fileInterface);
    strategyMap = new HashMap<>();
    this.getStrategyList();
    this.processStrategy();
  }

  @Override
  public double sellStocks(String portfolioName, String symbol, int numShares, LocalDate date) {
    // Checking if numShares is less than the currently less than bought shares
    Portfolio portfolioToModify = this.getPortfolioObjectById(portfolioName);
    Set<Share> newShares = new HashSet<>(portfolioToModify.getListOfShares());
    if (!checkValidNumStocks(symbol, numShares, newShares, date)) {
      throw new IllegalArgumentException("Number of shares is less than shares bought in "
              + "this portfolio!");
    }
    double stockSellingPrice = 0.0;

    for (Share share : new HashSet<>(newShares)) {
      if (share.getCompanyName().equals(symbol)) {
        double currentShareSellingPrice = this.getStockPrice(share.getCompanyName(), date);
        newShares.remove(share);
        if (share.getNumShares() > numShares && (share.getPurchaseDate().isBefore(date)
                || share.getPurchaseDate().isEqual(date))) {
          stockSellingPrice += numShares * currentShareSellingPrice;
          newShares.add(new Share(share.getCompanyName(), share.getPurchaseDate(), share.getPrice(),
                  share.getNumShares() - numShares));
          break;
        } else {
          numShares -= share.getNumShares();
          stockSellingPrice += share.getNumShares() * currentShareSellingPrice;
        }
      }
    }
    this.modifyPortfolio(portfolioToModify.getId(), newShares, portfolioToModify.getCreationDate());
    return stockSellingPrice - BROKER_FEES;
  }

  /**
   * A method to return the list of shares in a particular portfolio.
   *
   * @param portfolioName name of the portfolio whose stocks is to be returned.
   * @return set of shares in the portfolio.
   */
  @Override
  public Set<Share> getListOfShares(String portfolioName) {
    Portfolio portfolioToModify = this.getPortfolioObjectById(portfolioName);
    return portfolioToModify.getListOfShares();
  }

  protected void modifyPortfolio(String portfolioName, Set<Share> sharesToModify,
                                 LocalDate creationDate) {
    Set<Share> processedShare = new HashSet<>();
    String stockFileName = null;
    for (Share share : sharesToModify) {
      Share finalShare = new Share(share.getCompanyName(), share.getPurchaseDate(),
              share.getPrice(), share.getNumShares());
      processedShare.add(finalShare);
    }
    portfolios.remove(new Portfolio(portfolioName, sharesToModify, creationDate));
    portfolios.add(new Portfolio(portfolioName, processedShare, creationDate));
    stockFileName = getSharesFile(portfolioName);
    if (null != stockFileName && !stockFileName.isEmpty()) {
      fileInterface.clearFile(RELATIVE_PATH, PORTFOLIO_DIRECTORY, stockFileName, "csv");
      String formattedString = fileInterface
              .convertObjectListIntoString(new ArrayList<>(processedShare));
      fileInterface.writeToFile(RELATIVE_PATH, PORTFOLIO_DIRECTORY, stockFileName,
              formattedString.getBytes());
    }
  }

  private String getSharesFile(String portfolioName) {
    String stockFileName = null;
    List<String> fileContent = fileInterface.readFromFile(RELATIVE_PATH, PORTFOLIO_DIRECTORY,
            PORTFOLIO_FILENAME);
    for (String portfolio : fileContent) {
      String[] portfolioRecord = portfolio.split(",");
      if (portfolioRecord.length == 4) {
        if (portfolioName.equals(portfolioRecord[0])) {
          stockFileName = portfolioRecord[2].substring(3);
        }
      }
    }
    return stockFileName;
  }

  @Override
  public double appendPortfolio(String portfolioName, String symbol, int numShares, LocalDate date)
          throws NoSuchElementException {
    if (!checkTicker(symbol) && numShares <= 0) {
      throw new NoSuchElementException("Entered ticker symbol or share numbers is invalid");
    }
    String stockFileName = null;
    double currentShareBuyingPrice = this.getStockPrice(symbol, date);
    stockFileName = getSharesFile(portfolioName);
    Share addShare = new Share(symbol, date,
            currentShareBuyingPrice + ((BROKER_FEES * 1.00) / numShares), numShares);
    if (null != stockFileName && !stockFileName.isEmpty()) {
      String formattedString = fileInterface.convertObjectIntoString(addShare.toString(),
              null);
      fileInterface.writeToFile(RELATIVE_PATH, PORTFOLIO_DIRECTORY, stockFileName,
              formattedString.getBytes());
    }
    return (numShares * currentShareBuyingPrice) + BROKER_FEES;
  }

  @Override
  public void createPortfolio(String portfolioName, LocalDate date) {
    Set<Share> sharesSet = new HashSet<>(shares.values());
    if (this.idIsPresent(portfolioName)) {
      throw new IllegalArgumentException("Portfolio is already present!");
    }
    Portfolio portfolioObject = new Portfolio(portfolioName, sharesSet, date);
    String formattedString = fileInterface.convertObjectListIntoString(new ArrayList<>(sharesSet));
    String shareFileName = UUID.randomUUID().toString();
    List<String> referenceList = new ArrayList<>();
    referenceList.add(shareFileName);
    if (fileInterface.writeToFile(RELATIVE_PATH, PORTFOLIO_DIRECTORY, shareFileName,
            formattedString.getBytes())) {
      fileInterface.writeToFile(RELATIVE_PATH, PORTFOLIO_DIRECTORY, PORTFOLIO_FILENAME,
              (fileInterface.convertObjectIntoString(portfolioObject.toString(), referenceList)
                      + MUTABLE).getBytes());
    }
    shares = new HashMap<>();
    portfolios.add(portfolioObject);
  }

  @Override
  public List<String> getPortfolio() {
    List<String> portfolioOutput = new ArrayList<>();
    List<String> fileContent = fileInterface.readFromFile(RELATIVE_PATH, PORTFOLIO_DIRECTORY,
            PORTFOLIO_FILENAME);
    for (String portfolio : fileContent) {
      String[] portfolioFields = portfolio.trim().split(",");
      List<String> stockFileContent = fileInterface.readFromFile(RELATIVE_PATH, PORTFOLIO_DIRECTORY,
              portfolioFields[2].substring(3));
      Set<Share> shareList = new HashSet<>();
      for (String stock : stockFileContent) {
        String[] stockFields = stock.trim().split(",");
        Share shareObject = new Share(stockFields[0], LocalDate.parse(stockFields[1]),
                Double.parseDouble(stockFields[2]), Double.parseDouble(stockFields[3]));
        shareList.add(shareObject);
      }
      Portfolio portfolioObject = new Portfolio(portfolioFields[0], shareList,
              LocalDate.parse(portfolioFields[1]));
      String serialNumber = portfolioOutput.size() + ".";
      String portfolioHeaderString;
      if (portfolioFields.length == 4) {
        portfolioHeaderString = String.format("||%-18s||%-40s||%-18s||%-40s||", serialNumber,
                portfolioFields[0], LocalDate.parse(portfolioFields[1]), portfolioFields[3]);
      } else {
        portfolioHeaderString = String.format("||%-18s||%-40s||%-18s||", serialNumber,
                portfolioFields[0], LocalDate.parse(portfolioFields[1]));
      }
      portfolioOutput.add(portfolioHeaderString);
      portfolios.add(portfolioObject);
    }
    return portfolioOutput;
  }

  @Override
  public boolean createStrategy(String portfolioName, String investmentAmount, LocalDate date,
                                LocalDate endDate, ArrayList<String> shares,
                                ArrayList<Integer> weightage, int type) {
    if (!this.idIsPresent(portfolioName) || date.isBefore(LocalDate.now())) {
      return false;
    }
    Map<String, Integer> map = IntStream.range(0, shares.size())
            .boxed()
            .collect(Collectors.toMap(shares::get, weightage::get));
    StringBuilder recordData = new StringBuilder();
    recordData.append(portfolioName).append(RECORD_FIELD_SEPERATOR);
    recordData.append(LocalDate.now()).append(RECORD_FIELD_SEPERATOR);
    recordData.append(date).append(RECORD_FIELD_SEPERATOR);
    recordData.append(endDate).append(RECORD_FIELD_SEPERATOR);
    recordData.append(type).append(RECORD_FIELD_SEPERATOR);
    recordData.append(investmentAmount).append(RECORD_FIELD_SEPERATOR);
    List<String> sharesList = getShareTickerInPortfolio(portfolioName);
    for (int i = 0; i < sharesList.size() - 1; i++) {
      if (map.containsKey(sharesList.get(i))) {
        recordData.append(sharesList.get(i)).append(VALUE_SEPERATOR)
                .append(map.get(sharesList.get(i))).append(RECORD_FIELD_SEPERATOR);
      }
    }
    if (map.containsKey(sharesList.get(sharesList.size() - 1))) {
      recordData.append(sharesList.get(sharesList.size() - 1))
              .append(VALUE_SEPERATOR)
              .append(map.get(sharesList.get(sharesList.size() - 1)))
              .append(LINE_BREAKER);
    }
    if (strategyMap.containsKey(portfolioName)) {
      strategyMap.replace(portfolioName, recordData.toString().split(",", 2)[1]);
      fileInterface.clearFile(RELATIVE_PATH, PORTFOLIO_DIRECTORY, STRATEGY_FILENAME, "csv");
      fileInterface.writeToFile(RELATIVE_PATH, PORTFOLIO_DIRECTORY, STRATEGY_FILENAME,
              hashMapToRecordData(strategyMap).toString().trim().getBytes());
    } else {
      strategyMap.put(portfolioName, recordData.toString().split(",", 2)[1]);
      fileInterface.writeToFile(RELATIVE_PATH, PORTFOLIO_DIRECTORY, STRATEGY_FILENAME,
              recordData.toString().getBytes());
    }
    this.processStrategy();
    return true;
  }

  @Override
  public boolean createPortfolioStrategy(String portfolioName, String investmentAmount,
                                         LocalDate date,
                                         LocalDate endDate, ArrayList<String> shares,
                                         ArrayList<Integer> weightage, int type) {
    if (date.isBefore(LocalDate.now())) {
      return false;
    }
    StringBuilder recordData = new StringBuilder();
    recordData.append(portfolioName).append(RECORD_FIELD_SEPERATOR);
    recordData.append(LocalDate.now()).append(RECORD_FIELD_SEPERATOR);
    recordData.append(date).append(RECORD_FIELD_SEPERATOR);
    recordData.append(endDate).append(RECORD_FIELD_SEPERATOR);
    recordData.append(type).append(RECORD_FIELD_SEPERATOR);
    recordData.append(investmentAmount).append(RECORD_FIELD_SEPERATOR);
    for (int i = 0; i < shares.size() - 1; i++) {
      recordData.append(shares.get(i)).append(VALUE_SEPERATOR)
              .append(weightage.get(i)).append(RECORD_FIELD_SEPERATOR);
    }
    recordData.append(shares.get(shares.size() - 1)).append(VALUE_SEPERATOR)
            .append(weightage.get(shares.size() - 1));
    if (strategyMap.containsKey(portfolioName)) {
      strategyMap.replace(portfolioName, recordData.toString().split(",", 2)[1]);
      fileInterface.clearFile(RELATIVE_PATH, PORTFOLIO_DIRECTORY, STRATEGY_FILENAME, "csv");
      fileInterface.writeToFile(RELATIVE_PATH, PORTFOLIO_DIRECTORY, STRATEGY_FILENAME,
              hashMapToRecordData(strategyMap).toString().trim().getBytes());
    } else {
      strategyMap.put(portfolioName, recordData.toString().split(",", 2)[1]);
      fileInterface.writeToFile(RELATIVE_PATH, PORTFOLIO_DIRECTORY, STRATEGY_FILENAME,
              recordData.toString().getBytes());
    }
    this.processStrategy();
    return true;
  }

  private StringBuilder hashMapToRecordData(HashMap<String, String> strategyMap) {
    StringBuilder fileFormatData = new StringBuilder();
    for (Map.Entry<String, String> entry : strategyMap.entrySet()) {
      fileFormatData.append(entry.getKey())
              .append(RECORD_FIELD_SEPERATOR)
              .append(entry.getValue())
              .append(LINE_BREAKER);
    }
    return fileFormatData;
  }

  private void getStrategyList() {
    List<String> fileContent = fileInterface.readFromFile(RELATIVE_PATH, PORTFOLIO_DIRECTORY,
            STRATEGY_FILENAME);
    for (String strategy : fileContent) {
      String[] strategyFields = strategy.trim().split(",", 2);
      strategyMap.put(strategyFields[0], strategyFields[1]);
    }
  }

  private synchronized void processStrategy() {
    List<String> fileContent = fileInterface.readFromFile(RELATIVE_PATH, PORTFOLIO_DIRECTORY,
            STRATEGY_FILENAME);
    for (String strategy : fileContent) {
      String[] strategyTodayFields = strategy.trim().split(",", 3);
      String portfolioName = strategyTodayFields[0];
      LocalDate today = LocalDate.now();
      LocalDate lastUpdateDate = LocalDate.parse(strategyTodayFields[1]);
      String[] strategyDateComparison = strategyTodayFields[2].trim().split(",", 4);
      LocalDate startDate = LocalDate.parse(strategyDateComparison[0]);
      LocalDate endDate = LocalDate.parse(strategyDateComparison[1]);
      String frequency = strategyDateComparison[2];
      if (startDate.equals(endDate) && frequency.equals("0")
              && (startDate.equals(today) || lastUpdateDate.isBefore(today))
              && this.idIsPresent(portfolioName)) {
        String[] strategyInvestFields = strategyDateComparison[3].trim().split(",");
        int totalAmount = Integer.parseInt(strategyInvestFields[0]);
        ArrayList<String> sharesList = new ArrayList<>();
        ArrayList<Integer> weightageList = new ArrayList<>();
        for (int i = 1; i < strategyInvestFields.length; i++) {
          String[] tickerData = strategyInvestFields[i].split(":");
          String ticker = tickerData[0];
          int weightage = Integer.parseInt(tickerData[1]);
          if (weightage != 0) {
            double amountToInvest = (totalAmount * weightage) * 0.01;
            double stockPrice = this.getStockPrice(ticker, startDate);
            double numberOfShares = amountToInvest / stockPrice;
            boolean status = this.buyStrategyShare(portfolioName, ticker, numberOfShares,
                    startDate, false);
            sharesList.add(ticker);
            weightageList.add(weightage);
          }
        }
        this.createStrategy(portfolioName, strategyInvestFields[0], today, endDate, sharesList,
                weightageList, -1);
      } else if (!endDate.isBefore(startDate) && !frequency.equals("-1")
              && !startDate.isAfter(today) && !this.idIsPresent(portfolioName)) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime todayDateTime = LocalDate.now().atStartOfDay();
        long days = startDateTime.until(todayDateTime, ChronoUnit.DAYS);
        if (days % Integer.parseInt(frequency) != 0) {
          continue;
        }
        String[] strategyInvestFields = strategyDateComparison[3].trim().split(",");
        int totalAmount = Integer.parseInt(strategyInvestFields[0]);
        ArrayList<String> sharesList = new ArrayList<>();
        ArrayList<Integer> weightageList = new ArrayList<>();
        for (int i = 1; i < strategyInvestFields.length; i++) {
          String[] tickerData = strategyInvestFields[i].split(":");
          String ticker = tickerData[0];
          int weightage = Integer.parseInt(tickerData[1]);
          if (weightage != 0) {
            double amountToInvest = (totalAmount * weightage) * 0.01;
            double stockPrice = this.getStockPrice(ticker, startDate);
            double numberOfShares = amountToInvest / stockPrice;
            boolean status = this.buyStrategyShare(portfolioName, ticker, numberOfShares,
                    startDate, true);
            sharesList.add(ticker);
            weightageList.add(weightage);
          }
        }
        this.createPortfolioStrategy(portfolioName, strategyInvestFields[0], today, endDate,
                sharesList, weightageList, Integer.parseInt(frequency));
      }
    }
  }

  private boolean buyStrategyShare(String portfolioName, String symbol, double numShares,
                                   LocalDate date, boolean create) {
    if (!checkTicker(symbol) && numShares < 0) {
      return false;
    }
    Map<String, Share> shares = new HashMap<>();
    shares.put(symbol, new Share(symbol, LocalDate.now(),
            this.getStockPrice(symbol, date), numShares));
    String shareFileName = null;
    shareFileName = getSharesFile(portfolioName);
    if (create) {
      Set<Share> sharesSet = new HashSet<>(shares.values());
      Portfolio portfolioObject = new Portfolio(portfolioName, sharesSet, date);
      String formattedString = fileInterface.convertObjectListIntoString(
              new ArrayList<>(sharesSet));
      if (null == shareFileName) {
        shareFileName = UUID.randomUUID().toString();
        List<String> referenceList = new ArrayList<>();
        referenceList.add(shareFileName);
        if (fileInterface.writeToFile(RELATIVE_PATH, PORTFOLIO_DIRECTORY, shareFileName,
                formattedString.getBytes())) {
          fileInterface.writeToFile(RELATIVE_PATH, PORTFOLIO_DIRECTORY, PORTFOLIO_FILENAME,
                  (fileInterface.convertObjectIntoString(portfolioObject.toString(), referenceList)
                          + MUTABLE).getBytes());
        }
      } else {
        fileInterface.writeToFile(RELATIVE_PATH, PORTFOLIO_DIRECTORY, shareFileName,
                formattedString.getBytes());
      }
      shares = new HashMap<>();
      portfolios.add(portfolioObject);
    } else {
      double currentShareBuyingPrice = this.getStockPrice(symbol, date);
      Share addShare = new Share(symbol, date, currentShareBuyingPrice + BROKER_FEES,
              numShares);
      if (null != shareFileName && !shareFileName.isEmpty()) {
        String formattedString = fileInterface.convertObjectIntoString(addShare.toString(),
                null);
        fileInterface.writeToFile(RELATIVE_PATH, PORTFOLIO_DIRECTORY, shareFileName,
                formattedString.getBytes());
      }
    }
    return true;
  }

  /**
   * A method to balance the portfolio on a given date.
   *
   * @param portfolioName name of the portfolio.
   * @param weights       weightage for the stock in the portfolio.
   * @param date          date on which portfolio is to be balanced.
   * @return whether successfully balanced or not.
   */
  @Override
  public boolean balancePortfolio(String portfolioName, HashMap<Share,
          Float> weights, LocalDate date) {
    Portfolio portfolioToModify = this.getPortfolioObjectById(portfolioName);
    Set<Share> newShares = new HashSet<>();
    double cost = 0;
    HashMap<Share, Double> prices = new HashMap<>();
    for (Share share : weights.keySet()) {
      if (share.getPurchaseDate().isBefore(date)
              || share.getPurchaseDate().isEqual(date)) {
        double price = this.getStockPrice(share.getCompanyName(), date) * share.getNumShares();
        cost += price;
        prices.put(share, this.getStockPrice(share.getCompanyName(), date));
      } else {
        throw new IllegalArgumentException("Stock not present on " + date);
      }
    }
    for (Share share : weights.keySet()) {
      if (share.getPurchaseDate().isBefore(date)
              || share.getPurchaseDate().isEqual(date)) {
        double allottedPrice = (weights.get(share) / 100) * cost;
        double excessShares = (allottedPrice - (prices.get(share) *
                share.getNumShares())) / (prices.get(share));
        if (excessShares > 0) {
          double newPrice = (share.getShareValue() + (prices.get(share) * (excessShares)))
                  / (share.getNumShares() + excessShares);
          newShares.add(new Share(share.getCompanyName(), share.getPurchaseDate(), newPrice,
                  share.getNumShares() + excessShares));
        } else {
          newShares.add(new Share(share.getCompanyName(), share.getPurchaseDate(), share.getPrice(),
                  share.getNumShares() + excessShares));
        }
      }
    }
    this.modifyPortfolio(portfolioToModify.getId(), newShares, portfolioToModify.getCreationDate());
    return true;
  }
}