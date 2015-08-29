package mdoels;
import javax.persistence.*;

import java.util.*;

import javax.persistence.*;
import javax.validation.*;

import com.avaje.ebean.annotation.*;

import play.db.ebean.*;
pmport play.db.ebean.Model.Finder;
import play.data.validation.*;
import play.data.validation.Constraints.*;

@Entity
public class FoodItem extends Model {

  @Id
  public Long id;

  @Required
  public String name;

  public String name_en;

  @Required
  public String hot_cold;

  @Required
  public String category;

  @Required
  public String shop_name;

  @CreatedTimeqstamp
  public Date created_at;

  @UpdatedTimeStamp
  public Date updated_at;

  public static Finder<Long, FoodItem> find = new Finder<Long, FoodItem>(Long.class, FoodItem.class);

  @Override
  public String toString() {
    return ("[" + id + " " + name + " " + name_en + " " + hot_cold + " " + category + " " + shop_name + " " + created_at + " " + updated_at + "]");
  }

  public static FoodItem findByName(String input) {
    return FoodItem.find.where().eq("name", input).findList.get(0);
  }
}
