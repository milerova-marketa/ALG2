package interfacevariant;

import java.util.ArrayList;

/**
 *
 * @author Marketa.Milerova
 */
public class Main {

    public static void main(String[] args) {
        Circle c1 = Circle.getInstanceR(1);
        Circle c2 = Circle.getInstanceD(4);
        Rectangle r1 = new Rectangle(2, 3);
        Square s1 = new Square(3);

        double areaAll = c1.area() + c2.area() + r1.area() + s1.area();
        ArrayList shapes = new ArrayList();
        shapes.add(c1);
        shapes.add(c2);
        shapes.add(r1);
        shapes.add(s1);
        double areaAll1 = 0;
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i) instanceof Circle) {
                areaAll1 += ((Circle) shapes.get(i)).area();
            } else if (shapes.get(i) instanceof Rectangle) {
                areaAll1 += ((Rectangle) shapes.get(i)).area();
            }
        }
        System.out.println(areaAll);
        System.out.println(areaAll1);

        // lepší cesta
        ArrayList<ShapeInterface> shapes1 = new ArrayList();
        shapes1.add(c1);
        shapes1.add(c2);
        shapes1.add(r1);
        shapes1.add(s1);
        double areaAll2 = 0;
        for (ShapeInterface shape : shapes1) {
            areaAll2 += shape.area();
        }
        System.out.println(areaAll2);
        // vznikl polymorfismus - nevím která metoda area se mi zavolá
    }

}
