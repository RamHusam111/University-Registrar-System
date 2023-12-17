package object_orienters;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Hello world!
 *
 */
public class RegistrarDriver {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("students.txt")));
        List<Student> students = new ArrayList<>();
        String line;

        Department chemistryDepartment = new Department("Chemistry", new Faculty("Science", null));
                
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line, ",");
            int id = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            String email = st.nextToken();

            Student student = new Student(id,email, name, chemistryDepartment);
            students.add(student);
        }
        br.close();
        students.stream().forEach(e -> System.out.println(e));
    }
}
