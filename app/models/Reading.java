package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
import utilities.Conversion;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Reading extends Model {
  public int code;
  public double temperature;
  public double windSpeed;
  public int windDirection;
  public double pressure;
  public String dateTime;

  public Reading(int code, double temperature, double windSpeed, int windDirection, double pressure) {
    this.code = code;
    this.temperature = temperature;
    this.windSpeed = windSpeed;
    this.windDirection = windDirection;
    this.pressure = pressure;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); //Create DateTimeFormatter
    this.dateTime = formatter.format(LocalDateTime.now()); //Get Current Date Time & Set formatted String
  }

  public String weatherString() {
    return Conversion.weatherCodeToString(code);
  }

  public double getTemperatureInF() {
    return Conversion.convertTemperatureCToF(temperature);
  }

  public double getBeaufort() {
    return Conversion.convertWindSpeedToBeaufort(windSpeed);
  }

  public double getTemperature() {
    return temperature;
  }

  public int getCode() {
    return code;
  }

  public double getWindSpeed() {
    return windSpeed;
  }

  public int getWindDirection() {
    return windDirection;
  }

  public double getPressure() {
    return pressure;
  }

  public String getCompassDirection() {
    String compassDirection = Conversion.convertDegreesToCompassDirection(windDirection);
    return compassDirection;
  }

  public double getWindChill() {
    double windChill = Conversion.convertToWindChill(temperature, windSpeed);
    return windChill;
  }

  public String weatherCodeToIcons() {
    String weatherCodeIcon = Conversion.weatherCodeToIcon(Conversion.weatherCodeToString(code));
    return weatherCodeIcon;
  }

  public String temperatureToIcon() {
    String tempIcon = Conversion.temperatureToIcon(temperature);
    return tempIcon;
  }
}