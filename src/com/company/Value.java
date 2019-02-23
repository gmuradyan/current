package com.company;

public class Value extends Phrase {
    private String result = new String();

    Value(){
   }

    Value(String val) {
        this.result = val;
        super.in_phrase = val;
    }

    public String getResult() {
        return this.result;
    }
}
