package object_orienters;

public class Student extends Person {
    private Schedule schedule;
    private int creditLooad;

    public Student(int id, String name, String email, Schedule schedule, int creditLooad) {
        super(id, name, email);
        this.schedule = schedule;
        this.creditLooad = creditLooad;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public int getCreditLooad() {
        return creditLooad;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void setCreditLooad(int creditLooad) {
        this.creditLooad = creditLooad;
    }

    @Override
    public String toString() {
        return "Student{" +
                "schedule=" + schedule +
                ", creditLooad=" + creditLooad +
                '}';
    }
}
