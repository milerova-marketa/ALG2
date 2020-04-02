package shapes;

/**
 *
 * @author Marketa.Milerova
 */
public class Circle extends Shape {

    private double r;

    private Circle(double r) {
        this.r = r;
    }
    //dodělat tovární metodu na zadání poloměru a průměru
    public static Circle getInstanceR(double r){
        return new Circle(r);
    }
    public static Circle getInstanceD(double d){
        return new Circle(d/2);
    }

    public double getR() {
        return r;
    }

    @Override
    public String toString() {
        return "Circle{" + "r=" + r + '}';
    }

    @Override
    public double area() {
        return Math.PI * r * r;
    }
//    public static void main(String[] args) {
//        Circle c = new Circle(2);
//        System.out.println(c.area());
//    }

    @Override
    public double perimeter() {
        return 2*Math.PI*r;
    }
}
