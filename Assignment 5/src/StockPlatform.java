import java.io.InputStreamReader;

import controller.Control;
import controller.ControlImpl;
import model.Model;
import model.ModelImpl;

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
    Model model = new ModelImpl();
    controller.execute(model);
  }
}
