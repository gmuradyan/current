package com.company.Commands;

import com.company.InOut;

public class Commands implements Command {
    public  void handleCommand(String[] args){
        InOut.printMsg(InOut.ANSI_YELLOW, InOut.INFO, "UNKNOWN METHOD1");
    }
}
