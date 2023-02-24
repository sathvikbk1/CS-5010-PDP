package view.gui.costondate;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.WindowConstants;

import view.gui.AbstractView;
import controller.ActionCommands;

/**
 * A class view to display the cost of a portfolio on a particular date.
 */
public class CostOnDate3 extends AbstractView {

  private final JFrame frame;
  private final JButton button1;

  /**
   * A constructor to initialize the view.
   *
   * @param portfolioName name of the portfolio.
   * @param value         cost of the portfolio on given date.
   */
  public CostOnDate3(String portfolioName, double value) {
    frame = new JFrame("Cost On Date");
    JPanel panel = new JPanel();
    frame.getContentPane();


    button1 = new JButton("Back");
    JLabel label = new JLabel();
    if (portfolioName == null) {
      label.setText("No such portfolio");
    } else {
      label.setText("Portfolio Name: ");
      label.setText("<html>" + label.getText() + " " + "<br>" + portfolioName
              + " " + "<br>" + "Cost on date: " + "$" + value + "</html>");
    }

    GridLayout layout = new GridLayout(5, 1);
    panel.setLayout(layout);
    panel.add(label);
    panel.add(button1);
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.add(panel);


    frame.setSize(575, 500);
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
