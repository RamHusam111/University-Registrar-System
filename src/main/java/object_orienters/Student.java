package object_orienters;

import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Student extends Person {
    private String major;
    private Optional<String> minor;
    private Year yearEnrolled;
    private final boolean isCurrentlyRegisterd;
    private Map<Course, Double> completedCoursesGrades;
    private double gpa;

    public Student(int id, String name, String email) {
        super(id, name, email);
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

    @Override
    public String toString() {
        return super.toString() + " major=" + major + ", minor=" + minor + ", yearEnrolled=" + yearEnrolled
                + ", isCurrentlyRegisterd=" + isCurrentlyRegisterd + ", completedCoursesGrades="
                + completedCoursesGrades + ", gpa=" + gpa + "]";
    }
}
