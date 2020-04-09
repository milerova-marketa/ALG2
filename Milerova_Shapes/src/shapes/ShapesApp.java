/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Marketa.Milerova
 */
public class ShapesApp {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Shape> shapes = new ArrayList();

    public static void main(String[] args) {
        int option = 0;
        do {
            option = GUI();
            switch (option) {
                case 1:
                    addObject();
                    break;
                case 2:
                    System.out.println("Celková plocha: " + areaOfAll());
                    break;
                case 3:
                    printAll(shapes);
                    break;
                case 4:
                    int imax = maxArea();
                    System.out.println("Objekt s největším obsahem je: " + shapes.get(imax) + ", jehož obsah je: " + shapes.get(imax).area());
                    break;
                case 5:
                    sort();
                    break;
                case 6:
                    shapes.clear();
                    break;
                case 7: break;
                default:
                    System.out.println("Zadejte validní číslo");
            }
        } while (option != 7);
    }

    private static double areaOfAll() {
        double area = 0;
        for (Shape shape : shapes) {
            area += shape.area();
        }
        return area;
    }

    private static void printAll(ArrayList<Shape> array) {
        System.out.println("-------------------------");
        System.out.println("Máme zadány tyto objekty:");
        for (Shape object : array) {
            System.out.println(object);
        }
        System.out.println("-------------------------");
    }

    private static int GUI() {
        System.out.println("------------------------------------------");
        System.out.println("Vyberte jednu z následujích možností:");
        System.out.println("1 - Přidání objektu");
        System.out.println("2 - Zobrazení celkové plochy");
        System.out.println("3 - Výčet všech přidaných objektů");
        System.out.println("4 - Zobrazení objektu s maximálním obsahem");
        System.out.println("5 - Výčet všech objektů sestupně podle obsahu");
        System.out.println("6 - Reset");
        System.out.println("7 - Konec");
        System.out.println("------------------------------------------");
        System.out.println("");
        return sc.nextInt();
        //1 2 4 6 1 1 4 1 3 2 1 3 7
    }

    private static int GUIShape() {
        System.out.println("---------------");
        System.out.println("Chcete přidat");
        System.out.println("1 - Čtverec");
        System.out.println("2 - Obdélník");
        System.out.println("3 - Kruh");
        System.out.println("4 - Elipsu");
        System.out.println("5 - Trojúhelník");
        System.out.println("---------------");
        System.out.println();
        return sc.nextInt();
    }

    private static void addObject() {
        double a, b, r, c;
        int option = 0;
        do {
            option = GUIShape();
            switch (option) {
                case 1:
                    System.out.println("Zadejte délku strany čtverce");
                    a = sc.nextDouble();
                    shapes.add(new Square(a));
                    break;
                case 2:
                    System.out.println("Zadejte délky stran obdélníka");
                    a = sc.nextDouble();
                    b = sc.nextDouble();
                    shapes.add(new Rectangle(a, b));
                    break;
                case 3:
                    System.out.println("Zadejte poloměr kruhu");
                    r = sc.nextDouble();
                    shapes.add(Circle.getInstanceR(r));
                    break;
                case 4:
                    System.out.println("Zadejte délku hlavní a vedlejší poloosy");
                    a = sc.nextDouble();
                    b = sc.nextDouble();
                    shapes.add(new Ellipse(a, b));
                    break;
                case 5:
                    System.out.println("Zadejte délky všech stran trojúhelníku");
                    a = sc.nextDouble();
                    b = sc.nextDouble();
                    c = sc.nextDouble();
                    shapes.add(new Triangle(a, b, c));
                    break;
                default:
                    System.out.println("Zadejte validní číslo");
            }
        } while (option <= 0 || option > 5);
    }

    private static int maxArea() {
        double max = 0;
        int imax = -1;
        for (int i = 0; i < shapes.size(); i++) {
            if (max < shapes.get(i).area()) {
                imax = i;
                max = shapes.get(i).area();
            }

        }
        return imax;
    }

    private static void sort() {
        ArrayList<Shape> shapesSort = (ArrayList<Shape>)shapes.clone();
        Collections.sort(shapesSort);  
        printAll(shapesSort);
    }

}
