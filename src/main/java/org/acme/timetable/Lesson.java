package org.acme.timetable;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

// This class represents a lesson to be scheduled, including subject, teacher, student group, timeslot, and room.
@PlanningEntity
public class Lesson {
    @PlanningId
    private Long id;
    // The subject of the lesson (e.g., Math, English)
    private String subject;
    // The teacher assigned to the lesson
    private String teacher;
    // The student group attending the lesson
    private String studentGroup;

    // The timeslot assigned to this lesson (planning variable)
    @PlanningVariable(valueRangeProviderRefs = {"timeslotRange"})
    private Timeslot timeslot;

    // The room assigned to this lesson (planning variable)
    @PlanningVariable(valueRangeProviderRefs = {"roomRange"})
    private Room room;

    // Default constructor required by OptaPlanner
    public Lesson() {}

    // Constructor to create a lesson with subject, teacher, and student group
    public Lesson(Long id, String subject, String teacher, String studentGroup) {
        this.id = id;
        this.subject = subject;
        this.teacher = teacher;
        this.studentGroup = studentGroup;
    }

    public Long getId() {
        return id;
    }

    // Getter for subject
    public String getSubject() {
        return subject;
    }


    // Getter for teacher
    public String getTeacher() {
        return teacher;
    }
    

    // Getter for student group
    public String getStudentGroup() {
        return studentGroup;
    }
    

    // Getter and setter for timeslot
    public Timeslot getTimeslot() {
        return timeslot;
    }
    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    // Getter and setter for room
    public Room getRoom() {
        return room;
    }
    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", teacher='" + teacher + '\'' +
                ", studentGroup='" + studentGroup + '\'' +
                ", timeslot=" + timeslot +
                ", room=" + room +
                '}';
    }
} 