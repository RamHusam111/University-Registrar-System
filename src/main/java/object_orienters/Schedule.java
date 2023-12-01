package object_orienters;

import java.util.List;

public class Schedule {
    private List<Course> courses;
    private int creditLooad;

    public Schedule(List<Course> courses, int creditLooad) {
        this.courses = courses;
        this.creditLooad = creditLooad;
    }

    public List<Course> getCourse() {
        return courses;
    }

    public void setCourse(List<Course> courses) {
        this.courses = courses;
    }

    public int getCreditLooad() {
        return creditLooad;
    }

    public void setCreditLooad(int creditLooad) {
        this.creditLooad = creditLooad;
    }

}
