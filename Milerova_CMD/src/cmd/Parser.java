/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd;

/**
 *
 * @author Marketa.Milerova
 */
public class Parser {

    public static Command parse(String line) {
        if (line.isEmpty()) {
            return null;
        }
        String[] p = line.split(" +");
        char first = Character.toUpperCase(p[0].charAt(0));
        String name = Command.COMMAND_PACKAGE + "." + first + p[0].substring(1);
        Command command;
        try {
            Class c = Class.forName(name);
            command = (Command) c.newInstance();
            command.setParams(p);
            return command;
        } catch (Exception e) {
            throw new RuntimeException("Nepodařilo se naparsovat příkaz");
        }
    }
}
