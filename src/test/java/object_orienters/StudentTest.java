//package object_orienters;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.time.LocalDate;
//import java.time.Year;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//
//public class StudentTest {
//    Student stu1 = new Student("Abd", null);
//    Student stu2 = new Student("Ali", null);
//    Student stu3 = new Student("Omar", null);
//    Course c1 = new Course("SWER141", "Intro to Programming", null, 3, null);
//    Course c2 = new Course("SWER241", "Data Structure", null, 3, null);
//    Course c3 = new Course("SWER348", "Advanced Object Oriented", null, 3, null);
//
//    @Test
//    public void testEnterGradesMethod() {
//        Map<Course, String> map1 = new HashMap<>();
//        map1.put(c1, "A");
//        map1.put(c2, "B+");
//        map1.put(c3, "D");
//        Map<Course, String> map2 = new HashMap<>();
//        map2.put(c1, "B");
//        map2.put(c2, "B+");
//        map2.put(c3, "F");
//        Map<Course, String> map3 = new HashMap<>();
//        map3.put(c1, "C");
//        map3.put(c2, "A");
//        map3.put(c3, "A");
//
//        stu1.enterGrades(map1);
//        stu2.enterGrades(map2);
//        stu3.enterGrades(map3);
//
//        assertEquals(4.0, stu1.getCompletedCoursesGrades().get(c1));
//        assertEquals(3.5, stu1.getCompletedCoursesGrades().get(c2));
//        assertEquals(1.0, stu1.getCompletedCoursesGrades().get(c3));
//
//        assertEquals(3.0, stu2.getCompletedCoursesGrades().get(c1));
//        assertEquals(3.5, stu2.getCompletedCoursesGrades().get(c2));
//        assertEquals(0.0, stu2.getCompletedCoursesGrades().get(c3));
//
//        assertEquals(2.0, stu3.getCompletedCoursesGrades().get(c1));
//        assertEquals(4.0, stu3.getCompletedCoursesGrades().get(c2));
//        assertEquals(4.0, stu3.getCompletedCoursesGrades().get(c3));
//    }
//
//    @Test
//    public void testpreRequisitesCheckMethod() {
//        testEnterGradesMethod();
//        c2.addPrerequisites(c1);
//        c3.addPrerequisites(c1);
//        c3.addPrerequisites(c2);
//        assertTrue(stu1.preRequisitesCheck(c3));
//        assertTrue(stu2.preRequisitesCheck(c2));
//        assertTrue(stu2.preRequisitesCheck(c3));
//        assertTrue(stu3.preRequisitesCheck(c3));
//    }
//
//    @Test
//    public void testCalculateGpaMethod() {
//        testEnterGradesMethod();
//        assertEquals("0", 2.8333333333333335, stu1.calculateGPA(), 0);
//        assertEquals("0", 2.1666666666666665, stu2.calculateGPA(), 0);
//        assertEquals("0", 3.3333333333333335, stu3.calculateGPA(), 0);
//    }
//
//}
