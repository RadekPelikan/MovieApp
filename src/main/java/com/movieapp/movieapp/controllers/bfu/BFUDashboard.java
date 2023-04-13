package com.movieapp.movieapp.controllers.bfu;

import com.movieapp.movieapp.MovieApplication;
import com.movieapp.movieapp.controllers.admin.ScreeningCreation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BFUDashboard implements Initializable {

    public TabPane tabs;

    public void onLogout(ActionEvent actionEvent) throws IOException {
        MovieApplication.logOut();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tabs.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        ScreeningList.refresh();
                        ReservationList.refresh();
                    }
                }
        );

    }
}
