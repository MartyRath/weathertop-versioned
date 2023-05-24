package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller {
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = listStationsAlphabetically(member.stations); //alphabetising stations
    render("dashboard.html", member, stations);
  }

  public static void addStation(String name, double latitude, double longitude) {
    Logger.info("Adding a new station called " + name);
    Member member = Accounts.getLoggedInMember();
    Station station = new Station(name, latitude, longitude);
    member.stations.add(station);
    member.save();
    redirect("/dashboard");
  }

  public static void deleteStation(Long id) {
    Logger.info("Deleting a Station");
    Member member = Accounts.getLoggedInMember();
    Station station = Station.findById(id);
    member.stations.remove(station);
    member.save();
    station.delete();
    redirect("/dashboard");
  }

  public static List<Station> listStationsAlphabetically(List<Station> stations) {
    Logger.info("Alphabetising stations");
    List<Station> sortedStations = new ArrayList<>(stations);
    //Collections.sort will rearrange list based on Comparator.comparing key.
    Collections.sort(sortedStations, Comparator.comparing(Station::getName));
    return sortedStations;
  }
}

