package view.gui.strategy;

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
 * A class view to obtain the name of the strategy.
 */
public class StrategyName extends AbstractView {
  private final JFrame frame;
  private final JLabel label1;
  private final JLabel label2;
  private final JTextField textField1;
  private final JButton button1;

  /**
   * A constructor to initialize the menu options.
   */
  public StrategyName() {
    frame = new JFrame("Strategy Name");
    JPanel panel = new JPanel();
    frame.getContentPane();

    label1 = new JLabel("Enter the following information to proceed :");
    label2 = new JLabel("Enter the name of the Strategy:");
    textField1 = new JTextField("Strategy  Name", 20);
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
    button1.setActionCommand(ActionCommands.STRATEGY_START_DATE);
    button1.addActionListener(listener);
  }

  @Override
  public void setVisible(boolean b) {
    label1.setVisible(true);
    label2.setVisible(true);
    textField1.setVisible(true);
    button1.setVisible(true);

  }

  @Override
  public void dispose() {
    frame.dispose();
  }

  @Override
  public String getStrategyName() {
    return textField1.getText();
  }
}

