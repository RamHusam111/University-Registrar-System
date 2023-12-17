package object_orienters;

import java.util.List;

public class Schedule {
    private List<Course> courses;

    public Schedule(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getCourse() {
        return courses;
    }

    public void setCourse(List<Course> courses) {
        this.courses = courses;
    }

    // TODO: implement a method to calculate the credit load of a schedule by
    // streaming through the courses list
    public int getCreditLoad() {
        return 0;
    }

    public List<Course> getCourses() {
        return courses;
    }

    // TODO: test this method
    public void buildSchedule(List<Course> newCourses) {
        // Check for conflicts and add new courses to the schedule if there are no
        // conflicts
        newCourses.stream()
                .filter(course -> !hasConflict(course))
                .forEach(course -> {
                    courses.add(course);
                    System.out.println("Added " + course.getCourseName() + " to the schedule.");
                });
    }

    // TODO: test this method
    private boolean hasConflict(Course newCourse) {
        // Check for time conflicts with existing courses in the schedule
        return courses.stream()
                .anyMatch(existingCourse -> hasTimeConflict(existingCourse, newCourse));
    }

    // TODO: test this method
    private boolean hasTimeConflict(Course course1, Course course2) {
        // Check if two courses have time conflicts
        return course1.getWeeklyMeetings().stream()
                .anyMatch(weeklyMeeting1 -> course2.getWeeklyMeetings().stream()
                        .anyMatch(weeklyMeeting2 -> weeklyMeeting1.getDay().stream()
                                .anyMatch(day1 -> weeklyMeeting2.getDay().contains(day1))
                                && !weeklyMeeting1.getHour().plus(weeklyMeeting1.getDuration())
                                        .isBefore(weeklyMeeting2.getHour())
                                && !weeklyMeeting2.getHour().plus(weeklyMeeting2.getDuration())
                                        .isBefore(weeklyMeeting1.getHour())));
    }

    //TODO: implement this method to print sth like below
    public void printSchedule() {
        // needs to string to show sched as table
        // day | mon | tues | wed | thurs | fri
        // 8-9 | | | |
        // 9-10| | | |
    }

    // TODO: check this

    // public void buildSchedule(List<Course> newCourses) {
    // newCourses.stream()
    // .filter(course -> !hasConflict(course))
    // .forEach(course -> {
    // course.getWeeklyMeetings().forEach(weeklyMeeting -> {
    // scheduleMap.put(weeklyMeeting, course);
    // System.out.println("Added " + course.getCourseName() + " to the schedule.");
    // });
    // });
    // }

    // private boolean hasConflict(Course newCourse) {
    // return newCourse.getWeeklyMeetings().stream()
    // .anyMatch(weeklyMeeting -> scheduleMap.containsKey(weeklyMeeting));
    // }

}
