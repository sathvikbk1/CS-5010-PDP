package model.file;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * An abstract class used for handling the file operations.
 */
public abstract class FileOperationAbs implements FileOperation {


  /**
   * helper function to create the document.
   *
   * @param name name of the document.
   * @return Document parsable format of the XML file.
   */
  Document createDocument(String name) {
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
  XPath createPath() {
    final XPathFactory pf = XPathFactory.newInstance();
    return pf.newXPath();
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
  NodeList costOfShares(final String path,
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
  Collection<String> convertToCollection(final NodeList nodes) {
    final Collection<String> result = new ArrayList<String>();
    if (nodes != null) {
      for (int i = 0; i < nodes.getLength(); i++) {
        result.add(nodes.item(i).getNodeValue());
      }
    }
    return result;
  }
}
