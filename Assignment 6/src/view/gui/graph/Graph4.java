package view.gui.graph;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.WindowConstants;

import view.gui.AbstractView;
import controller.ActionCommands;

/**
 * A class view to plot the graph.
 */
public class Graph4 extends AbstractView {
  private final JFrame frame;
  private final JButton button1;

  private final JLabel label;

  private final Map<Object, Double> data;

  /**
   * A constructor to initialize the plotting view.
   *
   * @param data data used for plotting.
   */
  public Graph4(Map<Object, Double> data) {
    this.data = data;

    frame = new JFrame("Cost On Date");
    JPanel panel = new JPanel();
    frame.getContentPane();


    button1 = new JButton("Back");

    label = new JLabel("Fetching data...");

    GridLayout layout = new GridLayout(2, 1);
    panel.setLayout(layout);
    label.setPreferredSize(new Dimension(700, 1500));
    panel.add(label);
    panel.add(button1);

    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.add(panel);


    frame.setSize(700, 2000);
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
    printGraph();
  }

  @Override
  public void dispose() {
    frame.dispose();
  }

  private void printGraph() {
    if (data.size() == 0) {
      promptErrorMessage("Insufficient data");
      return;
    }

    double max = data.values().stream().max(Double::compareTo).get();
    int scale = 10;
    while (max / scale > 50) {
      scale += 10;
    }

    StringBuilder sb = new StringBuilder("<html><br><br><ul>");
    for (Map.Entry<Object, Double> e : data.entrySet()) {
      int count = (int) Math.ceil(e.getValue() / scale);
      String bar = "*".repeat(count);
      sb.append(String.format("<li>%s: %s</li>", e.getKey(), bar));
    }
    sb.append(String.format("<li>Scale: * = $%d</li>", scale));
    sb.append("</ul><br><br></html>");

    label.setText(sb.toString());
  }
}
