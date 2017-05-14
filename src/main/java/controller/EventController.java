package controller;

import com.sun.org.apache.regexp.internal.RE;
import dao.EventDao;
import model.Event;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rafalstepien on 28/04/2017.
 */
public class EventController {

    public static ModelAndView renderEvents(Request req, Response res) {
        EventDao eventDao = new EventDao();
        Map params = new HashMap<>();
        params.put("eventContainer", eventDao.getAll());
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderEventsInfo(Request req, Response res) {
        EventDao eventDao = new EventDao();
        Map params = new HashMap<>();
        String stringId = req.queryParams("event-id");
        Integer integerId = Integer.parseInt(stringId);
        params.put("event", eventDao.getById(integerId));
        Event event = eventDao.getById(integerId);
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
        EventDao eventDao = new EventDao();
        eventDao.addEventToDatabase(eventName, eventDescription, fullDate, eventCategory);

        Map params = new HashMap<>();
        params.put("eventContainer", eventDao.getAll());
        return new ModelAndView(params, "product/index");
    }
}
