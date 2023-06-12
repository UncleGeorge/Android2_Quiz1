package com.example.zhangzhiwangsnotes;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable {
    private String name;
    private String description;
    private boolean priority;
    private Date date;

    public Note(String name, String description, boolean priority, Date date) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isPriority() {
        return priority;
    }

    public Date getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
