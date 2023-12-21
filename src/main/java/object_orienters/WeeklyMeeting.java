package object_orienters;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

public class WeeklyMeeting {
    private DayOfWeek day;
    private Duration duration;
    private String room;
    private LocalTime hour;
    private String id;
    private String timeId;

    public WeeklyMeeting(DayOfWeek day, Duration duration, String room, LocalTime hour) {
        this.day = day;
        this.duration = duration;
        this.room = room;
        this.hour = hour;
        this.timeId = day.name() +
                " " + duration.toMinutes() +
                " " + hour.toString();

        this.id = timeId + " " + room;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public Duration getDuration() {
        return duration;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public String toString() {

        return "Weekly Meeting:>" + day.name() +
                " " + duration.toMinutes() +
                " " + hour.toString() + " " + room;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof WeeklyMeeting)) 
            return false;
        return this.id.equals(((WeeklyMeeting) o).id);
    }

    public boolean equalsTime(WeeklyMeeting wm) {
        if (!(wm instanceof WeeklyMeeting)) 
            return false;
        return this.timeId.equals(((WeeklyMeeting) wm).timeId);
    }
}
