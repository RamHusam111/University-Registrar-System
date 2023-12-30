package object_orienters;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Course {

    private Type courseType;
    private Faculty courseFaculty;
    private String courseID;
    private String courseName;
    private int creditHours;
    private List<Course> preRequisites;
    private List<WeeklyMeeting> weeklyMeetings;
    private Teacher teacher;

    //TODO: add equal method to check unique if in register method semester
    public Course(String courseID, String courseName, Faculty courseFaculty, int creditHours,
            List<WeeklyMeeting> weeklyMeetings) {
        this.courseID = courseID;
        this.courseFaculty = courseFaculty;
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.preRequisites = new ArrayList<>(); // optional of nullable
        this.weeklyMeetings = weeklyMeetings;
        courseType = Type.UNIVERSITY_REQUIREMENT;
    }

     public Course(String courseID, String courseName, Specialization specialization, int creditHours,
            List<WeeklyMeeting> weeklyMeetings) {
        this.courseID = courseID;
        this.courseFaculty = specialization.getFaculty();
        courseFaculty.addMajorCourse(this);
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.preRequisites = new ArrayList<>(); // optional of nullable
        this.weeklyMeetings = weeklyMeetings;
        courseType = specialization.getType() == Specialization.Type.MAJOR ? Type.MAJOR_REQUIREMENT : Type.MINOR_REQUIREMENT;

    }

    @Override
    public String toString() {
        return "Course{" +
                ", courseType='" + courseType + '\'' +
                // ", courseDepartment=" + courseDepartment +
                ", courseName='" + courseName + '\'' +
                ", creditHours=" + creditHours +
                '}';
    }

    public String getCourseID() {
        return courseID;
    }

    public Type getCourseType() {
        return courseType;
    }

    public Faculty getCourseFaculty() {
        return courseFaculty;
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
    public Optional<Teacher> getTeacher() {
        return Optional.ofNullable(teacher);
    }

     public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Course> getpreRequisites() {
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

    public enum Type {
        MAJOR_REQUIREMENT, UNIVERSITY_REQUIREMENT, MINOR_REQUIREMENT;
    }

}