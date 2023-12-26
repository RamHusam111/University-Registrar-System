package object_orienters;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Semester {

    private String name; //name of the semester
    private String semesterName; //name + year
    private LocalDate semesterStartDate;
    private LocalDate semesterEndDate;
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Course> courses;
    private final long weeksNumber;
    public boolean isFall;
    public boolean isSpring;
    public boolean isSummer;

    public Semester(LocalDate semesterStartDate, LocalDate semesterEndDate, List<Student> students,
                    List<Teacher> teachers, List<Course> courses) {

        this.semesterStartDate = semesterStartDate;
        this.semesterEndDate = semesterEndDate;
        this.name = giveName();
        this.semesterName = this.name + " - " + semesterStartDate.getYear();
        this.students = students;
        this.teachers = teachers;
        this.courses = courses;
        this.weeksNumber = calculateWeeksBetween(semesterStartDate, semesterEndDate);
        this.isFall = this.name.equals("Fall");
        this.isSpring = this.name.equals("Spring");
        this.isSummer = this.name.equals("Summer");
    }




    //TODO: Test this method
    public void registerInACourse(Course course, List<Student> lStudents) {

        boolean y = courses.stream().flatMap(e -> e.getWeeklyMeetings().stream()).anyMatch( wm -> course.getWeeklyMeetings().stream().anyMatch( wm2 -> wm2.hasRoomConflict(wm)));
        if(y){
            System.out.println("Error registering " + course.getCourseName() + " because of conflict");
            return;
        }
       

        //check if prerequisits are met
        lStudents.stream().filter(e -> !e.preRequisitesCheck(course)).forEach(student ->{
        System.out.println("Prequisites need to be completed for "+student.getId() +": "
         + student.getName() + "> to registered in " + course.getCourseName());
        });

        //check if student is free on weekly meetings
        lStudents.stream().filter(e -> !e.isFreeOn(course.getWeeklyMeetings())).forEach(student -> {
            System.out.println("Error registering " + student.getId() + " " + student.getName() + " in "
                    + course.getCourseName() + " because of conflict");
        });

        lStudents.stream().filter(e -> e.isFreeOn(course.getWeeklyMeetings()) && e.preRequisitesCheck(course)).forEach(student -> {
            student.getRegisteredCourses().add(course);
            System.out.println(student.getId() + " " + student.getName() + " registered in " + course.getCourseName());
        });
        courses.add(course);
        course.getTeacher().getRegisteredCourses().add(course);
        this.students.addAll(students);
        this.teachers.add(course.getTeacher());

    }

    public void setSemesterStartDate(LocalDate semesterStartDate) {
        this.semesterStartDate = semesterStartDate;
    }

    public void setSemesterEndDate(LocalDate semesterEndDate) {
        this.semesterEndDate = semesterEndDate;
    }
    private void setName(String name) {
        this.name = name;
    }
    
    //TODO: Test this method
    public String giveName() {
        int startMonth = semesterStartDate.getMonthValue();
        if ((startMonth >= 9 && startMonth <= 12)) {
            // Fall semester (September to December)
            this.setName("Fall");
        }
        else if ((startMonth >= 1 && startMonth <= 6)) {
            // Spring semester (January to May)
            this.setName("Spring");
        }
        else if ((startMonth >= 6 && startMonth <= 8)) {
            // Summer semester (June to August)
            this.setName("Summer");
        }

        return name;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public long getWeeksNumber() {
        return weeksNumber;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Course> getCourse() {
        return courses;
    }

    public LocalDate getSemesterStartDate() {
        return semesterStartDate;
    }

    public LocalDate getSemesterEndDate() {
        return semesterEndDate;
    }

    //TODO: Test this method
    static long calculateWeeksBetween(LocalDate startDate, LocalDate endDate) {
        // Calculate the number of weeks in the Semester
        return ChronoUnit.WEEKS.between(startDate, endDate);
    }
    





}
