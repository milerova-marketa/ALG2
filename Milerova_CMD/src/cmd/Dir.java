package cmd;

import java.io.File;
import java.util.Date;

/**
 *
 * @author Marketa.Milerova
 */
public class Dir extends Command {

    @Override
    public Status execute(File actualDir) {
        File[] files;
        if (params.length == 1) {
            files = actualDir.listFiles();
            return new Status(actualDir, dirToString(files));
        }
        if (params.length > 2) {
            return new Status(actualDir, "Nevalidni prikaz");
        }
        File directory;
        directory = new File(actualDir.getAbsolutePath() + "\\" + params[1]);
        return new Status(actualDir, dirToString(directory.listFiles()));
    }

    private String dirToString(File[] files) {
        StringBuilder sb = new StringBuilder();
        for (File file : files) {
            if (file.isDirectory()) {
                sb.append(String.format("%s%n", file.getName()));
            } else {
                sb.append(String.format("%-20s%6d ", file.getName(), file.length()));
                sb.append(new Date(file.lastModified())).append("\n");
            }
        }
        return sb.toString();
    }

}
