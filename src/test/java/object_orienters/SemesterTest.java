package object_orienters;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SemesterTest {
    private LocalDate start;
    private LocalDate end;
    private List<Semester> semesters;
    private Semester semester;

    @Before
    public void setUp() throws IOException {
        String filePath = "src/test/resources/SemestersDates.csv";
        semesters = Files.lines(Paths.get(filePath))
                .skip(1) // Skipping the header row
                .map(line -> line.split(","))
                .map(parts -> new Semester(
                        LocalDate.parse(parts[0].trim()),
                        LocalDate.parse(parts[1].trim())))
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
        assertEquals(expectedWeeks,
                Semester.calculateWeeksBetween(semester.getSemesterStartDate(), semester.getSemesterEndDate()));
    }
    @Test
    public void testSemesterInitialization() {
        assertEquals("Fall - 2023", semester.getSemesterName());
        assertEquals(17, semester.getWeeksNumber());
        assertEquals(start, semester.getSemesterStartDate());
        assertEquals(end, semester.getSemesterEndDate());
    }
    @Test
    public void testRegisterWithRoomConflict() throws IOException {
        List<WeeklyMeeting> weeklyMeetings = new ArrayList<>();
        List<Course> courses = new ArrayList<>();

        int offset = 8;
        BufferedReader brwm = new BufferedReader(new FileReader("src/test/resources/WeeklyMeetings.csv"));
        String line = brwm.readLine();
        while (line != null) {
            String[] attributes = line.split(",");
            DayOfWeek day = DayOfWeek.valueOf(attributes[0].toUpperCase());
            Duration duration = Duration.ofMinutes(Long.parseLong(attributes[1]));
            LocalTime hour = LocalTime.parse(attributes[3]);
            WeeklyMeeting wm = new WeeklyMeeting(day, duration, "M-101", hour);
            weeklyMeetings.add(wm);
            line = brwm.readLine();
        }
        brwm.close();

        BufferedReader br = new BufferedReader(new FileReader("src/test/resources/Courses.csv"));
        line = br.readLine();
        int i = 0;
        while (line != null) {
            String[] attributes = line.split(",");
            String code = (attributes[0].toUpperCase());
            String name = attributes[1];
            Faculty faculty = new Faculty(attributes[2]);
            Course c = new Course(code, name, faculty, 2,
                    List.of(weeklyMeetings.get(offset * i++), weeklyMeetings.get(offset * i++)), 50);
            courses.add(c);
            line = br.readLine();
        }
        br.close();

        i = 0;
        for (Course course : courses) {
            Specialization spec = new Specialization("maths", new Faculty("Science"), Specialization.Type.MAJOR);
            semester.registerInACourse(course, List.of(new Student("Jhon", spec)), new Teacher("AahmD", spec));
            assertTrue(semester.getTeachers().size() == 1 && semester.getStudents().size() == 1
                    && semester.getCourse().size() == 1);
        }

    }

}