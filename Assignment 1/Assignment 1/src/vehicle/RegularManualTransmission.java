package vehicle;

/**
 * the following class RegularManualTransmission implements the interface ManualTransmission.
 */

public class RegularManualTransmission implements ManualTransmission {

  /**
   * In this constructor we will be using 3 parameters currentSpeed, currentGear and currentStatus
   * to perform increase gear, increase speed, decrease speed and decrease gear.
   *
   * @param currentSpeed represents the current speed of the car
   * @param currentGear represents the current gear the car is in
   * @param currentStatus represent the current status of the vehicle
   */

  private int currentSpeed;

  private int currentGear;

  private String currentStatus;

  int[] lowSpeeds = new int[5];
  int[] highSpeeds = new int[5];

  /**
   * the constructor RegularManualTransmission creates a low and high threshold for
   * each gear in the form of l1h1,l2h2 ... lnhn, where h stands for high and l stands
   * for low.
   */

  public RegularManualTransmission(
          int l1,
          int h1,
          int l2,
          int h2,
          int l3,
          int h3,
          int l4,
          int h4,
          int l5,
          int h5)
          throws IllegalArgumentException {

    if (l1 < 0 || l2 < 0 || l3 < 0 || l4 < 0 || l5 < 0 || h1 < 0 || h2 < 0 || h3 < 0 || h4 < 0) {
      throw new IllegalArgumentException("It is not possible to have negative values");
    }
    if (l1 > h1 || l2 > h2 || l3 > h3 || l4 > h4 || l5 > h5) {
      throw new IllegalArgumentException("l cannot be greater than h");
    }
    if (l1 > l2 || l2 > l3 || l3 > l4 || l4 > l5) {
      throw new IllegalArgumentException("higher gear cannot be less");
    }
    if (l1 != 0) {
      throw new IllegalArgumentException("Lower speed of first gear should be 0");
    }

    if (checkOverlap(l1, h1, l2, h2) || checkOverlap(l2, h2, l3, h3)
            || checkOverlap(l3, h3, l4, h4) || checkOverlap(l4, h4, l5, h5)) {
      throw new IllegalArgumentException("The ranges cannot be non-overlapping");
    }

    if (l3 < h1 || l4 < h1 || l5 < h1 || l4 < h2 || l5 < h2 || l5 < h3) {
      throw new IllegalArgumentException("The non adj ranges cannot be overlapping");
    }

    this.lowSpeeds[0] = l1;
    this.lowSpeeds[1] = l2;
    this.lowSpeeds[2] = l3;
    this.lowSpeeds[3] = l4;
    this.lowSpeeds[4] = l5;
    this.highSpeeds[0] = h1;
    this.highSpeeds[1] = h2;
    this.highSpeeds[2] = h3;
    this.highSpeeds[3] = h4;
    this.highSpeeds[4] = h5;
    this.currentStatus = "OK: everything is OK.";
    this.currentSpeed = 0;
    this.currentGear = 0; // since we are using an array, 0 is 1
  }

  private boolean checkOverlap(int l, int h, int adjL, int adjH) {
    return adjL > h || (adjL >= l && adjH <= h);
  }


  @Override
  public RegularManualTransmission setStatus(String message) {
    return this;
  }

  @Override
  public String getStatus() {
    return this.currentStatus;
  }

  @Override
  public int getSpeed() {
    return this.currentSpeed;
  }

  @Override
  public int getGear() {
    return this.currentGear + 1;
  }

  @Override
  public RegularManualTransmission increaseSpeed() {
    int speed = this.currentSpeed;
    int gear = this.currentGear;

    if (speed >= highSpeeds[4]) {
      this.currentStatus = "Cannot increase speed. Reached maximum speed.";
      return this;
    }

    if (speed < highSpeeds[gear] && speed >= lowSpeeds[gear]) {
      speed++;
      if (gear + 1 <= 4 && speed <= highSpeeds[gear + 1] && speed >= lowSpeeds[gear + 1]) {
        this.currentStatus = "OK: you may increase the gear.";
      } else {
        this.currentStatus = "OK: everything is OK.";
      }
    } else {
      this.currentStatus = "Cannot increase speed, increase gear first.";
    }
    this.currentSpeed = speed;
    return this;
  }

  @Override
  public RegularManualTransmission decreaseSpeed() {
    int speed = this.currentSpeed;
    int gear = this.currentGear;

    if (speed <= lowSpeeds[0]) {
      this.currentStatus = "Cannot decrease speed. Reached minimum speed.";
      return this;
    }

    if (speed <= highSpeeds[gear] && speed > lowSpeeds[gear]) {
      speed--;
      if (gear - 1 >= 0 && speed <= highSpeeds[gear - 1] && speed >= lowSpeeds[gear - 1]) {
        this.currentStatus = "OK: you may decrease the gear.";
      } else {
        this.currentStatus = "OK: everything is OK.";
      }
    } else {
      this.currentStatus = "Cannot decrease speed, decrease gear first.";
    }
    this.currentSpeed = speed;
    return this;
  }

  @Override
  public RegularManualTransmission increaseGear() {
    int s = this.currentSpeed;
    int g = this.currentGear;

    if (g >= 4) {
      this.currentStatus = "Cannot increase gear. Reached maximum gear.";
      return this;
    }

    if (s <= highSpeeds[g + 1] && s >= lowSpeeds[g + 1]) {
      g++;
      this.currentStatus = "OK: everything is OK.";
    } else {
      this.currentStatus = "Cannot increase gear, increase speed first.";
    }
    this.currentGear = g;
    return this;
  }

  @Override
  public RegularManualTransmission decreaseGear() {
    int s = this.currentSpeed;
    int g = this.currentGear;

    if (g <= 0) {
      this.currentStatus = "Cannot decrease gear. Reached minimum gear.";
      return this;
    }

    if (s < highSpeeds[g - 1] && s > lowSpeeds[g - 1]) {
      g--;
      this.currentStatus = "OK: everything is OK.";
    } else {
      this.currentStatus = "Cannot decrease gear, decrease speed first.";
    }
    this.currentGear = g;
    return this;
  }

}
