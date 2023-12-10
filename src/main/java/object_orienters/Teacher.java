package object_orienters;

import java.util.List;

public class Teacher extends Person {

  private List<Course> teachingCourses;
  private Department department;

  public Teacher(int id, String name, Department department, List<Course> teachingCourses, String email,
      List<Course> registeredCourses) {
    super(id, name, email, registeredCourses);
    this.teachingCourses = teachingCourses;
    this.department = department;
  }

  public List<Course> getTeachingCourses() {
    return teachingCourses;
  }

  public void setTeachingCourses(List<Course> teachingCourses) {
    this.teachingCourses = teachingCourses;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

}
