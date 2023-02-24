package vehicle;

/**
 * This interface represents a set of operations on the mechanics of a manual transmission vehicle.
 */
public interface ManualTransmission {

  /**
   * Return the current state of the vehicle.
   * We will be using getSpeed to get the current speed, getGear to get the current gear
   * the vehicle is running in.
   * Then getStatus is used to get the current status.
   * There are four actions that can be performed: increase speed, increase gear, decrease gear
   * and decrease speed.
   */
  RegularManualTransmission setStatus(String message);

  String getStatus();

  int getSpeed();

  int getGear();

  ManualTransmission decreaseSpeed();

  ManualTransmission increaseGear();

  ManualTransmission decreaseGear();

  ManualTransmission increaseSpeed();

}
