package com.example.lists.classes;

public class Movies {
    private String title;
    private int date;
    private String duration;
    private static int obj_id = 0;
    private int id;

    public Movies(String title, int date, String duration) {
        this.title = title;
        this.date = date;
        this.duration = duration;
        this.id = obj_id++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
