package utilities;

import models.Reading;
import java.util.List;

/**
 * The Analytics class has methods to return trends, minimum and maximum values from input data.
 */
public class Analytics {

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

  //This method takes in a list of type Reading, and a field name from this list of type String.
  //It returns the value from the input field from the input Reading list.
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

  ////////////TRENDS///////////////
  //This method returns a rising or falling arrow if the last three readings are rising or falling, respectively.
  //Its parameters are a list of type Reading, and a field name from this list of type String.
  public static String getTrends(List<Reading> readings, String field) {
    //if the list has less than 3 readings, return null.
    if (readings.size() < 3) {
      return null;
    }

    // Assigning the last three readings to variables
    Reading lastReading = readings.get(readings.size() - 1);
    Reading secondLastReading = readings.get(readings.size() - 2);
    Reading thirdLastReading = readings.get(readings.size() - 3);

    //Getting the double value from the appropriate field
    double last = getValueFromField(lastReading, field);
    double secondLast = getValueFromField(secondLastReading, field);
    double thirdLast = getValueFromField(thirdLastReading, field);

    //Comparing the last three input doubles from the appropriate field.
    if (last > secondLast && secondLast > thirdLast) {
      return "fa-solid fa-arrow-up";  //rising arrow
    } else if (last < secondLast && secondLast < thirdLast) {
      return "fa-solid fa-arrow-down"; //falling arrow
    }
    return null;
  }
}
