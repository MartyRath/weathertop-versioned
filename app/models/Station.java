package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

import utilities.Conversion;


@Entity
public class Station extends Model
{
  public String name;
  public double latitude;
  public double longitude;

  @OneToMany(cascade = CascadeType.ALL)
  public List<Reading> readings = new ArrayList<Reading>();

  public Station(String name, double latitude, double longitude)
  {
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public Reading latestReadings(){
    if(readings.size() != 0) {
      Reading latestReadings = readings.get(readings.size() - 1);
      return latestReadings;
    }
    else return null;
  }

  public List<Reading> latestThreeReadings(){
    if(readings.size() != 0) {
      List<Reading> latestThreeReadings = new ArrayList<>();
      latestThreeReadings.add(readings.get(readings.size() - 1));
      latestThreeReadings.add(readings.get(readings.size() - 2));
      latestThreeReadings.add(readings.get(readings.size() - 3));
      return latestThreeReadings;
    }
    else return null;
  }

  public double getMinTemp(){
    return Conversion.getMinValue(readings, "temperature");
  }

  public double getMinWindSpeed(){
    return Conversion.getMinValue(readings, "windSpeed");
  }

  public double getMinPressure(){
    return Conversion.getMinValue(readings, "pressure");
  }
  public double getMaxTemp(){
    return Conversion.getMaxValue(readings, "temperature");
  }

  public double getMaxWindSpeed(){
    return Conversion.getMaxValue(readings, "windSpeed");
  }

  public double getMaxPressure(){
    return Conversion.getMaxValue(readings, "pressure");
  }

}
