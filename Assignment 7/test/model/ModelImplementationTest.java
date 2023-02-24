package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static utility.Constants.PORTFOLIO_DIRECTORY;
import static utility.Constants.PORTFOLIO_FILENAME;
import static utility.Constants.PORTFOLIO_NOT_FOUND;
import static utility.Constants.RELATIVE_PATH;
import static utility.Constants.STRATEGY_FILENAME;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.Test;

/**
 * Classes to test model in MVC.
 */
public class ModelImplementationTest {

  // create Empty portfolio
  @Test
  public void testEmptyCreatePortfolio() {
    ModelInterface model = new MockModel();
    try {
      model.createPortfolio("", LocalDate.now());
      fail("On creating empty portfolio model should fail");
    } catch (IllegalArgumentException e) {
      // Test passed
    }
  }

  // create valid portfolio
  @Test
  public void testCreateValidPortfolios() {
    ModelInterface model = new MockModel();
    String[] companies = {"AAPL", "MSFT", "GOOG", "AMZN", "NFLX", "META", "CTSH", "CRM", "TSLA"};
    for (String company : companies) {
      model.addShareToModel(company, LocalDate.now(), Math.abs(new Random()
              .nextInt(10)) + 1, -1);
    }
    try {
      model.createPortfolio("port5", LocalDate.now());
    } catch (IllegalArgumentException e) {
      fail("Test case failed even though valid data was given");
    }
  }

  private boolean compareStringContents(String a, String b) {
    return a.replaceAll("\\s+", "")
            .equalsIgnoreCase(b.replaceAll("\\s+", ""));
  }

  // test correct ticker
  @Test
  public void testCheckTickers() {
    ModelInterface model = new MockModel();
    assertTrue(model.checkTicker("AAPL"));
  }

  // test non-existent ticker
  @Test
  public void testInvalidTickers() {
    ModelInterface model = new MockModel();
    assertFalse(model.checkTicker("Apple"));
  }

  //Test get valid portfolio by id
  @Test
  public void testGetPortfolioByIds() {
    ModelInterface model = new MockModel();
    String[] companies = {"AAPL", "MSFT", "GOOG"};
    for (String company : companies) {
      model.addShareToModel(company, LocalDate.now(), 2, -1);
    }
    model.createPortfolio("port1", LocalDate.now());
    assertEquals("+id:port1\ncreationDate:2022-11-17\n"
                    + "*shares:+companyName:AAPL,purchaseDate:2022-11-17,price:20.0,numShares:2|"
                    + "+companyName:MSFT,purchaseDate:2022-11-17,price:20.0,numShares:2|"
                    + "+companyName:GOOG,purchaseDate:2022-11-17,price:20.0,numShares:2"
            , model.getPortfolioById("port1"));

  }

  //Test get invalid portfolio by id
  @Test
  public void testInvalidGetPortfolioByIds() {
    ModelInterface model = new MockModel();
    String[] companies = {"AAPL", "MSFT", "GOOG", "AMZN", "NFLX", "META", "CTSH", "CRM", "TSLA"};
    for (String company : companies) {
      model.addShareToModel(company, LocalDate.now(),
              Math.abs(new Random().nextInt(10)) + 1, -1);
    }
    model.createPortfolio("porttest", LocalDate.now());
    assertEquals(PORTFOLIO_NOT_FOUND, model.getPortfolioById("Yapple"));
  }

  // negative id or invalid string
  @Test
  public void testBlankIdPortfolioByIds() {
    ModelInterface model = new MockModel();
    String[] companies = {"ZD", "ZDGE", "ZDVSV", "ZEAL", "ZECP", "ZEN", "ZENV", "ZEPP"};
    for (String company : companies) {
      model.addShareToModel(company, LocalDate.now(), 30, -1);
    }
    try {
      model.getPortfolioById("");
      fail("Test case passed even though empty Id was given");
    } catch (IllegalArgumentException e) {
      // Test passed
    }
  }

