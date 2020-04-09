/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes;

/**
 *
 * @author Marketa.Milerova
 */
public class Ellipse extends Shape {

    private double a;
    private double b;

    public Ellipse(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double area() {
        return a * b * Math.PI;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" a = %.2f, b = %.2f", a,b);
    }

    @Override
    public double perimeter() {
        return Math.PI*Math.sqrt(2*(a*a+b*b));
    }
    
}
