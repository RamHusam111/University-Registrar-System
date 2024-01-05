package object_orienters;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Represents a student at the university.
 * A student can have a major and a minor specialization.
 * A student can be registered for courses.
 * A student can have a GPA status.
 * A student can have a faculty.
 * A student can have a list of completed courses and their grades.
 */
public class Student extends Person {
    private Specialization major;
    private Optional<Specialization> minor;
    private final boolean isCurrentlyRegisterd;
    private Map<Course, Double> completedCoursesGrades;
    private GPAstatus gpaStatus;
    private Faculty faculty;

    /**
     * Constructs a new Student with the given name and major specialization.
     * Initializes the student with the role STUDENT and adds the student to the
     * specialization's list of students and the faculty's list of students.
     *
     * @param name  The name of the student.
     * @param major The major specialization of the student.
     */
    public Student(String name, Specialization major) {
        super(Role.STUDENT, name);
        this.major = major;
        isCurrentlyRegisterd = true;
        completedCoursesGrades = new HashMap<>();
        this.faculty = major.getFaculty();
        faculty.getStudents().add(this);
    }

    /**
     * Constructs a new Student with the given name, major specialization, and minor
     * specialization.
     * Initializes the student with the role STUDENT and adds the student to the
     * specialization's list of students and the faculty's list of students.
     *
     * @param name  The name of the student.
     * @param major The major specialization of the student.
     * @param minor The minor specialization of the student.
     */
    public Student(String name, Specialization major, Specialization minor) {
        super(Role.STUDENT, name);
        this.major = major;
        this.minor = Optional.ofNullable(minor);
        isCurrentlyRegisterd = true;
        completedCoursesGrades = new HashMap<>();
        this.faculty = major.getFaculty();
        faculty.getStudents().add(this);
    }

    /**
     * Records the grade for a completed course.
     * courses and updates the teacher's course list if necessary.
     *
     * @param course The course for which the grade is being entered.
     * @param grade  The grade received in the course.
     */
    public void enterCourseGrade(Course course, String grade) {
        if (getRegisteredCourses().contains(course)) {
            completedCoursesGrades.put(course, convertGrade(grade));
            getRegisteredCourses().remove(course);

            if (course.getTeacher().isPresent())
                course.getTeacher().get().getRegisteredCourses().remove(course);
            course.setTeacher(null);

        } else
            System.out.println("Error: " + this.getName() + " is not registered in " + course.getCourseName());
    }

    /**
     * Converts a letter grade to its numerical equivalent based on a standard
     * grading scale.
     * For example, an "A" might convert to 4.0, a "B+" to 3.5, and so forth.
     *
     * @param letterGrade The letter grade to be converted.
     * @return The numerical equivalent of the letter grade as a Double.
     */
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

    /**
     * Retrieves the student's major specialization.
     *
     * @return The student's major specialization.
     */
    public Specialization getMajor() {
        return major;
    }

    /**
     * Retrieves the student's minor specialization, if any.
     *
     * @return An Optional containing the minor specialization if set, or an empty
     *         Optional otherwise.
     */
    public Optional<Specialization> getMinor() {
        return this.minor;
    }

    /**
     * Retrieves the faculty associated with the student's major.
     *
     * @return The faculty associated with the student's major.
     */
    public Faculty getFaculty() {
        return faculty;
    }

    /**
     * Checks whether the student is currently registered for any courses.
     *
     * @return True if the student is currently registered, false otherwise.
     */
    public boolean isCurrentlyRegisterd() {
        return isCurrentlyRegisterd;
    }

    /**
     * Retrieves the student's GPA status after recalculating their GPA.
     *
     * @return The student's current GPA status.
     */

    // Tested
    public GPAstatus getGpaStatus() {
        calculateGPA();
        return gpaStatus;
    }

    /**
     * Retrieves a map of completed courses along with the grades received.
     *
     * @return A map with Course keys and Double values representing grades.
     */
    public Map<Course, Double> getCompletedCoursesGrades() {
        return completedCoursesGrades;
    }

    /**
     * Checks if the student has completed all prerequisites for a given course.
     *
     * @param course The course for which prerequisites are being checked.
     * @return True if all prerequisites are met, false otherwise.
     */
    public boolean preRequisitesCheck(Course course) {
        Set<Course> preRequisites = course.getPrerequisites();
        return preRequisites.stream().allMatch(e -> this.completedCoursesGrades.keySet().contains(e));
        // Angela changed the method from registerCourse to completed courses ^(in the
        // allMatch method)
    }

