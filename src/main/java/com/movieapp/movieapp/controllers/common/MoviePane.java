package com.movieapp.movieapp.controllers.common;

import com.movieapp.movieapp.common.Movie;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MoviePane implements Initializable {
    @FXML
    public Label titleLabel;
    @FXML
    public Label ratingLabel;
    @FXML
    public Label yearLabel;
    @FXML
    public Label durationLabel;

    private final Movie movie;

    public MoviePane(Movie movie) {
        this.movie = movie;
    }

    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleLabel.setText(movie.getTitle());
        ratingLabel.setText(movie.getRating() + "%");
        yearLabel.setText("(" + movie.getYear() + ")");
        durationLabel.setText(movie.getDuration() + "min");
    }
}
