package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

import models.Reading;

import utilities.Conversion;
@Entity
public class Station extends Model
{
  public String name;
  @OneToMany(cascade = CascadeType.ALL)
  public List<Reading> readings = new ArrayList<Reading>();

  public Station(String name)
  {
    this.name = name;
  }

  public Reading latestReadings(){
    if(readings.size() != 0) {
      Reading latestReadings = readings.get(readings.size() - 1);
      return latestReadings;
    }
    else return null;
  }

}
