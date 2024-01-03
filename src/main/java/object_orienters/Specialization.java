package object_orienters;

import java.util.ArrayList;
import java.util.List;

public class Specialization {
    private String name;
    private Faculty faculty;
    private Type type;
    private List<Teacher> teachers;

    public Specialization(String name, Faculty faculty, Type type) {
        this.teachers = new ArrayList<>();
        this.name = name;
        this.faculty = faculty;
        this.type = type;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        MAJOR, MINOR;
    }

    public List<Teacher> getTeachers() {
        return this.teachers;
    }

    public String toString() {
        return name;
    }
}