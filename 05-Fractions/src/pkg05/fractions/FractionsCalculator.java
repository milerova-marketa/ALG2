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
public class FractionsCalculator {

    public static Fraction add(Fraction a, Fraction b) {
        int cit = a.getCitatel() * b.getJmenovatel();
        int cit2 = b.getCitatel()*a.getJmenovatel();
        Fraction c = new Fraction(a.getCitatel() * b.getJmenovatel() + b.getCitatel() * a.getJmenovatel(), a.getJmenovatel() * b.getJmenovatel());
        return c;
    }

    public static Fraction substract(Fraction a, Fraction b) {
        Fraction c = new Fraction(-1*b.getCitatel(),b.getJmenovatel());
        return add(a, c);
    }

    public static Fraction multiply(Fraction a, Fraction b) {
        Fraction c = new Fraction(a.getCitatel() * b.getCitatel(), a.getJmenovatel() * b.getJmenovatel());
        return c;
    }
    
    public static Fraction divide(Fraction a, Fraction b){
        Fraction c = new Fraction(b.getJmenovatel(),b.getCitatel());
        return multiply(a, c);
    }
}
