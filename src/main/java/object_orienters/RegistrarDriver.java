package object_orienters;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Hello world!
 *
 */
public class RegistrarDriver {
    public static void main(String[] args) throws IOException {
        Semester semester = new Semester(LocalDate.of(2020, 9, 1), LocalDate.of(2020, 12, 15), null, null, null);
        System.out.println(semester.getSemesterStartDate() +" " + semester.getSemesterEndDate());



        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("students.txt")));
        // List<Student> students = new ArrayList<>();
        // String line;

        // Department chemistryDepartment = new Department("Chemistry", new Faculty("Science", null));
                
        // while ((line = br.readLine()) != null) {
        //     StringTokenizer st = new StringTokenizer(line, ",");
        //     int id = Integer.parseInt(st.nextToken());
        //     String name = st.nextToken();
        //     String email = st.nextToken();

        //     Student student = new Student(id,email, name, chemistryDepartment);
        //     students.add(student);
        // }
        // br.close();
        // students.stream().forEach(e -> System.out.println(e));





//        Teacher teacher1 = new Teacher(1, "Dr. Smith", null, "smith@example.com");
//        Teacher teacher2 = new Teacher(2, "Dr. Jones", null, "jones@example.com");
//
//        Course course1 = new Course("Calculus", teacher1, "Math", null, 3, null,
//                Arrays.asList(new WeeklyMeeting(DayOfWeek.MONDAY, Duration.ofMinutes(90), "101", LocalTime.of(8, 0)),
//                        new WeeklyMeeting(DayOfWeek.WEDNESDAY, Duration.ofMinutes(90), "101", LocalTime.of(8, 0))));
//
//        Course course2 = new Course("Physics", teacher2, "Science", null, 4, null,
//                Arrays.asList(new WeeklyMeeting(DayOfWeek.TUESDAY, Duration.ofMinutes(60), "102", LocalTime.of(9, 30)),
//                        new WeeklyMeeting(DayOfWeek.THURSDAY, Duration.ofMinutes(60), "102", LocalTime.of(9, 30))));
//
//        List<Course> courses = Arrays.asList(course1, course2);
//        Schedule schedule = new Schedule(courses);

        // Create teachers
        Teacher teacher1 = new Teacher(101, "Alice Johnson", new Department("Computer Science"), "alice.johnson@university.edu");
        Teacher teacher2 = new Teacher(102, "Bob Smith", new Department("Mathematics"), "bob.smith@university.edu");

        // Create courses with weekly meetings
        Course course1 = new Course("Introduction to Programming", teacher1, 3,
                Arrays.asList(new WeeklyMeeting(DayOfWeek.MONDAY, Duration.ofHours(2), "101", LocalTime.of(10, 0)),
                        new WeeklyMeeting(DayOfWeek.THURSDAY, Duration.ofHours(2), "101", LocalTime.of(10, 0))));

        Course course2 = new Course("Calculus I", teacher2, 3,
                Arrays.asList(new WeeklyMeeting(DayOfWeek.TUESDAY, Duration.ofHours(1), "202", LocalTime.of(9, 30)),
                        new WeeklyMeeting(DayOfWeek.FRIDAY, Duration.ofHours(1), "202", LocalTime.of(9, 30))));

        // Create a list of courses
        List<Course> courses = Arrays.asList(course1, course2);

        // Create a schedule with these courses
        Schedule schedule = new Schedule(courses);

        // Output the schedule
        System.out.println(schedule.displaySchedule());
    }
}
