package object_orienters;

import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Student extends Person {
    private int creditLooad;
    private String major;
    private Optional<String> minor;
    private Year yearEnrolled;
    private boolean isCurrentlyRegisterd;
    private int load;
    private Map<Course, Double> completedCoursesGrades;
    private double gpa;

    public Student(int id, String name, String email, Schedule schedule, int creditLooad) {
        super(id, name, email);
        this.schedule = schedule;
        this.creditLooad = creditLooad;
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

    public int getLoad() {
        return load;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public int getCreditLooad() {
        return creditLooad;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void setCreditLooad(int creditLooad) {
        this.creditLooad = creditLooad;
    }

    @Override
    public String toString() {
        return "Student{" +
                "schedule=" + schedule +
                ", creditLooad=" + creditLooad +
                '}';
    }
}
