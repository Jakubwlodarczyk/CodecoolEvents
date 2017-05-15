import controller.EventController;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import  static spark.Spark.*;


public class Main {

    public static void main(String[] args) {

        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        get("/", EventController::renderEvents, new ThymeleafTemplateEngine());

        get("/event-info", EventController::renderEventsInfo, new ThymeleafTemplateEngine() );

        get("/add-event", EventController::renderAddForm, new ThymeleafTemplateEngine() );

        post("/add-event", EventController::addNewEvent, new ThymeleafTemplateEngine() );
    }
    
}