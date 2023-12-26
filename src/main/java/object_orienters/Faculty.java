package object_orienters;

import java.util.ArrayList;
import java.util.List;

public class Faculty {
    private String name;
    private List<String> rooms;
    private List<Department> departments;
    private WeeklyMeeting freeHour;
    private List<Student> students;
    private List<Teacher> teachers;

    public Faculty(String name, WeeklyMeeting freeHour) {
        this.name = name;
        this.rooms = new ArrayList<>();
        this.departments = new ArrayList<>();
        this.freeHour = freeHour;
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
    }
    public Faculty() {
        this.teachers = new ArrayList<>();
    }

    // NOTE: Angela removed students and teachers from the constructor, they are
    // updated now once a student or teacher has been created
    public Faculty(String name, List<String> rooms, List<Department> departments, WeeklyMeeting freeHour) {
        this.name = name;
        this.rooms = rooms;
        this.departments = departments;
        this.freeHour = freeHour;
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<String> getRooms() {
        return rooms;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public WeeklyMeeting getFreeHour() {
        return freeHour;
    }

    public void setFreeHour(WeeklyMeeting freeHour) {
        this.freeHour = freeHour;

    }

    public void setNewRoom(String room) {
        this.rooms.add(room);
    }

    public void setNewDepartment(Department department) {
        this.departments.add(department);
    }

}
