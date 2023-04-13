package com.movieapp.movieapp.common;

public class Room {
    public int id;
    public String name;
    public String street;
    public int streetNo;
    public String city;
    public String zip;
    public int capacity;

    public Room(int id, String name, String street, int streetNo, String city, String zip, int capacity) {
        this(name, street, streetNo, city, zip, capacity);
        this.id = id;
    }

    public Room(String name, String street, int streetNo, String city, String zip, int capacity) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.streetNo = streetNo;
        this.city = city;
        this.zip = zip;
        this.capacity = capacity;
    }

    public boolean isValid() {
        if (name.length() > 100) return false;
        if (street.length() > 100) return false;
        if (streetNo < 0 || streetNo > 1000) return false;
        if (city.length() > 100) return false;
        if (zip.length() > 10) return false;
        if (capacity < 0 || capacity > 1000) return false;

        return true;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public int getStreetNo() {
        return streetNo;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", streetNo='" + streetNo + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
