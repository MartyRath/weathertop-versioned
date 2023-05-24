package utilities;

import java.util.HashMap;

/**
 * The Conversions Class contains methods to perform conversions based on input.
 */
public class Conversion {
  /**
   * This method refers a weather condition to the relevant fontawesome icon class.
   *
   * @param weatherCondition A weather condition, like clear, snow, etc.
   * @return A fontawesome icon class reference
   */
  public static String weatherConditionToIcon(String weatherCondition) {
    HashMap<String, String> weatherCodeToIcon = new HashMap<String, String>();
    weatherCodeToIcon.put("Clear", "fa-sharp fa-solid fa-sun");
    weatherCodeToIcon.put("Partial Clouds", "fa-brands fa-soundcloud");
    weatherCodeToIcon.put("Cloudy", "fa-solid fa-cloud");
    weatherCodeToIcon.put("Light Showers", "fa-brands fa-drupal");
    weatherCodeToIcon.put("Heavy Showers", "fa-solid fa-cloud-showers-heavy");
    weatherCodeToIcon.put("Rain", "fa-solid fa-cloud-rain");
    weatherCodeToIcon.put("Snow", "fa-solid fa-snowflake");
    weatherCodeToIcon.put("Thunder", "fa-solid fa-bolt");

    String weatherCodeIcon = weatherCodeToIcon.get(weatherCondition);
    return weatherCodeIcon;
  }

  /**
   * This method reads in a temperature and, based on its value, refers it to
   * the relevant fontawesome icon class.
   *
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
   *
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
   * Converts degrees to compass directions.
   *
   * @param degrees Compass degrees representing direction
   * @return String representation of compass direction from degrees
   */
  public static String convertDegreesToCompassDirection(int degrees) {
    String[] directions = {"North", "North North East", "North East", "East North East", "East",
        "East South East", "South East", "South South East", "South", "South South West", "South West",
        "West South West", "West", "West North West", "North West", "North North West"};

    if (degrees < 0) {
      degrees += 360;
    }

    int index = (int) Math.round(((degrees % 360) / 22.5)); //dividing degrees by 22.5 to get the index
    index = index % 16; //ensures index is between 0 and 15
    return directions[index];
  }

  /**
   * Converts Celsius to Fahrenheit.
   *
   * @param temperature Temperature in Celsius
   * @return Temperature in Fahrenheit, from input Celsius
   */
  public static double convertTemperatureCToF(double temperature) {
    double farenheit = temperature * 9 / 5 + 32;
    return farenheit;
  }

  /**
   * Converts wind speed from kilometers per hour to the Beaufort scale
   *
   * @param windSpeed Wind Speed in kilometers per hour to be converted
   * @return Beaufort value from input wind speed
   */
  public static double convertWindSpeedKMToBeaufort(double windSpeed) {
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

  /**
   * Converts temperature and wind speed to wind chill.
   * @param temperature Temperature in Celsius
   * @param windSpeed Wind speed in kilometers per hour
   * @return Wind chill rounded to one decimal
   */
  public static double convertToWindChill(double temperature, double windSpeed) {
    int result = (int) Math.pow(windSpeed, 0.16);
    double windChill = 13.12 + 0.6215 * temperature - 11.37 * result + 0.3965 * temperature * result;
    double windChillToOneDecimalPlace = Math.round(windChill * 10.0) / 10.0;
    return windChillToOneDecimalPlace;
  }

}