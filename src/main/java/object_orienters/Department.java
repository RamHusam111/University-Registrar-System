package object_orienters;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String name;
    private List<Teacher> teachers;
    private List<Student> students;
    private Faculty faculty;
    private List<Course> majors;
    private List<Course> minors;

    
    //NOTE: Angela removed students and teachers from the constructor, they are updated now once a student or teacher has been created
    public Department(String name, Faculty faculty, List<Course> majors, List<Course> minors) {
        this.name = name;
        this.teachers = new ArrayList<>();
        this.students = new ArrayList<>();
        this.majors = majors;
        this.minors = minors;
        this.faculty = faculty;
    }

    public Department(String name, Faculty faculty) {
        this.name = name;
        this.teachers = new ArrayList<>();
        this.students = new ArrayList<>();
        this.majors = new ArrayList<>();
        this.minors = new ArrayList<>();

        this.faculty = faculty;
    }

    public Department(String name) {
        this.name = name;
        this.teachers = new ArrayList<>();
        this.students = new ArrayList<>();
        this.faculty = new Faculty();

    }

    public String getName() {
        return name;
    }

    public List<Course> getMajors() {
        return majors;
    }

    public List<Course> getMinors() {
        return minors;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addMajor(Course major) {
        this.majors.add(major);
    }

    public void addMinor(Course minor) {
        this.minors.add(minor);
    }

    public Faculty getFaculty() {
        return faculty;
    }

    @Override
    public String toString() {
        return "Department{" + name + ", majors= " + majors + ", minors= " + minors;
    }


}
