package object_orienters;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

//import org.junit.jupiter.api.BeforeAll;

public class SemesterTest {

        // @Test
        // public void testSemesterGiveName() {
        // List<Semester> validSemesters = new ArrayList<>();
        // List<Semester> invalidSemesters = new ArrayList<>();
        // Semester s1 = new Semester(LocalDate.of(2020, 9, 1), LocalDate.of(2020, 12,
        // 15), new ArrayList(),
        // new ArrayList(), new ArrayList());
        // Semester s2 = new Semester(LocalDate.of(2021, 2, 1), LocalDate.of(2021, 6,
        // 15), new ArrayList(),
        // new ArrayList(), new ArrayList());
        // Semester s3 = new Semester(LocalDate.of(2021, 7, 1), LocalDate.of(2021, 8,
        // 15), new ArrayList(),
        // new ArrayList(), new ArrayList());
        // validSemesters.add(s1);
        // validSemesters.add(s2);
        // validSemesters.add(s3);

        // invalidSemesters.add(new Semester(LocalDate.of(2020, 9, 1),
        // LocalDate.of(2019, 12, 15), null, null, null));
        // // invalidSemesters.add(new Semester(LocalDate.of(2021, 2, 1),
        // // LocalDate.of(2021, 1, 15), null, null, null));
        // // setUp();
        // validSemesters.add(new Semester(LocalDate.of(2020, 9, 1), LocalDate.of(2020,
        // 12, 15), new ArrayList(),
        // new ArrayList(), new ArrayList()));
        // validSemesters.add(new Semester(LocalDate.of(2021, 2, 1), LocalDate.of(2021,
        // 6, 15), new ArrayList(),
        // new ArrayList(), new ArrayList()));
        // validSemesters.add(new Semester(LocalDate.of(2021, 7, 1), LocalDate.of(2021,
        // 8, 15), new ArrayList(),
        // new ArrayList(), new ArrayList()));

        // assertAll("Semester Name",
        // () -> assertEquals("Fall 2020", validSemesters.get(0).giveName()),
        // () -> assertEquals("Spring 2021", validSemesters.get(1).giveName()),
        // () -> assertEquals("Summer 2021", validSemesters.get(2).giveName()));
        // }

    @Test
    public void testSemesterGiveName() {
        List<Semester> validSemesters = new ArrayList<>();
        validSemesters.add(new Semester(LocalDate.of(2020, 9, 1), LocalDate.of(2020, 12, 15), new ArrayList(),
                new ArrayList(), new ArrayList()));
        validSemesters.add(new Semester(LocalDate.of(2021, 2, 1), LocalDate.of(2021, 6, 15), new ArrayList(),
                new ArrayList(), new ArrayList()));
        validSemesters.add(new Semester(LocalDate.of(2021, 7, 1), LocalDate.of(2021, 8, 15), new ArrayList(),
                new ArrayList(), new ArrayList()));

        assertAll("Semester Name",
                () -> assertEquals("Fall 2020", validSemesters.get(0).giveName()),
                () -> assertEquals("Spring 2021", validSemesters.get(1).giveName()),
                () -> assertEquals("Summer 2021", validSemesters.get(2).giveName()));
    }

        @Test // TODO: note I don't think list of students should be in consutructor;
        public void testRegisterInACourse() {
                Semester semester = new Semester(LocalDate.of(2020, 9, 1), LocalDate.of(2020, 12, 15), new ArrayList(),
                                new ArrayList(), new ArrayList());
                Department dep = new Department("null", new Faculty("null", new WeeklyMeeting(DayOfWeek.FRIDAY,
                                Duration.ofMinutes(60), "null", LocalTime.of(0, 0, 0))));
                Teacher t = new Teacher(0, "Jhon doe",
                                dep,
                                "null");
                Course course = new Course("Math", t, 3, new ArrayList<>());

                Student stu1 = new Student(1, "Abd", "null", dep);
                Student stu2 = new Student(1, "Ali", "null", dep);
                Student stu3 = new Student(1, "Omar", "null", dep);

                semester.registerInACourse(course, List.of(stu1, stu2, stu3));
               
                assertTrue(semester.getCourse().contains(course) && semester.getStudents().contains(stu1)
                                && semester.getStudents().contains(stu2) && semester.getStudents().contains(stu3));
        }

        @Test // TODO: note I don't think list of students should be in consutructor;
        public void testRegisterInACourse() {
                Semester semester = new Semester(LocalDate.of(2020, 9, 1), LocalDate.of(2020, 12, 15), new ArrayList(),
                                new ArrayList(), new ArrayList());
                Department dep = new Department("null", new Faculty("null", new WeeklyMeeting(DayOfWeek.FRIDAY,
                                Duration.ofMinutes(60), "null", LocalTime.of(0, 0, 0))));
                Teacher t = new Teacher(0, "Jhon doe",
                                dep,
                                "null");
                Course course = new Course("Math", t, 3, new ArrayList<>());

                Student stu1 = new Student(1, "Abd", "null", dep);
                Student stu2 = new Student(1, "Ali", "null", dep);
                Student stu3 = new Student(1, "Omar", "null", dep);

                semester.registerInACourse(course, List.of(stu1, stu2, stu3));
               
                assertTrue(semester.getCourse().contains(course) && semester.getStudents().contains(stu1)
                                && semester.getStudents().contains(stu2) && semester.getStudents().contains(stu3));
        }

}
