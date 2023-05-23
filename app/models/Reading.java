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

  public String weatherCodeToString() {
    return Conversion.weatherCodeToString(code);
  }

  public String weatherCodeToIcon() {
    String weatherCodeIcon = Conversion.weatherCodeToIcon(Conversion.weatherCodeToString(code));
    return weatherCodeIcon;
  }

  public String temperatureToIcon() {
    String tempIcon = Conversion.temperatureToIcon(temperature);
    return tempIcon;
  }

  public double getTemperature() {
    return temperature;
  }

  public double convertTemperatureCToF() {
    return Conversion.convertTemperatureCToF(temperature);
  }

  public double convertWindSpeedKMToBeaufort() {
    return Conversion.convertWindSpeedKMToBeaufort(windSpeed);
  }

  public String getCompassDirection() {
    String compassDirection = Conversion.convertDegreesToCompassDirection(windDirection);
    return compassDirection;
  }

  public double getWindChill() {
    double windChill = Conversion.convertToWindChill(temperature, windSpeed);
    return windChill;
  }

  public double getPressure() {
    return pressure;
  }

}