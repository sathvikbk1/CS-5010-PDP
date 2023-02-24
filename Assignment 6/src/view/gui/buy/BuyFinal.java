package view.gui.buy;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.WindowConstants;

import view.gui.AbstractView;
import controller.ActionCommands;

/**
 * A class view that represents the final buy view.
 */
public class BuyFinal extends AbstractView {
  private final JFrame frame;
  private final JLabel label1;
  private final JButton button1;
  private final JButton button2;
  private final JButton button3;
  private final JButton button4;

  /**
   * A constructor to initialize the view.
   */
  public BuyFinal() {
    frame = new JFrame("Finalize Buy");
    JPanel panel = new JPanel();
    frame.getContentPane();

    label1 = new JLabel("Select one of the following options to continue:");
    Dimension size1 = label1.getPreferredSize();
    label1.setBounds(0, 0, size1.width, size1.height);


    button1 = new JButton("Buy a new stock");
    button2 = new JButton("Sell a stock");
    button3 = new JButton("Create a strategy");
    button4 = new JButton("Close the portfolio");

    GridLayout layout = new GridLayout(5, 1);
    panel.setLayout(layout);
    panel.add(label1);
    panel.add(button1);
    panel.add(button2);
    panel.add(button3);
    panel.add(button4);
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.add(panel);
    frame.setSize(700, 500);
    frame.setVisible(true);
  }

  @Override
  public void addActionListener(ActionListener listener) {
    button1.setActionCommand(ActionCommands.BUY_STOCK);
    button1.addActionListener(listener);
    button2.setActionCommand(ActionCommands.SELL_STOCK);
    button2.addActionListener(listener);
    button3.setActionCommand(ActionCommands.STRATEGY_1);
    button3.addActionListener(listener);
    button4.setActionCommand(ActionCommands.BUY_STOCK_FINAL_NAME);
    button4.addActionListener(listener);
  }

  @Override
  public void setVisible(boolean b) {
    frame.setVisible(true);
    label1.setVisible(true);
    button1.setVisible(true);
    button2.setVisible(true);
  }

  @Override
  public void dispose() {
    frame.dispose();
  }


}