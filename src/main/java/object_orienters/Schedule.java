package object_orienters;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Schedule {
    private List<Course> courses;
    Map<WeeklyMeeting, Course> meetingCourseMap;


    public Schedule(List<Course> courses) {
        this.courses = courses;
        this.meetingCourseMap = new HashMap<>();
        this.courses.forEach(course ->
                course.getWeeklyMeetings().forEach(weeklyMeeting ->
                        meetingCourseMap.put(weeklyMeeting, course)));

    }

    // TODO: test this method
    public int getCreditLoad() {
        return courses.stream().mapToInt(e -> e.getCreditHours()).sum();
    }

    // Method to display the schedule
    public String displaySchedule() {
        StringBuilder scheduleBuilder = new StringBuilder();

        // Grouping weekly meetings by day
        Map<DayOfWeek, List<WeeklyMeeting>> meetingsByDay = courses.stream()
                .flatMap(course -> course.getWeeklyMeetings().stream())
                .collect(Collectors.groupingBy(WeeklyMeeting::getDay));

        // Sorting and formatting schedule by day
        meetingsByDay.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    DayOfWeek day = entry.getKey();
                    List<WeeklyMeeting> meetings = entry.getValue();
                    scheduleBuilder.append(day).append(":\n");

                    meetings.stream()
                            .sorted(Comparator.comparing(WeeklyMeeting::getHour))
                            .forEach(weeklyMeeting -> {
                                Course course = meetingCourseMap.get(weeklyMeeting);
                                scheduleBuilder.append(formatMeeting(course, weeklyMeeting)).append("\n");
                            });
                });

        return scheduleBuilder.toString();
    }

    // Helper method to format a single meeting entry
    private String formatMeeting(Course course, WeeklyMeeting meeting) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return String.format("  %s - %s | %s | Room: %s",
                meeting.getHour().format(timeFormatter),
                meeting.getHour().plus(meeting.getDuration()).format(timeFormatter),
                course.getCourseName(),
                meeting.getRoom());
    }







}
