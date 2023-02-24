package model.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;

import javax.xml.xpath.XPathExpressionException;

import model.plans.Strategy;
import model.plans.StrategyImpl;
import model.portfolio.Portfolio;
import model.portfolio.PortfolioFlexImpl;
import model.stocks.Stock;
import model.stocks.StockFlexImpl;

import static model.file.InterfaceSerializer.interfaceSerializer;

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
    try {
      if (portfolio.getName() == null
              || portfolio.getName().isEmpty()) {
        throw new IllegalArgumentException("Portfolio can't be empty");
      }

      Gson gson = new GsonBuilder()
              .serializeNulls()
              .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
              .registerTypeAdapter(Portfolio.class,
                      interfaceSerializer(PortfolioFlexImpl.class))
              .registerTypeAdapter(Stock.class,
                      interfaceSerializer(StockFlexImpl.class))
              .registerTypeAdapter(Strategy.class,
                      interfaceSerializer(StrategyImpl.class))
              .setPrettyPrinting()
              .create();
      String json = gson.toJson(portfolio);


      //PortfolioModelSetFlexible user = gson.fromJson(json, PortfolioModelFlexibleImpl.class);

      FileWriter fWriter = new FileWriter("./"
              + portfolio.getName() + ".json");
      fWriter.write(json);
      fWriter.close();
    } catch (Exception e) {
      e.printStackTrace();
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
    Portfolio portfolio = null;
    try {
      if (name.isEmpty()) {
        throw new IllegalArgumentException("Portfolio not found");
      }

      File file = new File("./" + name + ".json");
      BufferedReader br = new BufferedReader(new FileReader(file));
      StringBuilder result = new StringBuilder();
      String line;

      while ((line = br.readLine()) != null) {
        result.append(line);
      }
      Gson gson = new GsonBuilder()
              .serializeNulls()
              .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
              .registerTypeAdapter(Portfolio.class,
                      interfaceSerializer(PortfolioFlexImpl.class))
              .registerTypeAdapter(Stock.class,
                      interfaceSerializer(StockFlexImpl.class))
              .registerTypeAdapter(Strategy.class,
                      interfaceSerializer(StrategyImpl.class))
              .setPrettyPrinting()
              .create();
      portfolio = gson.fromJson(
              result.toString(), PortfolioFlexImpl.class);


    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
    return portfolio;
  }
}
