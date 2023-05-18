package utilities;

import models.Reading;

import java.util.HashMap;
import java.util.List;

public class Conversion {

  //////////////////////ICONS//////////////////////////////////////
  //This method takes in weatherCodeString and returns code for icon
  public static String weatherCodeToIcon(String weatherCode) {
    HashMap <String, String> weatherCodeToIcon = new HashMap<String, String>();
    weatherCodeToIcon.put("Clear", "fa-sharp fa-solid fa-sun");
    weatherCodeToIcon.put("Partial Clouds", "fa-brands fa-soundcloud");
    weatherCodeToIcon.put("Cloudy", "fa-solid fa-cloud");
    weatherCodeToIcon.put("Light Showers", "fa-brands fa-drupal");
    weatherCodeToIcon.put("Heavy Showers", "fa-solid fa-cloud-showers-heavy");
    weatherCodeToIcon.put("Rain", "fa-solid fa-cloud-rain");
    weatherCodeToIcon.put("Snow","fa-solid fa-snowflake");
    weatherCodeToIcon.put("Thunder", "fa-solid fa-bolt");

    String weatherCodeIcon = weatherCodeToIcon.get(weatherCode);
    return weatherCodeIcon;
  }

  public static String temperatureToIcon(double temperature) {
    if (temperature <= 5) {
      return "fa-solid fa-temperature-low";
    } else if (temperature >= 17) {
      return "fa-solid fa-temperature-high";
    }
    else return "fa-solid fa-temperature-half";
  }
  //////////////////////ICONS////////////////////////////////////

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

  public static double convertTemperatureToF(double temperature) {
    double farenheit = temperature * 9 / 5 + 32;
    return farenheit;
  }

  public static double convertWindSpeedToBeaufort(double windSpeed) {
    //if (windSpeed < 0) {
    //  throw new IllegalArgumentException("Wind speed cannot be negative");
    //}
    //if (Double.isNaN(windSpeed)) {
    //  throw new IllegalArgumentException("Wind speed cannot be NaN");
    //}

    double beaufort = 0; //add exceptions instead of this later...
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

  // This method takes in a wind direction degrees integer and returns a compass direction String.
  // This is achieved by creating an array of type String with 16 compass directions,
  // each separated by 22.5 degrees (360/16)
  // We use degrees modulo 360 to ensure that when we divide by 22.5, we get a valid index.
  // We then return the String compass direction using this index.
  public static String convertDegreesToCompassDirection(int degrees) {
    String[] directions = {"North", "North North East", "North East", "East North East", "East", "East South East",
                            "South East", "South South East", "South", "South South West", "South West",
                            "West South West", "West", "West North West", "North West", "North North West"};
    int index = (int) Math.round(((degrees % 360) / 22.5));
    return directions[index];
  }

  public static double convertToWindChill(double temperature, double windSpeed){
    int result = (int) Math.pow(windSpeed, 0.16);
    double windChill = 13.12 + 0.6215 * temperature - 11.37 * result + 0.3965 * temperature * result;
    double windChillToOneDecimalPlace = Math.round(windChill * 10.0) / 10.0;
    return windChillToOneDecimalPlace;
  }

  public static double getMinValue(List<Reading> readings, String field) {
    double minValue = Double.MAX_VALUE;

    for (Reading reading: readings){
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

      if(value < minValue){
        minValue = value;
      }
    }
    return minValue;
  }

  public static double getMaxValue(List<Reading> readings, String field) {
    double maxValue = Double.MIN_VALUE;

    for (Reading reading: readings){
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

      if(value > maxValue){
        maxValue = value;
      }
    }
    return maxValue;
  }
}
