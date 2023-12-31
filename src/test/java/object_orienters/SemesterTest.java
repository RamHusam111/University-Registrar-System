package object_orienters;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class SemesterTest {
    private LocalDate start;
    private LocalDate end;
    private List<Semester> semesters;
    private Semester semester;


    @Before
    public void setUp() throws IOException {
        String filePath = "src/main/resources/SemestersDates.csv";
        semesters = Files.lines(Paths.get(filePath))
                .skip(1) // Skipping the header row
                .map(line -> line.split(","))
                .map(parts -> new Semester(
                        LocalDate.parse(parts[0].trim()),
                        LocalDate.parse(parts[1].trim())
                ))
                .collect(Collectors.toList());
        semester = semesters.get(0);
        start = LocalDate.of(2023, 9, 1);
        end = LocalDate.of(2023, 12, 31);

    }

    @Test
    public void testGiveName() {
        assertEquals("Fall", semester.giveName());
    }

    @Test
    public void testCalculateWeeksBetween() {

        long expectedWeeks = 17;
        assertEquals(expectedWeeks, Semester.calculateWeeksBetween(semester.getSemesterStartDate(), semester.getSemesterEndDate()));
    }

    @Test
    public void testSemesterInitialization() {
        assertEquals("Fall - 2023", semester.getSemesterName());
        assertEquals(17, semester.getWeeksNumber());
        assertEquals(start, semester.getSemesterStartDate());
        assertEquals(end, semester.getSemesterEndDate());
    }


}