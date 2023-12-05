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
    private Map<DayOfWeek, FreeHours> freehouers = new HashMap<>();
    //private List<Students> students;
    //private List<Teachers> teachers;

//    public Faculty(String name, List<String> rooms, List<Departments> departments, Map<DayOfWeek, FreeHours> freehouers, List<Students> students, List<Teachers> teachers) {
//        this.name = name;
//        this.rooms = rooms;
//        this.departments = departments;
//        this.freehouers = freehouers;
//        this.students = students;
//        this.teachers = teachers;
//    }

    public String getName() {
        return name;
    }

    public List<String> getRooms() {
        return rooms;
    }

    public List<Department> getDepartments() {
        return departments;
    }
    //    public List<Student> getStudents() {
//        return students;
//    }
//
//    public List<Teacher> getTeachers() {
//        return teachers;
//    }

    public void setNewRoom(String room) {
        this.rooms.add(room);
    }

    public void setNewDepartment(Department department) {
        this.departments.add(department);
    }
    public void addFreeHour(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        freehouers.put(dayOfWeek, new FreeHours(startTime, endTime));
    }

    private static class FreeHours {
        private LocalTime startTime;
        private LocalTime endTime;

        public FreeHours(LocalTime startTime, LocalTime endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}
