package com.example.lists.classes;

public class Music {
    private String title;
    private int date;
    private String author;
    private static int obj_id = 0;
    private int id;

    public Music(String title, int date, String author) {
        this.title = title;
        this.date = date;
        this.author = author;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
