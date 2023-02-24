package view.gui.loadportfolio;

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
 * A class view to load the portfolio into memory.
 */
public class LoadPortfolio2 extends AbstractView {
  private final JFrame frame;
  private final JLabel label1;
  private final JButton button1;

  /**
   * A constructor to initialize the view.
   */
  public LoadPortfolio2() {
    frame = new JFrame("Load Portfolio Status");
    JPanel panel = new JPanel();
    frame.getContentPane();

    label1 = new JLabel("Portfolio loaded successfully :");

    button1 = new JButton("Next");

    GridLayout layout = new GridLayout(2,1);
    panel.setLayout(layout);
    panel.add(label1);
    panel.add(button1);

    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.add(panel);

    frame.setSize(575, 150);
    frame.setVisible(true);
  }

  @Override
  public void addActionListener(ActionListener listener) {
    button1.setActionCommand(ActionCommands.CREATE_PORTFOLIO);
    button1.addActionListener(listener);
  }

  @Override
  public void setVisible(boolean b) {
    label1.setVisible(true);
    button1.setVisible(true);

  }

  @Override
  public void dispose() {
    frame.dispose();
  }
}