  // Check if valuation is correct.
  @Test
  public void testBlankGetValuations() {
    ModelInterface model = new MockModel();
    model.addShareToModel("WAVC", LocalDate.now(),
            Math.abs(new Random().nextInt(10)) + 1, -1);
    try {
      model.getValuationGivenDate("", LocalDate.parse("2021-11-02"));
      fail("Test case passed even though invalid Id was given");
    } catch (IllegalArgumentException e) {
      // Test passed
    }
  }

  // invalid id/name
  @Test
  public void testInvalidGetValuations() {
    ModelInterface model = new MockModel();
    model.addShareToModel("YELP", LocalDate.now(),
            Math.abs(new Random().nextInt(10)) + 1, 20);
    try {
      model.getValuationGivenDate("NOTYELP", LocalDate.parse("2021-11-02"));
      fail("Test case passed even though invalid Id was given");
    } catch (IllegalArgumentException e) {
      // Test passed
    }
  }

  // valid get valuation
  @Test
  public void testValidGetValuations() {
    ModelInterface model = new MockModel();
    model.addShareToModel("IBM", LocalDate.parse("2021-11-02"),
            22, -1);

    model.createPortfolio("blipblop", LocalDate.parse("2021-11-02"));
    assertEquals(220, model.getValuationGivenDate("blipblop",
            LocalDate.parse("2021-11-02")), 1);
    assertEquals(0, model.getValuationGivenDate("blipblop",
            LocalDate.parse("2021-11-01")), 0);
  }

  // test blank ID present
  @Test
  public void testBlankIdIsPresents() {
    ModelInterface model = new MockModel();
    model.addShareToModel("TAGS", LocalDate.now(),
            Math.abs(new Random().nextInt(10)) + 1, -1);
    try {
      model.idIsPresent("");
      fail("Test case passed even though blank Id was given");
    } catch (IllegalArgumentException e) {
      // Test passed
    }
  }

  // test invalid ID present
  @Test
  public void testInvalidIdIsPresents() {
    ModelInterface model = new MockModel();
    model.addShareToModel("AADR", LocalDate.now(),
            Math.abs(new Random().nextInt(10)) + 1, -1);
    assertFalse(model.idIsPresent("notPresent"));
  }

  // test valid ID present
  @Test
  public void testIdIsPresents() {
    ModelInterface model = new MockModel();
    model.addShareToModel("IBM", LocalDate.now(), 24, -1);
    model.createPortfolio("Porttest", LocalDate.now());
    assertTrue(model.idIsPresent("Porttest"));
  }

  // test can't create share
  @Test
  public void testCantCreateShares() {
    ModelInterface model = new MockModel();
    assertFalse(model.canCreateShare());
  }

  // test can create share
  @Test
  public void testCanCreateShares() {
    ModelInterface model = new MockModel();
    model.addShareToModel("IBM", LocalDate.now(),
            Math.abs(new Random().nextInt(10)) + 1, -1);
    assertTrue(model.canCreateShare());
  }

  @Test
  public void testValidSellShares() {
    ModelInterface model = new MockModel();
    model.addShareToModel("IBM", LocalDate.of(2022, 11, 14),
            20, 30);
    String portfolioName = "Porttest";
    model.createPortfolio(portfolioName, LocalDate.of(2020, 12, 12));
    assertEquals("+id:Porttest\n" + "creationDate:2020-12-12\n"
                    + "*shares:+companyName:IBM,purchaseDate:2022-11-14,price:31.0,numShares:20",
            model.getPortfolioById(portfolioName));
    assertEquals(170.0, model.sellStocks(portfolioName, "IBM", 19,
            LocalDate.now()), 0.0);
    assertEquals("+id:Porttest\n" + "creationDate:2020-12-12\n"
                    + "*shares:+companyName:IBM,purchaseDate:2022-11-14,price:31.0,numShares:1",
            model.getPortfolioById(portfolioName));
  }

