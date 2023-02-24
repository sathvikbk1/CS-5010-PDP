package model.file;

import javax.xml.xpath.XPathExpressionException;

import model.portfolio.Portfolio;

/**
 * An interface to handle the file saving and
 * retrieving of relevant platform compatible files.
 */
public interface FileOperation {
  /**
   * A method to save the portfolio in the XML format.
   *
   * @param portfolio portfolio to be saved.
   */
  void saveFile(Portfolio portfolio);

  /**
   * A method to retrieve the platform compatible files.
   *
   * @param name name of the file.
   * @return the Portfolio extracted.
   * @throws XPathExpressionException Invalid path exception.
   */
  Portfolio retrieveFile(String name) throws XPathExpressionException;
}
