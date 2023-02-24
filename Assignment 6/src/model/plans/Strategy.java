package model.plans;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * An interface to support the creation of investment strategies.
 */
public interface Strategy {
  String getPlanName();

  HashMap<String, Double[]> getValues();

  double getInvestmentPrice();

  void setLastDate(LocalDate date);

  LocalDate getLastDate();

  LocalDate getStartDate();

  LocalDate getEndDate();

  double getCostPerCycle();

  int getInterval();
}
