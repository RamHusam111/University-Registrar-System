package object_orienters;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Faculty {
    private String name;
    private List<String> rooms;
    private List<Department> departments;
    private WeeklyMeetings freeHour;
    private List<Student> students;
    private List<Teacher> teachers;

    public Faculty(String name, List<String> rooms, List<Department> departments, WeeklyMeetings freeHour,
            List<Student> students, List<Teacher> teachers) {
        this.name = name;
        this.rooms = rooms;
        this.departments = departments;
        this.freeHour = freeHour;
        this.students = students;
        this.teachers = teachers;
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

    public WeeklyMeetings getFreeHour() {
        return freeHour;
    }

    public void setFreeHour(WeeklyMeetings freeHour) {
        this.freeHour = freeHour;

    }

    public void setNewRoom(String room) {
        this.rooms.add(room);
    }

    public void setNewDepartment(Department department) {
        this.departments.add(department);
    }

}
