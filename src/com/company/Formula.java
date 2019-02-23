package com.company;

import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formula extends Phrase {

    private class FormulaStruct {
        public String referenc;
        public char before_sign;
        public FormulaIs type = FormulaIs.ERROR;

        public FormulaStruct(String ref, char before, FormulaIs type) {
            this.referenc = ref;
            this.before_sign = before;
            this.type = type;
        }
    }
    public List<FormulaStruct> formulaValue = new LinkedList<FormulaStruct>();

    private String result = new String();

    Formula() {
    }

    Formula(String sheetPos, String val) {
            super.in_phrase = val;
            if (!analyzeFormula(sheetPos, val)) {
                throw new  ExceptionInInitializerError();
            }
    }

    private char detectFirstSign(char sign) {
        return (sign!='-')?'+':'-';
    }

    public boolean analyzeFormula(String cellPos, String formula) {
        try {
            char sign_after;
            char sign_before = detectFirstSign(formula.charAt(0));
            if (sign_before == '-') {
                formula = formula.substring(1);
            }

            Pattern p = Pattern.compile("[\\+\\-\\*/\\(\\)]");
            String tmp;
            int index = 1;
            while (index > 0) {
                index = -1;
                Matcher m = p.matcher(formula);
                if (m.find()) {
                    index = m.start();
                    tmp = formula.substring(0, index);
                    sign_after = formula.charAt(index);
                } else {
                    tmp = formula;
                    sign_after = 'x';
                }

                if (!tmp.isEmpty() && tmp.matches("\\d+(\\.\\d+)?$")) {
                    formulaValue.add(new FormulaStruct(tmp, sign_before, FormulaIs.VALUE));
                } else {
                    if (!checkRefNet(cellPos, tmp)) {
                        InOut.printMsg(InOut.ANSI_RED, InOut.ERROR, "Loop");
                        return false;
                    }
                    formulaValue.add(new FormulaStruct(tmp, sign_before, FormulaIs.FUNCTION));
                }

                if (index >= 0) {
                    formula = formula.substring(index + 1);
                }
                sign_before = sign_after;
            }
        } catch (NumberFormatException e) {
            InOut.printMsg(InOut.ANSI_RED, InOut.ERROR, "Incorrect value type in streams's formula part");
            return false;
        } catch (StringIndexOutOfBoundsException e) {
            InOut.printMsg(InOut.ANSI_RED, InOut.ERROR, "Incorrect input1");
            return false;
        } catch (NullPointerException e) {
            InOut.printMsg(InOut.ANSI_RED, InOut.ERROR, "Incorrect input1");
            return false;
        }
        catch (ClassCastException e){
            InOut.printMsg(InOut.ANSI_RED, InOut.ERROR, "Incorrect input1");
            return false;
        }
        return true;
    }

    private boolean checkRefNet(String cellPos, String ref1) {

        Set<String> refNetSet = new HashSet<String>();
        if (!refNetSet.add(cellPos) || !refNetSet.add(ref1)) {
            return false;
        }
        boolean numberRef = true;
        while (true) {
            SheetList.getPos(ref1);
            SheetCell cell = SheetList.sheetList.get(SheetList.row_i).get(SheetList.column_i);

            if (cell.phrase.checkPhrase(cell.phrase.in_phrase) == FormulaIs.VALUE) {
                break;
            } else {
                for (FormulaStruct fs : ((Formula) cell.phrase).formulaValue
                ) {
                    if (fs.type == FormulaIs.FUNCTION) {
                        numberRef = false;
                        if (!refNetSet.add(fs.referenc)) {
                            return false;
                        }
                        ref1 = fs.referenc;
                    }
//                    else if (fs.type == FormulaIs.VALUE) {
//
//                    }
                }
            }
            if (numberRef) {
                break;
            }
        }
        return true;
    }


    private Double getValue(FormulaStruct fs) {
        if (fs.type == FormulaIs.VALUE) {
            return Double.parseDouble(fs.referenc);
        } else if (fs.type == FormulaIs.FUNCTION) {
            SheetList.getPos(fs.referenc);
            return Double.parseDouble(SheetList.sheetList.get(SheetList.row_i).get(SheetList.column_i).getCellContain());
        } else {
            throw new NumberFormatException();

        }
    }

    private String colculateFormula() {
        Double result = 0.0;
        boolean first = true;
        try {
            for (FormulaStruct fs : formulaValue
            ) {
                switch (fs.before_sign) {
                    case '+':
                        first = false;
                        result = result + getValue(fs);
                        break;
                    case '-':
                        first = false;
                        result = result - getValue(fs);
                        break;
                    case '*':
                        if (first) result = 1.0;
                        first = false;
                        result = result * getValue(fs);
                        break;
                    case '/':
                        //TODO div to 0sho
                        Double tmp=getValue(fs);
                        if(tmp==0.0){
                            result=0.0;
                            throw new ArithmeticException();
                        }
                        if (first){
                            result=tmp;
                        }
                        result = result / tmp;
                        break;
                }
            }
        } catch (NumberFormatException e) {
           // InOut.printMsg(InOut.ANSI_RED, InOut.ERROR, "Incorrect value type in streams's formula part");
        } catch (NullPointerException e) {
          //  InOut.printMsg(InOut.ANSI_RED, InOut.ERROR, "Incorrect value type in streams's formula part");
        } catch (ArithmeticException e) {
          //  InOut.printMsg(InOut.ANSI_RED, InOut.ERROR, "Incorrect value type in streams's formula part");
        } finally {
            return result.toString();
        }
    }

    public String getResult() {
        return colculateFormula();
    }
}
