package controller;

import dao.EventDao;
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
        params.put("eventContainer", eventDao.getAll());
        return new ModelAndView(params,"product/eventInfo");
    }
}
