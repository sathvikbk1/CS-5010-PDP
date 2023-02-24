package model.file;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;

import model.portfolio.Portfolio;
import model.portfolio.PortfolioFlexImpl;
import model.stocks.Stock;
import model.stocks.StockFlexImpl;

/**
 * A class to save and retrieve files of type flexible portfolio.
 */
public class FileOperationFlexImpl extends FileOperationAbs implements FileOperation {
  /**
   * A method to save the portfolio created in the XML format.
   *
   * @param portfolio portfolio to be saved.
   */
  @Override
  public void saveFile(Portfolio portfolio) {

    DocumentBuilder domBuilder = null;
    String[] cols = new String[]{"TickerValue", "NumberOfShares", "Cost", "Date", "Commission"};
    try {
      DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
      domBuilder = domFactory.newDocumentBuilder();
    } catch (FactoryConfigurationError | Exception exp) {
      System.err.println(exp);
    }
    assert domBuilder != null;
    Document newDoc = domBuilder.newDocument();
    Element rootElement = newDoc.createElement("portfolio");
    newDoc.appendChild(rootElement);

    List<Map<String, String>> data = new ArrayList<>();

    try {
      Map<String, String> row = new HashMap<>();
      int i = 1;
      for (Stock stock : portfolio.getStockList()) {
        Element rowElement = newDoc.createElement("stock");
        row.put(cols[0], stock.getStockName());
        Element entryElement = newDoc.createElement(cols[0]);
        entryElement.appendChild(newDoc.createTextNode(stock.getStockName() + ""));
        rowElement.appendChild(entryElement);
        row.put(cols[1], stock.getNoOfShares() + "");
        entryElement = newDoc.createElement(cols[1]);
        entryElement.appendChild(newDoc.createTextNode(stock.getNoOfShares() + ""));
        rowElement.appendChild(entryElement);
        row.put(cols[2], stock.getCost() + "");
        entryElement = newDoc.createElement(cols[2]);
        entryElement.appendChild(newDoc.createTextNode(stock.getCost() + ""));
        rowElement.appendChild(entryElement);
        row.put(cols[3], stock.getCost() + "");
        entryElement = newDoc.createElement(cols[3]);
        entryElement.appendChild(newDoc.createTextNode(stock.getDate() + ""));
        rowElement.appendChild(entryElement);
        row.put(cols[4], stock.getCost() + "");
        entryElement = newDoc.createElement(cols[4]);
        entryElement.appendChild(newDoc.createTextNode(stock.getCommission() + ""));
        rowElement.appendChild(entryElement);
        data.add(row);
        rootElement.appendChild(rowElement);
        i = i + 1;
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    TransformerFactory tranFactory = TransformerFactory.newInstance();
    Transformer aTransformer;
    try {
      aTransformer = tranFactory.newTransformer();
      aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
      aTransformer.setOutputProperty(OutputKeys.METHOD, "xml");
      aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
      Source src = new DOMSource(newDoc);
      Result result = new StreamResult(new File(portfolio.getName() + ".xml"));
      StringWriter outWriter = new StringWriter();
      aTransformer.transform(src, result);
    } catch (TransformerException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * A method to retrieve portfolio files of compatible type in XML format.
   *
   * @param name name of the file.
   * @return Portfolio retrieved.
   * @throws XPathExpressionException Invalid path exception.
   */
  @Override
  public Portfolio retrieveFile(String name) throws XPathExpressionException {
    final Document document = createDocument(name);
    final XPath path = createPath();

    final NodeList numberOfShares = costOfShares("//NumberOfShares/text()",
            document, path);
    final Collection<String> noOfShare = convertToCollection(numberOfShares);

    final NodeList costs = costOfShares("//Cost/text()", document,
            path);
    final Collection<String> price = convertToCollection(costs);

    final NodeList tickerValues = costOfShares("//TickerValue/text()", document,
            path);
    final Collection<String> ticker = convertToCollection(tickerValues);
    final NodeList dateValues = costOfShares("//Date/text()", document,
            path);
    final Collection<String> date = convertToCollection(dateValues);
    final NodeList comValues = costOfShares("//Commission/text()", document,
            path);
    final Collection<String> com = convertToCollection(comValues);
    List<Stock> mStock = new ArrayList<>();
    List<Stock> transactionHistory = new ArrayList<>();
    List<String> values = new ArrayList<>();
    int i = 0;
    for (final String tickers : ticker) {
      values.add(tickers);
      i = i + 1;
    }
    values.addAll(noOfShare);
    values.addAll(price);
    values.addAll(date);
    values.addAll(com);
    for (int j = 0; j != i; j++) {
      mStock.add(new StockFlexImpl(values.get(j), Double.parseDouble(values.get(j + i)),
              Double.parseDouble(values.get(j + (2 * i))), LocalDate.parse(values.get(j + (3 * i))),
              Double.parseDouble(values.get(j + (4 * i)))));
      transactionHistory.add(new StockFlexImpl(values.get(j), Double.parseDouble(values.get(j + i)),
              Double.parseDouble(values.get(j + (2 * i))), LocalDate.parse(values.get(j + (3 * i))),
              Double.parseDouble(values.get(j + (4 * i)))));
    }
    return new PortfolioFlexImpl(name, mStock, transactionHistory);
  }
}
