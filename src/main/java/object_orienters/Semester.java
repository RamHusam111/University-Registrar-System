package object_orienters;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Semester {

    private String name; // name of the semester
    private String semesterName; // name + year
    private LocalDate semesterStartDate;
    private LocalDate semesterEndDate;
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Course> courses;
    private final long weeksNumber;
    private boolean isFall;
    private boolean isSpring;
    private boolean isSummer;

    public Semester(LocalDate semesterStartDate, LocalDate semesterEndDate) {

        this.semesterStartDate = semesterStartDate;
        this.semesterEndDate = semesterEndDate;
        this.name = giveName();
        this.semesterName = this.name + " - " + semesterStartDate.getYear();
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.weeksNumber = calculateWeeksBetween(semesterStartDate, semesterEndDate);
        this.isFall = this.name.equals("Fall");
        this.isSpring = this.name.equals("Spring");
        this.isSummer = this.name.equals("Summer");
    }

    // TODO: Test this method
    public void registerInACourse(Course course, List<Student> lStudents, Teacher teacher) {
        // Check for room conflict
        boolean roomConflict = courses.stream()
                .flatMap(e -> e.getWeeklyMeetings().stream())
                .anyMatch(wm -> course.getWeeklyMeetings().stream().anyMatch(wm2 -> wm2.hasRoomConflict(wm)));

        if (roomConflict) {
            System.out.println("Error registering " + course.getCourseName() + " because another course has conflict with room");
            return;
        }

        // Check if teacher is free
        if (!teacher.isFreeOn(course.getWeeklyMeetings())) {
            System.out.println("Error registering " + course.getCourseName() + " because teacher has conflict with course Weekly Meetings");
            return;
        }

        // Check if prerequisites are met
        lStudents.stream().filter(e -> !e.preRequisitesCheck(course)).forEach(student -> {
            System.out.println("Prerequisites need to be completed for " + student.getId() + ": "
                    + student.getName() + "> to registered in " + course.getCourseName());
        });

        // Check if student is free on weekly meetings
        lStudents.stream().filter(e -> !e.isFreeOn(course.getWeeklyMeetings())).forEach(student -> {
            System.out.println("Error registering " + student.getId() + " " + student.getName() + " in "
                    + course.getCourseName() + " because of conflict");
        });

        // Check for course capacity
        long currentEnrollment = lStudents.stream()
                .filter(student -> student.getRegisteredCourses().contains(course))
                .count();

        if (currentEnrollment >= course.getCapacity()) {
            System.out.println("Error registering students in " + course.getCourseName() + " as the course is already full.");
            return;
        }

        // Register students who meet all criteria
        lStudents.stream()
                .filter(student -> student.isFreeOn(course.getWeeklyMeetings()) && student.preRequisitesCheck(course))
                .forEach(student -> {
                    student.getRegisteredCourses().add(course);
                    System.out.println(student.getId() + " " + student.getName() + " registered in " + course.getCourseName());
                });

        courses.add(course);
        course.setTeacher(teacher);
        teacher.getRegisteredCourses().add(course);
        this.students.addAll(students);
        this.teachers.add(teacher);
    }


    // TODO: Test this method
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

    //HELPER NETHOD
    private void setName(String name) {
        this.name = name;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public long getWeeksNumber() {
        return weeksNumber;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Course> getCourse() {
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

    // TODO: Test this method
    static long calculateWeeksBetween(LocalDate startDate, LocalDate endDate) {
        // Calculate the number of weeks in the Semester
        return ChronoUnit.WEEKS.between(startDate, endDate);
    }

}
