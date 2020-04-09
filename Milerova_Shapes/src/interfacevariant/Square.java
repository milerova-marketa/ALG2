package interfacevariant;

/**
 *
 * @author Marketa.Milerova
 */
public class Square extends Rectangle { //vztah: IS A, neboli každý čtverec je obdélník ale každý obdélník není čtverec

    private double a;

    public Square(double a) {
        super(a, a);
        this.a = a;
    }
    
    
//    public static void main(String[] args) {
//        Square s = new Square(4);
//        System.out.println(s.area());
//    }

    @Override
    public String toString() {
        return "Square{" + "a=" + a + '}';
    }

}
