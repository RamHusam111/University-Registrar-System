package object_orienters;

import java.util.List;

public abstract class Person {
    private int id;
    private String name;
    private String email;
    List<Course> registeredCourses;
    Schedule schedule;

    public Person(int id, String name, String email, List<Course> registeredCourses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.registeredCourses = registeredCourses;
    }

    public void setRegisteredCourses(List<Course> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
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

}