  @Test
  public void testDataPersistsInFile() {
    ModelInterface model = new ModelImplementation();
    model.addShareToModel("IBM", LocalDate.of(2022, 11, 14),
            20, 30);
    String portfolioName = "Porttest" + Math.random();
    model.createPortfolio(portfolioName, LocalDate.of(2020, 12, 12));
    FileInterface fileInterface = new CSVFile();
    List<String> ports = fileInterface.readFromFile(RELATIVE_PATH, PORTFOLIO_DIRECTORY,
            PORTFOLIO_FILENAME);
    boolean portfolioPersists = false;
    for (String port : ports) {
      if (port.contains("Porttest")) {
        portfolioPersists = true;
        break;
      }
    }
    assertTrue(portfolioPersists);
  }

  @Test
  public void testSellAllShare() {
    ModelInterface model = new MockModel();
    model.addShareToModel("IBM", LocalDate.now(), 20, 30);
    String portfolioName = "Porttest";
    model.createPortfolio(portfolioName, LocalDate.now());
    assertEquals(600, model.sellStocks(portfolioName, "IBM", 20,
            LocalDate.now()));
  }

  // To sell share is greater than number of present shares
  @Test
  public void testInvalidSellShares() {
    ModelInterface model = new MockModel();
    model.addShareToModel("IBM", LocalDate.now(), 20, 30);
    String portfolioName = "Porttest";
    model.createPortfolio(portfolioName, LocalDate.now());

    try {
      model.sellStocks(portfolioName, "IBM", 30, LocalDate.now());
      fail("Test case passed even though blank Id was given");
    } catch (IllegalArgumentException e) {
      // Test passed
    }
  }

  @Test
  public void testInvalidDateFormat() {
    ModelInterface model = new MockModel();

    try {
      model.addShareToModel("IBM", LocalDate.parse("20-20-20200"), 20,
              30);
      fail("Share added even though date was incorrectly formatted");
    } catch (DateTimeException e) {
      // Test passed
    }
  }

  // test valid get cost basis function
  @Test
  public void testValidGetCostBasis() {
    ModelInterface model = new MockModel();
    model.addShareToModel("IBM", LocalDate.parse("2021-11-01"), 20,
            30);
    String portfolioName = "Porttest";
    model.createPortfolio(portfolioName, LocalDate.now());
    assertEquals(620.0, model.getCostBasis(portfolioName,
            LocalDate.parse("2021-11-01")), 1);
  }


  @Test
  public void testDailyDifferentGetPortfolioPerformance() {
    ModelInterface model = new MockModel();
    model.addShareToModel("IBM", LocalDate.parse("2021-11-01"), 20,
            -1);
    model.addShareToModel("AAPL", LocalDate.parse("2021-11-02"), 20,
            -1);
    model.addShareToModel("MSFT", LocalDate.parse("2021-11-03"), 20,
            -1);
    String portfolioName = "Porttest2";
    model.createPortfolio(portfolioName, LocalDate.parse("2020-11-02"));
    double[] answer = {200.0, 400.0, 600.0};
    List<Double> actual = model.getPortfolioPerformance(portfolioName,
            LocalDate.parse("2021-11-01"), LocalDate.parse("2021-11-03"),
            Periodicity.DAY);
    assertEquals(3, actual.size());
    for (int i = 0; i <= 2; i++) {
      assertEquals(answer[i], actual.get(i), 0.0);
    }

  }

  @Test
  public void testDailySameGetPortfolioPerformance() {
    ModelInterface model = new MockModel();
    model.addShareToModel("IBM", LocalDate.parse("2021-11-01"), 20,
            -1);
    model.addShareToModel("AAPL", LocalDate.parse("2021-11-01"), 20,
            -1);
    model.addShareToModel("MSFT", LocalDate.parse("2021-11-03"), 20,
            -1);
    String portfolioName = "Porttest";
    model.createPortfolio(portfolioName, LocalDate.parse("2020-11-02"));
    double[] answer = {400.0, 400.0, 600.0};
    List<Double> actual = model.getPortfolioPerformance(portfolioName,
            LocalDate.parse("2021-11-01"), LocalDate.parse("2021-11-03"),
            Periodicity.DAY);
    assertEquals(3, actual.size());
    for (int i = 0; i <= 2; i++) {
      assertEquals(answer[i], actual.get(i), 0.0);
    }
  }

