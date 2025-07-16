package org.acme.timetable.solver;

import org.acme.timetable.Lesson;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;

// Provides constraints for the timetable planning problem using OptaPlanner's Constraint Streams API.
public class TimetableConstraintProvider implements ConstraintProvider {
    // Defines the constraints to be used by the solver
    @Override
    public Constraint[] defineConstraints(ConstraintFactory factory) {
        return new Constraint[] {
            // Hard constraints
            roomConflict(factory),
            teacherConflict(factory),
            studentGroupConflict(factory),
            // Soft constraints not implemented yet
        };
    }

    // Hard constraint: penalize if two lessons are assigned to the same room and timeslot
    private Constraint roomConflict(ConstraintFactory factory) {
        // A room can accomodate at most one lesson at the same time

        // Select a lesson
        return factory
        .forEach(Lesson.class)
        .join(Lesson.class,            
            Joiners.equal(Lesson::getTimeslot),
            Joiners.equal(Lesson::getRoom),
            Joiners.lessThan(Lesson::getId))
        .penalize(HardSoftScore.ONE_HARD)
        .asConstraint("Room conflict");
    }

    private Constraint teacherConflict(ConstraintFactory factory) {
        return factory
        .forEach(Lesson.class)
        .join(Lesson.class,
            Joiners.equal(Lesson::getTimeslot),
            Joiners.equal(Lesson::getTeacher),
            Joiners.lessThan(Lesson::getId))
        .penalize(HardSoftScore.ONE_HARD)
        .asConstraint("Teacher conflict");
    }
    
    private Constraint studentGroupConflict(ConstraintFactory factory) {
        return factory
        .forEach(Lesson.class)
        .join(Lesson.class,
            Joiners.equal(Lesson::getTimeslot),
            Joiners.equal(Lesson::getStudentGroup),
            Joiners.lessThan(Lesson::getId))
            .penalize(HardSoftScore.ONE_HARD)
            .asConstraint("Student group conflict");
    }

} 