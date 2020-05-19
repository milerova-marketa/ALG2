/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import app.Client;
import app.Property;
import app.Reservation;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Marketa.Milerova
 */
public class FileHandler {

    private static final String[] FILE_NAMES = {"ClientInfo", "PropertyInfo", "ReservationInfo"};

    /**
     * Metoda na vytvoření souboru z daného seznamu
     *
     * @param list seznam 1 - klient 2 - objekt 3 - rezervace
     * @param fileType typ souboru
     * @param string seznam naformátovaný ve Stringu na výpis
     * @return název souboru
     * @throws java.io.IOException
     */
    public static String createFile(int list, int fileType, String string) throws IOException {
        String nameFile;
        String type;
        File file;
        switch (list) {
            case 1:
                nameFile = "ClientDetail";
                break;
            case 2:
                nameFile = "PropertyDetail";
                break;
            case 3:
                nameFile = "ReservationDetail";
                break;
            default:
                throw new IllegalArgumentException("Neplatný seznam");
        }
        switch (fileType) {
            case 2:
                type = ".txt";
                file = new File(nameFile + type);
                file.createNewFile();
                writeToTXT(file, string);
                break;
            case 3:
                type = ".dat";
                file = new File(nameFile + type);
                file.createNewFile();
                writeToDAT(file, list);
                break;
            default:
                throw new IllegalArgumentException("Neplatný typ souboru");
        }
        return nameFile + type;
    }

