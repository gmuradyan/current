package com.company;

import com.company.Commands.CommandExit;

public class Main {

    public static void main(String[] args) {
        // make Preliminary dates List
        SheetList.inPreliminary("5,88,9,3,4,12,147,45,6,3,30,147,8,99,51,27,23,4,78,951,2523,0,7,-6,1,-45,3,1");
        InOut.outSheet(SheetList.sheetList);

        while (CommandExit.exit) {
            System.out.println();
            System.out.print("> ");
            String inputStream = InOut.inputStream();
            CommandManager.commandManager(inputStream);
        }
    }
}
