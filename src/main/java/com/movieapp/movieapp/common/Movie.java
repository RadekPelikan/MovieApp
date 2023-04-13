package com.movieapp.movieapp.common;

public class Movie {
    private int id;
    private final String title;
    private final int duration;
    private final int rating;
    private final int year;

    public Movie(int id, String title, int duration, int rating, int year) {
        this(title, duration, rating, year);
        this.id = id;
    }

    public Movie(String title, int duration, int rating, int year) {
        this.title = title;
        this.duration = duration;
        this.rating = rating;
        this.year = year;
    }

    public boolean isValid() {
        if (title.length() > 100) return false;
        if (duration < 0 || duration > 300) return false;
        if (rating < 0 || rating > 100) return false;
        if (year < 1900 || year > 3000) return false;

        return true;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public int getRating() {
        return rating;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", duration=" + duration +
                ", rating=" + rating +
                ", year=" + year +
                '}';
    }

}
