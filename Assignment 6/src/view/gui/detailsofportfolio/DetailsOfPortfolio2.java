package view.gui.detailsofportfolio;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.WindowConstants;

import model.stocks.Stock;
import view.gui.AbstractView;
import controller.ActionCommands;
import model.Model;
import model.portfolio.Portfolio;

/**
 * A class view to display the contents of the particular portfolio.
 */
public class DetailsOfPortfolio2 extends AbstractView {
  private final JFrame frame;
  private final JButton button1;

  /**
   * A constructor to initialize the view.
   *
   * @param readOnlyModal value to be read from.
   * @param portfolioName name of the portfolio.
   */
  public DetailsOfPortfolio2(Model readOnlyModal, String portfolioName) {
    frame = new JFrame("Portfolio Details");
    JPanel panel = new JPanel();
    frame.getContentPane();


    button1 = new JButton("Back");

    List<Portfolio> portfolios = readOnlyModal.getMPortfolio();

    JLabel label = new JLabel("No portfolios created yet");

    StringBuilder sb = new StringBuilder("<html>Portfolio Name: " + portfolioName + "<br><ul>");

    if (portfolios != null && portfolios.size() > 0) {
      for (Portfolio p : portfolios) {
        if (p.getName().equals(portfolioName)) {
          sb.append("<br>Portfolio Name: <br>" + p.getName());
          for (Stock st : p.getStockList()) {
            sb.append("<li>").append(st.getStockName()).append(": ")
                    .append(st.getNoOfShares()).append(" at Cost -> ")
                    .append(st.getCost()).append("</li>");
          }
          sb.append("</ul><br><br></html>");
          label.setText(sb.toString());
          break;
        }
      }
    }

    GridLayout layout = new GridLayout(5, 1);
    panel.setLayout(layout);
    panel.add(label);
    panel.add(button1);

    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.add(panel);
    frame.setSize(700, 500);
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
