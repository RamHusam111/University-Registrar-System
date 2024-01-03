package object_orienters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class RegistrarDriver extends Thread {

    public static Runnable Action1 = () -> {
        System.out.println("Hello World! its Action 1");
    };

    public static Map<String, Semester> semesters = new HashMap<>();
    public static Map<Integer, Student> students = new HashMap<>();
    public static Map<Integer, Teacher> teachers = new HashMap<>();
    public static Map<String, Course> courses = new HashMap<>();
    public static List<WeeklyMeeting> weeklyMeetings = new ArrayList<>();
    public static Map<String, Specialization> specializations = new HashMap<>();
    public static Map<String, Faculty> faculties = new HashMap<>();

    public static void main(String[] args) throws InterruptedException, IOException {

        
        
        ////////////////////////////////
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int input = 1;

        while (input != 0) {
            System.out.println("Choose what you want to do: ");
            System.out.println(
                    "1 - Create New Semester \n2 - Add New Student \n3 - Add New Teacher \n4 - Add New Course \n5 - View Course Prerequisites \n6 - Register \n7 - View Registered Students \n8 - View Registered Teachers \n9 - View Available Courses \n0 - Exit");

            try {
                input = Integer.parseInt(in.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 0 and 9");
                continue;
            }

            Task t = TaskSwitcher.get(input);

            t.start();
            t.join();

        }

        in.close();

    }

    public void readFiles() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/WeeklyMeetings.csv"));
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] values = line.split(",");
                DayOfWeek day = DayOfWeek.valueOf(values[0].toUpperCase());
                Duration duration = Duration.ofMinutes(Long.parseLong(values[1]));
                String room = values[2];
                LocalTime hour = LocalTime.parse(values[3]);
                WeeklyMeeting wm = new WeeklyMeeting(day, duration, room, hour);
                weeklyMeetings.add(wm);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            bufferedReader = new BufferedReader(new FileReader("src/main/resources/Faculties.csv"));
            line = bufferedReader.readLine();
            while (line != null) {
                Faculty faculty = new Faculty(line);
                faculties.put(faculty.getName(), faculty);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            bufferedReader = new BufferedReader(new FileReader("src/main/resources/SemesterDates.csv"));
            line = bufferedReader.readLine();
            while (line != null) {
                String[] values = line.split(",");
                Semester semester = new Semester(LocalDate.parse(values[0].trim()), LocalDate.parse(values[1].trim()));
                semesters.put(semester.getSemesterName(), semester);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            bufferedReader = new BufferedReader(new FileReader("src/main/resources/students.csv"));
            line = bufferedReader.readLine();
            while (line != null) {
                String[] values = line.split(",");
                Specialization spec = new Specialization(values[1], faculties.get(values[2]),
                        Specialization.Type.valueOf(values[3]));
                specializations.put(spec.getName(), spec);
                Student student = new Student(values[0], spec);
                students.put(student.getId(), student);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            bufferedReader = new BufferedReader(new FileReader("src/main/resources/Teachers.csv"));
            line = bufferedReader.readLine();
            while (line != null) {
                String[] values = line.split(",");
                Teacher teacher = new Teacher(values[0], specializations.get(values[1]));
                teachers.put(teacher.getId(), teacher);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            bufferedReader = new BufferedReader(new FileReader("src/main/resources/Courses.csv"));
            line = bufferedReader.readLine();
            int courseCount = 0;
            while (line != null) {
                String[] values = line.split(",");
                int startIndex = courseCount * 3;
                List<WeeklyMeeting> courseMeetings = IntStream.range(startIndex, startIndex + 3)
                        .mapToObj(index -> weeklyMeetings.get(index))
                        .collect(Collectors.toList());
                Course course = new Course(values[0], values[1], faculties.get(values[2]),
                        Integer.parseInt(values[3]), courseMeetings, Integer.parseInt(values[4]));
                courses.put(course.getCourseID(), course);
                line = bufferedReader.readLine();
                courseCount++;
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
