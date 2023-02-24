package model.file;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import model.plans.Strategy;
import model.portfolio.Portfolio;
import model.portfolio.PortfolioFlexImpl;
import model.portfolio.PortfolioImpl;
import model.stocks.Stock;
import model.stocks.StockFlexImpl;
import model.stocks.StockImpl;

import static org.junit.Assert.assertTrue;

/**
 * A JUnit test class to test if the file save and retrieve operations
 * are functioning as expected.
 */
public class FileOperationImplTest {
  private Portfolio portfolio;
  private Portfolio portfolio2;

  @Before
  public void setUp() {
    Stock st = new StockImpl("GOOGL", 50, 10);
    Stock st2 = new StockImpl("IBM", 20, 22);
    List<Stock> stocks = new ArrayList<>();
    stocks.add(st);
    stocks.add(st2);
    portfolio = new PortfolioImpl("name", stocks);
    Stock stock = new StockFlexImpl("GOOGL", 50, 89, LocalDate.parse("2022-11-08"), 10);
    Stock stock1 = new StockFlexImpl("IBM", 20, 140, LocalDate.parse("2022-11-09"), 15);
    List<Stock> stocks1 = new ArrayList<>();
    List<Stock> stocks2 = new ArrayList<>();
    List<Strategy> strategies = null;
    stocks1.add(stock);
    stocks1.add(stock1);
    stocks2.add(stock);
    stocks2.add(stock1);
    portfolio2 = new PortfolioFlexImpl("another", stocks1, stocks2, strategies);
  }

  /**
   * A function to test the saving of an inflexible portfolio.
   */
  @Test
  public void testSaveFile() {
    FileOperation fp = new FileOperationImpl();
    boolean check = true;
    try {
      fp.saveFile(portfolio);
    } catch (Exception e) {
      check = false;
    }
    assertTrue(check);
  }

  /**
   * A test to see if the file retrieval for a valid file of
   * inflexible portfolio type executes or not.
   *
   * @throws XPathExpressionException in case of invalid path.
   */
  @Test
  public void testRetrieveFile() throws XPathExpressionException {
    FileOperation fp = new FileOperationImpl();
    boolean check = true;
    try {
      fp.retrieveFile("name");
    } catch (Exception e) {
      check = false;
    }
    assertTrue(check);
  }

  /**
   * A test to see if the file retrieval for an invalid file executes as expected.
   *
   * @throws XPathExpressionException in case of an invalid path.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRetrieveFile2() throws XPathExpressionException {
    FileOperation fp = new FileOperationImpl();
    fp.retrieveFile("xyz");
  }

  /**
   * A function to test the saving of a flexible portfolio.
   */
  @Test
  public void testSaveFileFlex() {
    FileOperation fp = new FileOperationImpl();
    boolean check = true;
    try {
      fp.saveFile(portfolio2);
    } catch (Exception e) {
      check = false;
    }
    assertTrue(check);
  }

  /**
   * A test to see if the file retrieval for a valid file of
   * flexible portfolio type executes or not.
   *
   * @throws XPathExpressionException in case of invalid path.
   */
  @Test
  public void testRetrieveFileFlex() throws XPathExpressionException {
    FileOperation fp = new FileOperationImpl();
    boolean check = true;
    try {
      fp.retrieveFile("another");
    } catch (Exception e) {
      check = false;
    }
    assertTrue(check);
  }
}