package com.movieapp.movieapp.controllers.common;

import com.movieapp.movieapp.common.Movie;
import com.movieapp.movieapp.common.Room;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class RoomPane implements Initializable {
    @FXML
    public Label nameLabel;
    @FXML
    public Label capacityLabel;
    @FXML
    public Label cityLabel;
    @FXML
    public Label streetLabel;

    private final Room room;

    public RoomPane(Room room) {
        this.room = room;
    }

    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameLabel.setText(room.getName());
        capacityLabel.setText(room.getCapacity() + "");
        cityLabel.setText(room.getCity());
        streetLabel.setText(room.getStreet() + " " + room.getStreetNo());
    }
}
