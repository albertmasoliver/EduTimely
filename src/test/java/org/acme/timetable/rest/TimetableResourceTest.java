package org.acme.timetable.rest;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@QuarkusTest
class TimetableResourceTest {
    @Test
    void testSolveEndpoint() {
        // Prepare a minimal Timetable JSON
        String timetableJson = """
        {
          \"timeslotList\": [
            {\"dayOfWeek\": \"MONDAY\", \"startTime\": \"09:00\", \"endTime\": \"10:00\"},
            {\"dayOfWeek\": \"MONDAY\", \"startTime\": \"10:00\", \"endTime\": \"11:00\"}
          ],
          \"roomList\": [
            {\"name\": \"Room A\"},
            {\"name\": \"Room B\"}
          ],
          \"lessonList\": [
            {\"subject\": \"Math\", \"teacher\": \"A\", \"studentGroup\": \"1\"},
            {\"subject\": \"English\", \"teacher\": \"B\", \"studentGroup\": \"2\"}
          ]
        }
        """;
        given()
            .contentType("application/json")
            .body(timetableJson)
        .when()
            .post("/timetable")
        .then()
            .statusCode(200)
            .body("lessonList.timeslot", everyItem(notNullValue()))
            .body("lessonList.room", everyItem(notNullValue()));
    }

    @Test
    void testWeeklyPhysicsUniversitySchedule() {
        // Example: 5 days, 4 timeslots per day, 3 rooms, 6 subjects, 3 groups, 6 professors
        String timetableJson = """
        {
          \"timeslotList\": [
            {\"dayOfWeek\": \"MONDAY\", \"startTime\": \"08:00\", \"endTime\": \"09:00\"},
            {\"dayOfWeek\": \"MONDAY\", \"startTime\": \"09:00\", \"endTime\": \"10:00\"},
            {\"dayOfWeek\": \"MONDAY\", \"startTime\": \"10:00\", \"endTime\": \"11:00\"},
            {\"dayOfWeek\": \"MONDAY\", \"startTime\": \"11:00\", \"endTime\": \"12:00\"},
            {\"dayOfWeek\": \"TUESDAY\", \"startTime\": \"08:00\", \"endTime\": \"09:00\"},
            {\"dayOfWeek\": \"TUESDAY\", \"startTime\": \"09:00\", \"endTime\": \"10:00\"},
            {\"dayOfWeek\": \"TUESDAY\", \"startTime\": \"10:00\", \"endTime\": \"11:00\"},
            {\"dayOfWeek\": \"TUESDAY\", \"startTime\": \"11:00\", \"endTime\": \"12:00\"},
            {\"dayOfWeek\": \"WEDNESDAY\", \"startTime\": \"08:00\", \"endTime\": \"09:00\"},
            {\"dayOfWeek\": \"WEDNESDAY\", \"startTime\": \"09:00\", \"endTime\": \"10:00\"},
            {\"dayOfWeek\": \"WEDNESDAY\", \"startTime\": \"10:00\", \"endTime\": \"11:00\"},
            {\"dayOfWeek\": \"WEDNESDAY\", \"startTime\": \"11:00\", \"endTime\": \"12:00\"},
            {\"dayOfWeek\": \"THURSDAY\", \"startTime\": \"08:00\", \"endTime\": \"09:00\"},
            {\"dayOfWeek\": \"THURSDAY\", \"startTime\": \"09:00\", \"endTime\": \"10:00\"},
            {\"dayOfWeek\": \"THURSDAY\", \"startTime\": \"10:00\", \"endTime\": \"11:00\"},
            {\"dayOfWeek\": \"THURSDAY\", \"startTime\": \"11:00\", \"endTime\": \"12:00\"},
            {\"dayOfWeek\": \"FRIDAY\", \"startTime\": \"08:00\", \"endTime\": \"09:00\"},
            {\"dayOfWeek\": \"FRIDAY\", \"startTime\": \"09:00\", \"endTime\": \"10:00\"},
            {\"dayOfWeek\": \"FRIDAY\", \"startTime\": \"10:00\", \"endTime\": \"11:00\"},
            {\"dayOfWeek\": \"FRIDAY\", \"startTime\": \"11:00\", \"endTime\": \"12:00\"}
          ],
          \"roomList\": [
            {\"name\": \"Aula Magna\"},
            {\"name\": \"Lab Física\"},
            {\"name\": \"Aula 101\"}
          ],
          \"lessonList\": [
            {\"subject\": \"Física I\", \"teacher\": \"Dr. Feynman\", \"studentGroup\": \"Grup 1\"},
            {\"subject\": \"Física I\", \"teacher\": \"Dr. Feynman\", \"studentGroup\": \"Grup 2\"},
            {\"subject\": \"Física II\", \"teacher\": \"Dr. Einstein\", \"studentGroup\": \"Grup 1\"},
            {\"subject\": \"Física II\", \"teacher\": \"Dr. Einstein\", \"studentGroup\": \"Grup 2\"},
            {\"subject\": \"Matemàtiques\", \"teacher\": \"Dr. Noether\", \"studentGroup\": \"Grup 1\"},
            {\"subject\": \"Matemàtiques\", \"teacher\": \"Dr. Noether\", \"studentGroup\": \"Grup 2\"},
            {\"subject\": \"Electromagnetisme\", \"teacher\": \"Dr. Maxwell\", \"studentGroup\": \"Grup 1\"},
            {\"subject\": \"Electromagnetisme\", \"teacher\": \"Dr. Maxwell\", \"studentGroup\": \"Grup 2\"},
            {\"subject\": \"Química\", \"teacher\": \"Dr. Curie\", \"studentGroup\": \"Grup 1\"},
            {\"subject\": \"Química\", \"teacher\": \"Dr. Curie\", \"studentGroup\": \"Grup 2\"},
            {\"subject\": \"Laboratori\", \"teacher\": \"Dr. Rutherford\", \"studentGroup\": \"Grup 1\"},
            {\"subject\": \"Laboratori\", \"teacher\": \"Dr. Rutherford\", \"studentGroup\": \"Grup 2\"}
          ]
        }
        """;
        given()
            .contentType("application/json")
            .body(timetableJson)
        .when()
            .post("/timetable")
        .then()
            .statusCode(200)
            .body("lessonList.timeslot", everyItem(notNullValue()))
            .body("lessonList.room", everyItem(notNullValue()));
    }
}