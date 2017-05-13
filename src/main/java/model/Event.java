package model;

import java.util.Date;

/**
 * Created by jakub on 12/05/17.
 */
public class Event {

    public String name;
    public String description;
    public Date date;
    public String category;

    public Event(String name, String description, Date date, String category) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.category = category;

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
