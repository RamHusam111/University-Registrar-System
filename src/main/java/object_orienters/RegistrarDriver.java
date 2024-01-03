package object_orienters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Hello world!
 *
 */
public class RegistrarDriver extends Thread {

    public static Runnable Action1 = () -> {
        System.out.println("Hello World! its Action 1");
    };

    public static Map<String, Semester> semesters = new HashMap<>();
    public static Map<Integer, Student> students = new HashMap<>();
    public static Map<Integer, Teacher> teachers = new HashMap<>();
    public static Map<String, Course> courses = new HashMap<>();
    public static List<WeeklyMeeting> weeklyMeetings = new ArrayList<>();
    public static Map<String, Specialization> specializations = new HashMap<>();
    public static Map<String, Faculty> faculties = new HashMap<>();

    public static void main(String[] args) throws InterruptedException, IOException {

        
        
        ////////////////////////////////
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int input = 1;

        while (input != 0) {
            System.out.println("Choose what you want to do: ");
            System.out.println(
                    "1 - Create New Semester \n2 - Add New Student \n3 - Add New Teacher \n4 - Add New Course \n5 - View Course Prerequisites \n6 - Register \n7 - View Registered Students \n8 - View Registered Teachers \n9 - View Available Courses \n0 - Exit");

            try {
                input = Integer.parseInt(in.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 0 and 9");
                continue;
            }

            Task t = TaskSwitcher.get(input);

            t.start();
            t.join();

        }

        in.close();

    }

}
