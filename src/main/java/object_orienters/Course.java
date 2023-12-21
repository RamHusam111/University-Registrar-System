package object_orienters;

import java.util.ArrayList;
import java.util.List;

public class Course {
    // **REMOVE ALL COMMENTS AFTER CREATING NEEDED CLASSES (Teacher && Department)**
    private Teacher teacher;
    private String courseType;
    private Department courseDepartment;
    private String courseName;
    private int creditHours;
    private List<Course> preRequisites;
    private List<WeeklyMeeting> weeklyMeetings;

    public Course(String courseName, Teacher teacher, String courseType, Department courseDepartment,  int creditHours,
            List<Course> prerequisites, List<WeeklyMeeting> weeklyMeetings) {
        this.teacher = teacher;
        teacher.getTeachingCourses().add(this);
        this.courseType = courseType;
        this.courseDepartment = courseDepartment;
        courseDepartment.getMajors().add(this);
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.preRequisites = prerequisites; //optional of nullable
        this.weeklyMeetings = weeklyMeetings;
    }

    public Course(String courseName,Teacher teacher, int creditHours, List<WeeklyMeeting> weeklyMeetings) {
        this.teacher = teacher;
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.weeklyMeetings = weeklyMeetings;
        this.preRequisites = new ArrayList<>();

    }

    @Override
    public String toString() {
        return "Course{" +
                "teacher=" + teacher +
                ", courseType='" + courseType + '\'' +
                ", courseDepartment=" + courseDepartment +
                ", courseName='" + courseName + '\'' +
                ", creditHours=" + creditHours +
                '}';
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
        courseDepartment.getMajors().add(this);
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

     public void addPrerequisites(Course preRequisite) {
        this.preRequisites.add(preRequisite);
    }

    public List<WeeklyMeeting> getWeeklyMeetings() {
        return weeklyMeetings;
    }

    public void setWeeklyMeetings(List<WeeklyMeeting> weeklyMeetings) {
        this.weeklyMeetings = weeklyMeetings;
    }
}