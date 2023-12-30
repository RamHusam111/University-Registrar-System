package object_orienters;

import org.junit.jupiter.api.Test;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

class WeeklyMeetingTest {

    @Test
    void testConstructorAndGetters() {
        WeeklyMeeting meeting = new WeeklyMeeting(DayOfWeek.MONDAY, Duration.ofHours(2), "M101", LocalTime.of(10, 0));
        assertEquals(DayOfWeek.MONDAY, meeting.getDay());
        assertEquals(Duration.ofHours(2), meeting.getDuration());
        assertEquals("M101", meeting.getRoom());
        assertEquals(LocalTime.of(10, 0), meeting.getHour());
    }

    @Test
    void testSetters() {
        WeeklyMeeting meeting = new WeeklyMeeting(DayOfWeek.MONDAY, Duration.ofHours(2), "101", LocalTime.of(10, 0));
        meeting.setDay(DayOfWeek.TUESDAY);
        meeting.setDuration(Duration.ofHours(1));
        meeting.setRoom("102");
        meeting.setHour(LocalTime.of(11, 0));

        assertEquals(DayOfWeek.TUESDAY, meeting.getDay());
        assertEquals(Duration.ofHours(1), meeting.getDuration());
        assertEquals("102", meeting.getRoom());
        assertEquals(LocalTime.of(11, 0), meeting.getHour());
    }

    @Test
    void testToString() {
        WeeklyMeeting meeting = new WeeklyMeeting(DayOfWeek.MONDAY, Duration.ofHours(2), "101", LocalTime.of(10, 0));
        String expectedString = "Weekly Meeting:>MONDAY 120 10:00 101";
        assertEquals(expectedString, meeting.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        WeeklyMeeting meeting1 = new WeeklyMeeting(DayOfWeek.MONDAY, Duration.ofHours(2), "101", LocalTime.of(10, 0));
        WeeklyMeeting meeting2 = new WeeklyMeeting(DayOfWeek.MONDAY, Duration.ofHours(2), "101", LocalTime.of(10, 0));
        WeeklyMeeting meeting3 = new WeeklyMeeting(DayOfWeek.TUESDAY, Duration.ofHours(1), "102", LocalTime.of(11, 0));

        assertEquals(meeting1, meeting2);
        assertNotEquals(meeting1, meeting3);
    }

    @Test
    void testEqualsTime() {
        WeeklyMeeting meeting1 = new WeeklyMeeting(DayOfWeek.MONDAY, Duration.ofHours(2), "101", LocalTime.of(10, 0));
        WeeklyMeeting meeting2 = new WeeklyMeeting(DayOfWeek.MONDAY, Duration.ofHours(2), "102", LocalTime.of(10, 0));

        assertTrue(meeting1.equalsTime(meeting2));
    }

    @Test
    void testHasRoomConflict() {
        WeeklyMeeting meeting1 = new WeeklyMeeting(DayOfWeek.MONDAY, Duration.ofHours(2), "S206", LocalTime.of(10, 0));
        WeeklyMeeting meeting2 = new WeeklyMeeting(DayOfWeek.MONDAY, Duration.ofHours(1), "S206", LocalTime.of(11, 0));
        WeeklyMeeting meeting3 = new WeeklyMeeting(DayOfWeek.TUESDAY, Duration.ofHours(1), "S206", LocalTime.of(10, 0));

        assertTrue(meeting1.hasRoomConflict(meeting2));
        assertFalse(meeting1.hasRoomConflict(meeting3));
    }
}
