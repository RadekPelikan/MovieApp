package com.movieapp.movieapp;

import com.movieapp.movieapp.common.Movie;
import com.movieapp.movieapp.common.Reservation;
import com.movieapp.movieapp.common.Room;
import com.movieapp.movieapp.common.Screening;

import java.sql.*;
import java.util.ArrayList;

public class DBDriver {

    static final String url = "jdbc:mysql://localhost:3306/";
    static final String dba = "movieapp";
    static final String usr = "root";
    static final String pas = "";

    private final static DBDriver instance = new DBDriver();

    public static DBDriver getInstance() {
        return instance;
    }

    public ArrayList<Movie> getMovies() {
        return MovieDriver.selectAll();
    }

    public int createMovie(Movie movie) {
        return MovieDriver.insertOne(movie);
    }

    public ArrayList<Room> getRooms() {
        return RoomDriver.selectAll();
    }

    public int createRoom(Room room) {
        return RoomDriver.insertOne(room);
    }

    public ArrayList<Screening> getAvailableScreenings() {
        return ScreeningDriver.selectAllAvailable();
    }


    public ArrayList<Screening> getScreenings() {
        return ScreeningDriver.selectAll();
    }

    public int createScreening(Screening screening) {
        return ScreeningDriver.insertOne(screening);
    }

    public ArrayList<Reservation> getReservations() {
        return ReservationDriver.selectActive();
    }

    public int reserveScreening(int id) {
        return ReservationDriver.insertOne(id);
    }

    public int cancelReservation(int id) {
        return ReservationDriver.updateOne(id, "cancelled");
    }
}

class MovieDriver {

