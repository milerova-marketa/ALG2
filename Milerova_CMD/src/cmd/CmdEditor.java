package cmd;

import java.io.File;

/**
 *
 * @author Marketa.Milerova
 */
public class CmdEditor implements CmdInterface {

    private boolean isRunning;
    private File actualDir;
    private Command command;

    public CmdEditor() {
        isRunning = true;
        actualDir = new File(System.getProperty("user.dir"));

    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public String getActualDir() {
        return actualDir.getAbsolutePath();
    }

    @Override
    public String parseAndExecute(String line) {
        command = Parser.parse(line);
        if(command==null){
            return "";
        }
        Status status = command.execute(actualDir);
        actualDir = status.getActualDir();
        if (actualDir == null) {
            isRunning = false;
        }
        return status.getMessage();
    }

}
