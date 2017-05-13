package model;


/**
 * Created by jakub on 12/05/17.
 */
public class Event {

    public String name;
    public String description;
    public String date;
    public String category;

    public Event(String name, String description, String date, String category) {
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
        this.name = description;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.name = date;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.name = category;
    }
}
