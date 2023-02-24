package controller;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import javax.swing.AbstractAction;
import javax.xml.xpath.XPathExpressionException;

import model.portfolio.Portfolio;
import view.gui.buy.Buy1;
import view.gui.buy.Buy2;
import view.gui.buy.Buy3;
import view.gui.buy.Buy4;
import view.gui.buy.Buy5;
import view.gui.buy.BuyFinal;
import view.gui.buy.BuyName;
import view.gui.costofportfolio.TotalCostPortfolio1;
import view.gui.costofportfolio.TotalCostPortfolio2;
import view.gui.costondate.CostOnDate1;
import view.gui.costondate.CostOnDate2;
import view.gui.costondate.CostOnDate3;
import view.gui.CreatePortfolio;
import view.gui.detailsofportfolio.DetailsOfPortfolio1;
import view.gui.detailsofportfolio.DetailsOfPortfolio2;
import view.gui.graph.Graph1;
import view.gui.graph.Graph2;
import view.gui.graph.Graph3;
import view.gui.graph.Graph4;
import view.gui.ListAllPortfolios;
import view.gui.loadportfolio.LoadPortfolio1;
import view.gui.Menu;
import view.gui.loadportfolio.LoadPortfolio2;
import view.gui.portfoliodetails.PortfolioDetails1;
import view.gui.portfoliodetails.PortfolioDetails2;
import view.gui.retrieveportfolio.RetrievePortfolio1;
import view.gui.retrieveportfolio.RetrievePortfolio2;
import view.gui.sell.Sell1;
import view.gui.sell.Sell2;
import view.gui.sell.Sell3;
import view.gui.sell.Sell4;
import view.gui.sell.Sell5;
import view.gui.strategy.Strategy1;
import view.gui.strategy.Strategy2;
import view.gui.strategy.Strategy3;
import view.gui.strategy.Strategy4;
import view.gui.strategy.StrategyCost;
import view.gui.strategy.StrategyEndDate;
import view.gui.strategy.StrategyInterval;
import view.gui.strategy.StrategyName;
import view.gui.strategy.StrategyStartDate;
import view.gui.valueofportfolio.ValueOfPortfolio1;
import view.gui.valueofportfolio.ValueOfPortfolio2;
import view.gui.valueondate.ValueOnDate1;
import view.gui.valueondate.ValueOnDate2;
import view.gui.valueondate.ValueOnDate3;
import view.gui.View;
import model.Model;

/**
 * A controller class to handle the graphical user interface interactions with the model.
 */
public class GUIControl extends AbstractAction implements Control {
  private Model model;
  private View view;
  private String stockName;
  private String strategyName;
  private double share;
  private double price;
  private double commission;
  private LocalDate buyDate;
  private LocalDate date;
  private LocalDate date2;
  private String portfolioName;
  private String stockName1;
  private double quantity;
  private int interval;
  private final HashMap<String, Double[]> list;

  /**
   * A constructor to initialize the view.
   *
   * @param view view to be initialized.
   */
  public GUIControl(View view) {
    this.view = view;
    stockName = "";
    strategyName = "";
    share = 0;
    price = 0;
    commission = 0;
    buyDate = null;
    date = null;
    date2 = null;
    portfolioName = "";
    stockName1 = "";
    quantity = 0;
    interval = 0;
    list = new HashMap<>();
  }

