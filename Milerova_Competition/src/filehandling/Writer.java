/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filehandling;

import app.Runner;
import java.util.List;
import java.io.IOException;

/**
 *
 * @author Marketa.Milerova
 */
public abstract class Writer {

    public abstract void saveResults(String resultFilePath, List<Runner> runners) throws IOException;
}
