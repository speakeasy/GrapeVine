package org.speakeasy.grapevine;

import java.io.File;
import java.net.InetAddress;
import java.util.HashMap;
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
    private HashMap<InetAddress, Integer> proxies = new HashMap();
    private static int queue = 0;
    private static int lastqueue = 0;

    public BotHandler(File database, File proxyList) {
        loadDatabase(database.getAbsolutePath());
    }

    public BotHandler(File database) {
        loadDatabase(database.getAbsolutePath());
    }
    
    public BotHandler() {
        BotHandler.database = new SQLiteJDBC(new File(System.getProperty("user.dir")));
    }

    private void loadDatabase(String db) {
        try {
            BotHandler.database = new SQLiteJDBC(db);
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
