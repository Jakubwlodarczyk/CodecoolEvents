package controller;

import dao.EventDao;
import model.Event;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventController {

    public static ModelAndView renderEvents(Request req, Response res) {
        Map params = new HashMap<>();
        params.put("eventContainer", EventDao.getAll());
        params.put("category", EventController.getCategories());
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

    public static ModelAndView renderEditEvent(Request req, Response res) {
        Map params = new HashMap<>();
        String stringId = req.params(":id");
        Integer integerId = Integer.parseInt(stringId);
        params.put("event", EventDao.getById(integerId));
        return new ModelAndView(params,"product/editEvent");
    }

    public static ModelAndView updateEventInDb(Request req, Response res) throws SQLException {
        String eventName = req.queryParams("event-name");
        String eventDescription = req.queryParams("event-description");
        String eventDate = req.queryParams("event-date");
        String eventTime = req.queryParams("event-time");
        String eventCategory = req.queryParams("event-category");
        String fullDate = eventDate + " " +eventTime;
        String stringId = req.queryParams("event-id");
        Integer integerId = Integer.parseInt(stringId);
        EventDao.updateEventInDatabase(eventName, eventDescription, fullDate, eventCategory, integerId);

        Map params = new HashMap<>();
        params.put("eventContainer", EventDao.getAll());
        return new ModelAndView(params, "product/index");
    }

    public static List<String> getCategories(){
        List<String> categories = new ArrayList<>();
        for (Event event : EventDao.getAll()) {
            if (!categories.contains(event.category)){
                categories.add(event.category);
            }
        }
        return categories;
    }

    public static List<Event> getEventListByCategory(String category) {
        List<Event> eventsByCategory = new ArrayList<>();
        if (category.equals("ALL")){
            return EventDao.getAll();
        }
        for (Event event : EventDao.getAll()){
            if (event.category.equals(category)){
                eventsByCategory.add(event);
            }
        }
        return eventsByCategory;
    }

    public static ModelAndView getEventByCategory(Request req, Response res) {
        Map params = new HashMap<>();
        params.put("eventContainer", EventController.getEventListByCategory(req.queryParams("chosen-category")));
        params.put("category", EventController.getCategories());
        return new ModelAndView(params, "product/index");
    }
}
