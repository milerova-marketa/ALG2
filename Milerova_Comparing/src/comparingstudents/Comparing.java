package comparingstudents;

import java.text.Collator;
import static mycomparing.MyComparing.print;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
import java.util.Locale;

/**
 *
 * @author Marketa.Milerova
 */
public class Comparing {

    public static void main(String[] args) {
        Student[] students = Datasource.loadDataAsArray();
        print(students);
        System.out.println("Sort by number");
        //sort(students);
        Arrays.sort(students); //students musí být typově kompatibilní s interface Comparable
        print(students);
        System.out.println("List");
        List<Student> students1 = Datasource.loadDataAsList();
        print(students1);
        System.out.println("Sort by number");
        //sort(students);
        Collections.sort(students1); //students musí být typově kompatibilní s interface Comparable
        print(students1);
        System.out.println(students[0].equals(students[1]));

        System.out.println("Sort by first name");
        Arrays.sort(students, new ComparatorByFirstName());
        print(students);

        System.out.println("Sort by last name");
        Arrays.sort(students, new Comparator<Student>() { //anonymní třída
            @Override
            public int compare(Student o1, Student o2) {
                Collator col = Collator.getInstance(new Locale("cs", "CZ"));
                return col.compare(o1.getLastName(), o2.getLastName());
//                return o1.getLastName().compareToIgnoreCase(o2.getLastName());
            }
        });
//        Arrays.sort(students, (Student o1, Student o2) -> o1.getLastName().compareTo(o2.getLastName())); //lambda výraz
        print(students);
        //to do: pole známek, prumer znamek, setridit podle prumeru
        System.out.println("Sort by grade");
        Arrays.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.getAverageGrade() > o2.getAverageGrade()) {
                    return 1;
                };
                return -1;
            }
        });
        print(students);
    }
}
