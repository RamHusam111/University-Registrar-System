package object_orienters;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Student extends Person {
    private String major;
    private Optional<String> minor;
    private Year yearEnrolled;
    private boolean isCurrentlyRegisterd;
    private Map<Course, Double> completedCoursesGrades;
    private double gpa;
    private GPAstatus statusGPA;
    private String stuLevel;

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
        return minor.orElse("No Minor");
    }

    public Year getYearEnrolled() {
        return yearEnrolled;
    }

    public boolean isCurrentlyRegisterd() {
        return isCurrentlyRegisterd;
    }

    public GPAstatus getStatusGPA() {
        return statusGPA;
    }

    public double getGpa() {
        return gpa;
    }

    public String getStuLevel() {
        int level = LocalDate.now().getYear() - this.getYearEnrolled().getValue();
        switch (level) {
            case 0:
            case 1:
                stuLevel = "First Year";
                break;
            case 2:
                stuLevel = "Second Year";
                break;
            case 3:
                stuLevel = "Third Year";
                break;
            case 4:
                stuLevel = "Fourth Year";
                break;
        }
        return stuLevel;
    }

    public double calculateGPA() {
        double totalPts = completedCoursesGrades.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getCreditHours() * entry.getValue()).sum();
        int creditHours = completedCoursesGrades.keySet().stream().mapToInt(course -> course.getCreditHours()).sum();
        double gpa = totalPts / creditHours;
        if (gpa >= 3.90) {
            statusGPA = GPAstatus.HIGHESTHONORS;
        } else if (gpa >= 3.50) {
            statusGPA = GPAstatus.DEANSLIST;
        } else if (gpa >= 3.00) {
            statusGPA = GPAstatus.HONORS;
        } else if (gpa < 3.00) {
            statusGPA = GPAstatus.NORMAL;
        } else if (gpa < 1.75) {
            statusGPA = GPAstatus.PROBATION;
        }
        return gpa;
    }

    private enum GPAstatus {
        HIGHESTHONORS, DEANSLIST, HONORS, NORMAL, PROBATION;
    }
}
