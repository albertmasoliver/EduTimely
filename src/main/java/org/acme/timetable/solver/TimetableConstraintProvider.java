package org.acme.timetable.solver;

import org.acme.timetable.Lesson;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;

public class TimetableConstraintProvider implements ConstraintProvider {
    @Override
    public Constraint[] defineConstraints(ConstraintFactory factory) {
        return new Constraint[] {
            roomConflict(factory),
            allLessonsAssigned(factory)
        };
    }

    private Constraint roomConflict(ConstraintFactory factory) {
        return factory.from(Lesson.class)
                .filter(lesson -> lesson.getRoom() != null && lesson.getTimeslot() != null)
                .join(Lesson.class,
                        org.optaplanner.core.api.score.stream.Joiners.equal(Lesson::getRoom),
                        org.optaplanner.core.api.score.stream.Joiners.equal(Lesson::getTimeslot),
                        org.optaplanner.core.api.score.stream.Joiners.lessThan(Lesson::hashCode))
                .penalize("Room conflict", HardSoftScore.ONE_HARD);
    }

    private Constraint allLessonsAssigned(ConstraintFactory factory) {
        return factory.from(Lesson.class)
                .filter(lesson -> lesson.getRoom() == null || lesson.getTimeslot() == null)
                .penalize("Unassigned lesson", HardSoftScore.ONE_SOFT);
    }
} 