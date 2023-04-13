package com.movieapp.movieapp.controllers.bfu;

import com.movieapp.movieapp.DBDriver;
import com.movieapp.movieapp.MovieApplication;
import com.movieapp.movieapp.common.Reservation;
import com.movieapp.movieapp.controllers.common.ReservationPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReservationList implements Initializable {

    @FXML
    public ListView<VBox> reservationWrapper;

    private static ReservationList instance;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }

    public void loadData() {
        reservationWrapper.getItems().clear();

        ArrayList<Reservation> reservations = DBDriver.getInstance().getReservations();

        for (Reservation reservation : reservations) {
            FXMLLoader loader = new FXMLLoader(MovieApplication.class.getResource("common/screening-pane-view.fxml"));
            ReservationPane controller = new ReservationPane(reservation);
            loader.setController(controller);

            VBox vbox;
            try {
                vbox = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            vbox.getProperties().put("id", reservation.getId());
            vbox.setPrefWidth(reservationWrapper.getPrefWidth() - 60);
            reservationWrapper.getItems().add(vbox);
        }
    }

    public static void refresh() {
        ReservationList.instance.loadData();
    }

    public ReservationList() {
        instance = this;
    }

    public void onCancel(ActionEvent actionEvent) {
        if (reservationWrapper.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        int id = (int) reservationWrapper.getSelectionModel().getSelectedItem().getProperties().get("id");
        int result = DBDriver.getInstance().cancelReservation(id);
        System.out.println(result);
        refresh();
        clearFields();
    }

    public void clearFields() {
        reservationWrapper.getSelectionModel().clearSelection();
    }
}
