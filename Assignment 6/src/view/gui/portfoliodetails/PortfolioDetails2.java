package view.gui.portfoliodetails;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.WindowConstants;

import view.gui.AbstractView;
import controller.ActionCommands;
import model.Model;
import model.portfolio.Portfolio;

/**
 * A class view to display the contents of a given portfolio.
 */
public class PortfolioDetails2 extends AbstractView {
  private final JFrame frame;
  private final JButton button1;

  /**
   * A constructor to initialize the view.
   */
  public PortfolioDetails2(Model readOnlyModal) {
    frame = new JFrame("Portfolio Details");
    JPanel panel = new JPanel();
    frame.getContentPane();


    button1 = new JButton("Back");

    List<Portfolio> portfolios = readOnlyModal.getMPortfolio();

    Label label = new Label("No portfolios created yet");

    if (portfolios != null && portfolios.size() > 0) {
      label.setText("Portfolio Details:");
      for (Portfolio p : portfolios) {
        label.setText(label.getText() + " " + p.getName() + "" + p.getStockList());
      }
    }

    GridLayout layout = new GridLayout(5, 1);
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

  @Override
  public void setVisible(boolean b) {
    frame.setVisible(true);
    button1.setVisible(true);
  }

  @Override
  public void dispose() {
    frame.dispose();
  }
}
