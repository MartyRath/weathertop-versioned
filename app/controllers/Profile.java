package controllers;

import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Profile extends Controller {
  public static void index() {
    Logger.info("Rendering Profile");
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stations;
    render("profile.html", member, stations);
  }
}