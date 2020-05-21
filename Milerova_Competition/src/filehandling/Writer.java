/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filehandling;

import app.Runner;
import java.io.File;
import java.util.List;
import java.io.IOException;

/**
 *
 * @author Marketa.Milerova
 */
public abstract class Writer {

    public static File dataDir = new File(System.getProperty("user.dir") + File.separator + "data");

    public abstract void saveResults(String resultFilePath, List<Runner> runners) throws IOException;
}
