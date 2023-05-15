package utilities;

public class Conversion {

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
  // We reference the index by degrees modulo 360 to ensure we get a valid index returned, then divided by 22.5.
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

}
