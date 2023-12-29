package object_orienters;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Person {

  private List<Course> teachingCourses;
  private Faculty faculty;

  public Teacher(String name, Faculty faculty, List<Course> teachingCourses,
      List<Course> registeredCourses) {
    super(name);
    this.teachingCourses = teachingCourses;
    this.faculty = faculty;
    faculty.getTeachers().add(this);
  }

  public Teacher(String name, Faculty faculty) {
    super(name);
    this.teachingCourses = new ArrayList<>();
    this.faculty = faculty;
    faculty.getTeachers().add(this);
  }

  public List<Course> getTeachingCourses() {
    return teachingCourses;
  }

  public void setTeachingCourses(List<Course> teachingCourses) {
    this.teachingCourses = teachingCourses;
  }

  public Faculty getFaculty() {
    return faculty;
  }

  // does it need a setter?
  public void setDepartment(Faculty faculty) {
    this.faculty = faculty;
  }

  @Override
  public String toString() {
    return super.toString() + "\nTeaching Courses: " + this.getTeachingCourses() + "\nFaculty: "
        + this.getFaculty();
  }
}
