/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fractions;

/**
 *
 * @author Marketa.Milerova
 */
public class Fractions {

    public static void main(String[] args) {
        Fraction z1 = new Fraction();
        z1.setCitatel(2);
        z1.setJmenovatel(5);
        Fraction z2 = new Fraction(3, 4);
        z2.setJmenovatel(8);
        System.out.println(FractionsCalculator.add(z1, z2));
        System.out.println(FractionsCalculator.substract(z1, z2));
        System.out.println(FractionsCalculator.substract(z2, z1));
        System.out.println(FractionsCalculator.multiply(z1, z2));
        System.out.println(FractionsCalculator.divide(z1, z2));
        System.out.println(FractionsCalculator.divide(z2, z1));
    }

}
