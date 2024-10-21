package com.monique.mailer.core.sheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.stream.Stream;

import org.dhatim.fastexcel.reader.Cell;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;

public class SheetData {

    public static LinkedHashMap<Integer, ArrayList<String>> extract(File location) {
        var data = new LinkedHashMap<Integer, ArrayList<String>>();

        try (FileInputStream file = new FileInputStream(location); ReadableWorkbook wb = new ReadableWorkbook(file)) {
            Sheet sheet = wb.getFirstSheet();
            try (Stream<Row> rows = sheet.openStream()) {
                rows.forEach(r -> {
                    data.put(r.getRowNum(), new ArrayList<>());

                    for (Cell cell : r) {
                        data.get(r.getRowNum()).add(cell.getRawValue());
                    }
                });
            }
        } catch (Exception e) {
            return null;
        }
        return data;
    }

    public static LinkedHashMap<Integer, ArrayList<String>> extract(InputStream file) {
        var data = new LinkedHashMap<Integer, ArrayList<String>>();

        try (ReadableWorkbook wb = new ReadableWorkbook(file)) {
            Sheet sheet = wb.getFirstSheet();
            try (Stream<Row> rows = sheet.openStream()) {
                rows.forEach(r -> {
                    data.put(r.getRowNum(), new ArrayList<>());

                    for (Cell cell : r) {
                        data.get(r.getRowNum()).add(cell.getRawValue());
                    }
                });
            }
        } catch (Exception e) {
            return null;
        }
        return data;
    }
}