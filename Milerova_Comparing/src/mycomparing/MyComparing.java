package mycomparing;

import comparingstudents.Datasource;
import comparingstudents.Student;
import java.util.List;

/**
 *
 * @author Marketa.Milerova
 */
public class MyComparing {

    public static void main(String[] args) {
        Student[] students = Datasource.loadDataAsArray();
        print(students);
        System.out.println("Sort by number");
        sort(students, new ComparatorStudentByNumber());
        print(students);
        System.out.println("Sort by last name");
        sort(students,new ComparatorByLastName());
        print(students);
        System.out.println("Sort by first name");
        sort(students, new ComparatorByFirstName());
        print(students);
        System.out.println("Sort by grade");
        sort(students,new ComparatorByGrade());
        print(students);
    }

    public static void sort(CompareInterface[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 1; j < array.length - i; j++) {
                if (array[j - 1].isBigger(array[j])) {
                    CompareInterface temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
    }

    public static void sort(Object[] array, ComparatorInterface o) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 1; j < array.length - i; j++) {
                if (o.bigger(array[j - 1], array[j])) {
                    Object temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
    }

    public static void print(Object[] array) {
        for (Object object : array) {
            System.out.println(object);
        }
    }

    public static void print(List list) {
        for (Object object : list) {
            System.out.println(object);
        }
    }
}
