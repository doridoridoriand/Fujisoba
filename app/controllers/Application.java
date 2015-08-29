package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.data.validation.Constraints.Required;

import java.util.*;

import views.html.*;
import models.*;

public class Application extends Controller implements Constants {
  public static Result index() {
    // DBからShopListモデルのデーターをすべて持ってきてList型でindex.scala.htmlに渡しているだけ
    // ShopListモデルのfindインスタンスに.allメソッドをつけでDBの内容読み取っている
    List<ShopList> entries = ShopList.find.all();
    return ok(index.render(indexPageName, entries));
  }

  // 新規データーのフォーム用
  public static Result add() {
    Form<ShopList> form = new Form(ShopList.class);
    return ok(create.render(addPageName, form));
  }

  // CREATE
  public static Result create() {
    Form<ShopList> form = new Form(ShopList.class).bindFromRequest();

    // formにデーターがなかった時のエラー処理
    if (!form.hasErrors()) {
      ShopList data = form.get();
      data.save();
      return redirect("/");
    } else {
      return badRequest(create.render("ERROR", form));
    }
  }

  // EDITの画面に行くためのID入力用ページの挙動
  public static Result setItem() {
    Form<ShopList> form = new Form(ShopList.class);
    return ok(item.render(setItemName, form));
  }

  // EDIT
  public static Result edit() {
    Form<ShopList> form = new Form(ShopList.class).bindFromRequest();
    if (!form.hasErrors()) {
      ShopList object = form.get();
      Long id = object.id;
      object = ShopList.find.byId(id);

      // ID検索をして該当するIDがなかった時のエラーハンドリング
      if (object != null) {
        form = new Form(ShopList.class).fill(object);
        return ok(edit.render("ID=" + id + "の投稿を編集する", form));
      } else {
        return ok(item.render(editNoitemFoundMessage, form));
      }
    } else {
      return ok(item.render(editWrongInputContentError, form));
    }
  }

  // UPDATE
  public static Result update() {
    Form<ShopList> form = new Form(ShopList.class).bindFromRequest();

    // エラーハンドリング
    if (!form.hasErrors()) {
      ShopList data = form.get();
      data.update();
      return redirect("/");
    } else {
      return ok(edit.render(updateWrongInputContentError, form));
    }
  }

  // DELETEの画面に行くためのID入力用のページの挙動
  public static Result setDelete() {
    Form<ShopList> form = new Form(ShopList.class);
    return ok(delete.render(deletePageName, form));
  }

  // DELETE
  public static Result delete() {
    Form<ShopList> form = new Form(ShopList.class).bindFromRequest();
    if (!form.hasErrors()) {
      ShopList object = form.get();
      Long id = object.id;
      object = ShopList.find.byId(id);

      // ID検索をして該当するIDがなかった時のエラーハンドリング
      if (object != null) {
        object.delete();
        return redirect("/");
      } else {
        return ok(delete.render(deleteNoItemFoundMesssage, form));
      }
    } else {
      return ok(delete.render(deleteWrongInputContentError, form));
    }
  }

  // Formからのデーターを読み込み(モデルの責務??)
  public static class FindForm {
    @Required
    public String input;
  }

  public static Result find() {
    Form<FindForm> form = new Form(FindForm.class).bindFromRequest();
    List<ShopList> entries = null;

    if (!form.hasErrors()) {
      String input = form.get().input;
      // FindFormのinputに書かれた文言がShopListモデルのname要素と一致するものを抜き出す(完全一致)
      entries = ShopList.find.where().eq("name", input).findList();
      // 完全一致で検索して、entryが一件も出てこなかった場合、ilikeで部分一致の再検索をかけるっていう雑な条件分岐
      // List型のentriesのサイズを調べてnullだったら一件もヒットしなかったとみなしている
      if (entries.size() == 0) {
        entries = ShopList.find.where().ilike("name", "%" + input + "%").findList();
      }
    }
    return ok(find.render("検索", form, entries));
  }
}
