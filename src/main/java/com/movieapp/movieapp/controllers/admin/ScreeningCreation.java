package com.movieapp.movieapp.controllers.admin;

import com.movieapp.movieapp.DBDriver;
import com.movieapp.movieapp.MovieApplication;
import com.movieapp.movieapp.common.Movie;
import com.movieapp.movieapp.common.Room;
import com.movieapp.movieapp.common.Screening;
import com.movieapp.movieapp.controllers.common.MoviePane;
import com.movieapp.movieapp.controllers.common.RoomPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class ScreeningCreation implements Initializable {

    @FXML
    public ListView<VBox> moviesWrapper;
    @FXML
    public ListView<VBox> roomsWrapper;
    @FXML
    public DatePicker dateInput;
    @FXML
    public Spinner<Integer> hourInput;
    @FXML
    public Spinner<Integer> minuteInput;
    @FXML
    public Label movieLabel;
    @FXML
    public Label roomLabel;
    @FXML
    public Label statusMessage;
    private static ScreeningCreation instance;

    @FXML
    public void onPublish(ActionEvent actionEvent) {
        statusMessage.textFillProperty().setValue(Color.RED);

        int movieId = (int) moviesWrapper.getSelectionModel().getSelectedItems().get(0).getProperties().get("id");
        int roomId = (int) roomsWrapper.getSelectionModel().getSelectedItems().get(0).getProperties().get("id");

        LocalDate localDate = dateInput.getValue();
        int hour = hourInput.getValue();
        int minute = minuteInput.getValue();

        Timestamp timestamp;
        try {
            timestamp = Timestamp.valueOf(
                    LocalDateTime.of(
                            localDate.getYear(),
                            localDate.getMonth(),
                            localDate.getDayOfMonth(),
                            hour,
                            minute
                    )
            );
        } catch (NumberFormatException e) {
            statusMessage.setText("Invalid screening date");
            return;
        }
        Screening screening = new Screening(timestamp, movieId, roomId);

        if (!screening.isValid()) {
            statusMessage.setText("Invalid screening data");
            return;
        }
        int result = DBDriver.getInstance().createScreening(screening);
        System.out.println(result);

        clearFields();

        // Change color of Status message
        statusMessage.textFillProperty().setValue(Color.GREEN);
        statusMessage.setText("Screening published successfully");
    }

    public void clearFields() {
        moviesWrapper.getSelectionModel().clearSelection();
        roomsWrapper.getSelectionModel().clearSelection();
        dateInput.setValue(null);
        hourInput.getValueFactory().setValue(0);
        minuteInput.getValueFactory().setValue(0);
        movieLabel.setText("Movie:");
        roomLabel.setText("Room:");
    }

    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> hourFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        SpinnerValueFactory<Integer> minuteFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);

        hourInput.setValueFactory(hourFactory);
        minuteInput.setValueFactory(minuteFactory);

        loadData();
    }

    private void loadData() {
        moviesWrapper.getItems().clear();
        roomsWrapper.getItems().clear();

        ArrayList<Movie> movies = DBDriver.getInstance().getMovies();

        for (Movie movie : movies) {
            FXMLLoader loader = new FXMLLoader(MovieApplication.class.getResource("common/movie-pane-view.fxml"));
            MoviePane controller = new MoviePane(movie);
            loader.setController(controller);

            VBox vbox;
            try {
                vbox = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            vbox.getProperties().put("id", movie.getId());
            vbox.getProperties().put("title", movie.getTitle());
            vbox.setPrefWidth(moviesWrapper.getPrefWidth() - 35);
            moviesWrapper.getItems().add(vbox);
        }

        ArrayList<Room> rooms = DBDriver.getInstance().getRooms();

        for (Room room : rooms) {
            FXMLLoader loader = new FXMLLoader(MovieApplication.class.getResource("common/room-pane-view.fxml"));
            RoomPane controller = new RoomPane(room);
            loader.setController(controller);

            VBox vbox;
            try {
                vbox = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            vbox.getProperties().put("id", room.getId());
            vbox.getProperties().put("name", room.getName());
            vbox.setPrefWidth(roomsWrapper.getPrefWidth() - 0);
            roomsWrapper.getItems().add(vbox);
        }
    }

    public static void refresh() {
        ScreeningCreation.instance.loadData();
    }

    public ScreeningCreation() {
        instance = this;
    }

    @FXML
    public void onMovieSelect(MouseEvent mouseEvent) {
        movieLabel.setText("Movie:\n" + moviesWrapper.getSelectionModel().getSelectedItems().get(0).getProperties().get("title").toString());
    }

    @FXML
    public void onRoomSelect(MouseEvent mouseEvent) {
        roomLabel.setText("Room:\n" + roomsWrapper.getSelectionModel().getSelectedItems().get(0).getProperties().get("name").toString());
    }

}
