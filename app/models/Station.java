package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;


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

  public static double minTemp(List<Reading> readings) {
    double minTemp = Double.MAX_VALUE;

    for (Reading reading: readings){
      if(reading.temperature < minTemp){
        minTemp = reading.temperature;
      }
    }
    return minTemp;
  }

  public static double maxValue(List<Reading> readings, double value) {
    double maxValue= Double.MIN_VALUE;

    for (Reading reading: readings){
      if(value > maxValue){
        maxValue = value;
      }
    }
    return maxValue;
  }
}
