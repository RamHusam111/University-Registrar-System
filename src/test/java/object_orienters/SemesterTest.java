package object_orienters;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

public class SemesterTest {

    List<Semester> validSemesters = new ArrayList<>();
    List<Semester> invalidSemesters = new ArrayList<>();

    @BeforeAll
    public void setUp() {
        validSemesters.add(new Semester(LocalDate.of(2020, 9, 1), LocalDate.of(2020, 12, 15), null, null, null));
        validSemesters.add(new Semester(LocalDate.of(2021, 2, 1), LocalDate.of(2021, 6, 15), null, null, null));
        validSemesters.add(new Semester(LocalDate.of(2021, 7, 1), LocalDate.of(2021, 8, 15), null, null, null));

        invalidSemesters.add(new Semester(LocalDate.of(2020, 9, 1), LocalDate.of(2019, 12, 15), null, null, null));
        //invalidSemesters.add(new Semester(LocalDate.of(2021, 2, 1), LocalDate.of(2021, 1, 15), null, null, null));
    }

    @Test
    public void testSemesterGiveName() {
       // setUp();
validSemesters.add(new Semester(LocalDate.of(2020, 9, 1), LocalDate.of(2020, 12, 15), null, null, null));
        validSemesters.add(new Semester(LocalDate.of(2021, 2, 1), LocalDate.of(2021, 6, 15), null, null, null));
        validSemesters.add(new Semester(LocalDate.of(2021, 7, 1), LocalDate.of(2021, 8, 15), null, null, null));


        assertAll("Semester Name",
            () -> assertEquals("Fall 2020", validSemesters.get(0).giveName()),
            () -> assertEquals("Spring 2021", validSemesters.get(1).giveName()),
            () -> assertEquals("Summer 2021", validSemesters.get(2).giveName())
        );
    }

}