    static ArrayList<Movie> selectAll() {
        ArrayList<Movie> movies = new ArrayList<>();

        String statement = "SELECT * FROM movie;";
        try (
                Connection connection = DriverManager.getConnection(DBDriver.url + DBDriver.dba, DBDriver.usr, DBDriver.pas);
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(statement)
        ) {
            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("duration"),
                        rs.getInt("rating"),
                        rs.getInt("year")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return movies;
    }

    static int insertOne(Movie movie) {
        String statement = "INSERT INTO movie (title, duration, rating, year) VALUES (?, ?, ?, ?);";
        try (
                Connection connection = DriverManager.getConnection(DBDriver.url + DBDriver.dba, DBDriver.usr, DBDriver.pas);
                PreparedStatement stmt = connection.prepareStatement(statement)
        ) {
            stmt.setString(1, movie.getTitle());
            stmt.setInt(2, movie.getDuration());
            stmt.setInt(3, movie.getRating());
            stmt.setInt(4, movie.getYear());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

class RoomDriver {
    static ArrayList<Room> selectAll() {
        ArrayList<Room> rooms = new ArrayList<>();

        String statement = "SELECT * FROM room;";
        try (
                Connection connection = DriverManager.getConnection(DBDriver.url + DBDriver.dba, DBDriver.usr, DBDriver.pas);
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(statement)
        ) {
            while (rs.next()) {
                rooms.add(new Room(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("street"),
                        rs.getInt("street_number"),
                        rs.getString("city"),
                        rs.getString("zip_code"),
                        rs.getInt("capacity")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rooms;
    }

    static int insertOne(Room room) {
        String statement = "INSERT INTO room (name, street, street_number, city, zip_code, capacity) VALUES (?, ?, ?, ?, ?, ?);";
        try (
                Connection connection = DriverManager.getConnection(DBDriver.url + DBDriver.dba, DBDriver.usr, DBDriver.pas);
                PreparedStatement stmt = connection.prepareStatement(statement)
        ) {
            stmt.setString(1, room.getName());
            stmt.setString(2, room.getStreet());
            stmt.setInt(3, room.getStreetNo());
            stmt.setString(4, room.getCity());
            stmt.setString(5, room.getZip());
            stmt.setInt(6, room.getCapacity());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

class ScreeningDriver {
    static ArrayList<Screening> selectAll() {
        ArrayList<Screening> screenings = new ArrayList<Screening>();

        String statement = "SELECT screening.id, screening.timestamp, movie.title, movie.year, movie.duration, " +
                "room.name AS 'room_name', room.city " +
                "FROM screening " +
                "JOIN movie ON " +
                "movie_id = movie.id " +
                "JOIN room ON " +
                "room_id = room.id;";

        try (
                Connection connection = DriverManager.getConnection(DBDriver.url + DBDriver.dba, DBDriver.usr, DBDriver.pas);
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(statement)
        ) {
            while (rs.next()) {

                screenings.add(new Screening(
                        rs.getInt("id"),
                        rs.getTimestamp("timestamp"),
                        rs.getString("title"),
                        rs.getInt("year"),
                        rs.getInt("duration"),
                        rs.getString("room_name"),
                        rs.getString("city")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return screenings;
    }

    static ArrayList<Screening> selectAllAvailable() {
        ArrayList<Screening> screenings = new ArrayList<Screening>();


        String statement = "SELECT screening.id, screening.timestamp, movie.title, movie.year, movie.duration, room.name AS 'room_name', room.city, (room.capacity - COUNT(reservation.id)) AS 'capacity_left'" +
                "FROM screening " +
                "LEFT JOIN reservation ON reservation.screening_id = screening.id AND reservation.state = 'active' " +
                "JOIN movie ON screening.movie_id = movie.id " +
                "JOIN room ON screening.room_id = room.id " +
                "GROUP BY screening.id " +
                "HAVING capacity_left > 0;";


        try (
                Connection connection = DriverManager.getConnection(DBDriver.url + DBDriver.dba, DBDriver.usr, DBDriver.pas);
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(statement)
        ) {
            while (rs.next()) {

                screenings.add(new Screening(
                        rs.getInt("id"),
                        rs.getTimestamp("timestamp"),
                        rs.getString("title"),
                        rs.getInt("year"),
                        rs.getInt("duration"),
                        rs.getString("room_name"),
                        rs.getString("city"),
                        rs.getInt("capacity_left")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return screenings;
    }

    static int insertOne(Screening screening) {
        String statement = "INSERT INTO screening (timestamp, movie_id, room_id) VALUES (?, ?, ?);";
        try (
                Connection connection = DriverManager.getConnection(DBDriver.url + DBDriver.dba, DBDriver.usr, DBDriver.pas);
                PreparedStatement stmt = connection.prepareStatement(statement)
        ) {
            stmt.setTimestamp(1, screening.getTimestamp());
            stmt.setInt(2, screening.getMovieId());
            stmt.setInt(3, screening.getRoomId());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

class ReservationDriver {

    static ArrayList<Reservation> selectActive() {
        ArrayList<Reservation> reservations = new ArrayList<Reservation>();

        String statement = "SELECT " +
                "reservation.id AS 'reservation_id', screening.id AS 'screening_id', " +
                "reservation.state, screening.timestamp, " +
                "movie.title, movie.year, movie.duration, " +
                "room.name AS 'room_name', room.city " +
                "FROM reservation " +
                "JOIN screening ON " +
                "screening_id = screening.id " +
                "JOIN movie ON " +
                "screening.movie_id = movie.id " +
                "JOIN room ON " +
                "screening.room_id = room.id " +
                "WHERE reservation.state = 'active';";

        try (
                Connection connection = DriverManager.getConnection(DBDriver.url + DBDriver.dba, DBDriver.usr, DBDriver.pas);
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(statement)
        ) {
            while (rs.next()) {
                reservations.add(new Reservation(
                        rs.getInt("reservation_id"),
                        rs.getString("state"),
                        new Screening(
                                rs.getInt("screening_id"),
                                rs.getTimestamp("timestamp"),
                                rs.getString("title"),
                                rs.getInt("year"),
                                rs.getInt("duration"),
                                rs.getString("room_name"),
                                rs.getString("city")
                        )
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reservations;
    }

    static int insertOne(int screeningId) {
        String statement = "INSERT INTO reservation (state, screening_id) VALUES (?, ?);";
        try (
                Connection connection = DriverManager.getConnection(DBDriver.url + DBDriver.dba, DBDriver.usr, DBDriver.pas);
                PreparedStatement stmt = connection.prepareStatement(statement)
        ) {
            stmt.setString(1, "active");
            stmt.setInt(2, screeningId);

            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    static int updateOne(int reservationId, String state) {
        String statement = "UPDATE reservation SET state = ? WHERE id = ?;";
        try (
                Connection connection = DriverManager.getConnection(DBDriver.url + DBDriver.dba, DBDriver.usr, DBDriver.pas);
                PreparedStatement stmt = connection.prepareStatement(statement)
        ) {
            stmt.setString(1, state);
            stmt.setInt(2, reservationId);

            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}