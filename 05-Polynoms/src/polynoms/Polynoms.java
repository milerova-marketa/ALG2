package polynoms;

/**
 *
 * @author Marketa.Milerova
 */
public class Polynoms {

    //díky tomuhle se z knihovní třídy nestane objekt
    private Polynoms() {

    }

    public static void main(String[] args) {
        Polynom p1 = Polynom.getInstance(1,2,3,4,5);
        Polynom p2 = Polynom.getInstance(5, 3, 2);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(sum(p1, p2));
        System.out.println(multiply(p1, p2));
    }

    public static Polynom sum(Polynom a, Polynom b) {
        Polynom max = (a.getDegree() > b.getDegree()) ? a : b;
        Polynom min = (a.getDegree() > b.getDegree()) ? b : a;
        double[] sumCoef = new double[max.getDegree() + 1];
        for (int i = 0; i < max.getDegree() + 1; i++) {
            sumCoef[i] = max.getCoefAt(i);
        }
        for (int i = 0; i < min.getDegree() + 1; i++) {
            sumCoef[i] += min.getCoefAt(i);
        }
        return Polynom.getInstanceReverted(sumCoef);
    }

    public static Polynom multiply(Polynom a, Polynom b) {
        double[] multiCoef = new double[a.getDegree() + b.getDegree() + 1];
        for (int i = 0; i < a.getDegree() + 1; i++) {
            for (int j = 0; j < b.getDegree() + 1; j++) {
                multiCoef[i + j] += (a.getCoefAt(i) * b.getCoefAt(j));
            }
        }
        return Polynom.getInstanceReverted(multiCoef);
    }
}
