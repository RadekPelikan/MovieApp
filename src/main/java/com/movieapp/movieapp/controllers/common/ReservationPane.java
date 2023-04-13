package com.movieapp.movieapp.controllers.common;

import com.movieapp.movieapp.common.Reservation;
import com.movieapp.movieapp.common.Screening;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ReservationPane implements Initializable {
    @FXML
    public Label titleLabel;
    @FXML
    public Label roomNameCityLabel;
    @FXML
    public Label yearLabel;
    @FXML
    public Label timestampDurationLabel;

    private final Reservation reservation;

    public ReservationPane(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Screening screening = reservation.getScreening();
        titleLabel.setText(screening.getTitle());
        roomNameCityLabel.setText(screening.getRoomName() + " (" + screening.getCity() + ")");
        yearLabel.setText("(" + screening.getYear() + ")");
        String formattedTimestamp = screening.getTimestamp().toLocalDateTime().format(DateTimeFormatter.ofPattern("d.m.yyyy HH:mm"));
        timestampDurationLabel.setText(formattedTimestamp + " - " + screening.getDuration() + "min");
    }

}
