package object_orienters;

import java.util.List;

public class Teacher extends Person{

  List<Course> teachingCourses;
  Department department;
      public Teacher(int id, String name, String email) {
        super(id, name, email);
    }

  
}
