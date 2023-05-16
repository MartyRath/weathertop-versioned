package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.yaml.snakeyaml.*;
import org.yaml.snakeyaml.constructor.Constructor;
import java.io.*;

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

  public void loadFromYaml() throws Exception
  {
    //Yaml yaml = new Yaml(new Constructor(List.class));

    InputStream is = new FileInputStream(new File("data.yml"));
    //readings = (List<Reading>) yaml.load(is);
    is.close();
  }

  public void saveToYaml() throws Exception {
    Yaml yaml = new Yaml();

    OutputStream os = new FileOutputStream(new File("data.yml"));
    yaml.dump(readings, new OutputStreamWriter(os));
    os.close();
  }

}
