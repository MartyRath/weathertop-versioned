package models;
import javax.persistence.Entity;
import play.db.jpa.Model;
import utilities.Conversion;

@Entity
public class Reading extends Model {
  public int code;
  public double temperature;
  public double windSpeed;
  public int windDirection;
  public int pressure;

  public Reading(int code, double temperature, double windSpeed, int windDirection, int pressure) {
    this.code = code;
    this.temperature = temperature;
    this.windSpeed = windSpeed;
    this.windDirection = windDirection;
    this.pressure = pressure;
  }

  public String weatherString() {
    return Conversion.weatherCodeToString(code);
  }

  public double getTemperatureInF() {
    return Conversion.convertTemperatureToF(temperature);
  }

  public double getBeaufort(){
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
  public int getPressure(){
    return pressure;
  }

  public String getCompassDirection(){
    String compassDirection = Conversion.convertDegreesToCompassDirection(windDirection);
    return compassDirection;
  }

  public double getWindChill(){
    double windChill = Conversion.convertToWindChill(temperature, windSpeed);
    return windChill;
  }

  public String weatherCodeToIcons(){
    String weatherCodeIcon = Conversion.weatherCodeToIcon(Conversion.weatherCodeToString(code));
    return weatherCodeIcon;
  }
}
