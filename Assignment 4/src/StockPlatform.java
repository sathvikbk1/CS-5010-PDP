import java.io.InputStreamReader;

import controller.Control;
import controller.ControlImpl;
import model.portfolio.Portfolio;
import model.portfolio.PortfolioImpl;

/**
 * The main class that calls the controller
 * to begin execution of the program.
 */
public class StockPlatform {
  /**
   * A main method to launch the program.
   *
   * @param args any arguments which might be passed to the main function.
   */
  public static void main(String[] args) {
    Control controller = new ControlImpl(new InputStreamReader(System.in),System.out);
    Portfolio portfolio = new PortfolioImpl();
    controller.execute(portfolio);
  }
}
