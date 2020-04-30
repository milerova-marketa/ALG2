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
public class Status {
    private File actualDir;
    private String message;

    public File getActualDir() {
        return actualDir;
    }

    public String getMessage() {
        return message;
    }

    public Status(File actualDir, String message) {
        this.actualDir = actualDir;
        this.message = message;
    }
    
}
