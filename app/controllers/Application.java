package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import views.html.*;
import models.*;

public class Application extends Controller {
  public static Result index() {
    List<ShopList> entries = ShopList.find.all();
    return ok(index.render(entries));
  }
}
