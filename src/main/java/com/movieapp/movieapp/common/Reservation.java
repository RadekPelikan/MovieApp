package com.movieapp.movieapp.common;

public class Reservation {
    private int id;
    private final String state;
    private final Screening screening;

    public Reservation(int id, String state, Screening screening) {
        this(state, screening);
        this.id = id;
    }

    public Reservation(String state, Screening screening) {
        this.state = state;
        this.screening = screening;
    }

    public int getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public Screening getScreening() {
        return screening;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "state='" + state + '\'' +
                ", screening=" + screening +
                '}';
    }
}
