package com.company.Commands;

import com.company.InOut;
import com.company.SheetList;

/*
seting new values in Sheet
 */
public class CommandSet extends Commands {

    public void handleCommand(String[] args) {
        try {
            args[2] = args[2].replaceAll("\\s+", "");

            if(!SheetList.isCellExist(args[1])){
                SheetList.addCell(args[1]);
            }
            SheetList.getCell(args[1]).setCellContain(args[1],args[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            InOut.printMsg(InOut.ANSI_RED, InOut.ERROR, "Please check, Is exist entered cell?" + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            InOut.printMsg(InOut.ANSI_RED, InOut.ERROR, "Please check, Is exist entered cell?" + e.getMessage());
        } catch (NumberFormatException e) {
            InOut.printMsg(InOut.ANSI_RED, InOut.ERROR, "Please check, Is entered value has correct format?" + e.getMessage());
        }
        catch (NullPointerException e) {
        InOut.printMsg(InOut.ANSI_RED, InOut.ERROR, "Please check,Is cell exist" + e.getMessage());
    }
    }
}