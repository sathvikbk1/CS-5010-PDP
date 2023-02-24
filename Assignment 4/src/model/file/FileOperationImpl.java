package model.file;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import model.portfolio.Portfolio;
import model.portfolio.PortfolioImpl;
import model.stocks.Stock;
import model.stocks.StockImpl;

/**
 * A class to handle the saving and retrieval of files.
 */
public class FileOperationImpl implements FileOperation {
  /**
   * A method to save the portfolio created in the XML format.
   *
   * @param portfolio portfolio to be saved.
   */
  @Override
  public void saveFile(Portfolio portfolio) {

    InputStream in = null;
    DocumentBuilder domBuilder = null;
    String[] cols = new String[]{"TickerValue", "NumberOfShares", "Cost"};
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
        data.add(row);
        rootElement.appendChild(rowElement);
        i = i + 1;
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    TransformerFactory tranFactory = TransformerFactory.newInstance();
    Transformer aTransformer = null;
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
    final Document noOfShares = createDocument(name);
    final XPath cost = createPath();

    final NodeList numberOfShares = costOfShares("//numberOfShares/text()",
            noOfShares, cost);
    final Collection<String> noOfShare = convertToCollection(numberOfShares);

    final NodeList costs = costOfShares("//Cost/text()", noOfShares,
            cost);
    final Collection<String> price = convertToCollection(costs);

    final NodeList tickerVals = costOfShares("//TickerValue/text()", noOfShares,
            cost);
    final Collection<String> ticker = convertToCollection(tickerVals);
    List<Stock> mStock = new ArrayList<>();
    Stock stock;
    List<String> vals = new ArrayList<>();
    int i = 0;
    for (final String tickers : ticker) {
      vals.add(tickers);
      i = i + 1;
    }
    vals.addAll(noOfShare);
    vals.addAll(price);
    for (int j = 0; j != i; j++) {
      mStock.add(new StockImpl(vals.get(j), Double.parseDouble(vals.get(j + i)),
              Double.parseDouble(vals.get(j + (2 * i)))));
    }
    return new PortfolioImpl(name, mStock);
  }

  /**
   * helper function to create the document.
   *
   * @param name name of the document.
   * @return Document parsable format of the XML file.
   */
  private Document createDocument(String name) {
    File file = new File(name + ".xml");

    Document doc;
    try {
      final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setNamespaceAware(true);
      final DocumentBuilder db = dbf
              .newDocumentBuilder();
      doc = db.parse(file);
    } catch (UnsupportedEncodingException e) {
      throw new IllegalArgumentException("Invalid");
    } catch (ParserConfigurationException e) {
      throw new IllegalArgumentException("Invalid");
    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid");
    } catch (SAXException e) {
      throw new IllegalArgumentException("Invalid");
    }
    return doc;
  }

  /**
   * A helper function to create the XML path.
   *
   * @return Path created.
   */
  private XPath createPath() {
    final XPathFactory pf = XPathFactory.newInstance();
    final XPath p = pf.newXPath();
    return p;
  }

  /**
   * A helper function to retrieve the fields from the XML file.
   *
   * @param path string format of the path.
   * @param doc  the document form of the file.
   * @param p    the path of the file.
   * @return a list of all such field occurances in the XML file.
   * @throws XPathExpressionException when an invalid path is encountered.
   */
  private NodeList costOfShares(final String path,
                                final Document doc, final XPath p) throws XPathExpressionException {
    NodeList nodes = null;
    if (doc != null) {
      try {
        final XPathExpression expr = p.compile(path);
        final Object result = expr
                .evaluate(doc, XPathConstants.NODESET);
        nodes = (NodeList) result;
      } catch (final XPathExpressionException exception) {
        throw new XPathExpressionException(exception);
      }
    }
    return nodes;
  }

  /**
   * A helper function to convert the list to a collection.
   *
   * @param nodes the node list generated.
   * @return a collection of the resultant fields.
   */
  private Collection<String> convertToCollection(final NodeList nodes) {
    final Collection<String> result = new ArrayList<String>();
    if (nodes != null) {
      for (int i = 0; i < nodes.getLength(); i++) {
        result.add(nodes.item(i).getNodeValue());
      }
    }
    return result;
  }

}