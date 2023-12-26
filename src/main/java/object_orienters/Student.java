package object_orienters;

import java.time.LocalDate;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Student extends Person {
    private String major;
    private Optional<String> minor;
    private LocalDate dateEnrolled; // YOUSEF CHANGED IT FROM yearEnrolled to dateEnrolled
    private final boolean isCurrentlyRegisterd;
    private Map<Course, Double> completedCoursesGrades;
    private Map<Course, Double> completedCourses ;
    private GPAstatus gpaStatus;
    private String stuLevel;
    private Department department;

    public Student(int id, String name, String email, Department department) {
        super(id, name, email);
        completedCourses = new HashMap<>();
        isCurrentlyRegisterd = true;
        completedCoursesGrades = new HashMap<>();
        // department.getStudents().add(this);
        // department.getFaculty().getStudents().add(this);

    }

    // TESTED
    public void enterGrades(Map<Course, String> grades) {
        completedCourses.putAll(grades.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(),
                entry -> convertGrade(entry.getValue()))));
        this.getRegisteredCourses().clear();
    }

    // HELPER METHOD FOR enterGrades METHOD
    private Double convertGrade(String letterGrade) {
        switch (letterGrade) {
            case "A":
                return 4.0;
            case "B+":
                return 3.5;
            case "B":
                return 3.0;
            case "C+":
                return 2.5;
            case "C":
                return 2.0;
            case "D+":
                return 1.5;
            case "D":
                return 1.0;
            case "F":
                return 0.0;
            default:
                return 0.0;
        }
    }

    public void setDateEnrolled(LocalDate dateEnrolled) {
        this.dateEnrolled = dateEnrolled;
    }

    public String getMajor() {
        return major;
    }

    public String getMinor() {
        return minor.orElse("No minor");
    }

    public LocalDate getDateEnrolled() {
        return this.dateEnrolled;
    }

    public Department getDepartment() {
        return department;
    }

    public boolean isCurrentlyRegisterd() {
        return isCurrentlyRegisterd;
    }

    public GPAstatus getGpaStatus() {
        calculateGPA();
        return gpaStatus;
    }

    public Map<Course, Double> getCompletedCourses() {
        return completedCourses;
    }

    // TESTED
    public boolean preRequisitesCheck(Course course) {
        List<Course> preRequisites = course.getpreRequisites();
        return preRequisites.stream().allMatch(e -> this.completedCourses.keySet().contains(e));
        // Angela changed the method from registerCourse to completed courses ^(in the
        // allMatch method)
    }

    // TODO: Fix code logic & Test this method
    public String getStudentLevel() {
        int year = LocalDate.now().getYear() - this.getDateEnrolled().getYear();
        // switch (level) {
        // case 0:
        // case 1:
        // stuLevel = "First Year";
        // break;
        // case 2:
        // stuLevel = "Second Year";
        // break;
        // case 3:
        // stuLevel = "Third Year";
        // break;
        // case 4:
        // stuLevel = "Fourth Year";
        // break;
        // default:
        // stuLevel = "Graduate";
        // break;
        // }
        return stuLevel;
    }

    // TESTED
    public double calculateGPA() {
        double totalPts = completedCourses.entrySet().parallelStream()
                .mapToDouble(entry -> entry.getKey().getCreditHours() * entry.getValue()).sum();
        int creditHours = completedCourses.keySet().parallelStream().mapToInt(course -> course.getCreditHours())
                .sum();
        double gpa = totalPts / creditHours;
        if (gpa >= 3.90) {
            gpaStatus = GPAstatus.HIGHESTHONORS;
        } else if (gpa >= 3.50) {
            gpaStatus = GPAstatus.DEANSLIST;
        } else if (gpa >= 3.00) {
            gpaStatus = GPAstatus.HONORS;
        } else if (gpa < 3.00) {
            gpaStatus = GPAstatus.NORMAL;
        } else if (gpa < 1.75) {
            gpaStatus = GPAstatus.PROBATION;
        }
        return gpa;
    }

    public String getReport() {
        return super.toString() + "\nMajor:" + this.getMajor() + "\nMinor: " + this.getMinor() + "\nAdmitted year: "
                + this.getDateEnrolled().getYear() + "\nStudent level: " + this.getStudentLevel()
                + "\nRegistered this semester: "
                + this.isCurrentlyRegisterd() + "\n Registered Courses: " + this.getRegisteredCourses() + "\nGPA "
                + this.calculateGPA() + "\nGPA Status: " + this.getGpaStatus();
    }

    private enum GPAstatus {
        HIGHESTHONORS, DEANSLIST, HONORS, NORMAL, PROBATION;
    }

}
