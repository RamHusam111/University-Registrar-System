package object_orienters;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
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

//    @Test
//    public void testSemesterGiveName() {
//        List<Semester> validSemesters = new ArrayList<>();
//        validSemesters.add(new Semester(LocalDate.of(2020, 9, 1), LocalDate.of(2020, 12, 15), new ArrayList(),
//                new ArrayList(), new ArrayList()));
//        validSemesters.add(new Semester(LocalDate.of(2021, 2, 1), LocalDate.of(2021, 6, 15), new ArrayList(),
//                new ArrayList(), new ArrayList()));
//        validSemesters.add(new Semester(LocalDate.of(2021, 7, 1), LocalDate.of(2021, 8, 15), new ArrayList(),
//                new ArrayList(), new ArrayList()));
//
//        assertAll("Semester Name",
//                () -> assertEquals("Fall 2020", validSemesters.get(0).giveName()),
//                () -> assertEquals("Spring 2021", validSemesters.get(1).giveName()),
//                () -> assertEquals("Summer 2021", validSemesters.get(2).giveName()));
//    }





    private Semester semester;
    private LocalDate start;
    private LocalDate end;
    private Course course1, course2;
    private Student student1, student2;

    @Before
    public void setUp() {
        // Set up a sample semester
        start = LocalDate.of(2023, 9, 1); // September 1, 2023
        end = LocalDate.of(2023, 12, 31); // December 31, 2023
        semester = new Semester(start, end, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    }

    @Test
    public void testGiveName() {
        // Test that the semester name is correctly determined
        String expectedName = "Fall";
        assertEquals(expectedName, semester.giveName());
    }

    @Test
    public void testCalculateWeeksBetween() {
        // Test the correct calculation of weeks between two dates
        long expectedWeeks = 17; // Total weeks between start and end dates
        assertEquals(expectedWeeks, Semester.calculateWeeksBetween(start, end));
    }

    @Test
    public void testSemesterInitialization() {
        // Test that the semester is correctly initialized
        assertEquals("Fall - 2023", semester.getSemesterName());
        assertEquals(17, semester.getWeeksNumber());
        assertEquals(start, semester.getSemesterStartDate());
        assertEquals(end, semester.getSemesterEndDate());
    }

}
