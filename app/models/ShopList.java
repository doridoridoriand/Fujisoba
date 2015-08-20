package models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.*;

import play.db.ebean.*;
import play.data.validation.*;

@Entity
public class ShopList extends Model {

  @Id
  public Long id;
  public String name;
  public String name_en;
  public String address;
  public String telephone_number;
  public Date created_at;


  public static Finder<Long, ShopList> find = new Finder<Long, ShopList>(Long.class, ShopList.class);

  @Override
  public String toString() {
    return (id + name + name_en + address + telephone_number + created_at);
  }

}
