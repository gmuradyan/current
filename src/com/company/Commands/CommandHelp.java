package com.company.Commands;

import com.company.InOut;
/*
print help msg
 */
public class CommandHelp extends Commands {
    public void handleCommand(String[] args) {
        InOut.printHelp();
    }
}
