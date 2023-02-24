package view.gui;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.WindowConstants;

import controller.ActionCommands;

/**
 * A class view to display all menu options.
 */
public class Menu extends AbstractView {
  private final JFrame frame;
  private final JButton button1;
  private final JButton button2;
  private final JButton button3;
  private final JButton button4;
  private final JButton button5;
  private final JButton button6;
  private final JButton button7;
  private final JButton button8;
  private final JButton button9;
  private final JButton button10;
  private final JButton button11;
  private final JButton button12;

  /**
   * A constructor to initialize the menu options.
   */
  public Menu() {
    super();
    frame = new JFrame("Page 2.1");
    JPanel panel = new JPanel();
    frame.getContentPane();

    JLabel label1 = new JLabel("Select one of the following options to continue:");
    Dimension size = label1.getPreferredSize();
    label1.setBounds(0, 0, size.width, size.height);

    button1 = new JButton("Create a new portfolio");
    Dimension size1 = button1.getPreferredSize();
    button1.setBounds(0, 0, size1.width, size1.height);

    button2 = new JButton("Show all portfolio");
    Dimension size2 = button2.getPreferredSize();
    button2.setBounds(0, 0, size2.width, size2.height);

    button3 = new JButton("Show detail of a portfolio");
    Dimension size3 = button3.getPreferredSize();
    button3.setBounds(0, 0, size3.width, size3.height);

    button4 = new JButton("Check total cost of portfolio");
    Dimension size4 = button4.getPreferredSize();
    button4.setBounds(0, 0, size4.width, size4.height);

    button5 = new JButton("Check total cost of portfolio on a given date");
    Dimension size5 = button5.getPreferredSize();
    button5.setBounds(0, 0, size5.width, size5.height);

    button6 = new JButton("Check total value of portfolio");
    Dimension size6 = button6.getPreferredSize();
    button6.setBounds(0, 0, size6.width, size6.height);

    button7 = new JButton("Check total value of portfolio on a given date");
    Dimension size7 = button7.getPreferredSize();
    button7.setBounds(0, 0, size7.width, size7.height);

    button8 = new JButton("Save a portfolio");
    Dimension size8 = button8.getPreferredSize();
    button8.setBounds(0, 0, size8.width, size8.height);

    button9 = new JButton("Retrieve a portfolio");
    Dimension size9 = button9.getPreferredSize();
    button9.setBounds(0, 0, size9.width, size9.height);

    button10 = new JButton("Load a portfolio");
    Dimension size10 = button10.getPreferredSize();
    button10.setBounds(0, 0, size10.width, size10.height);

    button11 = new JButton("Plot the portfolio");
    Dimension size12 = button11.getPreferredSize();
    button11.setBounds(0, 0, size12.width, size12.height);

    button12 = new JButton("Exit");
    Dimension size13 = button12.getPreferredSize();
    button12.setBounds(0, 0, size13.width, size13.height);

    panel.setLayout(new GridLayout(13, 1));
    panel.add(label1);
    panel.add(button1);
    panel.add(button2);
    panel.add(button3);
    panel.add(button4);
    panel.add(button5);
    panel.add(button6);
    panel.add(button7);
    panel.add(button8);
    panel.add(button9);
    panel.add(button10);
    panel.add(button11);
    panel.add(button12);
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.add(panel);
    frame.setSize(1000, 800);

  }

  /**
   * A method to set the views to visible.
   *
   * @param b to either set to true or not.
   */
  public void setVisible(boolean b) {
    frame.setVisible(true);
    button1.setVisible(true);
    button2.setVisible(true);
    button3.setVisible(true);
    button4.setVisible(true);
    button5.setVisible(true);
    button6.setVisible(true);
    button7.setVisible(true);
    button8.setVisible(true);
    button9.setVisible(true);
    button10.setVisible(true);
    button11.setVisible(true);
    button12.setVisible(true);
  }

  @Override
  public void dispose() {
    frame.dispose();
  }

  @Override
  public void addActionListener(ActionListener listener) {
    Menu menu = this;
    button1.setActionCommand(ActionCommands.CREATE_PORTFOLIO);
    button1.addActionListener(listener);

    button2.setActionCommand(ActionCommands.SHOW_ALL_PORTFOLIO);
    button2.addActionListener(listener);

    button3.setActionCommand(ActionCommands.DETAILS_OF_PORTFOLIO_NAME);
    button3.addActionListener(listener);

    button4.setActionCommand(ActionCommands.GET_COST_PORTFOLIO);
    button4.addActionListener(listener);

    button5.setActionCommand(ActionCommands.GET_COST_ON_DATE_NAME);
    button5.addActionListener(listener);

    button6.setActionCommand(ActionCommands.VALUE_PORTFOLIO_NAME);
    button6.addActionListener(listener);

    button7.setActionCommand(ActionCommands.VALUE_ON_DATE_NAME);
    button7.addActionListener(listener);

    button8.setActionCommand(ActionCommands.SAVE_PORTFOLIO);
    button8.addActionListener(listener);

    button9.setActionCommand(ActionCommands.RETRIEVE_PORTFOLIO);
    button9.addActionListener(listener);

    button10.setActionCommand(ActionCommands.LOAD_PORTFOLIO);
    button10.addActionListener(listener);

    button11.setActionCommand(ActionCommands.PLOT_PORTFOLIO);
    button11.addActionListener(listener);

    button12.setActionCommand(ActionCommands.EXIT);
    button12.addActionListener(listener);


  }
}
