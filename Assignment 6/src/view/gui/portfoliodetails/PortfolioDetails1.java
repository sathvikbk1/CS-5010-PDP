package view.gui.portfoliodetails;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.WindowConstants;

import view.gui.AbstractView;
import controller.ActionCommands;

/**
 * A class view to display the contents of a portfolio.
 */
public class PortfolioDetails1 extends AbstractView {
  private final JFrame frame;
  private final JLabel label1;
  private final JTextField textField1;
  private final JButton button1;

  /**
   * A constructor to initialize the view.
   */
  public PortfolioDetails1() {
    frame = new JFrame("Portfolio Details");
    JPanel panel = new JPanel();
    frame.getContentPane();

    label1 = new JLabel("Enter the following information to proceed :");
    JLabel label2 = new JLabel("Enter the name of the portfolio :");
    textField1 = new JTextField("Portfolio name", 20);
    textField1.setOpaque(false);
    button1 = new JButton("Get Details");

    GridLayout layout = new GridLayout(4, 1);
    panel.setLayout(layout);
    panel.add(label1);
    panel.add(label2);
    panel.add(textField1);
    panel.add(button1);

    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.add(panel);

    frame.setSize(700, 500);
    frame.setVisible(true);
  }

  @Override
  public void addActionListener(ActionListener listener) {
    button1.setActionCommand(ActionCommands.PORTFOLIO_DETAILS_VALUE);
    button1.addActionListener(listener);

  }

  @Override
  public void setVisible(boolean b) {
    label1.setVisible(true);
    textField1.setVisible(true);
    button1.setVisible(true);
  }

  @Override
  public void dispose() {
    frame.dispose();
  }

  @Override
  public String getPortfolioName() {
    return textField1.getText();
  }
}
