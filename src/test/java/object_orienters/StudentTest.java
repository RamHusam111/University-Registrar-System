package object_orienters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class StudentTest {

    List<Student> students = new ArrayList<>();
    List<Course> courses = new ArrayList<>();
    Semester sem;

    public void readFromFile() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/test/resources/students.csv"));
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] values = line.split(",");
                Faculty fac = new Faculty(values[1]);
                Specialization spec = new Specialization(values[2], fac, Specialization.Type.MAJOR);
                Student stu = new Student(values[0], spec);
                students.add(stu);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/test/resources/Courses.csv"));
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] values = line.split(",");
                Faculty fac = new Faculty(values[2]);
                int creditHours = Integer.parseInt(values[3]);
                DayOfWeek day = DayOfWeek.valueOf(values[4].toUpperCase());
                Duration duration = Duration.ofMinutes(Long.parseLong(values[5]));
                String room = values[6];
                LocalTime hour = LocalTime.parse(values[7]);
                Course course = new Course(values[0], values[1], fac, creditHours, List
                        .of(new WeeklyMeeting(day, duration, room, hour)));
                courses.add(course);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(students.size());
        System.out.println(courses.size());
    }

    @Test
    public void testEnterGradesMethod() {
        readFromFile();
        courses.get(1).addPrerequisites(courses.get(0));
        courses.get(2).addPrerequisites(courses.get(1));
        courses.get(3).addPrerequisites(courses.get(2));
        courses.get(4).addPrerequisites(courses.get(3));
        courses.get(5).addPrerequisites(courses.get(4));
        courses.get(6).addPrerequisites(courses.get(5));
        courses.get(7).addPrerequisites(courses.get(6));
        sem = new Semester(LocalDate.of(2023, 9, 1), LocalDate.of(2023, 12, 31));
        for (Course course : courses) {
            sem.registerInACourse(course, students,
                    new Teacher("null", new Specialization("null", new Faculty("null"), Specialization.Type.MAJOR)));
        }
        String[] grades = { "A", "B+", "B", "C+", "C", "D+", "D", "F" };
        ArrayList<String> list = new ArrayList<>(Arrays.asList(grades));
        int i = 0;
        for (Student stu : students) {
            for (Course course : courses) {
                if (i == 8)
                    break;
                stu.enterCourseGrade(course, list.get(i++));
            }
        }
        double numGrades[] = { 4, 3.5, 3, 2.5, 2, 1.5, 1, 0 };
        int j = 0;
        for (Course course : courses) {
            if (students.get(0).getCompletedCoursesGrades().get(course) != null) {
                assertEquals(numGrades[j++], students.get(0).getCompletedCoursesGrades().get(course));
            }
        }
    }

    @Test
    public void testpreRequisitesCheckMethod() {
        testEnterGradesMethod();

        int j = 0;
        for (int i = 0; i < students.size(); i++) {
            System.out.println("i = " + i);
            System.out.println(students.get(i).getCompletedCoursesGrades().keySet());
            System.out.println(students.get(i).getRegisteredCourses());
            assertTrue(students.get(i).preRequisitesCheck(courses.get(0)));
            assertTrue(students.get(i).preRequisitesCheck(courses.get(1)));
            assertTrue(students.get(i).preRequisitesCheck(courses.get(2)));
            assertTrue(students.get(i).preRequisitesCheck(courses.get(3)));
            assertTrue(students.get(i).preRequisitesCheck(courses.get(4)));
            assertTrue(students.get(i).preRequisitesCheck(courses.get(5)));
            assertTrue(students.get(i).preRequisitesCheck(courses.get(6)));
            assertTrue(students.get(i).preRequisitesCheck(courses.get(7)));
        }
    }

    @Test
    public void testCalculateGpaMethod() {
        readFromFile();

    }

}
