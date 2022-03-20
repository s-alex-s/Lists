package com.example.lists.classes;

public class Games {
    private String title;
    private int release_date;
    private String genre;
    private static int obj_id = 0;
    private int id;

    public Games(String title, int release_date, String genre) {
        this.title = title;
        this.release_date = release_date;
        this.genre = genre;
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

    public int getRelease_date() {
        return release_date;
    }

    public void setRelease_date(int release_date) {
        this.release_date = release_date;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
