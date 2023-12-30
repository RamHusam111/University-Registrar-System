package object_orienters;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Student extends Person {
    private Specialization major;
    private Optional<Specialization> minor;
    private final boolean isCurrentlyRegisterd;
    private Map<Course, Double> completedCoursesGrades;
    private GPAstatus gpaStatus;
    private Faculty faculty;

    public Student(String name, Specialization major) {
        super(Role.STUDENT, name);
        this.major = major;
        isCurrentlyRegisterd = true;
        completedCoursesGrades = new HashMap<>();
        this.faculty = major.getFaculty();
        faculty.getStudents().add(this);
    }

    public Student(String name, Specialization major, Specialization minor) {
        super(Role.STUDENT, name);
        this.major = major;
        this.minor = Optional.of(minor);
        isCurrentlyRegisterd = true;
        completedCoursesGrades = new HashMap<>();
        this.faculty = major.getFaculty();
        faculty.getStudents().add(this);
    }


    // Changed
    public void enterCourseGrade(Course course, String grade) {
        if (getRegisteredCourses().contains(course)) {
            completedCoursesGrades.put(course, convertGrade(grade));
            getRegisteredCourses().remove(course);

            if( course.getTeacher().isPresent())
                course.getTeacher().get().getRegisteredCourses().remove(course);
            course.setTeacher(null);
        }
        else
            System.out.println("Error: " + this.getName() + " is not registered in " + course.getCourseName());
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

    public Specialization getMajor() {
        return major;
    }

    public Optional<Specialization> getMinor() {
        return this.minor;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public boolean isCurrentlyRegisterd() {
        return isCurrentlyRegisterd;
    }

    // TODO: test this method
    public GPAstatus getGpaStatus() {
        calculateGPA();
        return gpaStatus;
    }

    public Map<Course, Double> getCompletedCoursesGrades() {
        return completedCoursesGrades;
    }

    // TESTED
    public boolean preRequisitesCheck(Course course) {
        List<Course> preRequisites = course.getpreRequisites();
        return preRequisites.stream().allMatch(e -> this.completedCoursesGrades.keySet().contains(e));
        // Angela changed the method from registerCourse to completed courses ^(in the
        // allMatch method)
    }

    // TESTED
    public double calculateGPA() {
        double totalPts = completedCoursesGrades.entrySet().parallelStream()
                .mapToDouble(entry -> entry.getKey().getCreditHours() * entry.getValue()).sum();
        int creditHours = completedCoursesGrades.keySet().parallelStream().mapToInt(course -> course.getCreditHours())
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
                + this.getDateEnrolled().getYear() + "\nRegistered this semester: "
                + this.isCurrentlyRegisterd() + "\n Registered Courses: " + this.getRegisteredCourses() + "\nGPA "
                + this.calculateGPA() + "\nGPA Status: " + this.getGpaStatus();
    }

    private enum GPAstatus {
        HIGHESTHONORS, DEANSLIST, HONORS, NORMAL, PROBATION;
    }

}
