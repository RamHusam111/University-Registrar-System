package object_orienters;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
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

}
