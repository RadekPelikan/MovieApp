package com.movieapp.movieapp.controllers.bfu;

import com.movieapp.movieapp.DBDriver;
import com.movieapp.movieapp.MovieApplication;
import com.movieapp.movieapp.common.Screening;
import com.movieapp.movieapp.controllers.common.ScreeningPane;
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

public class ScreeningList implements Initializable {

    @FXML
    public ListView<VBox> movieSelectWrapper;

    private static ScreeningList instance;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }

    @FXML
    public void loadData() {
        movieSelectWrapper.getItems().clear();

        ArrayList<Screening> screenings = DBDriver.getInstance().getAvailableScreenings();

        for (Screening screening : screenings) {
            FXMLLoader loader = new FXMLLoader(MovieApplication.class.getResource("common/screening-pane-view.fxml"));
            ScreeningPane controller = new ScreeningPane(screening);
            loader.setController(controller);

            VBox vbox;
            try {
                vbox = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            vbox.getProperties().put("id", screening.getId());
            vbox.setPrefWidth(movieSelectWrapper.getPrefWidth() - 60);
            movieSelectWrapper.getItems().add(vbox);
        }
    }

    public static void refresh() {
        ScreeningList.instance.loadData();
    }

    public ScreeningList() {
        instance = this;
    }

    public void onReserve(ActionEvent actionEvent) {
        if (movieSelectWrapper.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        int id = (int) movieSelectWrapper.getSelectionModel().getSelectedItem().getProperties().get("id");
        int result = DBDriver.getInstance().reserveScreening(id);
        System.out.println(result);
        refresh();
        clearFields();
    }

    public void clearFields() {
        movieSelectWrapper.getSelectionModel().clearSelection();
    }

}
