package model.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * A class that implements the methods and the URL
 * calls to the AlphaVantage API to retrieve ticker
 * close data value till date.
 */
public class ApiCallImpl implements ApiCall {

  private final String apiKey;
  private final String stockSymbol;
  private URL url;

  /**
   * A constructor to initialise the API key and the ticker.
   *
   * @param stockSymbol ticker value of the stock.
   */
  public ApiCallImpl(String stockSymbol) throws IllegalArgumentException {
    this.apiKey = "1BLY12GU5EH8GB6T";
    this.stockSymbol = stockSymbol;
    this.url = null;
  }

  /**
   * A method to retrieve the data from the API call.
   *
   * @return a hash map of everyday closing value for a ticker.
   */
  @Override
  public HashMap<LocalDate, Double[]> getData() {
    this.initializeURL();
    InputStream in = null;
    HashMap<LocalDate, Double[]> list = new HashMap<>();
    try {
      /*
      Execute this query. This returns an InputStream object.
      In the csv format, it returns several lines, each line being separated
      by commas. Each line contains the date, price at opening time, highest
      price for that date, lowest price for that date, price at closing time
      and the volume of trade (no. of shares bought/sold) on that date.

      This is printed below.
       */
      in = url.openStream();
      int b = 0;
      String line;
      String splitBy = ",";
      try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
        while ((line = br.readLine()) != null) {
          if (b == 0) {
            b++;
            continue;
          }
          b++;
          String[] country = line.split(splitBy);
          Double[] vals = {Double.parseDouble(country[1]),
                  Double.parseDouble(country[2]), Double.parseDouble(country[3]),
                  Double.parseDouble(country[4])};
          list.put(LocalDate.parse(country[0]), vals);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }
    return list;
  }

  /**
   * A helper function to initialise the URL for the API call.
   */
  private void initializeURL() throws RuntimeException {
    try {
      /*
      create the URL. This is the query to the web service. The query string
      includes the type of query (DAILY stock prices), stock symbol to be
      looked up, the API key and the format of the returned
      data (comma-separated values:csv). This service also supports JSON
      which you are welcome to use.
       */
      this.url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stockSymbol + "&apikey=" + this.apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }
  }
}
