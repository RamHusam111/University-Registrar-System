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

    public Course(String courseID, String courseName, Faculty courseFaculty, int creditHours,
            List<WeeklyMeeting> weeklyMeetings) {
        this.courseID = courseID;
        this.courseFaculty = courseFaculty;
        // courseFaculty.getMajors().add(this);
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.preRequisites = new ArrayList<>(); // optional of nullable
        this.weeklyMeetings = weeklyMeetings;
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

    public void setCourseType(Type courseType) {
        this.courseType = courseType;
    }

    public Faculty getCourseDepartment() {
        return courseFaculty;
    }

    public void setCourseDepartment(Faculty courseFaculty) {
        this.courseFaculty = courseFaculty;
        // courseFaculty.getMajors().add(this);
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

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
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