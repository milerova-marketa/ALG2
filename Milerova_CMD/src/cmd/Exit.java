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
public class Exit extends Command{

    @Override
    public Status execute(File actualDir) {
        return new Status(null,"KONEC\n");
    }
    
}
