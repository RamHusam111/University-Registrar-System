package object_orienters;

import java.util.List;

public class Departments {
    private String name;
    private List<String> majors;
    private List<String> minors;
//    private List<Teachers> teachers;

//    public Department(String name, List<String> majors, List<String> minors, List<Teachers> teachers) {
//        this.name = name;
//        this.majors = majors;
//        this.minors = minors;
//        this.teachers = teachers;
//    }

    public String getName() {
        return name;
    }

    public List<String> getMajors() {
        return majors;
    }

    public List<String> getMinors() {
        return minors;
    }

//    public List<Teachers> getTeachers() {
//        return teachers;
//    }

    public void addMajor(String major) {
        this.majors.add(major);
    }

    public void addMinor(String minor) {
        this.minors.add(minor);
    }


    @Override
    public String toString() {
        return "Department{" + name + ", majors= " + majors + ", minors= " + minors;
    }


}
