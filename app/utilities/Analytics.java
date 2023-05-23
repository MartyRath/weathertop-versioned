package utilities;

import models.Reading;
import java.util.List;

/**
 * The Analytics class has methods to return trends, minimum and maximum values from input data.
 */
public class Analytics {

  /**
   * Gets the value from the input reading object fields: temperature, windSpeed or pressure.
   * @param reading Object of type Reading.
   * @param fieldName Name of the field from input reading object.
   * @return Double value from input Reading object field.
   */
  private static double getValueFromField(Reading reading, String fieldName) {
    double value = 0;
    switch (fieldName) {
      case "temperature":
        value = reading.temperature;
        break;
      case "windSpeed":
        value = reading.windSpeed;
        break;
      case "pressure":
        value = reading.pressure;
        break;
    }
    return value;
  }

  /**
   * Gets the minimum value in the readings list based on the input field name.
   * @param fieldName The name of a field from the Reading class.
   * @return Minimum value of type double from readings list.
   */
  public static double getMinValue(List<Reading> readings, String fieldName) {
    double minValue = Double.MAX_VALUE;

    for (Reading reading : readings) {
      double value = getValueFromField(reading, fieldName);

      if (value < minValue) {
        minValue = value;
      }
    }
    return minValue;
  }

  /**
   * Gets the maximum value in the readings list based on the input field name.
   * @param fieldName The name of a field from the Reading class.
   * @return Maximum value of type double from readings list.
   */
  public static double getMaxValue(List<Reading> readings, String fieldName) {
    double maxValue = Double.MIN_VALUE;

    for (Reading reading : readings) {
      double value = getValueFromField(reading, fieldName);

      if (value > maxValue) {
        maxValue = value;
      }
    }
    return maxValue;
  }

  /**
   * Gets trends from last three inputs in readings list and returns if entries are rising or falling.
   * Works with fields: temperature, windSpeed or pressure.
   * @param fieldName The name of a field from the Reading class.
   * @return fontawesome reference to display an arrow up if data is rising, and down if data is falling.
   */
  public static String getTrends(List<Reading> readings, String fieldName) {
    //if the input list has less than 3 readings, return null.
    if (readings.size() < 3) {
      return null;
    }

    Reading lastReading = readings.get(readings.size() - 1);
    Reading secondLastReading = readings.get(readings.size() - 2);
    Reading thirdLastReading = readings.get(readings.size() - 3);

    //Getting the value from the appropriate field.
    double last = getValueFromField(lastReading, fieldName);
    double secondLast = getValueFromField(secondLastReading, fieldName);
    double thirdLast = getValueFromField(thirdLastReading, fieldName);

    if (last > secondLast && secondLast > thirdLast) {
      return "fa-solid fa-arrow-up";  //rising arrow
    } else if (last < secondLast && secondLast < thirdLast) {
      return "fa-solid fa-arrow-down"; //falling arrow
    }
    return null;
  }
}
