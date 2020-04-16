package comparingstudents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Marketa.Milerova
 */
public class Datasource {

    private static Student[] data = {
        new Student("Alice", "Mala", 345, 1, 2, 3, 2.5, 2),
        new Student("Bob", "Velky", 123, 1, 1, 2.5, 2, 1, 1),
        new Student("Cyril", "Stredni", 567, 1.5, 1, 1.5, 1, 1, 1)
    };

    public static Student[] loadDataAsArray() {
        return Arrays.copyOf(data, data.length);
    }

    public static List<Student> loadDataAsList() {
//        ArrayList<Student> students = new ArrayList<>();
//        students.addAll(Arrays.asList(data));
        return Arrays.asList(data);
    }
}
