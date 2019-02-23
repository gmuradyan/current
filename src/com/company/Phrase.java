package com.company;

public class Phrase {
    protected String in_phrase;

    public Phrase() {
    }

    Phrase(String phrase) {
        this.in_phrase = phrase;
    }

    public String getResult() {
        System.out.println("Phrase ");
        return in_phrase;
    }

    public FormulaIs checkPhrase(String ph) { //String ph

        if (ph.matches("^[-+]?\\d+(\\.\\d+)?$")) {
            return FormulaIs.VALUE;
            //TODO improve regex for Formula
        } else if(ph.matches("^[a-z0-9\\+\\-\\*/.]*$"))/* if (ph.matches("")) */{

            return FormulaIs.FUNCTION;
        }
        return FormulaIs.ERROR;
    }
}
