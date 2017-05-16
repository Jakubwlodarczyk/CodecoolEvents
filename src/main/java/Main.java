import controller.DatabaseController;
import controller.EventController;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import  static spark.Spark.*;


public class Main {

    public static void main(String[] args) {

        DatabaseController.createTable(args);

        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        get("/sorted", EventController::getEventByCategory, new ThymeleafTemplateEngine() );

        get("/", EventController::renderEvents, new ThymeleafTemplateEngine());

        get("/events/:id/info", EventController::renderEventsInfo, new ThymeleafTemplateEngine() );

        get("/add-event", EventController::renderAddForm, new ThymeleafTemplateEngine() );

        post("/add-event", EventController::addNewEvent, new ThymeleafTemplateEngine() );

        get("/events/:id/remove", (Request req, Response res) -> {
            EventController.removeEvent(Integer.parseInt(req.params(":id")));
            res.redirect("/");
            return null;
        });

        get("/events/:id/edit", EventController::renderEditEvent, new ThymeleafTemplateEngine() );

        post("edit-event", EventController::updateEventInDb, new ThymeleafTemplateEngine() );

    }

}