  @Test
  public void testMonthlyGetPortfolioPerformance() {
    ModelInterface model = new MockModel();
    model.addShareToModel("IBM", LocalDate.parse("2021-11-01"), 20,
            -1);
    model.addShareToModel("AAPL", LocalDate.parse("2021-11-01"), 20,
            -1);
    model.addShareToModel("MSFT", LocalDate.parse("2021-12-03"), 20,
            -1);
    String portfolioName = "Porttest";
    model.createPortfolio(portfolioName, LocalDate.parse("2020-11-02"));
    double[] answer = {400.0, 600.0, 600.0};
    List<Double> actual = model.getPortfolioPerformance(portfolioName,
            LocalDate.parse("2020-11-01"), LocalDate.parse("2022-01-01"),
            Periodicity.MONTH);
    assertEquals(3, actual.size());
    for (int i = 0; i <= 1; i++) {
      assertEquals(answer[i], actual.get(i), 0.0);
    }

  }

  @Test
  public void testYearlyGetPortfolioPerformance() {
    ModelInterface model = new MockModel();
    model.addShareToModel("IBM", LocalDate.parse("2020-11-01"), 20,
            -1);
    model.addShareToModel("AAPL", LocalDate.parse("2019-11-01"), 20,
            -1);
    model.addShareToModel("MSFT", LocalDate.parse("2021-12-03"), 20,
            -1);
    String portfolioName = "Porttest";
    model.createPortfolio(portfolioName, LocalDate.parse("2019-11-02"));
    double[] answer = {400.0, 600.0, 600.0};
    List<Double> actual = model.getPortfolioPerformance(portfolioName,
            LocalDate.parse("2020-11-01"), LocalDate.parse("2022-01-01"),
            Periodicity.YEAR);
    assertEquals(3, actual.size());
    for (int i = 0; i <= 1; i++) {
      assertEquals(answer[i], actual.get(i), 0.0);
    }

  }

  @Test
  public void testValidAppendPortfolios() {
    ModelInterface model = new MockModel();
    model.addShareToModel("IBM", LocalDate.parse("2021-11-01"), 20,
            -1);
    model.addShareToModel("AAPL", LocalDate.parse("2021-11-01"), 20,
            -1);
    String portfolioName = "Porttest";
    model.createPortfolio(portfolioName, LocalDate.parse("2020-11-02"));
    assertEquals("+id:Porttest\n" + "creationDate:2020-11-02\n"
                    + "*shares:+companyName:IBM,purchaseDate:2021-11-01,price:11.0,numShares:20|"
                    + "+companyName:AAPL,purchaseDate:2021-11-01,price:11.0,numShares:20",
            model.getPortfolioById(portfolioName));
    model.addShareToModel("MSFT", LocalDate.parse("2021-12-03"), 20,
            -1);
    assertEquals("+id:Porttest\n" + "creationDate:2020-11-02\n"
                    + "*shares:+companyName:IBM,purchaseDate:2021-11-01,price:11.0,numShares:20|"
                    + "+companyName:AAPL,purchaseDate:2021-11-01,price:11.0,numShares:20|"
                    + "+companyName:MSFT,purchaseDate:2021-12-03,price:11.0,numShares:20",
            model.getPortfolioById(portfolioName));

  }

  @Test
  public void testInvalidAppendPortfolios() {
    ModelInterface model = new MockModel();
    String portfolioName = "Porttest";
    try {
      model.appendPortfolio(portfolioName, "AZDESS", 20,
              LocalDate.parse("2020/01/01"));
      fail("Invalid appending operation & function did not throw any error");
    } catch (NoSuchElementException e) {
      // accepted
    }
  }

