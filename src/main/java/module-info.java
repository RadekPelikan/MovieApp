module com.movieapp.movieapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.movieapp.movieapp to javafx.fxml;
    exports com.movieapp.movieapp;
    exports com.movieapp.movieapp.controllers;
    opens com.movieapp.movieapp.controllers to javafx.fxml;
    exports com.movieapp.movieapp.controllers.admin;
    opens com.movieapp.movieapp.controllers.admin to javafx.fxml;
    exports com.movieapp.movieapp.controllers.bfu;
    opens com.movieapp.movieapp.controllers.bfu to javafx.fxml;
    exports com.movieapp.movieapp.common;
    opens com.movieapp.movieapp.common to javafx.fxml;
    exports com.movieapp.movieapp.controllers.common;
    opens com.movieapp.movieapp.controllers.common to javafx.fxml;
}