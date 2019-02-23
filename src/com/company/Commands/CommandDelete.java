package com.company.Commands;

import com.company.SheetList;
/*
removing exist value or formula from Sheet
 */
public class CommandDelete extends Commands {

    public void handleCommand(String[] args) {
        try {
            if (SheetList.isCellExist(args[1])) {
                SheetList.removeCell(args[1]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("incorrect index for list" + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("incorrect index for list" + e.getMessage());
        }
        catch (NullPointerException e) {
            System.out.println("Is cell exist?" + e.getMessage());
        }

    }
}

