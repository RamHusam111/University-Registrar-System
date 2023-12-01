package object_orienters;

import java.util.List;

public abstract class Person {
    private int id;
    private String name;
    private String email;
     List<Course> registeredCourses;

    public Person(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

   
}
