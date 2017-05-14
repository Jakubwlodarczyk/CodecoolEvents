import controller.EventController;
import dao.EventDao;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import  static spark.Spark.*;


public class Main {

    public static void main(String[] args) {
        if(args.length > 0 && args[0].equals("--create-tables")) {
            try {
                EventDao.createTables();
            } catch (SQLException e) {
                System.out.println("Cannot create tables in db.");
                System.out.println(e);
            }
        }

        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // Always start with more specific routes
        get("/hello", (req, res) -> "Hello World");

        // Always add generic routes to the end
        get("/", EventController::renderEvents, new ThymeleafTemplateEngine());
        // Equivalent with above
        get("/index", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render( EventController.renderEvents(req, res) );
        });

        get("/event-info", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render( EventController.renderEventsInfo(req, res) );
        });
    }


}