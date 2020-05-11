package milerova_hurricane;

import java.util.Scanner;

/**
 *
 * @author Marketa.Milerova
 */
public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static HurricaneHandlerInterface hh;
    public static boolean end = false;

    public static void main(String[] args) {
        try {
            hh = new HurricaneHandler();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        while (!end) {
            try {
                menu();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void menu() {
        System.out.println("Zvolte:");
        System.out.println("1. Výpis informací o všech hurikánech ve zvoleném období");
        System.out.println("2. Výpis informací o daném hurikánu");
        System.out.println("3. Výpis všech hurikánů seřazených dle rychlosti");
        System.out.println("4. Konec aplikace");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                System.out.println("Napište počáteční a koncový rok daného období");
                int startYear = sc.nextInt();
                int endYear = sc.nextInt();
                System.out.println(hh.getList(startYear, endYear));
                break;
            case 2:
                System.out.println("Napište jméno hurikánu");
                String name = sc.next();
                System.out.println(hh.getList(name));
                break;
            case 3:
                System.out.println(hh.getList());
                break;
            case 4:
                end = true;
                break;
            default:
                System.out.println("Invalid number");
        }
    }

}
