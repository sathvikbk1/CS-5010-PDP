package view.gui.strategy;

import java.awt.GridLayout;
import java.awt.Dimension;
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
 * A class view to obtain the options available
 * for creating a strategy.
 */
public class Strategy1 extends AbstractView {
  private final JFrame frame;
  private final JLabel label1;
  private final JButton button1;
  private final JButton button2;

  /**
   * A constructor to initialize the view.
   */
  public Strategy1() {
    frame = new JFrame("Create Strategy");
    JPanel panel = new JPanel();
    frame.getContentPane();

    label1 = new JLabel("Select one of the following options to continue:");
    Dimension size1 = label1.getPreferredSize();
    label1.setBounds(0, 0, size1.width, size1.height);


    button1 = new JButton("Add new stock to strategy");
    button2 = new JButton("Finalize strategy");
    GridLayout layout = new GridLayout(4, 1);
    panel.setLayout(layout);
    panel.add(label1);
    panel.add(button1);
    panel.add(button2);

    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.add(panel);
    frame.setSize(700, 500);
    frame.setVisible(true);
  }

  @Override
  public void addActionListener(ActionListener listener) {
    button1.setActionCommand(ActionCommands.STRATEGY_2);
    button1.addActionListener(listener);
    button2.setActionCommand(ActionCommands.STRATEGY_NAME);
    button2.addActionListener(listener);
  }

  @Override
  public void setVisible(boolean b) {
    label1.setVisible(true);
    button1.setVisible(true);
    button2.setVisible(true);
  }

  @Override
  public void dispose() {
    frame.dispose();
  }
}
