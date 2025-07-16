package org.acme.timetable.solver;

import org.acme.timetable.Lesson;
import org.acme.timetable.Timetable;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.calculator.EasyScoreCalculator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Calculates the score for a timetable solution using a simple scoring algorithm.
public class TimetableEasyScoreCalculator implements EasyScoreCalculator<Timetable, HardSoftScore> {
    // Calculates the hard and soft score for the given timetable
    @Override
    public HardSoftScore calculateScore(Timetable timetable) {
        List<Lesson> lessonList = timetable.getLessonList();
        int hardScore = 0;
        for (Lesson a : lessonList) {
            for (Lesson b : lessonList){
                if(a.getTimeslot() != null && a.getTimeslot().equals(b.getTimeslot()) && a.getId() < b.getId()){

                    // A room can accomodate at most one lesson at the same time
                    if (a.getRoom() != null && a.getRoom().equals(b.getRoom())){
                        hardScore--;
                    }

                    // A teacher can teach at most one lesson at the same time
                    if(a.getTeacher().equals(b.getTeacher())){
                        hardScore--;
                    }

                    // A student can attend at most one lesson at the same time
                    if (a.getStudentGroup().equals(b.getStudentGroup())){
                        hardScore--;
                    }                    
                }
            }
        }
        int softScore = 0;
        return HardSoftScore.of(hardScore, softScore);
    }
} 