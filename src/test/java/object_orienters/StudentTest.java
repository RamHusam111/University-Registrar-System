package object_orienters;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest {
    Student stu1;
    Student stu2;
    Student stu3;
    Course c1;
    Course c2;
    Course c3;

    @BeforeEach
    public void setUp() {
        
    }

    @Test
    public void testEnterGradesMethod() {



        stu1 = new Student(1, "Abd", null, null);
        stu2 = new Student(1, "Ali", null, null);
        stu3 = new Student(1, "Omar", null, null);
        c1 = new Course("SWER141", null, 3, null);
        c2 = new Course("SWER241", null, 3, null);
        c3 = new Course("SWER348", null, 3, null);


        Map<Course, String> map1 = new HashMap<>();
        map1.put(c1, "A");
        map1.put(c2, "B+");
        map1.put(c3, "D");
        Map<Course, String> map2 = new HashMap<>();
        map2.put(c1, "B");
        map2.put(c2, "B+");
        map2.put(c3, "F");
        Map<Course, String> map3 = new HashMap<>();
        map3.put(c1, "C");
        map3.put(c2, "A");
        map3.put(c3, "A");

        stu1.enterGrades(map1);
        stu2.enterGrades(map2);
        stu3.enterGrades(map3);

        assertEquals(4.0, stu1.getCompletedCourses().get(c1));
        assertEquals(3.5, stu1.getCompletedCourses().get(c2));
        assertEquals(1.0, stu1.getCompletedCourses().get(c3));

        assertEquals(3.0, stu2.getCompletedCourses().get(c1));
        assertEquals(3.5, stu2.getCompletedCourses().get(c2));
        assertEquals(0.0, stu2.getCompletedCourses().get(c3));

        assertEquals(2.0, stu3.getCompletedCourses().get(c1));
        assertEquals(4.0, stu3.getCompletedCourses().get(c2));
        assertEquals(4.0, stu3.getCompletedCourses().get(c3));
    }

}
