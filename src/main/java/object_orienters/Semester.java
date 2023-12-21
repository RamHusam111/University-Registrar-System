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

    public Semester( LocalDate semesterStartDate, LocalDate semesterEndDate, List<Student> students,
            List<Teacher> teachers, List<Course> courses) {
        weeksNumber = calculateWeeksBetween(semesterStartDate, semesterEndDate);
        // give the semester name based on specific date then concat it with start year
        this.semesterName = this.giveName() + " - " + semesterStartDate.getYear();
        this.semesterStartDate = semesterStartDate;
        this.semesterEndDate = semesterEndDate;
        this.students = students;
        this.teachers = teachers;
        this.courses = courses;
    }



    
    public void registerInACourse(Course course, List<Student> students) {

        boolean y = courses.stream().flatMap(e -> e.getWeeklyMeetings().stream()).anyMatch( wm -> course.getWeeklyMeetings().stream().anyMatch( wm2 -> wm2.hasRoomConflict(wm)));
        if(y){
            System.out.println("Error registering " + course.getCourseName() + " because of conflict");
            return;
        }
       

        //check if prerequisits are met
        students.stream().filter(e -> !e.preRequisitesCheck(course)).forEach(student ->{
        System.out.println("Prequisites need to be completed for "+student.getId() +": "
         + student.getName() + "> to registered in " + course.getCourseName());
        });

        //check if student is free on weekly meetings
        students.stream().filter(e -> !e.isFreeOn(course.getWeeklyMeetings())).forEach(student -> {
            System.out.println("Error registering " + student.getId() + " " + student.getName() + " in "
                    + course.getCourseName() + " because of conflict");
        });

        students.stream().filter(e -> e.isFreeOn(course.getWeeklyMeetings()) && e.preRequisitesCheck(course)).forEach(student -> {
            student.getRegisteredCourses().add(course);
            System.out.println(student.getId() + " " + student.getName() + " registered in " + course.getCourseName());
        });
        courses.add(course);
        course.getTeacher().getRegisteredCourses().add(course);
        


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
    private static long calculateWeeksBetween(LocalDate startDate, LocalDate endDate) {
        // Calculate the number of weeks in the semester
        return ChronoUnit.WEEKS.between(startDate, endDate);
    }
    
    public boolean isFall = giveName().equals("Fall");

    public boolean isSpring = giveName().equals("Spring");

    public boolean isSummer = giveName().equals("Summer");





}
