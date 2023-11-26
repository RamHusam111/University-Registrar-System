package object_orienters;

import java.util.List;

public class Schedule {
    private List<Courses> courses;
    private int creditLooad;

    public Schedule(List<Courses> courses, int creditLooad) {
        this.courses = courses;
        this.creditLooad = creditLooad;
    }

    public List<Courses> getCourses() {
        return courses;
    }

    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }

    public int getCreditLooad() {
        return creditLooad;
    }

    public void setCreditLooad(int creditLooad) {
        this.creditLooad = creditLooad;
    }

}
