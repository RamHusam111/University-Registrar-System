package object_orienters;

import java.util.ArrayList;
import java.util.List;

public class Faculty {
    private String name;
    private List<Teacher> teachers;
    private List<Student> students;
    private List<Course> majorsCourses;
    private List<Course> minorsCourses;
    // private List<String> majors;
    // private List<String> minors;
    private static List<Faculty> faculties = new ArrayList<>();

    public Faculty(String name) {
        this.name = name;
        this.teachers = new ArrayList<>();
        this.students = new ArrayList<>();
        this.majorsCourses = new ArrayList<>();
        this.minorsCourses = new ArrayList<>();
        faculties.add(this);

    }

    public String getName() {
        return name;
    }

    public List<Course> getMajorsCourses() {
        return majorsCourses;
    }

    public List<Course> getMinorsCourses() {
        return minorsCourses;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addMajorCourse(Course majorCourse) {
        this.majorsCourses.add(majorCourse);
    }

    public void addMinor(Course minorCourse) {
        this.minorsCourses.add(minorCourse);
    }

    @Override
    public String toString() {
        return "Faculty{" + name + ", majors Courses = " + majorsCourses + ", minors = " + minorsCourses;
    }

}
