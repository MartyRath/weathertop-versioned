package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

import utilities.Conversion;


@Entity
public class Station extends Model {
  public String name;
  public double latitude;
  public double longitude;

  @OneToMany(cascade = CascadeType.ALL)
  public List<Reading> readings = new ArrayList<Reading>();

  public Station(String name, double latitude, double longitude) {
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public Reading latestReadings() {
    if (readings.size() != 0) {
      Reading latestReadings = readings.get(readings.size() - 1);
      return latestReadings;
    } else return null;
  }

  public double getMinValue(String field) {
    return Conversion.getMinValue(readings, field);
  }

  public double getMaxValue(String field) {
    return Conversion.getMaxValue(readings, field);
  }

  public String getTrends(String field) {
    return Conversion.getTrends(readings, field);
  }

}
