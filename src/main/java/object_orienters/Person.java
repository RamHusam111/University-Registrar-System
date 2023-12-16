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

    public void setRegisteredCourses(List<Course> registeredCourses) {
        this.registeredCourses = registeredCourses;
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

    public boolean isFreeOn(List<WeeklyMeeting> list) {
        return schedule.getCourses().stream().filter(e -> e.getWeeklyMeetings().contains(list)).count() == 0;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + "\nName: " + this.getName() + "\nEmail: " + this.getEmail();
    }
}
