package com.movieapp.movieapp.common;

import java.sql.Timestamp;

public class Screening {
    private int id;
    private final Timestamp timestamp;
    private int movieId;
    private int roomId;

    private String title;
    private int year;
    private int duration;
    private String roomName;
    private String city;
    private int capacityLeft;


    public Screening(int id, Timestamp timestamp, int movieId, int roomId) {
        this(timestamp, movieId, roomId);
        this.id = id;
    }

    public Screening(Timestamp timestamp, int movieId, int roomId) {
        this.timestamp = timestamp;
        this.movieId = movieId;
        this.roomId = roomId;
    }

    public Screening(int id, Timestamp timestamp, String title, int year, int duration, String roomName, String city) {
        this(timestamp, title, year, duration, roomName, city);
        this.id = id;
    }

    public Screening(Timestamp timestamp, String title, int year, int duration, String roomName, String city) {
        this.timestamp = timestamp;
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.roomName = roomName;
        this.city = city;
    }

    public Screening(int id, Timestamp timestamp, String title, int year, int duration, String roomName, String city, int capacityLeft) {
        this(timestamp, title, year, duration, roomName, city, capacityLeft);
        this.id = id;
    }

    public Screening(Timestamp timestamp, String title, int year, int duration, String roomName, String city, int capacityLeft) {
        this.timestamp = timestamp;
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.roomName = roomName;
        this.city = city;
        this.capacityLeft = capacityLeft;
    }

    public boolean isValid() {
        if (timestamp == null) return false;
        if (movieId < 0) return false;
        if (roomId < 0) return false;

        return true;
    }

    public int getId() {
        return id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getDuration() {
        return duration;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getCity() {
        return city;
    }

    public int getCapacityLeft() {
        return capacityLeft;
    }

    @Override
    public String toString() {
        return "Screening{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", movieId=" + movieId +
                ", roomId=" + roomId +
                '}';
    }
}
