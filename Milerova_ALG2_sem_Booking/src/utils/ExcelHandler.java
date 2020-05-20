/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Třída pro manipulaci s Excel soubory - použití externí knihovny
 *
 * @author Marketa.Milerova
 */
public class ExcelHandler {

    /**
     * Metoda načte z daného excelovského souboru potřebná data
     *
     * @param name název souboru
     * @return seznam informací k daným objektům
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static List<String> loadList(String name) throws FileNotFoundException, IOException {
        List<String> list = new ArrayList();
        StringBuilder sb = new StringBuilder();
        try (FileInputStream file = new FileInputStream(new File(name))) {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case STRING:
                            sb.append(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                Date date = cell.getDateCellValue();
                                sb.append(String.format("%02d.%02d.%04d-", date.getDate(), date.getMonth() + 1, 1900 + date.getYear()));
                            } else {
                                sb.append(cell.getNumericCellValue());
                            }
                    }
                    if (name.contains("Client")) {
                        sb.append(" ");
                    } else {
                        sb.append(";");
                    }
                }
                String text;
                if (name.contains("Client")) {
                    text = sb.toString().replace(".0", "");
                } else {
                    text = sb.toString().replace("-;;", "");
                    text = text.replace("-;", " ");
                    text = text.replace("  ", " ");
                    text = text.replace(" ;", ";");
                    text = text.replace("; ", ";");
                }
                if (name.contains("Reservation")) {
                    String termin = text.substring(text.length() - 11, text.length() - 1);
                    String uprava = text.substring(0, text.length() - 12);
                    text = uprava + ";" + termin;
                }
                list.add(text);
                sb = new StringBuilder();
            }
        }
        return list;
    }
}
