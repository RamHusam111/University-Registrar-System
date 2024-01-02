package object_orienters;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class RegistrarDriver extends Thread {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Choose what you want to do: ");
        System.out.println(
                "1 - Create New Semester \n2 - Add New Student \n3 - Add New Teacher \n4 - Add New Course \n5 - View Course Prerequisites \n6 - Register \n7 - View Registered Students \n8 - View Registered Teachers \n9 - View Available Courses");
        int input = in.nextInt();
        int n = 8; // Number of threads
        // here we print the console
        RegistrarDriver thread = new RegistrarDriver();
        thread.start();
    }

    public void run() {
        try {
            // Displaying the thread that is running
            System.out.println(
                    "Thread " + Thread.currentThread().getName()
                            + " is running");
        } catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }

}
