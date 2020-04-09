package shapes;

/**
 *
 * @author Marketa.Milerova
 */
public abstract class Shape implements Comparable<Shape> {

    //data
    protected String name = "Geometric object"; //protected je viditelný v potomcích

    public abstract double area(); //všichni potomci musí mít tuto metodu, musí ji mít naimplementovanou

    public abstract double perimeter();

    public String getShapeName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() { //prekryti toString Objectu, defaultní implementace, která může být překryta
        return name + ": " + getShapeName();
    }

    @Override
    public int compareTo(Shape o) {
        if (this.area() > o.area()) {
            return -1;
        } else if (this.area() < o.area()) {
            return 1;
        }
        return 0;
    }
}
