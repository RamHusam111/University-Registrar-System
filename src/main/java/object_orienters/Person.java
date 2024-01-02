package object_orienters;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Person {
    private int id;
    private String name;
    private String email;
    Role role = Role.STAFF;
    private List<Course> registeredCourses;
    protected Schedule schedule;
    private LocalDate dateEnrolled; // YOUSEF CHANGED IT FROM yearEnrolled to dateEnrolled
    private static int stuSequence = 1000;
    private static int teacherSequence = 5000;
    private static Year yearValue = Year.of(2023);


    public Person(Role role, String name) {
        this.name = name;
        this.role = role;
        dateEnrolled = LocalDate.now();
        registeredCourses = new ArrayList<>();
        this.id = this.setID();
        this.email = this.id + "@objectOrienters.com";
    }

    // TODO: test this method
    private int setID() {
        String id = "";
        Year enrolledYear = Year.of(dateEnrolled.getYear());
        if (this instanceof Student) {
            if (enrolledYear.isAfter(yearValue)) {
                yearValue = enrolledYear;
                stuSequence = 1000;
            }
            id = yearValue.getValue() + "0" + stuSequence;
            stuSequence++;

        } else if (this instanceof Teacher) {
            if (enrolledYear.isAfter(yearValue)) {
                yearValue = enrolledYear;
                teacherSequence = 1000;
            }
            id = yearValue.getValue() + "5" + teacherSequence;
            teacherSequence++;
        }
        return Integer.parseInt(id);
    }
    public void addRegisteredCourse(Course course) {
        // Check if the person is a student and the course is full
        if (this.role == Role.STUDENT && course.isFull()) {
            System.out.println("Cannot register in " + course.getCourseName() + " as the course is already full.");
        } else {
            this.registeredCourses.add(course);
        }
    }

    public void addListOfRegisteredCourses(List<Course> list) {
        list.stream().forEach(course -> {
            if (this.role == Role.STUDENT && course.isFull()) {
                System.out.println("Cannot register in " + course.getCourseName() + " as the course is already full.");
            } else {
                this.registeredCourses.add(course);
            }
        });
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    private Schedule getSchedule() {
        return schedule;
    }

    public LocalDate getDateEnrolled() {
        return dateEnrolled;
    }

    public void showSchedule() {
        this.getSchedule().displaySchedule();
    }

    // TODO: test this method
    public boolean isFreeOn(List<WeeklyMeeting> list) {
        return list.stream().allMatch(e -> isFreeOn(e));
    }

    // TODO: test this method
    public boolean isFreeOn(WeeklyMeeting weeklyMeeting) {

        // return registeredCourses.stream().flatMap(course ->
        // course.getWeeklyMeetings().stream()).noneMatch(wm -> {
        // return wm.getDay().equals(weeklyMeeting.getDay())
        // && (wm.getHour().plus(wm.getDuration()).isBefore(weeklyMeeting.getHour())
        // || wm.getHour().isAfter(weeklyMeeting.getHour().plus(wm.getDuration())));
        // });

        // public boolean isFreeOn(WeeklyMeeting weeklyMeeting) {
        return registeredCourses.stream()
                .flatMap(course -> course.getWeeklyMeetings().stream())
                .noneMatch(wm -> {
                    return wm.getDay().equals(weeklyMeeting.getDay())
                            && !(wm.getHour().plus(wm.getDuration()).isBefore(weeklyMeeting.getHour())
                                    || wm.getHour().isAfter(weeklyMeeting.getHour().plus(weeklyMeeting.getDuration())));
                });
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + "\nName: " + this.getName() + "\nEmail: " + this.getEmail();
    }

    public enum Role{
        STUDENT, TEACHER, STAFF;
    }

    //SCHEDULE CLASS
    private class Schedule {
        private List<Course> courses;
        Map<WeeklyMeeting, Course> meetingCourseMap;

        public Schedule(List<Course> courses) {
            this.courses = courses;
            this.meetingCourseMap = new HashMap<>();
            this.courses.forEach(course -> course.getWeeklyMeetings()
                    .forEach(weeklyMeeting -> meetingCourseMap.put(weeklyMeeting, course)));

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
}