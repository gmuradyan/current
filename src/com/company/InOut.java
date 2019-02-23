package com.company;

import java.util.*;

public class InOut {

    //colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    //msg types
    public static final String ERROR = "ERROR";
    public static final String INFO = "INFO";

    public static boolean outSheet(List<HashMap<String, SheetCell>> sheetList) {
        int row = 1;
        String space = " ";
        String spaces = new String();
        System.out.print(" {____} | ");

        Character ch = 'A';
        for (; ch <= 'N'; ++ch) {
            System.out.print("{_____" + ch + "______} | ");
        }
        System.out.println();

        for (int i = 0; i < SheetList.Nmax; ++i) {
            for (int j = Integer.toString(row).length(); j < 3; ++j)
                spaces = spaces.concat(space);
            System.out.print(" { " + row++ + spaces + "} | ");
            spaces = "";

            HashMap hm = SheetList.sheetList.get(i);
            int k = 0;
            for (Character j = 'a'; j < 'a' + SheetList.Mmax; ++j) {
                if (k < hm.size() && hm.containsKey(j.toString())) {
                    for (int h = ((SheetCell) hm.get(j.toString())).getCellContain().length(); h < 10; ++h)
                        spaces = spaces.concat(space);
                    System.out.print("[ " + ((SheetCell) hm.get(j.toString())).getCellContain() + spaces + " ]");
                    ++k;
                } else {
                    System.out.print("[            ]");
                }
                spaces = "";
                System.out.print(" | ");
            }
            System.out.println();
        }
        return true;
    }

    public static String inputStream() {
        String inStream = new String();
        Scanner scanIn = new Scanner(System.in);
        inStream = scanIn.nextLine();
        return inStream;
    }

    public static void printMsg(String color, String titel, String msg) {
        System.out.println(color + titel + ": ");
        System.out.println("[- " + msg + " -]" + ANSI_RESET);
    }

    public static void printHelp() {
        System.out.println(ANSI_PURPLE + "HELP:");
        System.out.println(ANSI_PURPLE + "Commands list");
        System.out.println(" Set ");
        System.out.println("   arguments: [ [command name] [position] [value/function] ]");
        System.out.println("Example : |> Set 6c 5a+10 ");
        System.out.println("=-------------------------------------------------------------=");
        System.out.println(" Delete");
        System.out.println("   arguments: [ [command name] [position] ]");
        System.out.println("Example : |> Delete 6c ");
        System.out.println("=-------------------------------------------------------------=");
        System.out.println(" Show");
        System.out.println("   arguments: [ [command name] ]");
        System.out.println("Example : |> Show ");
        System.out.println("=-------------------------------------------------------------=");
        System.out.println(" Exit");
        System.out.println("   arguments: [ [command name] ]");
        System.out.println("Example : |> Exit ");
        System.out.println("=-------------------------------------------------------------=");
        System.out.println("Admit commands by ENTER " + ANSI_RESET);
    }
}
