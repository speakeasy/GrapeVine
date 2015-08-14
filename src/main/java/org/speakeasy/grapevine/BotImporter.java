package org.speakeasy.grapevine;

import org.speakeasy.grapevine.flock.sqlite.SQLiteJDBC;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author speakeasy
 */
public class BotImporter {

    private static SQLiteJDBC database;
    private File fimport;
    private boolean isPyImport;
    private HashMap<Integer, String> fileLines = new HashMap();

    public BotImporter(SQLiteJDBC database, File fimport, boolean isPyImport) {
        this.database = database;
        this.fimport = fimport;
        this.isPyImport = isPyImport;
        readFile(fimport);
    }

    public void doImport() {
        if (this.isPyImport) {
            doPyImport();
            return;
        }
        ;
    }

    public void doPyImport() {
        ;
    }

    private void readFile(File fimport) {
        try {
            FileReader fread = new FileReader(fimport);
            BufferedReader bread = new BufferedReader(fread);
            String line;
            while ((line = bread.readLine()) != null) {
                fileLines.put(fileLines.size(), line);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BotImporter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BotImporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
