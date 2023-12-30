//package object_orienters;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertAll;
//import java.time.DayOfWeek;
//import java.time.Duration;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//
//public class PersonTest {
//    Student stu1;
//    Student stu2;
//    Student stu3;
//    Course c1;
//    Course c2;
//    Course c3;
//
//    WeeklyMeeting wm1 = new WeeklyMeeting(DayOfWeek.MONDAY, Duration.ofHours(1), "M101", LocalTime.of(9, 0));
//    WeeklyMeeting wm2 = new WeeklyMeeting(DayOfWeek.MONDAY, Duration.ofHours(1), "M101", LocalTime.of(10, 5));
//    WeeklyMeeting wm3 = new WeeklyMeeting(DayOfWeek.MONDAY, Duration.ofHours(1), "M101", LocalTime.of(9, 30));
//
//    @BeforeEach
//    public void setUp() {
//        stu1 = new Student(1, "Abd", "email", new Department("null", new Faculty("null", wm1)));
//        stu2 = new Student(1, "Ali", null, null);
//        stu3 = new Student(1, "Omar", null, null);
//        c1 = new Course("SWER141", null, 1, List.of(wm1));// 9
//        c2 = new Course("SWER241", null, 1, List.of(wm2));// 10:5
//        c3 = new Course("SWER348", null, 1, List.of(wm3));// 9:30
//
//        stu1.addRegisteredCourse(c1);
//    }
//
//    @Test
//    public void isFreeOnTest() {
//
//        // assertEquals(false, stu1.isFreeOn(wm1));
//        assertAll(
//                () -> assertEquals(false, stu1.isFreeOn(wm1)),
//                () -> assertEquals(true, stu1.isFreeOn(wm2)),
//                () -> assertEquals(false, stu1.isFreeOn(wm3)));
//    }
//
//    @Test
//    public void isFreeOnTest2() {
//        // stu1 = new Student(1, "Abd", "email", new Department("null", new
//        // Faculty("null", wm1)));
//
//        // c1 = new Course("SWER141", null, 1, List.of(wm1));// 9
//        // c2 = new Course("SWER241", null, 1, List.of(wm2));// 10:5
//        // c3 = new Course("SWER348", null, 1, List.of(wm3));// 9:30
//
//        // stu1.addRegisteredCourse(c1);
//
//        assertFalse(stu1.isFreeOn(List.of(wm1, wm3)));
//
//    }
//
//}
