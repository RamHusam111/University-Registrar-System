package object_orienters;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Course {
    private final int CAPACITY;
    private Type courseType;
    private Faculty courseFaculty;
    private String courseID;
    private String courseName;
    private int creditHours;
    private List<Course> preRequisites;
    private List<WeeklyMeeting> weeklyMeetings;
    private Teacher teacher;
    private List<Student> enrolledStudents;

    /**
     * Constructor for university requirement courses.
     *
     * @param courseID       The unique identifier for the course.
     * @param courseName     The name of the course.
     * @param courseFaculty  The faculty to which the course belongs.
     * @param creditHours    The number of credit hours the course offers.
     * @param weeklyMeetings The weekly meetings scheduled for the course.
     * @param CAPACITY       The maximum number of students that can be enrolled.
     */
    // Constructor for university requirement courses
    public Course(String courseID, String courseName, Faculty courseFaculty, int creditHours,
            List<WeeklyMeeting> weeklyMeetings, int CAPACITY) {
        this.enrolledStudents = new ArrayList<>();
        this.courseID = courseID;
        this.courseFaculty = courseFaculty;
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.preRequisites = new ArrayList<>();
        this.weeklyMeetings = weeklyMeetings;
        this.CAPACITY = CAPACITY; // Initialize the capacity field
        courseType = Type.UNIVERSITY_REQUIREMENT;
    }

    /**
     * Constructor for major/minor requirement courses.
     *
     * @param courseID       The unique identifier for the course.
     * @param courseName     The name of the course.
     * @param specialization The specialization that this course falls under.
     * @param creditHours    The number of credit hours the course offers.
     * @param weeklyMeetings The weekly meetings scheduled for the course.
     * @param CAPACITY       The maximum number of students that can be enrolled.
     */

    // Constructor for major/minor requirement courses
    public Course(String courseID, String courseName, Specialization specialization, int creditHours,
            List<WeeklyMeeting> weeklyMeetings, int CAPACITY) {
        this.enrolledStudents = new ArrayList<>();

        this.courseID = courseID;
        this.courseFaculty = specialization.getFaculty();
        courseFaculty.addMajorCourse(this);
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.preRequisites = new ArrayList<>();
        this.weeklyMeetings = weeklyMeetings;
        this.CAPACITY = CAPACITY; // Initialize the capacity field
        courseType = specialization.getType() == Specialization.Type.MAJOR ? Type.MAJOR_REQUIREMENT
                : Type.MINOR_REQUIREMENT;
    }

    /**
     * Enrolls a student in the course if there is available capacity.
     * If the course is full, enrollment is not possible and an appropriate message
     * is displayed.
     *
     * @param student The student to be enrolled in the course.
     */
    // Method to enroll a student in the course
    public void enrollStudent(Student student) {
        if (!isFull()) {
            enrolledStudents.add(student);
        } else {
            System.out.println("Course is full. Cannot enroll student: " + student.getName());
        }
    }

    /**
     * Checks if the course has reached its maximum capacity of enrolled students.
     *
     * @return true if the number of enrolled students equals or exceeds the
     *         capacity,
     *         false otherwise.
     */
    // Method to check if the course is full
    public boolean isFull() {
        return enrolledStudents.size() >= this.getCapacity();
    }

    /**
     * Returns a string representation of the course, including details such as
     * course type, course name, and credit hours.
     *
     * @return A formatted string containing key information about the course.
     */
    @Override
    public String toString() {
        return "Course ID: " + this.getCourseID() + "\nCourse Name: " + this.getCourseName() +
                "\nCredit Hours: " + this.getCourseName() + "\nCapacity: " + this.getCapacity() + "\nCourse Faculty: "
                + this.getCourseFaculty().getName() + "\nPrerequisites: [" + this.getPrerequisites().stream().map(e -> e.getCourseID() + ", ").reduce("", String::concat)
                + "]\nCourse Type: "
                + this.getCourseType();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Course) {
            return this.getCourseID().equals(((Course) obj).getCourseID());
        }
        return false;
    }

    public String getCourseID() {
        return courseID;
    }

    /**
     * Retrieves the type of the course.
     *
     * @return The course type, which indicates whether it is a major requirement,
     *         university requirement, or minor requirement.
     */
    public Type getCourseType() {
        return courseType;
    }

    /**
     * Retrieves the faculty to which the course is associated.
     *
     *
     * @return The faculty associated with the course.
     */
    public Faculty getCourseFaculty() {
        return courseFaculty;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCreditHours() {
        return creditHours;
    }

    /**
     * Retrieves the teacher assigned to the course.
     *
     * @return An Optional containing the teacher if one is assigned, or an empty
     *         Optional
     *         if no teacher is assigned to the course.
     */
    public Optional<Teacher> getTeacher() {
        return Optional.ofNullable(teacher);
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Course> getPrerequisites() {
        return preRequisites;
    }

    public void addPrerequisites(Course preRequisite) {
        this.preRequisites.add(preRequisite);
    }

    public List<WeeklyMeeting> getWeeklyMeetings() {
        return weeklyMeetings;
    }

    public void setWeeklyMeetings(List<WeeklyMeeting> weeklyMeetings) {
        this.weeklyMeetings = weeklyMeetings;
    }

    // Getter for capacity
    public int getCapacity() {
        return CAPACITY;
    }

    /**
     * Enumeration representing the different types of courses.
     * This helps categorize courses based on their requirement type such as
     * major requirement, minor requirement, or university requirement.
     */
    public enum Type {
        MAJOR_REQUIREMENT, UNIVERSITY_REQUIREMENT, MINOR_REQUIREMENT;
    }

}