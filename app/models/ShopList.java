package models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.*;

import com.avaje.ebean.annotation.*;

import play.db.ebean.*;
import play.data.validation.*;
import play.data.validation.Constraints.*;

@Entity
public class ShopList extends Model {

  @Id
  public Long id;

  @Required
  public String name;

  @Required
  public String name_en;

  @Required
  public String address;

  @Required
  public String telephone_number;

  @CreatedTimestamp
  public Date created_at;


  public static Finder<Long, ShopList> find = new Finder<Long, ShopList>(Long.class, ShopList.class);

  @Override
  public String toString() {
    return (id + name + name_en + address + telephone_number + created_at);
  }

}
