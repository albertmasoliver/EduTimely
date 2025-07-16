package org.acme.timetable;

import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.domain.solution.PlanningScore;

// This class represents the planning solution for the timetable, including lists of timeslots, rooms, lessons, and the score.
@PlanningSolution
public class Timetable {
    // List of available timeslots (problem facts)
    @ValueRangeProvider(id = "timeslotRange")
    @ProblemFactCollectionProperty
    private List<Timeslot> timeslotList;

    // List of available rooms (problem facts)
    @ValueRangeProvider(id = "roomRange")
    @ProblemFactCollectionProperty
    private List<Room> roomList;

    // List of lessons to schedule (planning entities)
    @PlanningEntityCollectionProperty
    private List<Lesson> lessonList;

    // The score of the current solution
    @PlanningScore
    private HardSoftScore score;

    // Default constructor
    public Timetable() {}

    // Constructor to create a timetable with timeslots, rooms, and lessons
    public Timetable(List<Timeslot> timeslotList, List<Room> roomList, List<Lesson> lessonList) {
        this.timeslotList = timeslotList;
        this.roomList = roomList;
        this.lessonList = lessonList;
    }

    // Getter and setter for timeslotList
    public List<Timeslot> getTimeslotList() {
        return timeslotList;
    }
    public void setTimeslotList(List<Timeslot> timeslotList) {
        this.timeslotList = timeslotList;
    }

    // Getter and setter for roomList
    public List<Room> getRoomList() {
        return roomList;
    }
    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    // Getter and setter for lessonList
    public List<Lesson> getLessonList() {
        return lessonList;
    }
    public void setLessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    // Getter and setter for score
    public HardSoftScore getScore() {
        return score;
    }
    public void setScore(HardSoftScore score) {
        this.score = score;
    }
} 