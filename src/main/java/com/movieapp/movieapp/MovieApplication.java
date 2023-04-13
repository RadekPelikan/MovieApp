package com.movieapp.movieapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MovieApplication extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        Scene selectUserScene = createScene("user-select-view.fxml");
        MovieApplication.stage = stage;
        stage.setTitle("MovieApp");
        stage.setResizable(false);
        stage.setScene(selectUserScene);
        stage.show();
    }

    public static void logOut() throws IOException {
        Scene scene = createScene("user-select-view.fxml");
        MovieApplication.getStage().setScene(scene);
    }

    public static void setScene(String userTag, String SceneTag) throws IOException {
        Scene scene = createScene(userTag + "/" + SceneTag + "-view.fxml");
        MovieApplication.getStage().setScene(scene);
    }

    public static Scene createScene(String viewFile) throws IOException {
        FXMLLoader view = new FXMLLoader(MovieApplication.class.getResource(viewFile));
        return new Scene(view.load());
    }


    public static Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch();
    }
}