package dao;

import model.Event;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class EventDao {
    public static Connection connection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
        } catch (SQLException e) {
            System.out.println("Connection to database failed.");
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public static void createTables() throws SQLException {
        Connection connection = connection();
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS \"events\" (\n" +
                "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`name`\tTEXT,\n" +
                "\t`description`\tTEXT,\n" +
                "\t`date`\tTEXT,\n" +
                "\t`category`\tTEXT\n" +
                ")");
    }

    public static List<Event> getAll() {
        List<Event> events = new ArrayList<>();
        try {
            Connection connection = EventDao.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from events where date > date('now')");
            while(rs.next()) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String datestring = rs.getString("date");
                Date date = format.parse(datestring);
                Event event = new Event(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        date,
                        rs.getString("category")
                );
                events.add(event);
            }
        } catch (SQLException e) {
            System.out.println("Connection to database failed.");
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println("Cannot convert date format from database");
            System.out.println(e.getMessage());
        }
        return events;
    }

    public static Event getById(Integer id) {
        Event event = null;
        try {
            Connection connection = EventDao.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from events where id =" + id);
            while(rs.next()) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String datestring = rs.getString("date");
                Date date = format.parse(datestring);
                event = new Event(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        date,
                        rs.getString("category")
                );
            }
        } catch (SQLException e) {
            System.out.println("Connection to database failed.");
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println("Cannot convert date format from database");
            System.out.println(e.getMessage());
        }
        return event;
    }

    public static void addEventToDatabase(String name, String description, String date, String category) throws SQLException {
        Connection connection = EventDao.connection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO events(name, description, date, category) " +
                "VALUES('" +name+ "', '" +description+ "', '" +date+ "', '" +category+ "');");
    }
}
