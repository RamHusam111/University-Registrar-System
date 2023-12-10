package object_orienters;

import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Student extends Person {
    private int creditLoad;
    private String major;
    private Optional<String> minor;
    private Year yearEnrolled;
    private boolean isCurrentlyRegisterd;
    private Map<Course, Double> completedCoursesGrades;
    private double gpa;

    public Student(int id, String name, String email, Schedule schedule, int creditLoad,
            List<Course> registeredCourses) {
        super(id, name, email, registeredCourses);
        this.schedule = schedule;
        this.creditLoad = creditLoad;
        isCurrentlyRegisterd = true;
    }

    public void enterGrades(List<Character> grades) {
        // TODO: implement this method
    }

    public String getMajor() {
        return major;
    }

    public String getMinor() {
        return minor.orElse("No minor");
    }

    public Year getYearEnrolled() {
        return yearEnrolled;
    }

    public boolean isCurrentlyRegisterd() {
        return isCurrentlyRegisterd;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public int getCreditLoad() {
        return creditLoad;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void setCreditLoad(int creditLoad) {
        this.creditLoad = creditLoad;
    }

    public boolean preRequisitesCheck(Course course) {
        List<Course> preRequisites = course.getpreRequisites();
        return preRequisites.stream().allMatch(e -> registeredCourses.contains(e)); // MUST BE COMPLETED COURSES (CREATE
                                                                                    // LIST OF COMPLETED COURSES IN STUDENT CLASS) NOT
                                                                                    // REGISTERED
    }

    @Override
    public String toString() {
        return "Student{" +
                "schedule=" + schedule +
                ", creditLooad=" + creditLoad +
                '}';
    }
}
