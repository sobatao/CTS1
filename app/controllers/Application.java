package controllers;

import models.User;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.home;
import views.html.index;

import static play.data.Form.form;

/**
 * Created by Meaks on 12/6/2015.
 */
public class Application extends Controller {



    public Result home(){
        return ok(home.render("This is the home page"));
    }

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public Result login() {
        DynamicForm userForm = form().bindFromRequest();
        String username = userForm.data().get("username");
        String password = userForm.data().get("password");

        User user = User.find.where().eq("username", username).findUnique();

        if(user != null && user.authenticate(password)) {
            session("user_id", user.id.toString());
            flash("success", "Welcome back " + user.username);
        } else {
            flash("error", "Invalid login. Check your username and password.");
        }

        return redirect(routes.Application.home());

    }

    public Result signup() {
        DynamicForm userForm = form().bindFromRequest();
        String username = userForm.data().get("username");
        String password = userForm.data().get("password");

        User user = User.createNewUser(username, password);

        if(user == null) {
            flash("error", "Invalid user");
            return redirect(routes.Application.index());
        }

        user.save();

        flash("success", "Welcome new user " + user.username);
        session("user_id", user.id.toString());
        return redirect(routes.Application.home());
    }

    public Result logout() {
        session().remove("user_id");
        return redirect(routes.Application.index());
    }





}
