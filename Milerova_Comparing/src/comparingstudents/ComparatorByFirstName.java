
package comparingstudents;

import java.util.Comparator;

/**
 *
 * @author Marketa.Milerova
 */
public class ComparatorByFirstName implements Comparator<Student>{

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getFirstName().compareToIgnoreCase(o2.getFirstName());
    }
    
}
