package object_orienters;

import java.util.List;
import java.util.Map;

public class Schedule {
    private List<Course> courses;
    private Map<WeeklyMeeting, Course> scheduleMap;

    public Schedule(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getCourse() {
        return courses;
    }

    public void setCourse(List<Course> courses) {
        this.courses = courses;
    }

    // TODO: test this method
    public int getCreditLoad() {
        return courses.stream().mapToInt(e -> e.getCreditHours()).sum();
    }

    public List<Course> getCourses() {
        return courses;
    }

    // TODO: test this method
    // public void buildSchedule(List<Course> newCourses) {
    // // Check for conflicts and add new courses to the schedule if there are no
    // // conflicts
    // newCourses.stream()
    // .filter(course -> !hasConflict(course))
    // .forEach(course -> {
    // courses.add(course);
    // System.out.println("Added " + course.getCourseName() + " to the schedule.");
    // });
    // }

    // TODO: implement this method to print sth like below
    public void printSchedule() {
        // needs to string to show sched as table
        // day | mon | tues | wed | thurs | fri
        // 8-9 | | | |
        // 9-10| | | |
    }


}
