package view.gui.buy;

import java.awt.event.ActionListener;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.WindowConstants;


import view.gui.AbstractView;
import controller.ActionCommands;

/**
 * A class view that represents the third buy view.
 */
public class Buy3 extends AbstractView {
  private final JFrame frame;
  private final JLabel label1;
  private final JLabel label2;
  private final JTextField textField1;
  private final JButton button1;

  /**
   * A constructor to initialize the view.
   */
  public Buy3() {
    frame = new JFrame("Buy Stock");
    JPanel panel = new JPanel();
    frame.getContentPane();

    label1 = new JLabel("Enter the following information to proceed :");
    label2 = new JLabel("Enter the stock price :");
    textField1 = new JTextField("Stock price", 5);
    textField1.setOpaque(false);
    button1 = new JButton("Next");

    GridLayout layout = new GridLayout(4, 1);
    panel.setLayout(layout);
    panel.add(label1);
    panel.add(label2);
    panel.add(textField1);
    panel.add(button1);

    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.add(panel);

    frame.setSize(575, 150);
    frame.setVisible(true);

  }


  @Override
  public void addActionListener(ActionListener listener) {
    button1.setActionCommand(ActionCommands.BUY_STOCK_DATE);
    button1.addActionListener(listener);

  }

  @Override
  public void setVisible(boolean b) {
    label1.setVisible(b);
    label2.setVisible(b);
    textField1.setVisible(b);
    button1.setVisible(b);
  }

  @Override
  public void dispose() {
    frame.dispose();
  }

  @Override
  public double getStockPrice() {
    return Double.parseDouble(textField1.getText());
  }
}
