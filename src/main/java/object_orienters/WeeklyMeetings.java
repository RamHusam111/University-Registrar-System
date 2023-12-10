package object_orienters;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class WeeklyMeetings {
    private List<String> day;
    private Duration duration;
    private String room;
    private LocalTime hour;

    public WeeklyMeetings(List<String> day, Duration duration, String room, LocalTime hour) {
        this.day = day;
        this.duration = duration;
        this.room = room;
        this.hour = hour;
    }

    public List<String> getDay() {
        return day;
    }

    public void setDay(List<String> day) {
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

}
