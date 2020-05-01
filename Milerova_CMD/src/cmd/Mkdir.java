/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marketa.Milerova
 */
public class Mkdir extends Command {

    @Override
    public Status execute(File actualDir) {
        if (params.length == 2) {
            File file = new File(actualDir.getAbsolutePath() + "\\" + params[1]);
            file.mkdir();
            return new Status(actualDir, "Soubor vytvořen\n");

        } else {
            return new Status(actualDir, "Nevalidní příkaz\n");
        }
    }

}
