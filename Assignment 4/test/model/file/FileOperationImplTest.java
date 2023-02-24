package model.file;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import model.portfolio.Portfolio;
import model.portfolio.PortfolioImpl;
import model.stocks.Stock;
import model.stocks.StockImpl;

import static org.junit.Assert.assertTrue;

/**
 * A JUnit test class to test if the file save and retrieve operations
 * are functioning as expected.
 */
public class FileOperationImplTest {
  private Portfolio portfolio;

  @Before
  public void setUp() {
    Stock st = new StockImpl("GOOGL", 50, 10);
    Stock st2 = new StockImpl("IBM", 20, 22);
    List<Stock> stocks = new ArrayList<>();
    stocks.add(st);
    stocks.add(st2);
    portfolio = new PortfolioImpl("name", stocks);
  }

  /**
   * A function to test the saving of a portfolio.
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
   * A test to see if the file retrieval for a valid file executes or not.
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
    fp.retrieveFile("abc");
  }
}