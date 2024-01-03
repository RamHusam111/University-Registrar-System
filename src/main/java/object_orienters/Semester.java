package object_orienters;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Semester {

    private String name; // name of the semester
    private String semesterName; // name + year
    private LocalDate semesterStartDate;
    private LocalDate semesterEndDate;
    private Set<Student> students;
    private Set<Teacher> teachers;
    private Set<Course> courses;
    private final long weeksNumber;
    private boolean isFall;
    private boolean isSpring;
    private boolean isSummer;

    public Semester(LocalDate semesterStartDate, LocalDate semesterEndDate) {

        this.semesterStartDate = semesterStartDate;
        this.semesterEndDate = semesterEndDate;
        this.name = giveName();
        this.semesterName = this.name + " - " + semesterStartDate.getYear();
        this.students = new HashSet<>();
        this.teachers = new HashSet<>();
        this.courses = new HashSet<>();
        this.isFall = this.name.equals("Fall");
        this.isSpring = this.name.equals("Spring");
        this.isSummer = this.name.equals("Summer");
        this.weeksNumber = calculateWeeksBetween(semesterStartDate, semesterEndDate);

    }

    // TODO: Test this method
    public void registerInACourse(Course course, List<Student> lStudents, Teacher teacher) {
        // Check for room conflict
        boolean roomConflict = courses.stream()
                .flatMap(e -> e.getWeeklyMeetings().stream())
                .anyMatch(wm -> course.getWeeklyMeetings().stream().anyMatch(wm2 -> wm2.hasRoomConflict(wm)));

        if (roomConflict) {
            System.out.println(
                    "Error registering " + course.getCourseName() + " because another course has conflict with room");
            return;
        }

        // Check if teacher is free
        if (!teacher.isFreeOn(course.getWeeklyMeetings())) {
            System.out.println("Error registering " + course.getCourseName()
                    + " because teacher has conflict with course Weekly Meetings");
            return;
        }

        // Check if prerequisites are met
        lStudents.stream().filter(e -> !e.preRequisitesCheck(course)).forEach(student -> {
            System.out.println("Prerequisites need to be completed for " + student.getId() + ": "
                    + student.getName() + "> to register in " + course.getCourseName());
        });

        // Check if student is free on weekly meetings
        lStudents.stream().filter(e -> !e.isFreeOn(course.getWeeklyMeetings())).forEach(student -> {
            System.out.println("Error registering " + student.getId() + " " + student.getName() + " in "
                    + course.getCourseName() + " because of conflict");
        });

        // Register students who meet all criteria
        List<Student> registeredStudents = lStudents.stream()
                .filter(student -> student.isFreeOn(course.getWeeklyMeetings()) && student.preRequisitesCheck(course))
                .collect(Collectors.toList());
        registeredStudents.stream().forEach(student -> {
            // Use enrollStudent method of Course class to add student
            course.enrollStudent(student);
            if (!course.isFull()) {
                student.addRegisteredCourse(course);
                System.out.println(
                        student.getId() + " " + student.getName() + " registered in " + course.getCourseName());
                courses.add(course);
                course.setTeacher(teacher);
                teacher.getRegisteredCourses().add(course);
                this.students.add(student);
                this.teachers.add(teacher);
            }
        });

        // Set up the course with the teacher and add to courses list

    }

    public String giveName() {
        int startMonth = semesterStartDate.getMonthValue();
        if ((startMonth >= 9 && startMonth <= 12)) {
            // Fall semester (September to December)
            this.setName("Fall");
        } else if ((startMonth >= 1 && startMonth <= 6)) {
            // Spring semester (January to May)
            this.setName("Spring");
        } else if ((startMonth >= 6 && startMonth <= 8)) {
            // Summer semester (June to August)
            this.setName("Summer");
        }

        return name;
    }

    // HELPER NETHOD
    private void setName(String name) {
        this.name = name;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public long getWeeksNumber() {
        return weeksNumber;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public Set<Course> getCourse() {
        return courses;
    }

    public LocalDate getSemesterStartDate() {
        return semesterStartDate;
    }

    public LocalDate getSemesterEndDate() {
        return semesterEndDate;
    }

    public boolean isFall() {
        return isFall;
    }

    public boolean isSpring() {
        return isSpring;
    }

    public boolean isSummer() {
        return isSummer;
    }

    static long calculateWeeksBetween(LocalDate startDate, LocalDate endDate) {
        // Calculate the number of weeks in the Semester
        return ChronoUnit.WEEKS.between(startDate, endDate);
    }

    @Override
    public String toString() {
        return "Semester: " + semesterName + "[from:" + semesterStartDate + ", to:" + semesterEndDate + "]";
    }

}
