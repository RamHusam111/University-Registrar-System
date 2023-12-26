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