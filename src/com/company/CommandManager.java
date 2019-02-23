package com.company;

import com.company.Commands.*;

public class CommandManager {

    private static CommandTypes detectCmd(String cmd) {
        CommandTypes result = CommandTypes.UNKNOWN;
//        switch (cmd) {
//            case "set":
//                result = CommandTypes.SET;
//                break;
//            case "show":
//                result = CommandTypes.SHOW;
//                break;
//            case "delete":
//                result = CommandTypes.DELETE;
//                break;
//            case "help":
//                result = CommandTypes.HELP;
//                break;
//            case "exit":
//                result = CommandTypes.EXIT;
//                break;
//            default:
//                result = CommandTypes.UNKNOWN;
//                break;
//        }

        if(cmd.equals("set"))
            result = CommandTypes.SET;
        else if(cmd.equals("show"))
            result = CommandTypes.SHOW;
        else if(cmd.equals("delete"))
            result = CommandTypes.DELETE;
        else if(cmd.equals("help"))
            result = CommandTypes.HELP;
          else if(cmd.equals("exit"))
              result = CommandTypes.EXIT;
          else
              result = CommandTypes.UNKNOWN;

        return result;
    }

    public static void commandManager(String command) {

        try {
            command = command.toLowerCase();
            String[] splitedStr = command.split("\\s+", 3);
            if (splitedStr.length > 0) {
                Commands com = new Commands();
                switch (detectCmd(splitedStr[0])) {
                    case SET:
                        if (splitedStr.length < 3) {
                            InOut.printMsg(InOut.ANSI_RED, InOut.ERROR, "few arguments for SET method");
                            return ;
                        }
                        com = new CommandSet();
                        break;
                    case SHOW:
                        com = new CommandShow();
                        break;
                    case DELETE:
                        if (splitedStr.length < 2) {
                            InOut.printMsg(InOut.ANSI_RED, InOut.ERROR, "few arguments for DELETE method");
                            return ;
                        }
                        com = new CommandDelete();
                        break;
                    case HELP:
                        com = new CommandHelp();
                        break;
                    case EXIT:
                        com = new CommandExit();
                        break;
                }
                com.handleCommand(splitedStr);
            }
        }catch (ArrayIndexOutOfBoundsException e){
            InOut.printMsg(InOut.ANSI_RED, InOut.ERROR, "few arguments");
        }
    }
}
