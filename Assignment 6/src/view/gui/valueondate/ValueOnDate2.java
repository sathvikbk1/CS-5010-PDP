package view.gui.valueondate;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.time.LocalDate;

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
 * A class view to obtain the date on which the value
 * is to be displayed for a particular portfolio.
 */
public class ValueOnDate2 extends AbstractView {
  private final JFrame frame;
  private final JLabel label1;
  private final JTextField textField1;
  private final JButton button1;

  /**
   * A constructor to initialize the menu options.
   */
  public ValueOnDate2() {
    frame = new JFrame("Value on Date");
    JPanel panel = new JPanel();
    frame.getContentPane();

    label1 = new JLabel("Enter the following information to proceed :");
    JLabel label2 = new JLabel("Enter the date in the format YYYY-MM-DD :");
    textField1 = new JTextField("YYYY-MM-DD", 5);
    textField1.setOpaque(false);
    button1 = new JButton("Get Cost");

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
    button1.setActionCommand(ActionCommands.VALUE_ON_DATE_FINAL);
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
  public LocalDate getStockDate() {
    return LocalDate.parse(textField1.getText());
  }
}
