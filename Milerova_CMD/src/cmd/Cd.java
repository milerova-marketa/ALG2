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
public class Cd extends Command {

    @Override
    public Status execute(File actualDir) {
        File file;
        if (params.length == 1) {
            return new Status(actualDir, "Zadejte parametr");
        }
        if (params.length > 2) {
            return new Status(actualDir, "Nevalidni prikaz");
        }
        if(params[1].equals("..")){
            file = new File(actualDir.getParent());
        } else{
            try{
            file = new File(actualDir.getAbsolutePath() + "\\" + params[1]);
            } catch(Exception e){
                throw new RuntimeException("Slozka neexistuje");
            }
        }
        
        return new Status(file,"");
    }

}
