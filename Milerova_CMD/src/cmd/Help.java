/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd;

import java.io.File;

/**
 *
 * @author Marketa.Milerova
 */
public class Help extends Command {

    @Override
    public Status execute(File actualDir) {
        String help = "HELP\n"
                + String.format("%-20s %s%n", "help", "Display help")
                + String.format("%-20s %s%n", "dir", "Display list of files and folders")
                + String.format("%-20s %s%n", "dir <parameter>", "Display list of files and folders of directory")
                + String.format("%-20s %s%n", "cd ..", "Return to parent directory")
                + String.format("%-20s %s%n", "cd <parameter>", "Change actual dir to parameter")
                + String.format("%-20s %s%n", "exit", "End CMD");
        return new Status(actualDir, help);
    }

}
