package com.movieapp.movieapp.controllers.common;

import com.movieapp.movieapp.common.Room;
import com.movieapp.movieapp.common.Screening;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ScreeningPane implements Initializable {
    @FXML
    public Label titleLabel;
    @FXML
    public Label roomNameCityLabel;
    @FXML
    public Label capacityLabel;
    @FXML
    public Label yearLabel;
    @FXML
    public Label timestampDurationLabel;

    private final Screening screening;

    public ScreeningPane(Screening screening) {
        this.screening = screening;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleLabel.setText(screening.getTitle());
        roomNameCityLabel.setText(screening.getRoomName() + " (" + screening.getCity() + ")");
        capacityLabel.setText(screening.getCapacityLeft() + " left");
        yearLabel.setText("(" + screening.getYear() + ")");
        String formattedTimestamp = screening.getTimestamp().toLocalDateTime().format(DateTimeFormatter.ofPattern("d.m.yyyy HH:mm"));
        timestampDurationLabel.setText(formattedTimestamp + " - " + screening.getDuration() + "min");
    }


}
