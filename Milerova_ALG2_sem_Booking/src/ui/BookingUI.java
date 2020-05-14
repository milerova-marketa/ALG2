package ui;

import app.Company;
import app.CompanyInterface;
import java.util.Locale;
import java.util.Scanner;
import utils.Formater;

/**
 * upravit company aby pracoval podle stringu se seznamy
 *
 * @author Marketa.Milerova
 */
public class BookingUI {

    public static CompanyInterface booking;
    public static Scanner sc = new Scanner(System.in, "ISO-8859-2").useLocale(new Locale("cs", "CZ"));

    public static void main(String[] args) {
        booking = new Company();
        mainMenu();
    }

    private static void mainMenu() {
        boolean isEnd = false;
        int option;
        while (!isEnd) {
            System.out.println("***************************");
            System.out.println("1. Správa klientů");
            System.out.println("2. Správa objektů");
            System.out.println("3. Správa rezervací");
            System.out.println("4. Načíst data ze souborů");
            System.out.println("5. Výpis všech detailů");
            System.out.println("6. Konec");
            System.out.println("***************************");
            option = loadOption();
            switch (option) {
                case 1:
                    clientMenu();
                    break;
                case 2:
                    propertyMenu();
                    break;
                case 3:
                    reservationMenu();
                    break;
                case 4:
                    loadDataMenu();
                    break;
                case 5:
                    System.out.println(saveDataMenu());
                    break;
                case 6:
                    isEnd = true;
                    break;
                default:
                    System.out.println("Zadejte validní číslo");
            }
        }
    }

    private static void clientMenu() {
        String name = "client";
        boolean isEnd = false;
        int option, index;
        while (!isEnd) {
            System.out.println("*******************Seznam klientů*******************");
            System.out.print(booking.getList(name));
            System.out.println("****************************************************");
            System.out.println("1. Zobrazit detail");
            System.out.println("2. Přidat klienta");
            System.out.println("3. Seřadit");
            System.out.println("4. Návrat do hlavní nabídky");
            System.out.println("****************************************************");
            option = loadOption();
            switch (option) {
                case 1:
                    if (booking.canWe("client")) {
                        System.out.println("Zvolte číslo u klienta");
                        index = loadOption();
                        System.out.println("**************************Detail klienta**************************");
                        System.out.println(booking.getDetail(index, name));
                        System.out.println("******************************************************************");
                    } else {
                        System.out.println("Tuto operaci nelze provést");
                    }
                    break;
                case 2:
                    System.out.println(loadClient());
                    break;
                case 3:
                    if (booking.canWe("client")) {
                        System.out.println("Chcete řadit dle:");
                        System.out.println("1. Křestního jména");
                        System.out.println("2. Příjmení");
                        option = loadOption();
                        System.out.println(booking.sort(option, name));
                    } else {
                        System.out.println("Tuto operaci nelze provést");
                    }
                    break;
                case 4:
                    isEnd = true;
                    break;
                default:
                    System.out.println("Zadejte validní vstup");
            }
        }
    }

    private static void propertyMenu() {
        String name = "property";
        boolean isEnd = false;
        int option, index;
        String info;
        while (!isEnd) {
            System.out.println("*************************Seznam objektů*************************");
            System.out.print(booking.getList(name));;
            System.out.println("****************************************************************");
            System.out.println("1. Zobrazit detail");
            System.out.println("2. Přidat objekt");
            System.out.println("3. Seřadit");
            System.out.println("4. Návrat do hlavní nabídky");
            System.out.println("****************************************************************");
            option = loadOption();
            switch (option) {
                case 1:
                    if (booking.canWe("property")) {
                        System.out.println("Zvolte číslo u objektu");
                        index = loadOption();
                        System.out.println("********************************Detail objektu********************************");
                        System.out.print(booking.getDetail(index, name));
                        System.out.println("******************************************************************************");
                    } else {
                        System.out.println("Tuto operaci nelze provést");
                    }
                    break;
                case 2:
                    System.out.println(loadProperty());
                    break;
                case 3:
                    if (booking.canWe("property")) {
                        System.out.println("Chcete řadit dle:");
                        System.out.println("1. Názvu");
                        System.out.println("2. Destinace");
                        System.out.println("3. Ceny");
                        option = loadOption();
                        System.out.println(booking.sort(option, name));
                    } else {
                        System.out.println("Tuto operaci nelze provést");
                    }
                    break;
                case 4:
                    isEnd = true;
                    break;
                default:
                    System.out.println("Zadejte validní vstup");
            }
        }
    }

    private static void reservationMenu() {
        String name = "reservation";
        boolean isEnd = false;
        int option, index;
        while (!isEnd) {
            System.out.println("**************************Seznam rezervací*************************");
            System.out.print(booking.getList(name));
            System.out.println("*******************************************************************");
            System.out.println("1. Zobrazit detail");
            System.out.println("2. Přidat rezervaci");
            System.out.println("3. Odebrat rezervaci");
            System.out.println("4. Seřadit");
            System.out.println("5. Návrat do hlavní nabídky");
            System.out.println("*******************************************************************");
            option = loadOption();
            switch (option) {
                case 1:
                    if (booking.canWe("reservation")) {
                        System.out.println("Zvolte číslo u rezervace");
                        index = loadOption();
                        System.out.println("**************************************************************Detail rezervace*************************************************************");
                        System.out.println(booking.getDetail(index, name));
                        System.out.println("*******************************************************************************************************************************************");
                    } else {
                        System.out.println("Tuto operaci nelze provést");
                    }
                    break;
                case 2:
                    System.out.println(loadReservation());
                    break;
                case 3:
                    if (booking.canWe("reservation")) {
                        System.out.println("Zvolte číslo u rezervace");
                        index = loadOption();
                        System.out.println(booking.removeReservation(index));
                    } else {
                        System.out.println("Tuto operaci nelze provést");
                    }
                    break;
                case 4:
                    if (booking.canWe("reservation")) {
                        System.out.println("Chcete seřadit dle:");
                        System.out.println("1. Příjmení klienta");
                        System.out.println("2. Názvu objeku");
                        System.out.println("3. Data rezervace");
                        option = loadOption();
                        System.out.println(booking.sort(option, name));
                    } else {
                        System.out.println("Tuto operaci nelze provést");
                    }
                    break;
                case 5:
                    isEnd = true;
                    break;
                default:
                    System.out.println("Zadejte validní vstup");
            }
        }
    }

