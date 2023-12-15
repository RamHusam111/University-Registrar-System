package object_orienters;

import java.util.List;

public class Department {
    private String name;
    private List<Course> majors;
    private List<Course> minors;
    private List<Teacher> teachers;

    public Department(String name, List<Course> majors, List<Course> minors, List<Teacher> teachers) {
        this.name = name;
        this.majors = majors;
        this.minors = minors;
        this.teachers = teachers;
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

    public void addMajor(Course major) {
        this.majors.add(major);
    }

    public void addMinor(Course minor) {
        this.minors.add(minor);
    }


    @Override
    public String toString() {
        return "Department{" + name + ", majors= " + majors + ", minors= " + minors;
    }


}
