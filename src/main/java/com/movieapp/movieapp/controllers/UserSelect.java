package com.movieapp.movieapp.controllers;

import com.movieapp.movieapp.MovieApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserSelect implements Initializable {

@FXML
    public void onBFUclick(ActionEvent actionEvent) throws IOException {
        MovieApplication.setScene("bfu", "dashboard");
    }

    @FXML
    public void onAdminclick(ActionEvent actionEvent) throws  IOException {
        MovieApplication.setScene("admin", "dashboard");
    }

    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}