    /**
     * Metoda k čtení textu ze souboru
     *
     * @param path cesta k souboru
     * @return text ze souboru
     * @throws java.io.FileNotFoundException
     */
    public static List<String> readFile(String path) throws FileNotFoundException, IOException {
        File f = new File(path);
        List<String> list = new ArrayList();
        String[] textList = new String[8];
        String textFormated = "";
        if (path.endsWith(".dat")) {
            try (DataInputStream dis = new DataInputStream(new FileInputStream(f))) {
                boolean isEnd = false;
                while (!isEnd) {
                    try {
                        if (path.contains(FILE_NAMES[0])) {
                            textList[0] = dis.readUTF();
                            textList[1] = dis.readUTF();
                            textList[2] = dis.readUTF();
                            textList[3] = "" + dis.readInt();
                            textFormated = Formater.format(textList[0], textList[1], textList[2], textList[3]);

                        } else if (path.contains(FILE_NAMES[1])) {
                            textList[0] = dis.readUTF();
                            textList[1] = dis.readUTF();
                            textList[2] = dis.readUTF();
                            textList[3] = "" + dis.readDouble();
                            int n = dis.readInt();
                            String[] rooms = new String[2 * n];
                            for (int i = 0; i < 2 * n; i = i + 2) {
                                rooms[i] = "" + dis.readInt();
                                rooms[i + 1] = "" + dis.readInt();
                            }
                            n = dis.readInt();
                            String[] dates = new String[n];
                            for (int i = 0; i < (n); i++) {
                                dates[i] = String.format("%02d.%02d.%04d", dis.readInt(), dis.readInt(), dis.readInt());
                            }
                            textFormated = Formater.format(textList[0], textList[1], textList[2], textList[3], rooms, dates);
                        } else if (path.contains(FILE_NAMES[2])) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(dis.readUTF()).append(";");
                            sb.append(dis.readUTF()).append(";");
                            sb.append(dis.readInt()).append(";");
                            sb.append(String.format("%02d.", dis.readInt()));
                            sb.append(String.format("%02d.", dis.readInt()));
                            sb.append(String.format("%04d ", dis.readInt()));
                            sb.append(String.format("%02d.", dis.readInt()));
                            sb.append(String.format("%02d.", dis.readInt()));
                            sb.append(String.format("%04d;", dis.readInt()));
                            sb.append(String.format("%02d.", dis.readInt()));
                            sb.append(String.format("%02d.", dis.readInt()));
                            sb.append(String.format("%04d", dis.readInt()));
                            textFormated = sb.toString();
                        }
                        list.add(textFormated);
                    } catch (EOFException e) {
                        isEnd = true;
                    }
                }
                return list;
            }
        } else if (path.endsWith(".txt")) {
            try (Scanner rf = new Scanner(f)) {
                while (rf.hasNext()) {
                    list.add(rf.nextLine());
                }
                return list;
            }
        } else if (path.endsWith(".xlsx")) {
            return ExcelHandler.loadList(path);
        }
        throw new IllegalArgumentException("Nevalidní typ");
    }

    private static void writeToTXT(File file, String string) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(string);
        }
    }

    public static void createBinaries() throws FileNotFoundException, IOException {
        for (int i = 0; i < FILE_NAMES.length; i++) {
            File binaryFile = new File(FILE_NAMES[i] + ".dat");
            binaryFile.createNewFile();
            File txtFile = new File(FILE_NAMES[i] + ".txt");
            try (Scanner txt = new Scanner(txtFile)) {
                try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(binaryFile))) {
                    while (txt.hasNext()) {
                        switch (i) {
                            case 0:
                                dos.writeUTF(txt.next());
                                dos.writeUTF(txt.next());
                                dos.writeUTF(txt.next());
                                dos.writeInt(txt.nextInt());
                                break;
                            case 1:
                                String[] line = txt.nextLine().split(";");
                                dos.writeUTF(line[0]);
                                dos.writeUTF(line[1]);
                                dos.writeUTF(line[2]);
                                double price = Double.parseDouble(line[3]);
                                dos.writeDouble(price);
                                String[] rooms = line[4].split(",");
                                dos.writeInt(rooms.length);
                                for (String room : rooms) {
                                    String[] number = room.split(" +");
                                    dos.writeInt(Integer.parseInt(number[0]));
                                    dos.writeInt(Integer.parseInt(number[1]));
                                }
                                String[] dates = line[5].split(" +");
                                dos.writeInt(dates.length);
                                for (String date : dates) {
                                    LocalDate ld = LocalDate.parse(date, Parser.DTF);
                                    dos.writeInt(ld.getDayOfMonth());
                                    dos.writeInt(ld.getMonthValue());
                                    dos.writeInt(ld.getYear());
                                }
                                break;
                            case 2:
                                line = txt.nextLine().split(";");
                                dos.writeUTF(line[0]);
                                dos.writeUTF(line[1]);
                                dos.writeInt(Integer.parseInt(line[2]));
                                String[] date = (line[3] + " " + line[4]).split(" +");
                                for (int j = 0; j < date.length; j++) {
                                    LocalDate ld = LocalDate.parse(date[j], Parser.DTF);
                                    dos.writeInt(ld.getDayOfMonth());
                                    dos.writeInt(ld.getMonthValue());
                                    dos.writeInt(ld.getYear());
                                }
                                break;
                        }
                    }
                }
            }
        }
    }

    private static void writeToDAT(File file, int name) throws FileNotFoundException, IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            switch (name) {
                case 1:
                    List<Client> clients = DataHandler.clients;
                    for (Client client : clients) {
                        dos.writeUTF(client.getFirstName());
                        dos.writeUTF(client.getLastName());
                        dos.writeUTF(client.getNationality());
                        dos.writeInt(client.getAge());
                    }
                    break;
                case 2:
                    List<Property> properties = DataHandler.properties;
                    for (Property property : properties) {
                        dos.writeUTF(property.getClass().getSimpleName());
                        dos.writeUTF(property.getName());
                        dos.writeUTF(property.getDestination());
                        dos.writeInt(property.getCapacity());
                        dos.writeDouble(property.getPricePerNightPerPerson());
                    }
                    break;
                case 3:
                    List<Reservation> reservations = DataHandler.reservations;
                    for (Reservation reservation : reservations) {
                        dos.writeUTF(reservation.getClient().getFirstName());
                        dos.writeUTF(reservation.getClient().getLastName());
                        dos.writeUTF(reservation.getProperty().getName());
                        dos.writeInt(reservation.getnPeople());
                        dos.writeInt(reservation.getnDays());
                        dos.writeInt(reservation.getReservedDate().getStart().getDayOfMonth());
                        dos.writeInt(reservation.getReservedDate().getStart().getMonthValue());
                        dos.writeInt(reservation.getReservedDate().getStart().getYear());
                        dos.writeInt(reservation.getReservedDate().getEnd().getDayOfMonth());
                        dos.writeInt(reservation.getReservedDate().getEnd().getMonthValue());
                        dos.writeInt(reservation.getReservedDate().getEnd().getYear());
                        dos.writeInt(reservation.getDateOfReservation().getDayOfMonth());
                        dos.writeInt(reservation.getDateOfReservation().getMonthValue());
                        dos.writeInt(reservation.getDateOfReservation().getYear());
                        dos.writeDouble(reservation.getPrice());
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Seznam neexistuje");
            }
        }
    }
}