    private static void loadDataMenu() {
        System.out.println("1. Načíst textové soubory");
        System.out.println("2. Načíst binární soubory");
        System.out.println("3. Načíst xls soubory");
        int option = loadOption();
        System.out.println(booking.loadData(option));
    }

    private static String saveDataMenu() {
        System.out.println("Který seznam si přejete vidět detailně?");
        System.out.println("1. Seznam klientů");
        System.out.println("2. Seznam objektů");
        System.out.println("3. Seznam rezervací");
        int option = loadOption();
        System.out.println("Přejete si seznam seřadit dle:");
        switch (option) {
            case 1:
                if (booking.canWe("client")) {
                    System.out.println("1. Křestního jména");
                    System.out.println("2. Příjmení");
                    System.out.println("3. Národnosti");
                    System.out.println("4. Věku");
                } else {
                    return "Seznam je prázdný";
                }
                break;
            case 2:
                if (booking.canWe("property")) {
                    System.out.println("1. Názvu objektu");
                    System.out.println("2. Destinace");
                    System.out.println("3. Kapacity");
                    System.out.println("4. Ceny za noc");
                } else {
                    return "Seznam je prázdný";
                }
                break;
            case 3:
                if (booking.canWe("reservation")) {
                    System.out.println("1. Křestního jména klienta");
                    System.out.println("2. Příjmení klienta");
                    System.out.println("3. Názvu objektu");
                    System.out.println("4. Počtu lidí");
                    System.out.println("5. Počtu dní");
                    System.out.println("6. Data rezervace");
                    System.out.println("7. Celkové ceny");
                } else {
                    return "Seznam je prázdný";
                }
                break;
            default:
                return "Neplatná volba";
        }
        int option2 = loadOption();
        System.out.println("Přejete si seznam");
        System.out.println("1. Ukázat na obrazovku");
        System.out.println("2. Uložit do textového souboru");
        System.out.println("3. Uložit do binárního souboru");
        int option3 = loadOption();
        return (booking.saveData(option, option2, option3));
    }

    private static int loadOption() {
        String option = sc.nextLine();
        if (option.matches("[\\d]+")) {
            return Integer.parseInt(option);
        }
        return 0;
    }

    private static String loadProperty() {
        String type, name, destination, pricePerNight;
        String[] dates, rooms;
        System.out.println("Zadejte typ objektu");
        type = sc.nextLine();
        System.out.println("Zadejte název objektu");
        name = sc.nextLine();
        System.out.println("Zadejte zemi, ve které se objekt nachází");
        destination = sc.nextLine();
        System.out.println("Zadejte cenu za noc");
        pricePerNight = sc.nextLine();
        System.out.println("Kolik si přejete zadat pokojů?");
        int m, n;
        try {
            n = Integer.parseInt(sc.nextLine());
            rooms = new String[2 * n];
            for (int i = 0; i < rooms.length; i = i + 2) {
                System.out.println("Zadejte počet jednolůžek");
                rooms[i] = sc.nextLine();
                System.out.println("Zadejte počet dvoulůžek");
                rooms[i + 1] = sc.nextLine();
            }
            System.out.println("Kolik si přejete zadat termínů?");
            n = Integer.parseInt(sc.nextLine());
            dates = new String[2 * n];
            for (int i = 0; i < dates.length; i = i + 2) {
                System.out.println("Zadejte počáteční datum ve formátu dd.mm.yyyy");
                dates[i] = sc.nextLine();
                System.out.println("Zadejte koncové datum ve formátu dd.mm.yyyy");
                dates[i + 1] = sc.nextLine();
            }
        } catch (NumberFormatException e) {
            return "Přidání objektu selhalo - zadán nevalidní argument";
        }
        return booking.addProperty(Formater.format(type, name, destination, pricePerNight, rooms, dates));
    }

    private static String loadClient() {
        String lastName, firstName, nationality, age;
        System.out.println("Zadejte křestní jméno klienta");
        firstName = sc.nextLine();
        System.out.println("Zadejte příjmení klienta");
        lastName = sc.nextLine();
        System.out.println("Zadejte národnost klienta");
        nationality = sc.nextLine();
        System.out.println("Zadejte věk klienta");
        age = sc.nextLine();
        return booking.addClient(Formater.format(firstName, lastName, nationality, age));
    }

    private static String loadReservation() {
        int[] n = new int[4];
        try {
            System.out.print(booking.getList("client"));
            System.out.println("Vyberte číslo u klienta");
            n[0] = sc.nextInt();
            System.out.println(booking.getList("property"));
            System.out.println("Vyberte číslo u objektu");
            n[1] = sc.nextInt();
            System.out.println(booking.getDetail(n[1], "property"));
            System.out.println("Vyberte termín");
            n[2] = sc.nextInt();
            System.out.println("Pro kolik lidí si přejete zarezervovat objekt?");
            n[3] = sc.nextInt();
            sc.nextLine();
            return booking.addReservation(n);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
