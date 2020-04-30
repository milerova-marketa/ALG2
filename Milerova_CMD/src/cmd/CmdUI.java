package cmd;

import java.util.Scanner;

/**
 *
 * @author Marketa.Milerova
 */
public class CmdUI {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        CmdInterface cmd = new CmdEditor();
        String line;
        while (cmd.isRunning()) {
            System.out.print(cmd.getActualDir() + "> ");
            line = sc.nextLine();
            try{
            System.out.print(cmd.parseAndExecute(line));
            } catch(RuntimeException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