  @Test
  public void createStrategyTest() {
    ModelInterface model = new FlexibleModelImplementation();
    model.addShareToModel("IBM", LocalDate.parse("2021-11-01"), 20, 30);
    model.addShareToModel("AAPL", LocalDate.parse("2021-10-01"), 20, 30);
    String portfolioName = "Porttest_" + Math.random();
    model.createPortfolio(portfolioName, LocalDate.now());
    assertEquals(1240.0, model.getCostBasis(portfolioName, LocalDate.now()), 1);
    FileInterface fileInterface = new CSVFile();
    List<String> stratsOld = fileInterface.readFromFile(RELATIVE_PATH, PORTFOLIO_DIRECTORY,
            STRATEGY_FILENAME);
    boolean out = model.createStrategy(portfolioName, "10000", LocalDate.now(),
            LocalDate.of(2043, 11, 1),
            new ArrayList<String>(Arrays.asList("IBM", "AAPL")),
            new ArrayList<Integer>(Arrays.asList(20, 80)), -1);
    assertTrue(out);
    List<String> stratsNew = fileInterface.readFromFile(RELATIVE_PATH, PORTFOLIO_DIRECTORY,
            STRATEGY_FILENAME);
    assertEquals(stratsOld.size() + 1, stratsNew.size());
  }

  @Test
  public void testReBalancing() {
    ModelInterface model = new FlexibleModelImplementation();
    model.addShareToModel("IBM", LocalDate.parse("2021-11-01"), 20, 30);
    model.addShareToModel("AAPL", LocalDate.parse("2021-10-01"), 20, 30);
    String portfolioName = "Porttest_" + Math.random();
    model.createPortfolio(portfolioName, LocalDate.now());
    assertEquals(1240.0, model.getCostBasis(portfolioName, LocalDate.now()), 1);
    FileInterface fileInterface = new CSVFile();
    List<String> stratsOld = fileInterface.readFromFile(RELATIVE_PATH, PORTFOLIO_DIRECTORY,
            STRATEGY_FILENAME);
    HashMap<Share, Float> weights = new HashMap<>();
    weights.put(new Share("IBM", LocalDate.parse("2021-11-01"), 15, 30), (float) 50);
    weights.put(new Share("AAPL", LocalDate.parse("2021-10-01"), 25, 50), (float) 50);
    boolean out = model.balancePortfolio(portfolioName, weights, LocalDate.parse("2022-12-30"));
    List<String> stratsNew = fileInterface.readFromFile(RELATIVE_PATH, PORTFOLIO_DIRECTORY,
            STRATEGY_FILENAME);
    assertEquals(stratsOld.size(), stratsNew.size());
  }

  @Test
  public void testReBalanceInvalid() {
    ModelInterface model = new FlexibleModelImplementation();
    model.addShareToModel("IBM", LocalDate.parse("2021-11-01"), 20, 30);
    model.addShareToModel("AAPL", LocalDate.parse("2021-10-01"), 20, 30);
    String portfolioName = "Porttest_" + Math.random();
    model.createPortfolio(portfolioName, LocalDate.now());
    assertEquals(1240.0, model.getCostBasis(portfolioName, LocalDate.now()), 1);
    FileInterface fileInterface = new CSVFile();
    List<String> stratsOld = fileInterface.readFromFile(RELATIVE_PATH, PORTFOLIO_DIRECTORY,
            STRATEGY_FILENAME);
    HashMap<Share, Float> weights = new HashMap<>();
    weights.put(new Share("IBM", LocalDate.parse("2021-11-01"), 15, 30), (float) 50);
    weights.put(new Share("AAPL", LocalDate.parse("2021-10-01"), 25, 50), (float) 50);
    boolean out = false;
    try {
      out = model.balancePortfolio(portfolioName, weights, LocalDate.parse("2021-10-30"));
    } catch (IllegalArgumentException e) {
      //Ignored
    }
    assertFalse(out);
  }
}