package model.api;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * An interface to call the AlphaVantage API and retrieve
 * the closing value for a particular ticker to perform the
 * necessary calculations.
 */
public interface ApiCall {
  /**
   * A method to retrieve the data by making the API call.
   *
   * @return a hash map of everyday closing value for a ticker.
   * @throws IllegalArgumentException in case an illegal argument is passed.
   */
  HashMap<LocalDate, Double> getData() throws IllegalArgumentException;
}
