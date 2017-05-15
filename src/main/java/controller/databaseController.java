package controller;

import dao.EventDao;

import java.sql.SQLException;

import static spark.Spark.exception;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

/**
 * Created by jakub on 15/05/17.
 */
public class databaseController {

    public static void createTable(String[] args) {
        if(args.length > 0 && args[0].equals("--create-tables")) {
            try {
                EventDao.createTables();
            } catch (SQLException e) {
                System.out.println("Cannot create tables in db.");
                System.out.println(e);
            }
        }
    }
}
