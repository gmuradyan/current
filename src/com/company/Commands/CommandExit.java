package com.company.Commands;

import com.company.InOut;

public class CommandExit extends Commands {
    public static boolean exit=true;
    public void handleCommand(String[] args) {
        exit=false;
    }
}
