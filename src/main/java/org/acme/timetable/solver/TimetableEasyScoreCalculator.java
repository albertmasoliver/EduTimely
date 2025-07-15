package org.acme.timetable.solver;

import org.acme.timetable.Lesson;
import org.acme.timetable.Timetable;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.calculator.EasyScoreCalculator;

import java.util.HashSet;
import java.util.Set;

public class TimetableEasyScoreCalculator implements EasyScoreCalculator<Timetable, HardSoftScore> {
    @Override
    public HardSoftScore calculateScore(Timetable timetable) {
        int hardScore = 0;
        int softScore = 0;
        Set<String> roomTimeslotSet = new HashSet<>();
        for (Lesson lesson : timetable.getLessonList()) {
            if (lesson.getRoom() != null && lesson.getTimeslot() != null) {
                String key = lesson.getRoom().getName() + "@" + lesson.getTimeslot().toString();
                if (!roomTimeslotSet.add(key)) {
                    hardScore--;
                }
            }
        }
        // Example soft constraint: prefer all lessons assigned
        for (Lesson lesson : timetable.getLessonList()) {
            if (lesson.getRoom() == null || lesson.getTimeslot() == null) {
                softScore--;
            }
        }
        return HardSoftScore.of(hardScore, softScore);
    }
} 