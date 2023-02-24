package controller;


import model.Model;

/**
 * A controller interface to control the flow of the program
 * through different stages and of mimicking the operations of
 * a stock platform.
 */
public interface Control {
  /**
   * A function for the controller to start execution.
   */
  void execute(Model model);
}
