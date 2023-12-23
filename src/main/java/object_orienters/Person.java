package object_orienters;

import java.util.ArrayList;
import java.util.List;

public abstract class Person {
    private int id;
    private String name;
    private String email;
    private List<Course> registeredCourses;
    protected Schedule schedule;

    public Person(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        registeredCourses = new ArrayList<>();
    }


    public void addRegisteredCourse(Course course) {
        this.registeredCourses.add(course);
    }

    public void addListOfRegisteredCourses(List<Course> list) {
        list.stream().forEach(course -> this.registeredCourses.add(course));
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    // TODO: test this method
    public boolean isFreeOn(List<WeeklyMeeting> list) {
        return list.stream().allMatch(e -> isFreeOn(e));
    }

    // TODO: test this method
    public boolean isFreeOn(WeeklyMeeting weeklyMeeting) {

        // return registeredCourses.stream().flatMap(course ->
        // course.getWeeklyMeetings().stream()).noneMatch(wm -> {
        // return wm.getDay().equals(weeklyMeeting.getDay())
        // && (wm.getHour().plus(wm.getDuration()).isBefore(weeklyMeeting.getHour())
        // || wm.getHour().isAfter(weeklyMeeting.getHour().plus(wm.getDuration())));
        // });

        // public boolean isFreeOn(WeeklyMeeting weeklyMeeting) {
        return registeredCourses.stream()
                .flatMap(course -> course.getWeeklyMeetings().stream())
                .noneMatch(wm -> {
                    return wm.getDay().equals(weeklyMeeting.getDay())
                            && !(wm.getHour().plus(wm.getDuration()).isBefore(weeklyMeeting.getHour())
                                    || wm.getHour().isAfter(weeklyMeeting.getHour().plus(weeklyMeeting.getDuration())));
                });
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + "\nName: " + this.getName() + "\nEmail: " + this.getEmail();
    }
}




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




