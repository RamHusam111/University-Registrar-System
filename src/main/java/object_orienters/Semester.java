package object_orienters;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Semester {

    private String semesterName;
    private LocalDate semesterStartDate;
    private LocalDate semesterEndDate;
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Course> courses;

    private long weeksNumber;

    public Semester(String semesterName, LocalDate semesterStartDate, LocalDate semesterEndDate, List<Student> students,
            List<Teacher> teachers, List<Course> courses) {
        weeksNumber = calculateWeeksBetween(semesterStartDate, semesterEndDate);
        this.semesterName = semesterName;
        this.semesterStartDate = semesterStartDate;
        this.semesterEndDate = semesterEndDate;
        this.students = students;
        this.teachers = teachers;
        this.courses = courses;
    }

    public void registerInACourse(Course course, List<Student> students) {

        // TODO: check for prequisites after implemtning the method
        // students.stream().filter(e -> ).forEach(student ->{
        // System.out.println("Prequisites need to be completed for "+student.getId() +"
        // " + student.getName() + " to registered in " + course.getCourseName());
        // });

        students.stream().filter(e -> !e.isFreeOn(course.getWeeklyMeetings())).forEach(student -> {
            System.out.println("Error registering " + student.getId() + " " + student.getName() + " in "
                    + course.getCourseName() + " because of conflict");
        });

        students.stream().filter(e -> e.isFreeOn(course.getWeeklyMeetings())).forEach(student -> {
            student.getRegisteredCourses().add(course);
            System.out.println(student.getId() + " " + student.getName() + " registered in " + course.getCourseName());
        });

    }

    public void setSemesterStartDate(LocalDate semesterStartDate) {
        this.semesterStartDate = semesterStartDate;
    }

    public void setSemesterEndDate(LocalDate semesterEndDate) {
        this.semesterEndDate = semesterEndDate;
    }

    public void setWeeksNumber(long weeksNumber) {
        this.weeksNumber = weeksNumber;
    }

    public String getSemesterName() {
        return semesterName + " - " + semesterStartDate.getYear();
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

    private static long calculateWeeksBetween(LocalDate startDate, LocalDate endDate) {
        // Calculate the number of weeks in the semester
        return ChronoUnit.WEEKS.between(startDate, endDate);
    }

}
