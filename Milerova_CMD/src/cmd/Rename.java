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
public class Rename extends Command {

    @Override
    public Status execute(File actualDir) {
        if (params.length == 3) {
            File f1 = new File(actualDir.getAbsolutePath()+"\\"+params[1]);
            File f2 = new File(actualDir.getAbsolutePath()+"\\"+params[2]);
            f1.renameTo(f2);
            return new Status(actualDir,"Soubor přejmenován\n");
        } else {
            return new Status(actualDir, "Nevalidní příkaz\n");
        }
    }

}
