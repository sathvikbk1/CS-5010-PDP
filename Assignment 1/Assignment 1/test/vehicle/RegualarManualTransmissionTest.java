package vehicle;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * The following class is used to test the various test cases for manual transmission.
 */

public class RegualarManualTransmissionTest {

  ManualTransmission m1;
  ManualTransmission m2;
  ManualTransmission m3;
  ManualTransmission m4;
  ManualTransmission m5;
  ManualTransmission m6;

  @Before
  public void setUp() {
    m3 = new RegularManualTransmission(0, 2, 2, 4, 3, 6, 6, 8, 8, 10);
  }

  @Test
  public void testOverlapping() {
    boolean b = false;
    try {
      m3 = new RegularManualTransmission(0, 2, 2, 4, 3, 6, 6, 8, 8, 10);
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertFalse(b);
  }

  @Test
  public void testNegaitveSpeed() {
    boolean b = false;
    try {
      m3 = new RegularManualTransmission(0, 2, -2, 4, 4, 6, 6, 8, 8, 10);
    } catch (IllegalArgumentException e) {
      b = true;
    }
    assertTrue(b);

  }

  @Test
  public void testNegativegear() {
    m3.decreaseGear();
    assertEquals("Cannot decrease gear. Reached minimum gear.", m3.getStatus());
  }


  @Test
  public void testIncreaseSpeed() {

    m3.increaseSpeed();
    m3.increaseSpeed();
    m3.increaseGear();
    m3.increaseSpeed();
    m3.increaseSpeed();
    m3.increaseGear();
    m3.increaseSpeed();
    m3.increaseSpeed();
    m3.increaseGear();
    m3.increaseSpeed();
    m3.increaseSpeed();
    m3.increaseGear();
    m3.increaseSpeed();
    m3.increaseSpeed();

    assertEquals("Cannot increase speed, increase gear first.", m3.getStatus());
  }

  @Test
  public void testIncreaseGear() {
    m3.increaseSpeed();
    m3.increaseSpeed();
    m3.increaseGear();

    assertEquals("Cannot increase gear, increase speed first.", m3.getStatus());
  }

  @Test
  public void testOK() {
    m3.increaseSpeed();
    assertEquals("OK: everything is OK.", m3.getStatus());
  }

  @Test
  public void testIncreaseGearMessage() {
    m3.increaseSpeed();
    m3.increaseSpeed();
    m3.increaseGear();
    m3.increaseSpeed();
    assertEquals("OK: you may increase the gear.", m3.getStatus());
  }

  @Test
  public void testdecreaseGearMessage() {
    m3.decreaseGear();
    assertEquals("Cannot decrease gear. Reached minimum gear.", m3.getStatus());

  }

  @Test
  public void testInitalOk() {
    assertEquals("OK: everything is OK.", m3.getStatus());
  }

  @Test
  public void testInitalDecreasemessage() {
    m3.decreaseSpeed();
    assertEquals("Cannot decrease speed. Reached minimum speed.", m3.getStatus());
  }

  @Test
  public void testincreasegearatmaxspeed() {
    m3.increaseSpeed();
    m3.increaseSpeed();
    m3.increaseGear();
    m3.increaseSpeed();
    m3.increaseGear();
    m3.increaseSpeed();
    m3.increaseSpeed();
    m3.increaseSpeed();
    m3.increaseGear();
    m3.increaseSpeed();
    m3.increaseSpeed();
    m3.increaseGear();
    m3.increaseSpeed();
    m3.increaseSpeed();
    m3.increaseGear();
    assertEquals(10, m3.getSpeed());
    assertEquals("Cannot increase gear. Reached maximum gear.", m3.getStatus());
  }

  @Test
  public void testincreasespeedaftermax() {
    m3.increaseSpeed();
    m3.increaseSpeed();
    m3.increaseGear();
    m3.increaseSpeed();
    m3.increaseGear();
    m3.increaseSpeed();
    m3.increaseSpeed();
    m3.increaseSpeed();
    m3.increaseGear();
    m3.increaseSpeed();
    m3.increaseSpeed();
    m3.increaseGear();
    m3.increaseSpeed();
    m3.increaseSpeed();
    m3.increaseSpeed();
    assertEquals(10, m3.getSpeed());o
    assertEquals("Cannot increase speed. Reached maximum speed.", m3.getStatus());

  }

  @Test
  public void decreasespeedatspeed() {
    m3.increaseSpeed();
    m3.increaseSpeed();
    m3.increaseGear();
    m3.increaseSpeed();
    m3.decreaseGear();
    assertEquals("Cannot decrease gear, decrease speed first.", m3.getStatus());

  }

  @Test
  public void decresegear() {
    m3.increaseSpeed();
    m3.increaseSpeed();
    m3.increaseGear();
    m3.decreaseGear();
    m3.decreaseSpeed();
    assertEquals("Cannot decrease speed, decrease gear first.", m3.getStatus());
  }
}