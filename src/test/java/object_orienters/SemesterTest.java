package object_orienters;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class SemesterTest {
    private Semester semester;
    private LocalDate start;
    private LocalDate end;

    @Before
    public void setUp() {
        // Set up a sample semester
        start = LocalDate.of(2023, 9, 1);
        end = LocalDate.of(2023, 12, 31);
        semester = new Semester(start, end);
    }

    @Test
    public void testGiveName() {
        assertEquals("Fall", semester.giveName());
    }

    @Test
    public void testCalculateWeeksBetween() {

        long expectedWeeks = 17;
        assertEquals(expectedWeeks, Semester.calculateWeeksBetween(start, end));
    }

    @Test
    public void testSemesterInitialization() {
        assertEquals("Fall - 2023", semester.getSemesterName());
        assertEquals(17, semester.getWeeksNumber());
        assertEquals(start, semester.getSemesterStartDate());
        assertEquals(end, semester.getSemesterEndDate());
    }


}