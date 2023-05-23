package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

import utilities.Analytics;

/**
 * The Station class represents a weather station location, including its name and location. It also performs
 * analytics of input weather data.
 */
@Entity
public class Station extends Model {
  public String name;
  public double latitude;
  public double longitude;

  @OneToMany(cascade = CascadeType.ALL)
  public List<Reading> readings = new ArrayList<Reading>();

  /**
   * Constructor for weather Station
   * @param name The name of the station's location
   * @param latitude The latitude for the station's location
   * @param longitude The longitude for the station's location
   */
  public Station(String name, double latitude, double longitude) {
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  /**
   * Creates a list from the latest entry in readings list
   * @return A list of the latest object of type Reading in readings List.
   */
  public Reading latestReadings() {
    if (readings.size() != 0) {
      Reading latestReadings = readings.get(readings.size() - 1);
      return latestReadings;
    } else return null;
  }

  /**
   * Gets the minimum value in the readings list  based on the input field name
   * @param fieldName The name of a field from the Reading class
   * @return Minimum value of type double from readings list.
   */
  public double getMinValue(String fieldName) {
    return Analytics.getMinValue(readings, fieldName);
  }

  /**
   * Gets the maximum value in the readings list  based on the input field name
   * @param fieldName The name of a field from the Reading class
   * @return Maximum value of type double from readings list.
   */
  public double getMaxValue(String fieldName) {
    return Analytics.getMaxValue(readings, fieldName);
  }

  /**
   * Gets trends from last three inputs in readings list if entries are rising or falling.
   * @param fieldName The name of a field from the Reading class
   * @return fontawesome reference to display an arrow up if data is rising, and down if data is falling.
   */
  public String getTrends(String fieldName) {
    return Analytics.getTrends(readings, fieldName);
  }

  public String getName(){
    return name;
  }

}