  @Override
  public void execute(Model model) {
    this.model = model;
    this.model.setType();
    view.addActionListener(this);
    view.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {

      //1. Create a portfolio
      case ActionCommands.CREATE_PORTFOLIO:
        View createPortfolio = new CreatePortfolio();
        initViews(createPortfolio);
        break;
      case ActionCommands.BUY_STOCK:
        View buy1 = new Buy1();
        initViews(buy1);
        break;
      case ActionCommands.BUY_STOCK_QUANTITY:
        stockName = view.getStockAbbreviation().toUpperCase();
        if (stockName.equals("") || stockName.equals("Stock Name") || stockName == null) {
          view.promptErrorMessage("Please enter a stock name");
          return;
        }
        View buy2 = new Buy2();
        initViews(buy2);
        break;
      case ActionCommands.BUY_STOCK_PRICE:
        share = view.getStockQuantity();
        if (share == 0 || share <= -1) {
          view.promptErrorMessage("Please enter a quantity");
          return;
        }
        View buy3 = new Buy3();
        initViews(buy3);
        break;
      case ActionCommands.BUY_STOCK_DATE:
        price = view.getStockPrice();
        if (price == 0 || price <= -1) {
          view.promptErrorMessage("Please enter a price");
          return;
        }
        View buy4 = new Buy4();
        initViews(buy4);
        break;
      case ActionCommands.BUY_STOCK_COMMISSION:
        buyDate = view.getStockDate();
        if (buyDate == null || buyDate.isAfter(LocalDate.now())) {
          view.promptErrorMessage("Please enter a date");
          return;
        }
        View buy5 = new Buy5();
        initViews(buy5);
        break;
      case ActionCommands.BUY_STOCK_FINAL:
        commission = view.getStockCommission();
        if (commission == 0 || commission <= -1) {
          view.promptErrorMessage("Please enter a commission");
          return;
        }
        if (!this.model.createStockFlex(stockName, share, price, buyDate, commission, false)) {
          view.promptErrorMessage("Error in creating stock");
        }
        View buyFinal = new BuyFinal();
        initViews(buyFinal);
        break;
      case ActionCommands.BUY_STOCK_FINAL_NAME:
        View buyName = new BuyName();
        initViews(buyName);
        break;
      case ActionCommands.BUY_TO_MAIN_MENU:
        portfolioName = view.getPortfolioName();
        if (portfolioName.equals("") || portfolioName.equals("Portfolio Name")
                || portfolioName == null) {
          view.promptErrorMessage("Please enter a portfolio name");
          return;
        }
        if (!this.model.finalisePortfolio(portfolioName)) {
          this.view.promptErrorMessage("Error in finalising portfolio");
        } else {
          View menu = new Menu();
          initViews(menu);
        }
        break;
      case ActionCommands.SELL_STOCK:
        View sell1 = new Sell1();
        initViews(sell1);
        break;
      case ActionCommands.SELL_STOCK_QUANTITY:
        stockName = view.getStockAbbreviation().toUpperCase();
        if (stockName.equals("") || stockName.equals("Stock Name") || stockName == null) {
          view.promptErrorMessage("Please enter a stock name");
          return;
        }
        View sell2 = new Sell2();
        initViews(sell2);
        break;
      case ActionCommands.SELL_STOCK_PRICE:
        share = view.getStockQuantity();
        if (share == 0 || share <= -1) {
          view.promptErrorMessage("Please enter a quantity");
          return;
        }
        View sell3 = new Sell3();
        initViews(sell3);
        break;
      case ActionCommands.SELL_STOCK_DATE:
        price = view.getStockPrice();
        if (price == 0 || price <= -1) {
          view.promptErrorMessage("Please enter a price");
          return;
        }
        View sell4 = new Sell4();
        initViews(sell4);
        break;
      case ActionCommands.SELL_STOCK_COMMISSION:
        buyDate = view.getStockDate();
        if (buyDate == null || buyDate.isAfter(LocalDate.now())) {
          view.promptErrorMessage("Please enter a date");
          return;
        }
        View sell5 = new Sell5();
        initViews(sell5);
        break;
      case ActionCommands.SELL_STOCK_FINAL:
        commission = view.getStockCommission();
        if (commission == 0 || commission <= -1) {
          view.promptErrorMessage("Please enter a commission");
          return;
        }
        if (!this.model.createStockFlex(stockName, share, price, buyDate, commission, true)) {
          view.promptErrorMessage("Error in creating stock");
        }
        View buyFinal1 = new BuyFinal();
        initViews(buyFinal1);
        break;

      case ActionCommands.STRATEGY_1:
        View strategy1 = new Strategy1();
        initViews(strategy1);
        break;
      case ActionCommands.STRATEGY_2:
        View strategy2 = new Strategy2();
        initViews(strategy2);
        break;
      case ActionCommands.STRATEGY_3:
        stockName1 = view.getStockAbbreviation().toUpperCase();
        if (stockName1.equals("") || stockName1.equals("Stock Name")
                || stockName1 == null) {
          view.promptErrorMessage("Please enter a stock name");
          return;
        }
        View strategy3 = new Strategy3();
        initViews(strategy3);
        break;
      case ActionCommands.STRATEGY_4:
        quantity = view.getStockQuantity();
        if (quantity == 0 || quantity <= -1) {
          view.promptErrorMessage("Please enter a quantity");
          return;
        }
        View strategy4 = new Strategy4();
        initViews(strategy4);
        break;
      case ActionCommands.STRATEGY_PART1_FINAL:
        commission = view.getStockCommission();
        if (commission == 0 || commission <= -1) {
          view.promptErrorMessage("Please enter a commission");
          return;
        }
        list.put(stockName1, new Double[]{quantity, commission});
        View strategy11 = new Strategy1();
        initViews(strategy11);
        break;
      case ActionCommands.STRATEGY_NAME:
        View strategyName = new StrategyName();
        initViews(strategyName);
        break;
      case ActionCommands.STRATEGY_START_DATE:
        this.strategyName = String.valueOf(view.getStrategyName());
        if (this.strategyName.equals("") || this.strategyName.equals("Strategy Name")
                || this.strategyName == null) {
          view.promptErrorMessage("Please enter a strategy name");
          return;
        }
        View strategyStartDate = new StrategyStartDate();
        initViews(strategyStartDate);
        break;
      case ActionCommands.STRATEGY_END_DATE:
        date = view.getStockDate();
        if (date == null || date.isAfter(LocalDate.now())) {
          view.promptErrorMessage("Please enter a valid date");
          return;
        }
        View strategyEndDate = new StrategyEndDate();
        initViews(strategyEndDate);
        break;
      case ActionCommands.STRATEGY_INTERVAL:
        date2 = view.getStockDate2();
        if (date2 == null || date2.isAfter(LocalDate.now())) {
          view.promptErrorMessage("Please enter a valid date");
          return;
        }
        View strategyInterval = new StrategyInterval();
        initViews(strategyInterval);
        break;
      case ActionCommands.STRATEGY_COST:
        interval = view.getStockInterval();
        if (interval == 0 || interval <= -1) {
          view.promptErrorMessage("Please enter a valid interval");
          return;
        }
        View strategyCost = new StrategyCost();
        initViews(strategyCost);
        break;

      case ActionCommands.STRATEGY_FINAL:
        double cost = view.getStockCost();
        if (cost == 0 || cost <= -1) {
          view.promptErrorMessage("Please enter a valid cost");
          return;
        }
        try {
          this.model.dollarCostAveraging(this.strategyName,
                  list, date, date2, date, interval, cost);
        } catch (Exception a) {
          view.promptErrorMessage("Error in creating strategy");
        }
        View buyFinal2 = new BuyFinal();
        initViews(buyFinal2);
        break;

      //2.Show all portfolios
      case ActionCommands.PORTFOLIO_DETAILS_NAME:
        View portfolioDetails = new PortfolioDetails1();
        initViews(portfolioDetails);
        break;
      case ActionCommands.PORTFOLIO_DETAILS_VALUE:
        portfolioName = view.getPortfolioName();
        if (portfolioName.equals("") || portfolioName.equals("Portfolio Name")
                || portfolioName == null) {
          view.promptErrorMessage("Please enter a portfolio name");
          return;
        }
        if (!this.model.createStockFlex(stockName, share, price, buyDate, commission, false)) {
          view.promptErrorMessage("Error in creating stock");
        }
        View portfolioDetails2 = new PortfolioDetails2(this.model);
        initViews(portfolioDetails2);
        break;


      //3.Get portfolio details by name
      case ActionCommands.DETAILS_OF_PORTFOLIO_NAME:
        View detailsOfPortfolio1 = new DetailsOfPortfolio1();
        initViews(detailsOfPortfolio1);
        break;
      case ActionCommands.DETAILS_OF_PORTFOLIO_FINAL:
        portfolioName = view.getPortfolioName();
        if (portfolioName.equals("") || portfolioName.equals("Portfolio Name")
                || portfolioName == null) {
          view.promptErrorMessage("Please enter a portfolio name");
          return;
        }
        View detailsOfPortfolio2 = new DetailsOfPortfolio2(this.model, portfolioName);
        initViews(detailsOfPortfolio2);
        break;


      //4.Get portfolio cost
      case ActionCommands.GET_COST_PORTFOLIO:
        View totalCostPortfolio1 = new TotalCostPortfolio1();
        initViews(totalCostPortfolio1);
        break;
      case ActionCommands.GET_COST_PORTFOLIO_FINAL:
        portfolioName = view.getPortfolioName();
        if (portfolioName.equals("") || portfolioName.equals("Portfolio Name")
                || portfolioName == null) {
          view.promptErrorMessage("Please enter a portfolio name");
          return;
        }
        double values = this.model.getPortfolioCost(portfolioName);
        if (values == 0) {
          view.promptErrorMessage("Error in creating stock");
        }
        View totalCostPortfolio2 = new TotalCostPortfolio2(portfolioName, values);
        initViews(totalCostPortfolio2);
        break;

      //5.Get portfolio cost by date
      case ActionCommands.GET_COST_ON_DATE_NAME:
        View costOnDate1 = new CostOnDate1();
        initViews(costOnDate1);
        break;
      case ActionCommands.GET_COST_ON_DATE_DATE:
        portfolioName = view.getPortfolioName();
        if (portfolioName.equals("") || portfolioName.equals("Portfolio Name")
                || portfolioName == null) {
          view.promptErrorMessage("Please enter a portfolio name");
          return;
        }
        View costOnDate2 = new CostOnDate2();
        initViews(costOnDate2);
        break;
      case ActionCommands.GET_COST_ON_DATE_FINAL:
        date = view.getStockDate();
        if (date == null || date.isAfter(LocalDate.now())) {
          view.promptErrorMessage("Please enter a valid date");
          return;
        }
        double value = this.model.getPortfolioCostAtDate(portfolioName, date);
        if (value == 0) {
          view.promptErrorMessage("Error in creating stock");
        }
        View costOnDate3 = new CostOnDate3(portfolioName, value);
        initViews(costOnDate3);
        break;

      //6.Get Value of Portfolio
      case ActionCommands.VALUE_PORTFOLIO_NAME:
        View valueOfPortfolio1 = new ValueOfPortfolio1();
        initViews(valueOfPortfolio1);
        break;
      case ActionCommands.VALUE_PORTFOLIO_FINAL:
        portfolioName = view.getPortfolioName();
        if (portfolioName.equals("") || portfolioName.equals("Portfolio Name")
                || portfolioName == null) {
          view.promptErrorMessage("Please enter a portfolio name");
          return;
        }
        double valueOfPortfolio = 0;
        List<Portfolio> portfolios = this.model.getMPortfolio();
        for (Portfolio p : portfolios) {
          if (portfolioName.equals(p.getName())) {
            valueOfPortfolio = this.model.getPortfolioValue(portfolioName);
          }
        }
        if (valueOfPortfolio == 0) {
          view.promptErrorMessage("Error in creating stock");
        }
        View valueOfPortfolio2 = new ValueOfPortfolio2(portfolioName, valueOfPortfolio);
        initViews(valueOfPortfolio2);
        break;

      case ActionCommands.SHOW_ALL_PORTFOLIO:
        View listAllPortfolios = new ListAllPortfolios(this.model);
        initViews(listAllPortfolios);
        break;
      case ActionCommands.TO_MAIN_MENU:
        View mainMenu = new Menu();
        initViews(mainMenu);
        break;

      //7.Get Value of Portfolio by date
      case ActionCommands.VALUE_ON_DATE_NAME:
        View valueOnDate = new ValueOnDate1();
        initViews(valueOnDate);
        break;
      case ActionCommands.VALUE_ON_DATE_DATE:
        portfolioName = view.getPortfolioName();
        if (portfolioName.equals("") || portfolioName.equals("Portfolio Name")
                || portfolioName == null) {
          view.promptErrorMessage("Please enter a portfolio name");
          return;
        }
        View valueOnDate2 = new ValueOnDate2();
        initViews(valueOnDate2);
        break;
      case ActionCommands.VALUE_ON_DATE_FINAL:
        date = view.getStockDate();
        if (date == null || date.isAfter(LocalDate.now())) {
          view.promptErrorMessage("Please enter a valid date");
          return;
        }
        double valueOfPortfolioAtDate = 0;
        List<Portfolio> portfolio = this.model.getMPortfolio();
        for (Portfolio p : portfolio) {
          if (portfolioName.equals(p.getName())) {
            valueOfPortfolioAtDate = this.model.getPortfolioValueAtDate(portfolioName, date);
          }
        }
        if (valueOfPortfolioAtDate == 0) {
          view.promptErrorMessage("Error in creating stock");
        }
        View valueOnDate3 = new ValueOnDate3(portfolioName, valueOfPortfolioAtDate);
        initViews(valueOnDate3);
        break;

      //8.Save Portfolio
      case ActionCommands.SAVE_PORTFOLIO:
        this.model.savePortfolio();
        break;


      //9.Retrieve Portfolio
      case ActionCommands.RETRIEVE_PORTFOLIO:
        View retrievePortfolio = new RetrievePortfolio1();
        initViews(retrievePortfolio);
        break;

      case ActionCommands.RETRIEVE_PORTFOLIO_FINAL:
        portfolioName = view.getPortfolioName();
        try {
          this.model.retrievePortfolio(portfolioName);
        } catch (XPathExpressionException ex) {
          throw new RuntimeException(ex);
        } catch (IllegalArgumentException ex) {
          view.promptErrorMessage(ex.getMessage());
        }
        View retrievePortfolio2 = new RetrievePortfolio2();
        initViews(retrievePortfolio2);
        break;

      //10.Load Portfolio
      case ActionCommands.LOAD_PORTFOLIO:
        View loadPortfolio = new LoadPortfolio1();
        initViews(loadPortfolio);
        break;

      case ActionCommands.LOAD_PORTFOLIO_FINAL:
        portfolioName = view.getPortfolioName();
        try {
          List<Portfolio> mPortfolio = this.model.getMPortfolio();
          for (Portfolio p : mPortfolio) {
            if (portfolioName.equals(p.getName())) {
              this.model.loadPortfolio(portfolioName, p.getStockList(), p.getTransactionHistory());
            }
          }
        } catch (Exception x) {
          view.promptErrorMessage("Error in creating stock");
        }
        View loadPortfolio2 = new LoadPortfolio2();
        initViews(loadPortfolio2);
        break;


      //11.plot graph
      case ActionCommands.PLOT_PORTFOLIO:
        View graph1 = new Graph1();
        initViews(graph1);
        break;

      case ActionCommands.PLOT_PORTFOLIO_DATE_ONE:
        portfolioName = view.getPortfolioName();
        if (portfolioName.equals("") || portfolioName.equals("Portfolio Name")
                || portfolioName == null) {
          view.promptErrorMessage("Please enter a portfolio name");
          return;
        }
        View graph2 = new Graph2();
        initViews(graph2);
        break;

      case ActionCommands.PLOT_PORTFOLIO_DATE_TWO:
        date = view.getStockDate();
        if (date == null || date.isAfter(LocalDate.now())) {
          view.promptErrorMessage("Please enter a valid date");
          return;
        }
        View graph3 = new Graph3();
        initViews(graph3);
        break;

      case ActionCommands.PLOT_PORTFOLIO_FINAL:
        date2 = view.getStockDate2();
        Portfolio portfolio1 = null;
        for (Portfolio p : this.model.getMPortfolio()) {
          if (p.getName().equals(portfolioName)) {
            portfolio1 = p;
            break;
          }
        }
        View graph4 = new Graph4(this.model.visualize(portfolio1, date, date2));
        initViews(graph4);
        break;

      case ActionCommands.EXIT:
        System.exit(0);
        break;

      default:
        System.err.println("Invalid Command!");
    }
  }

  private void initViews(View view) {
    view.addActionListener(this);
    view.setVisible(true);
    this.view.dispose();
    this.view = view;
  }
}
