package view.gui;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.WindowConstants;

import controller.ActionCommands;
import model.Model;
import model.portfolio.Portfolio;

/**
 * A class view to list all the portfolios.
 */
public class ListAllPortfolios extends AbstractView {

  private final JFrame frame;
  private final JButton button1;

  /**
   * A constructor to initialize the view.
   *
   * @param readOnlyModal the model used to be referenced.
   */
  public ListAllPortfolios(Model readOnlyModal) {
    frame = new JFrame("Portfolios");
    JPanel panel = new JPanel();
    frame.getContentPane();


    button1 = new JButton("Back");

    List<Portfolio> portfolios = readOnlyModal.getMPortfolio();

    Label label = new Label("No portfolios created yet");

    if (portfolios != null && portfolios.size() > 0) {
      label.setText("Portfolios:");
      for (Portfolio p : portfolios) {
        label.setText(label.getText() + ", " + p.getName());
      }
    }

    GridLayout layout = new GridLayout(0, 1);
    panel.setLayout(layout);
    panel.add(label);
    panel.add(button1);

    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.add(panel);


    frame.setSize(700, 500);
  }

  @Override
  public void addActionListener(ActionListener listener) {
    button1.setActionCommand(ActionCommands.TO_MAIN_MENU);
    button1.addActionListener(listener);
  }

  public void setVisible(boolean b) {
    frame.setVisible(true);
    button1.setVisible(true);
  }

  public void dispose() {
    frame.dispose();
  }
}
