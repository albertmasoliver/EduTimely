package org.acme.timetable;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

// This class represents a timeslot for scheduling lessons, including day of week and start/end times.
// Because no Timeslot instances change during solving, a Timeslot is called a problem fact
public class Timeslot {
    // The day of the week for the timeslot (e.g., MONDAY)
    private DayOfWeek dayOfWeek;
    // The start time of the timeslot
    private LocalTime startTime;
    // The end time of the timeslot
    private LocalTime endTime;

    // Default constructor
    public Timeslot() {}

    // Constructor to create a timeslot with day, start, and end times
    public Timeslot(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getter and setter for dayOfWeek
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    // Getter and setter for startTime
    public LocalTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    // Getter and setter for endTime
    public LocalTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    // Override equals to compare timeslots by day, start, and end times
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Timeslot timeslot = (Timeslot) o;
        return dayOfWeek == timeslot.dayOfWeek &&
                Objects.equals(startTime, timeslot.startTime) &&
                Objects.equals(endTime, timeslot.endTime);
    }

    // Override hashCode for use in collections
    @Override
    public int hashCode() {
        return Objects.hash(dayOfWeek, startTime, endTime);
    }

    // String representation of the timeslot
    @Override
    public String toString() {
        return dayOfWeek + " " + startTime + "-" + endTime;
    }
} 