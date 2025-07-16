package org.acme.timetable;

import java.util.Objects;

// This class represents a room where lessons can be scheduled.
public class Room {
    // The name of the room (e.g., Room A, Lab 1)
    private String name;

    // Default constructor
    public Room() {}

    // Constructor to create a room with a name
    public Room(String name) {
        this.name = name;
    }

    // Getter and setter for name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Override equals to compare rooms by name
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(name, room.name);
    }

    // Override hashCode for use in collections
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    // String representation of the room
    @Override
    public String toString() {
        return name;
    }
} 