    /**
     * Calculates the student's GPA based on completed courses and their grades.
     * Utilizes parallel computation for efficiency.
     *
     * @return The calculated GPA.
     */
    public double calculateGPA() {
        ForkJoinPool pool = new ForkJoinPool();

        // Recursive task for calculating total points
        RecursiveTask<Double> totalPtsTask = new TotalPointsTask(completedCoursesGrades);

        // Recursive task for calculating total credit hours
        RecursiveTask<Double> totalCreditHours = new TotalCreditHours(completedCoursesGrades);

        // Execute tasks
        pool.execute(totalPtsTask);
        pool.execute(totalCreditHours);

        // Gather results
        double totalPts = totalPtsTask.join();
        double creditHours = totalCreditHours.join();

        // Shutdown the pool
        pool.shutdown();

        // Calculate and return GPA
        double gpa = totalPts / creditHours;
        updateGPAStatus(gpa);
        System.out.println(gpa);
        return gpa;
    }

    private static class TotalPointsTask extends RecursiveTask<Double> {
        private final static int THRESHOLD = 3;
        private Map<Course, Double> map;
        private int start;
        private int end;

        public TotalPointsTask(Map<Course, Double> map) {
            this.map = map;
            this.start = 0;
            this.end = map.size();
        }

        private TotalPointsTask(Map<Course, Double> map, int start, int end) {
            this.map = map;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Double compute() {
            int length = end - start;
            if (length <= THRESHOLD) {
                return map.entrySet().stream()
                        .skip(start)
                        .limit(end - start)
                        .mapToDouble(entry -> entry.getValue() * entry.getKey().getCreditHours())
                        .sum();
            } else {
                int mid = start + length / 2;
                TotalPointsTask left = new TotalPointsTask(map, start, mid);
                TotalPointsTask right = new TotalPointsTask(map, mid, end);
                left.fork();
                right.fork();
                double rightResult = right.join();
                double leftResult = left.join();
                return leftResult + rightResult;
            }
        }
    }

    private static class TotalCreditHours extends RecursiveTask<Double> {
        private final static int THRESHOLD = 3;
        private Map<Course, Double> map;
        private int start;
        private int end;

        public TotalCreditHours(Map<Course, Double> map) {
            this.map = map;
            this.start = 0;
            this.end = map.size();
        }

        private TotalCreditHours(Map<Course, Double> map, int start, int end) {
            this.map = map;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Double compute() {
            int length = end - start;
            if (length <= THRESHOLD) {
                return map.entrySet().stream()
                        .skip(start)
                        .limit(end - start)
                        .mapToDouble(entry -> entry.getKey().getCreditHours())
                        .sum();
            } else {
                int mid = start + length / 2;
                TotalCreditHours left = new TotalCreditHours(map, start, mid);
                TotalCreditHours right = new TotalCreditHours(map, mid, end);
                left.fork();
                right.fork();
                double rightResult = right.join();
                double leftResult = left.join();
                return leftResult + rightResult;
            }
        }
    }

    /**
     * Updates the student's GPA status based on the calculated GPA.
     * This method categorizes the GPA into various statuses such as Highest Honors,
     * Dean's List, Honors,
     * Normal, and Probation, based on predefined GPA thresholds.
     *
     * @param gpa The calculated Grade Point Average of the student.
     */
    // HELPER METHOD FOR calculateGPA()
    private void updateGPAStatus(double gpa) {
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
    }

    /**
     * Generates a comprehensive academic report for the student.
     * Includes details such as major, minor, year of admission, current
     * registration status,
     * registered courses, GPA, and GPA status.
     *
     * @return A formatted string containing the student's academic report.
     */
    public String getReport() {
        return super.toString() + "\nMajor:" + this.getMajor().getName()
                + (minor != null && minor.isPresent() ? "\nMinor: " + this.getMinor().get().getName() : "")
                + "\nAdmitted year: "
                + this.getDateEnrolled().getYear() + "\nRegistered this semester: "
                + this.isCurrentlyRegisterd() + "\n Registered Courses: " + this.getRegisteredCourses() + "\nGPA "
                + this.calculateGPA() + "\nGPA Status: " + this.getGpaStatus();
    }

    /**
     * Enumeration representing various GPA statuses, such as honors and probation.
     */
    public enum GPAstatus {
        HIGHESTHONORS, DEANSLIST, HONORS, NORMAL, PROBATION;
    }

}
