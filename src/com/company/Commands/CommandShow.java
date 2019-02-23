package com.company.Commands;

import com.company.CommandTypes;
import com.company.InOut;
import com.company.SheetList;

/*
printing Sheet
 */
public class CommandShow  extends Commands {

        public void handleCommand (String[]args){
            try {
                InOut.outSheet(SheetList.sheetList);
            }catch (NullPointerException e){
                System.out.println("Incorrect value in Sheet map" + e.getMessage());
            }catch (IndexOutOfBoundsException e){
            System.out.println("Incorrect value in Sheet list" + e.getMessage());
             }
    }

}
