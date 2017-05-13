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
        //Get events from database by Dao
        List<Event> events = new ArrayList<>();
        try {
            Connection connection = EventDao.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from events");
            while(rs.next()) {
                Event event = new Event(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("date"),
                        rs.getString("category")
                );
                events.add(event);
            }
        } catch (SQLException e) {
            System.out.println("Connection to database failed.");
            System.out.println(e.getMessage());
        }

        Map params = new HashMap<>();
        params.put("eventContainer", events);
        return new ModelAndView(params, "product/index");
    }
}
