package dao;

import java.sql.*;

/**
 * Created by rafalstepien on 28/04/2017.
 */
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
}
