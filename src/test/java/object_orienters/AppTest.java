package object_orienters;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

/**
 * Unit test for simple App.
 */
public class AppTest {
    List<WeeklyMeeting> weeklyMeetings = new ArrayList<>();
    List<Faculty> faculties = new ArrayList<>();
    List<Department> departments = new ArrayList<>();
    List<Student> students = new ArrayList<>();
    List<Teacher> teachers = new ArrayList<>();
    List<Person> people = new ArrayList<>();
    List<Course> courses = new ArrayList<>();

    @BeforeAll
    public void setUp() throws Exception {
        /*
         * object creations order according to class dependencies:
         * 1. WeeklyMeeting
         * 2. Faculty
         * 3. Department
         * 4. Teacher
         * 5. Course
         * 6. Schedule
         * 7. Student
         * 8. Semester
         * 
         * 
         * Angela
         */

         //Set up weekly meetings
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/WeeklyMeetings.csv"));
        String line = reader.readLine();
        while (line != null) {
            StringTokenizer tokenizer = new StringTokenizer(line, ",");
            while (tokenizer.hasMoreTokens()) {
                weeklyMeetings.add(new WeeklyMeeting(DayOfWeek.valueOf(tokenizer.nextToken()),
                        Duration.ofMinutes(Integer.parseInt(tokenizer.nextToken())), tokenizer.nextToken(),
                        LocalTime.parse(tokenizer.nextToken())));
            }
        }
        reader.close();

        //Set up faculties
        reader = new BufferedReader(new FileReader("src/test/resources/Faculties.csv"));
        line = reader.readLine();
        while (line != null) {
            StringTokenizer tokenizer = new StringTokenizer(line, ",");
            while (tokenizer.hasMoreTokens()) {
                faculties.add(new Faculty(tokenizer.nextToken(), new WeeklyMeeting(DayOfWeek.valueOf(tokenizer.nextToken()),
                        Duration.ofMinutes(Integer.parseInt(tokenizer.nextToken())), tokenizer.nextToken(),
                        LocalTime.parse(tokenizer.nextToken()))));
            }
        }
        reader.close();

        //Set up DEPARTMENTS
        reader = new BufferedReader(new FileReader("src/test/resources/Departments.csv"));
        line = reader.readLine();
        while (line != null) {
            StringTokenizer tokenizer = new StringTokenizer(line, ",");
            while (tokenizer.hasMoreTokens()) {
                departments.add(new Department(tokenizer.nextToken(), faculties.stream().filter(e -> e.getName().equals(tokenizer.nextToken())).findFirst().get()));
            }
        }
        reader.close();

        // BufferedReader reader = new BufferedReader(new
        // FileReader("src/test/resources/students.csv"));
        // String line = reader.readLine();
        // while (line != null) {
        // StringTokenizer tokenizer = new StringTokenizer(line, ",");
        // while (tokenizer.hasMoreTokens()) {
        // students.add(new Student(Integer.parseInt(tokenizer.nextToken()),
        // tokenizer.nextToken(),
        // tokenizer.nextToken(), null));
        // }

        // }
        // reader.close();

        // reader = new BufferedReader(new
        // FileReader("src/test/resources/teachers.csv"));
        // line = reader.readLine();
        // while (line != null) {
        // StringTokenizer tokenizer = new StringTokenizer(line, ",");
        // while (tokenizer.hasMoreTokens()) {

        // teachers.add(new Teacher(Integer.parseInt(tokenizer.nextToken()),
        // tokenizer.nextToken(), new Department(tokenizer.nextToken(), null),
        // Arrays.stream(tokenizer.nextToken().split(";")).map(e -> new Course(e, null,
        // 0, null)).collect(Collectors.toList()) , tokenizer.nextToken(),
        // Arrays.stream(tokenizer.nextToken().split(";")).map(e -> new Course(e, null,
        // 0, null)).collect(Collectors.toList())));
        // }

        // }
        // reader.close();

        // people.addAll(students);
        // people.addAll(teachers);
    }

    @Test
    public void testisPersonFreeOn() {

    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }
}
