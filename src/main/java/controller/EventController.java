package controller;

import dao.EventDao;
import model.Event;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
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
}
