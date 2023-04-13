package com.movieapp.movieapp.controllers.admin;

import com.movieapp.movieapp.DBDriver;
import com.movieapp.movieapp.common.Room;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class RoomCreation implements Initializable {
    public TextField nameInput;
    public TextField streetInput;
    public TextField streetNoInput;
    public TextField cityInput;
    public TextField zipInput;
    public Spinner<Integer> capacityInput;
    public Label statusMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> ratingFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 50);

        capacityInput.setValueFactory(ratingFactory);

    }
    public void onPublish(ActionEvent actionEvent) {
        statusMessage.textFillProperty().setValue(Color.RED);

        Room room;
        try {
            room = new Room(
                    nameInput.getText(),
                    streetInput.getText(),
                    Integer.parseInt(streetNoInput.getText()),
                    cityInput.getText(),
                    zipInput.getText(),
                    capacityInput.getValue()
            );
        } catch (NumberFormatException e) {
            statusMessage.setText("Invalid room data");
            return;
        }

        if (!room.isValid()) {
            statusMessage.setText("Invalid room data");
            return;
        }
        int result = DBDriver.getInstance().createRoom(room);
        System.out.println(result);

        clearFields();

        // Change color of Status message
        statusMessage.textFillProperty().setValue(Color.GREEN);
        statusMessage.setText("Room published successfully");
    }

    private void clearFields() {
        nameInput.setText("");
        streetInput.setText("");
        streetNoInput.setText("");
        cityInput.setText("");
        zipInput.setText("");
        capacityInput.getValueFactory().setValue(0);
    }
}
