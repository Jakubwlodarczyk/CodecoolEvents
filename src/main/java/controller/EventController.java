package controller;

import dao.EventDao;
import model.Event;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rafalstepien on 28/04/2017.
 */
public class EventController {

    public static ModelAndView renderProducts(Request req, Response res) {

        EventDao eventDao = new EventDao();
        Map params = new HashMap<>();
        params.put("eventContainer", eventDao.getAll());
        return new ModelAndView(params, "product/index");
    }
}
