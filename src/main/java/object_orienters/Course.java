package object_orienters;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class Course {
    private Teacher teacher;
    private String courseType;
    private Department courseDepartment;
    private String courseName;
    private int creditHours;
    private List<Course> preRequisites;
    private List<WeeklyMeetings> weeklyMeetings;

    public Course(Teacher teacher, String courseType, Department courseDepartment, String courseName, int creditHours,
            List<Course> preRequisites, List<WeeklyMeetings> weeklyMeetings) {
        this.teacher = teacher;
        this.courseType = courseType;
        this.courseDepartment = courseDepartment;
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.preRequisites = preRequisites;
        this.weeklyMeetings = weeklyMeetings;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public Department getCourseDepartment() {
        return courseDepartment;
    }

    public void setCourseDepartment(Department courseDepartment) {
        this.courseDepartment = courseDepartment;
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

    public void setPrerequisites(List<Course> preRequisites) {
        this.preRequisites = preRequisites;
    }

    public List<WeeklyMeetings> getWeeklyMeetings() {
        return weeklyMeetings;
    }

    public void setWeeklyMeetings(List<WeeklyMeetings> weeklyMeetings) {
        this.weeklyMeetings = weeklyMeetings;
    }
}