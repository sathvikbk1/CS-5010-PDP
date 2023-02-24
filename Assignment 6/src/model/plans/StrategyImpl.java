package model.plans;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * A class to create a strategy of investment, on a recurring basis.
 */
public class StrategyImpl implements Strategy {
  private final String planName;
  private final HashMap<String, Double[]> values;
  private final double investmentPrice;
  private final LocalDate startDate;
  private final LocalDate endDate;
  private LocalDate lastDate;
  private final int interval;

  /**
   * A constructor to initialize the object.
   *
   * @param name            name of the strategy.
   * @param values          values held in the strategy.
   * @param investmentPrice the investment price per instance.
   * @param interval        the interval after which the money is to be re-invested.
   * @param startDate       start date of the investment.
   * @param endDate         end date of the investment strategy.
   * @param lastDate        the last occurred investment in the plan.
   */
  public StrategyImpl(String name, HashMap<String, Double[]> values,
                      double investmentPrice, int interval, LocalDate startDate,
                      LocalDate endDate, LocalDate lastDate) {
    this.planName = name;
    this.values = values;
    this.investmentPrice = investmentPrice;
    this.interval = interval;
    this.startDate = startDate;
    this.endDate = endDate;
    this.lastDate = lastDate;
  }

  @Override
  public String getPlanName() {
    return this.planName;
  }

  @Override
  public HashMap<String, Double[]> getValues() {
    return this.values;
  }

  @Override
  public int getInterval() {
    return this.interval;
  }

  @Override
  public double getInvestmentPrice() {
    return this.investmentPrice;
  }

  @Override
  public void setLastDate(LocalDate date) {
    this.lastDate = date;
  }

  @Override
  public LocalDate getLastDate() {
    return this.lastDate;
  }

  @Override
  public LocalDate getStartDate() {
    return this.startDate;
  }

  @Override
  public LocalDate getEndDate() {
    return this.endDate;
  }

  @Override
  public double getCostPerCycle() {
    double com = 0;
    for (String name : this.values.keySet()) {
      com += this.values.get(name)[1];
    }
    return this.investmentPrice + com;
  }

}
