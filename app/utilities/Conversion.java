package utilities;

import models.Reading;

import java.util.HashMap;
import java.util.List;

public class Conversion {


  /**
   * This method refers a weather code to the relevant fontawesome icon class.
   * @param weatherCode A weather code based on weather conditions
   * @return A fontawesome icon class reference
   */
  public static String weatherCodeToIcon(String weatherCode) {
    HashMap<String, String> weatherCodeToIcon = new HashMap<String, String>();
    weatherCodeToIcon.put("Clear", "fa-sharp fa-solid fa-sun");
    weatherCodeToIcon.put("Partial Clouds", "fa-brands fa-soundcloud");
    weatherCodeToIcon.put("Cloudy", "fa-solid fa-cloud");
    weatherCodeToIcon.put("Light Showers", "fa-brands fa-drupal");
    weatherCodeToIcon.put("Heavy Showers", "fa-solid fa-cloud-showers-heavy");
    weatherCodeToIcon.put("Rain", "fa-solid fa-cloud-rain");
    weatherCodeToIcon.put("Snow", "fa-solid fa-snowflake");
    weatherCodeToIcon.put("Thunder", "fa-solid fa-bolt");

    String weatherCodeIcon = weatherCodeToIcon.get(weatherCode);
    return weatherCodeIcon;
  }

  /**
   * This method reads in a temperature and, based on its value, refers it to the relevant fontawesome icon class.
   * @param temperature Temperature in Celsius
   * @return A fontawesome icon class reference
   */
  public static String temperatureToIcon(double temperature) {
    if (temperature <= 5) {
      return "fa-solid fa-temperature-low";
    } else if (temperature >= 17) {
      return "fa-solid fa-temperature-high";
    } else return "fa-solid fa-temperature-half";
  }

  /**
   * Converts a weather code and to its relevant String representation.
   * @param weatherCode The weather code representing the weather condition
   * @return The weather condition String based on the weather code, or null if no matching case.
   */
  public static String weatherCodeToString(int weatherCode) {
    switch (weatherCode) {
      case 100:
        return "Clear";
      case 200:
        return "Partial Clouds";
      case 300:
        return "Cloudy";
      case 400:
        return "Light Showers";
      case 500:
        return "Heavy Showers";
      case 600:
        return "Rain";
      case 700:
        return "Snow";
      case 800:
        return "Thunder";
    }
    return null;
  }

  /**
   * Converts Celsius to Fahrenheit.
   * @param temperature Temperature in Celsius
   * @return Temperature in Fahrenheit
   */
  public static double convertTemperatureCToF(double temperature) {
    double farenheit = temperature * 9 / 5 + 32;
    return farenheit;
  }

  /**
   * Converts wind speed from kilometers per hour to the Beaufort scale
   * @param windSpeed Wind Speed in kilometers per hour to be converted
   * @return Beaufort value from input wind speed
   */
  public static double convertWindSpeedToBeaufort(double windSpeed) {
    double beaufort = 0;
    if (windSpeed <= 1) {
      return 0;
    } else if (windSpeed <= 5) {
      return 1;
    } else if (windSpeed <= 11) {
      return 2;
    } else if (windSpeed <= 19) {
      return 3;
    } else if (windSpeed <= 28) {
      return 4;
    } else if (windSpeed <= 38) {
      return 5;
    } else if (windSpeed <= 49) {
      return 6;
    } else if (windSpeed <= 61) {
      return 7;
    } else if (windSpeed <= 74) {
      return 8;
    } else if (windSpeed <= 88) {
      return 9;
    } else if (windSpeed <= 102) {
      return 10;
    } else if (windSpeed <= 117) {
      return 11;
    }
    return beaufort;
  }


  // each separated by 22.5 degrees (360/16)
  // The index is derived by dividing the degrees by 22.5.
  // We use degrees modulo 360 to ensure that when we divide by 22.5, we get a valid index.
  // Math round is used for accuracy. Int is used to convert the result of math round (long) to int.
  // We then return the String compass direction using this index.

  /**
   * Converts degrees to compass directions.
   * @param degrees Compass degrees representing direction
   * @return String representation of compass direction from degrees
   */
  public static String convertDegreesToCompassDirection(int degrees) {
    String[] directions = {"North", "North North East", "North East", "East North East", "East", "East South East",
        "South East", "South South East", "South", "South South West", "South West",
        "West South West", "West", "West North West", "North West", "North North West"};

    int index = (int) Math.round(((degrees % 360) / 22.5));
    if (index <=15){
    return directions[index];}
    else return "North";
  }

  public static double convertToWindChill(double temperature, double windSpeed) {
    int result = (int) Math.pow(windSpeed, 0.16);
    double windChill = 13.12 + 0.6215 * temperature - 11.37 * result + 0.3965 * temperature * result;
    double windChillToOneDecimalPlace = Math.round(windChill * 10.0) / 10.0;
    return windChillToOneDecimalPlace;
  }

  public static double getMinValue(List<Reading> readings, String field) {
    double minValue = Double.MAX_VALUE;

    for (Reading reading : readings) {
      double value = getValueFromField(reading, field);

      if (value < minValue) {
        minValue = value;
      }
    }
    return minValue;
  }

  public static double getMaxValue(List<Reading> readings, String field) {
    double maxValue = Double.MIN_VALUE;

    for (Reading reading : readings) {
      double value = getValueFromField(reading, field);

      if (value > maxValue) {
        maxValue = value;
      }
    }
    return maxValue;
  }

  //This method takes in a list of type Reading, and a field name from this list of type String.
  //It returns the value from the input field from the input Reading list.
  private static double getValueFromField(Reading reading, String field) {
    double value = 0;
    switch (field) {
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
