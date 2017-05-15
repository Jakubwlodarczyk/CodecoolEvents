package controller;

import dao.EventDao;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class EventController {

    public static ModelAndView renderEvents(Request req, Response res) {
        Map params = new HashMap<>();
        params.put("eventContainer", EventDao.getAll());
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderEventsInfo(Request req, Response res) {
        Map params = new HashMap<>();
        String stringId = req.params(":id");
        Integer integerId = Integer.parseInt(stringId);
        params.put("event", EventDao.getById(integerId));
        return new ModelAndView(params,"product/eventInfo");
    }

    public static ModelAndView renderAddForm(Request req, Response res) {
        Map params = new HashMap<>();
        return new ModelAndView(params, "product/addEvent");
    }

    public static ModelAndView addNewEvent(Request req, Response res) throws SQLException {
        String eventName = req.queryParams("event-name");
        String eventDescription = req.queryParams("event-description");
        String eventDate = req.queryParams("event-date");
        String eventTime = req.queryParams("event-time");
        String eventCategory = req.queryParams("event-category");
        String fullDate = eventDate + " " +eventTime;
        EventDao.addEventToDatabase(eventName, eventDescription, fullDate, eventCategory);

        Map params = new HashMap<>();
        params.put("eventContainer", EventDao.getAll());
        return new ModelAndView(params, "product/index");
    }

    public static void removeEvent(Integer id) throws SQLException {
        EventDao.removeEventFromDatabase(id);
    }
}
