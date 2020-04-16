package comparingstudents;

import mycomparing.CompareInterface;
import java.util.Objects;

/**
 *
 * @author Marketa.Milerova
 */
public class Student implements CompareInterface, Comparable<Student> {

    private String firstName;
    private String lastName;
    private int studentNumber;
    private double[] grades;

    public Student(String firstName, String lastName, int studentNumber, double... grades) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
        this.grades = grades;
    }

    @Override
    public String toString() {
        return String.format("%10s%10s%10d%10.2f", firstName, lastName, studentNumber,getAverageGrade());
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean isBigger(CompareInterface o) {
        return this.studentNumber > ((Student) o).studentNumber;
    }

    @Override
    public int compareTo(Student o) {
        return this.studentNumber - o.studentNumber;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.firstName);
        hash = 17 * hash + Objects.hashCode(this.lastName);
        hash = 17 * hash + this.studentNumber;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (this.studentNumber != other.studentNumber) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        return true;
    }
    public double getAverageGrade(){
        double sum = 0;
        for (double grade : grades) {
            sum+=grade;
        }
        return sum/grades.length;
    }

}
