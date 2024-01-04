package object_orienters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskSwitcher {

    private static Faculty createFaculty(String facultyName) {
        Faculty faculty = RegistrarDriver.faculties.get(
                facultyName);
        if (faculty == null) {
            faculty = new Faculty(facultyName);
            RegistrarDriver.faculties.put(facultyName, faculty);
        }
        return faculty;
    }

    private static Specialization createMajorSpecialization(String specialization) {
        if (RegistrarDriver.specializations.get(specialization) != null) {
            return RegistrarDriver.specializations.get(specialization);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String facultyName = null;
        try {
            System.out.println("Enter Major's Faculty Name: ");
            facultyName = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Specialization spec = new Specialization(specialization, createFaculty(facultyName),
                Specialization.Type.MAJOR);
        RegistrarDriver.specializations.put(spec.getName(), spec);
        return spec;
    }

    private static Specialization createMinorSpecialization(String specialization) {
        if (RegistrarDriver.specializations.get(specialization) != null) {
            return RegistrarDriver.specializations.get(specialization);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String facultyName = null;
        try {
            System.out.println("Enter Minor's Faculty Name: ");
            facultyName = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Specialization spec = new Specialization(specialization, createFaculty(facultyName),
                Specialization.Type.MINOR);
        RegistrarDriver.specializations.put(spec.getName(), spec);
        return spec;
    }

    private static Student createStudent(Integer id) {
        Student student = RegistrarDriver.students.get(id);
        if (student != null)
            return student;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter Student Name: ");
        String name = null;
        try {
            name = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Enter Student Major: ");
        String majorString = null;
        try {
            majorString = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Enter Student Minor or Write (null) if not applicable: ");
        String minorString = null;
        try {
            minorString = br.readLine();
            if (minorString.equals("null")) {
                minorString = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Specialization major = createMajorSpecialization(majorString);
        Specialization minor = null;
        if (minorString != null)
            minor = createMinorSpecialization(minorString);

        System.out.println(RegistrarDriver.specializations.get(majorString));

        student = new Student(name, major, minor);
        RegistrarDriver.students.put(student.getId(), student);
        return student;
    }

    private static Teacher createTeacher(Integer id) {
        Teacher teacher = RegistrarDriver.teachers.get(id);
        if (teacher != null)
            return teacher;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Teacher Name: ");
        String name = null;
        try {
            name = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Enter Teacher's Specialization: ");
        String specString = null;
        try {
            specString = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        teacher = new Teacher(name,
                createMajorSpecialization(specString));
        RegistrarDriver.teachers.put(teacher.getId(), teacher);

        return teacher;

    }

    public static Semester createSemester() {
        System.out.println("Creating Semester: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LocalDate startDate = null;
        LocalDate endDate = null;
        while (startDate == null) {

            try {
                System.out.println("Enter Semester Start Date (dd/MM/yyyy);");
                startDate = LocalDate.parse(br.readLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter date in dd/MM/yyyy format");
            }
        }
        while (endDate == null) {

            try {
                System.out.println("Enter Semester End Date (dd/MM/yyyy):");
                endDate = LocalDate.parse(br.readLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter date in dd/MM/yyyy format");
            }
        }
        Semester semester = new Semester(startDate,
                endDate);
        RegistrarDriver.semesters.put(semester.getSemesterName(), semester);
        System.out.println("Semester" + semester + " created successfully");
        return semester;
    }

    private static List<WeeklyMeeting> createWeeklyMeeting() {
        List<WeeklyMeeting> weeklyMeetings = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        WeeklyMeeting wm = null;
        try {
            do {
                System.out.println("Creating Course Weekly Meetings: ");
                DayOfWeek day = null;
                while (day == null) {
                    try {
                        System.out.println("Enter Meeting Day: ");
                        day = DayOfWeek.valueOf(br.readLine().toUpperCase());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid day. Please enter a valid day");
                    }
                }

                LocalTime startTime = null;
                while (startTime == null) {
                    try {
                        System.out.println("Enter Meeting Start Time (HH:mm): ");
                        startTime = LocalTime.parse(br.readLine(), DateTimeFormatter.ofPattern("HH:mm"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid time format. Please enter time in HH:mm format");
                    }
                }

                Duration duration = null;
                while (duration == null) {
                    try {
                        System.out.println("Enter Meeting Duration (in minutes): ");
                        duration = Duration.ofMinutes(Long.parseLong(br.readLine()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number");
                    }
                }

                System.out.println("Enter Weekly Meeting Room: ");
                String room = null;
                try {
                    room = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                wm = new WeeklyMeeting(day, duration, room, startTime);
                weeklyMeetings.add(wm);
                System.out.println("Do you want to add another weekly meeting? (y/n)");

            } while (wm == null || br.readLine().toLowerCase().equals("y"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return weeklyMeetings;
    }

    private static Course creatCourse() {
        System.out.println("Creating Course Process: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Course Code: ");
        String courseID = null;
        try {
            courseID = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Optional<Course> course = Optional.ofNullable(RegistrarDriver.courses.get(courseID));
        if (course.isPresent()) {
            System.out.println("Course exists");
            return course.get();
        }

        System.out.println("Enter Course Name: ");
        String name = null;
        try {
            name = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Enter Course Specialization: ");
        String specString = null;
        try {
            specString = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Specialization specialization = createMajorSpecialization(specString);

        int creditHours = 0;
        while (creditHours == 0) {
            try {
                System.out.println("Enter Course Credit Hours Num: ");
                creditHours = Integer.parseInt(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number");
            }
        }

        int capacity = 0;
        while (capacity == 0) {
            try {
                System.out.println("Enter Course Student Capacity Num: ");
                capacity = Integer.parseInt(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number");
            }
        }

        List<WeeklyMeeting> weeklyMeetings = createWeeklyMeeting();

        Course c = new Course(courseID, name, specialization, creditHours, weeklyMeetings, capacity);
        RegistrarDriver.courses.put(c.getCourseID(), c);
        System.out.println("Course " + c + " created successfully");
        return c;

    }

    ////////////////////////////////////////////////////////////////////////////

    private static Runnable action5 = () -> {
        System.out.println("Action 5: View Course Prerequisites");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Course Code: ");
        try {

            final String courseID = br.readLine();
            Course course = RegistrarDriver.courses.values().stream().filter(c -> c.getCourseID().equals(courseID))
                    .findFirst().orElse(null);
            if (course == null) {
                System.out.println("Course not found");
            } else {
                System.out.println("Course Prerequisites:");
                course.getPrerequisites().stream().map(c -> c.toString()).reduce((c1, c2) -> c1 + "\n" + c2);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    private static Runnable action1 = () -> {
        System.out.println("Action 1: Create New Semester");
        createSemester();

    };

    private static Runnable action2 = () -> {
        System.out.println("Action 2: Create New Student");
        Student student = createStudent(0);
        System.out.println("Student " + student + " created successfully");
    };

    private static Runnable action3 = () -> {
        System.out.println("Action 3: Create New Teacher");
        Teacher teacher = createTeacher(0);
        System.out.println("Teacher " + teacher + " created successfully");
    };

    private static Runnable action4 = () -> {
        System.out.println("Action 3: Create New Course");
    };

    private static Runnable action7 = () -> {
        System.out.println("Action 7: Available Students (Possibliy Not Registered yet)");
        System.out.println(
                RegistrarDriver.students.values().stream().map(s -> s.getReport())
                        .reduce((s1, s2) -> s1 + "\n-----------------------------------\n" + s2).get());
    };

    private static Runnable action8 = () -> {
        System.out.println("Action 8: Available Teachers (Possibliy Not Registered yet)");
        System.out.println(
                RegistrarDriver.teachers.values().stream().map(t -> t.toString())
                        .reduce((t1, t2) -> t1 + "\n-----------------------------------\n" + t2).get());
    };

    private static Runnable action9 = () -> {
        System.out.println("Action 9: Available Courses (Possibliy Not Registered yet)");
        System.out.println(
                RegistrarDriver.courses.values().stream().map(c -> c.toString())
                        .reduce((c1, c2) -> c1 + "\n-----------------------------------\n" + c2).get());
    };

    private static Runnable action6 = () -> {
        System.out.println("Action 6: Register Students and a Teacher in a Course");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(
                "Enter Semester Name: \n(Or Enter 'show' to view available semesters, any key to create a new semester)");
        String semesterName = null;
        while (semesterName == null) {
            try {
                semesterName = br.readLine();
                if (semesterName.trim().toLowerCase().contains("show")) {
                    System.out.println("Available Semesters: ");
                    System.out.println(
                            RegistrarDriver.semesters.values().stream().map(s -> s.getSemesterName())
                                    .reduce((s1, s2) -> s1 + "\n-----------------------------------\n" + s2).get());
                    System.out.println(
                            "Enter Semester Name: \n(Or Enter 'show' to view available semesters, any key to create a new semester)");
                    semesterName = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String sName = semesterName;
        Semester semester = RegistrarDriver.semesters.values().stream()
                .filter(s -> s.getSemesterName().trim().toLowerCase().equals(sName.trim().toLowerCase()))
                .findAny().orElseGet(TaskSwitcher::createSemester);

        System.out.println("Enter Course Code: (Enter 'show' to view available courses) ");
        String courseID = null;
        while (courseID == null) {
            try {
                courseID = br.readLine();
                if (courseID.trim().toLowerCase().equals("show")) {
                    action9.run();
                    System.out.println("Enter Course Code: (Enter 'show' to view available courses) ");
                    courseID = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Course course = Optional.ofNullable(RegistrarDriver.courses.get(courseID)).orElseGet(() -> {
            System.out.println("Course not found.");
            return creatCourse();
        });

        System.out.println("Enter Teacher ID: (Enter 'show' to view available Teachers) ");
        int teacherID = 0;
        while (teacherID == 0) {
            try {
                String ans = br.readLine();
                if (ans.equals("show")) {
                    action8.run();
                    System.out.println("Enter Teacher ID: (Enter 'show' to view available Teachers) ");
                    teacherID = 0;
                } else {
                    teacherID = Integer.parseInt(ans);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number");
            }
        }

        Teacher teacher = createTeacher(teacherID);

        List<Student> students = new ArrayList<>();

        int studentID = 0;
        while (studentID == 0) {
            try {
                System.out.println("Enter Student ID: (Enter 'show' to view available Students or 'done' to finish)");
                String ans = br.readLine();
                if (ans.equals("done")) {
                    break;
                } else if (ans.equals("show")) {
                    action7.run();
                    studentID = 0;
                } else {
                    studentID = Integer.parseInt(ans);
                    Student student = createStudent(studentID);
                    System.out.println("Student " + student + " added successfully");
                    students.add(student);
                    studentID = 0;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number");
            }
        }

        System.out.println("Registered Students: \n\n" + students
                + "\n\n\nWith Teacher: \n\n" + teacher
                + "\n\n\nIn Course: \n\n" + course);
        semester.registerInACourse(course, students, teacher);

    };

    private static Runnable action10 = () -> {
        System.out.println("Action 10: add a prerequisite to a course");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Course Code: ");
        String courseID = null;
        try {
            courseID = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Optional<Course> course = Optional.ofNullable(RegistrarDriver.courses.get(courseID));
        if (course.isPresent()) {
            System.out.println("Enter Prerequisite Course Code: ");
            String prerequisiteID = null;
            try {
                prerequisiteID = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Course prerequisite = Optional.ofNullable(RegistrarDriver.courses.get(prerequisiteID))
                    .orElseGet(() -> {
                        System.out.println("Course not found.");
                        return creatCourse();
                    });

            course.get().addPrerequisites(prerequisite);
            System.out.println("Prerequisite " + prerequisite + " added successfully to " + course.get());

        } else {
            System.out.println("Course not found");
        }
    };

    private static Runnable action11 = () -> {
        System.out.println("Action 11: View all Semester Deatials");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(
                "Enter Semester Name: \n(Or Enter 'show' to view available semesters)");
        String semesterName = null;
        while (semesterName == null) {
            try {
                semesterName = br.readLine();
                if (semesterName.trim().toLowerCase().contains("show")) {
                    System.out.println("Available Semesters: ");
                    System.out.println(
                            RegistrarDriver.semesters.values().stream().map(s -> s.getSemesterName())
                                    .reduce((s1, s2) -> s1 + "\n-----------------------------------\n" + s2).get());
                    System.out.println(
                            "Enter Semester Name: \n(Or Enter 'show' to view available semesters)");
                    semesterName = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String sName = semesterName;
        Optional<Semester> semesteropt = RegistrarDriver.semesters.values().stream()
                .filter(s -> s.getSemesterName().trim().toLowerCase().equals(sName.trim().toLowerCase()))
                .findAny();
        if (!semesteropt.isPresent()) {

            System.out.println("Semester not found");
        } else {
            Semester semester = semesteropt.get();

            System.out.println(semester);

            System.out.println("Details are as follows: ");
            System.out.println("Registered Courses: ");
            semester.getRegisteredCourses().stream()
                    .forEach((c1) -> System.out.println(c1 + "\n" + c1.getTeacher() + "\n" + c1.getEnrolledStudents()
                            + "\n-----------------------------------\n"));
        }

    };

    public static Task get(int input) {
        switch (input) {
            case 1:
                return new Task(input, action1);

            case 2:
                return new Task(input, action2);

            case 3:
                return new Task(input, action3);

            case 4:
                return new Task(input, action4);

            case 5:
                return new Task(input, action5);

            case 6:
                return new Task(input, action6);

            case 7:
                return new Task(input, action7);

            case 8:
                return new Task(input, action8);

            case 9:
                return new Task(input, action9);
            case 10:
                return new Task(input, action10);
            case 11:
                return new Task(input, action11);

            default:
                return new Task(input);
        }
    }
}