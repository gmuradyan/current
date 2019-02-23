package com.company;
import com.company.Phrase;
import com.company.FormulaIs;

public class SheetCell {

    Phrase phrase;//=new Phrase()

    SheetCell() {
    }

    SheetCell(Double value) {
        this.phrase=new Value(value.toString());
    }

    public void setCellContain(String sheetPos,String str) {
        try{
        switch (this.phrase.checkPhrase(str)){
            case ERROR:
                InOut.printMsg(InOut.ANSI_RED, InOut.ERROR,"the phrase is not formula or number");
                break;
            case VALUE:
                this.phrase=new Value(str);
                break;
            case FUNCTION:
                this.phrase=new Formula(sheetPos,str);
                break;
        }
        }catch (ExceptionInInitializerError e){
                InOut.printMsg(InOut.ANSI_RED, InOut.ERROR,"analyzing of formula filed ");
        }
    }

    public String getCellContain(){
       return this.phrase.getResult();
    }
}
