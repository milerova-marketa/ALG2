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
public class Tree extends Command {

    @Override
    public Status execute(File actualDir) {
        return new Status(actualDir, tree(actualDir, 0));
    }

    private String tree(File parent, int layer) {
        String tree = "";
        File[] files = parent.listFiles();
        for (File file : files) {
            for (int i = 0; i < layer; i++) {
                tree+=" ";
            }
            tree += ("-" + file.getName() + "\n");
            if (file.isDirectory()) {
                tree += tree(file, layer + 1);
            }
        }
        return tree;
    }
}
