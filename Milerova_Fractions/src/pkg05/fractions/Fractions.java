/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg05.fractions;

/**
 *
 * @author Marketa.Milerova
 */
public class Fractions {

    public static void main(String[] args) {
        Fraction z1 = new Fraction(8, 20);
        Fraction z2 = new Fraction(3, 4);
        System.out.println(FractionsCalculator.add(z1, z2));
        System.out.println(FractionsCalculator.substract(z1, z2));
        System.out.println(FractionsCalculator.substract(z2, z1));
        System.out.println(FractionsCalculator.multiply(z1, z2));
        System.out.println(FractionsCalculator.divide(z1, z2));
        System.out.println(FractionsCalculator.divide(z2, z1));
    }

}
