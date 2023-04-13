package com.movieapp.movieapp.controllers.admin;

import com.movieapp.movieapp.DBDriver;
import com.movieapp.movieapp.common.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class MovieCreation implements Initializable {
    public TextField movieInput;
    public TextField yearInput;
    public TextField durationInput;
    public Spinner<Integer> ratingInput;
    public Label statusMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> ratingFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);

        ratingInput.setValueFactory(ratingFactory);

    }


    public void onPublish(ActionEvent actionEvent) {
        statusMessage.textFillProperty().setValue(Color.RED);

        Movie movie;
        try {
            movie = new Movie(
                    movieInput.getText(),
                    Integer.parseInt(durationInput.getText()),
                    ratingInput.getValue(),
                    Integer.parseInt(yearInput.getText())
            );
        } catch (NumberFormatException e) {
            statusMessage.setText("Invalid movie data");
            return;
        }

        if (!movie.isValid()) {
            statusMessage.setText("Invalid movie data");
            return;
        }
        ;

        int result = DBDriver.getInstance().createMovie(movie);
        System.out.println(result);

        clearFields();

        // Change color of Status message
        statusMessage.textFillProperty().setValue(Color.GREEN);
        statusMessage.setText("Movie published successfully");
    }

    private void clearFields() {
        movieInput.setText("");
        yearInput.setText("");
        durationInput.setText("");
        ratingInput.getValueFactory().setValue(0);
    }

}
