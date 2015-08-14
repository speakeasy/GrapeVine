package org.speakeasy.grapevine;

import java.io.File;
import org.speakeasy.grapevine.flock.sqlite.SQLiteJDBC;

/**
 *
 * @author speakeasy
 */
public class BotImporter {

    private static SQLiteJDBC database;
    private static File fimport;
    private static boolean isPyImport;
    
    public BotImporter(SQLiteJDBC database, File fimport, boolean isPyImport) {
        this.database = database;
        this.fimport = fimport;
        this.isPyImport = isPyImport;
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
    
}
