package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public abstract class SheetList {

    public static final int Nmax = 10;
    public static final int Mmax = 14; // N
    public static long MmaxFill = 0;

    public static int row_i = -1;
    public static String column_i = "@";

    public static List<HashMap<String, SheetCell>> sheetList = new LinkedList<HashMap<String, SheetCell>>();

    private static boolean isPos(String pos) {
        boolean incorectPos = false;
        boolean mix = false;
        boolean isnumber = false;
        boolean islatter = false;
        boolean firstDigit = Character.isDigit(pos.charAt(0));
        for (Character s : pos.toCharArray()
        ) {
            if (firstDigit) {
                if (Character.isDigit(s)) {
                    if (mix) {
                        incorectPos = true;
                    }
                    // ++pos_i;
                    isnumber = true;
                } else {
                    islatter = true;
                    mix = true;
                }
            } else {
                if (!Character.isDigit(s)) {
                    if (mix) {
                        incorectPos = true;
                    }
                    //    ++pos_i;
                    islatter = true;
                } else {
                    isnumber = true;
                    mix = true;
                }
            }
        }
        return (!incorectPos && isnumber && islatter);
    }

    public static void getPos(String pos) {

        if (isPos(pos)) {
            String indexes[] = pos.split("[^a-z0-9]+|(?<=[a-z])(?=[0-9])|(?<=[0-9])(?=[a-z])");

            if (Character.isDigit(pos.charAt(0))) {
                row_i = Integer.parseInt(indexes[0]) - 1;
                column_i = indexes[1];
            } else {
                column_i = indexes[0];
                row_i = Integer.parseInt(indexes[1]) - 1;
            }
        } else {
            System.out.println("incorrect Pos numbers and leters");
        }
    }

    public static boolean isCellExist(String pos) {
        getPos(pos);
        return SheetList.sheetList.get(SheetList.row_i).containsKey(column_i);
    }

    public static void addCell(String pos) {
        getPos(pos);
        sheetList.get(row_i).put(column_i, new SheetCell());
    }

    public static SheetCell getCell(String pos) {
        getPos(pos);
        return SheetList.sheetList.get(SheetList.row_i).get(SheetList.column_i);
    }

    public static void removeCell(String pos) {
        getPos(pos);
        SheetList.sheetList.get(SheetList.row_i).remove(SheetList.column_i);
    }

    public static void inPreliminary(String prLine) {
        try {
            prLine = prLine.replaceAll("\\s+", "");
            String inputVal[] = prLine.split("\\,");
            MmaxFill = Math.round(Math.sqrt(inputVal.length));

            int k = 0;
            for (int i = 0; i < Nmax; ++i) {
                HashMap<String, SheetCell> rowMap = new HashMap<String, SheetCell>();
                for (Character j = 'a'; j < 'a' + MmaxFill && k < inputVal.length; ++j) {
                    rowMap.put(j.toString(), new SheetCell(Double.parseDouble(inputVal[k++])));
                }
                sheetList.add(rowMap);
            }
        } catch (NumberFormatException e) {
            InOut.printMsg(InOut.ANSI_RED, InOut.ERROR, "Incorrect value type in input stream");
        } catch (ArrayIndexOutOfBoundsException e) {
            InOut.printMsg(InOut.ANSI_RED, InOut.ERROR, "Incorrect value type in input stream");
        } catch (ArithmeticException e) {
            InOut.printMsg(InOut.ANSI_RED, InOut.INFO, "Please check input stream");
        }
    }
}
