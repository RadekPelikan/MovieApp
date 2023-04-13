package com.movieapp.movieapp.controllers.admin;

import com.movieapp.movieapp.MovieApplication;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboard implements Initializable {
    @FXML
    public TabPane tabs;
    @FXML
    public void onLogOut(ActionEvent actionEvent) throws IOException {
        MovieApplication.logOut();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tabs.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        ScreeningCreation.refresh();
                    }
                }
        );

    }
}
