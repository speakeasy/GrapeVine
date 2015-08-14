package org.speakeasy.grapevine;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.speakeasy.grapevine.flock.sqlite.SQLiteJDBC;

/**
 *
 * @author speakeasy
 */
public class BotHandler extends Thread {
    
    private static SQLiteJDBC database;
    private static File proxyList;
    private static int queue = 0;
    private static int lastqueue = 0;
    
    
    public BotHandler(File database, File proxyList) {
        try {
            BotHandler.database = new SQLiteJDBC(database.getAbsolutePath());
        } catch (Exception ex) {
            Logger.getLogger(BotHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public BotHandler(File database) {
        try {
            BotHandler.database = new SQLiteJDBC(database.getAbsolutePath());
        } catch (Exception ex) {
            Logger.getLogger(BotHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void start() {
        ;
    }
    
    public void loop() {
        ;
    }
    
    public static SQLiteJDBC getDatabase() {
        return BotHandler.database;
    }
    
    public void increaseQueue() {
        queue++;
    }
    
    public void setQueue() {
        lastqueue = queue;
    }
    
    public int getNumBotsProcesses() {
        return lastqueue - queue;
    }
}
