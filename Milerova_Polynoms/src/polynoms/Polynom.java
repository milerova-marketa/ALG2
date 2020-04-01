/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polynoms;

import java.util.Arrays;

/**
 *
 * @author Marketa.Milerova
 */
public class Polynom {

    private double[] coef;

    // tovární metody
    public static Polynom getInstanceReverted(double... coef) {
        return new Polynom(coef);
    }

    public static Polynom getInstance(double... coef) {
        double[] temp = new double[coef.length];
        for (int i = 0; i < coef.length; i++) {
            temp[i] = coef[coef.length - i - 1];
        }
        return new Polynom(temp);
    }

    //defenzivní kopie (jinak není private jelikož se předává přes referenci)
    private Polynom(double[] coef) {
        double[] coefTemp = new double[coef.length];
        System.arraycopy(coef, 0, coefTemp, 0, coef.length);
        this.coef = coefTemp;
    }

    public double getValuefor(double x) {
        double value = 0;
        for (int i = coef.length - 1; i > 0; i--) {
            value = (value + coef[i]) * x;
        }
        value += coef[0];
        return value;
    }

    public double getCoefAt(int exp) {
        return coef[exp];
    }

    public double[] getAllCoef() {
        return Arrays.copyOf(coef, coef.length);
    }

    public int getDegree() {
        return coef.length - 1;
    }

    @Override
    public String toString() {
        String polynomial = "";
        for (int i = coef.length - 1; i >= 0; i--) {
            if (polynomial.equals("") && i == 0) {
                polynomial = polynomial + coef[i];
            } else {
                if (coef[i] != 0) {
                    if (polynomial.equals("")) {
                        polynomial = (coef[i] < 0 ? "-" : "");
                    } else {
                        polynomial = polynomial
                                + (coef[i] < 0 ? " - " : " + ");

                    }
                    polynomial = polynomial
                            + (Math.abs(coef[i]) == 1
                            && i != 0
                                    ? "" : Math.abs(coef[i]));
                    polynomial = polynomial
                            + (i <= 1
                                    ? (i == 1 ? "x" : "")
                                    : ("x^" + i));
                }
            }
        }
        return polynomial;
    }

    public Polynom derivate() {
        double[] coefD = new double[coef.length - 1];
        for (int i = 0; i < coefD.length; i++) {
            coefD[i] = (i + 1) * coef[i + 1];
        }
        return new Polynom(coefD);
    }

    public double integrate(double a, double b) {
        double[] coefI = new double[coef.length + 1];
        for (int i = 0; i < coef.length; i++) {
            coefI[i + 1] = coef[i] / (i + 1);
        }
        Polynom ip = new Polynom(coefI);
        return ip.getValuefor(b) - ip.getValuefor(a);
    }

//    public static void main(String[] args) {
//        Polynom p1 = Polynom.getInstance(1, 2, 3, 4);
//        System.out.println(p1);
//        System.out.println(p1.getValuefor(0));
//        System.out.println(p1.getValuefor(1));
//        System.out.println(p1.getValuefor(2));
//        System.out.println(p1.integrate(0, 3));
//    }
}