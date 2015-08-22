package org.speakeasy.grapevine;

import java.io.File;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.speakeasy.grapevine.flock.Flock;
import org.speakeasy.grapevine.flock.FlockDBHelper;
import org.speakeasy.grapevine.flock.sqlite.SQLiteJDBC;

/**
 *
 * @author speakeasy
 */
public class BotHandler extends Thread {

    private static SQLiteJDBC database;
    private static Flock flock;
    private static FlockDBHelper fdbhelper;
    private static File proxyList;
    private static HashMap<InetAddress, Integer> proxies = new HashMap();
    private static int queue = 0;
    private static int lastqueue = 0;
    private static int processed = 0;
    private static boolean running = false;

    public BotHandler(File database, File proxyList) {
        loadDatabase(database.getAbsolutePath());
    }
    
    public BotHandler(File database) {
        loadDatabase(database.getAbsolutePath());
    }

    public BotHandler(String cwd) {
        loadDatabase(new File(cwd));
    }

    public BotHandler() {
        ;
    }

    private void loadDatabase(String db) {
        try {
            BotHandler.database = new SQLiteJDBC(db);
        } catch (Exception ex) {
            Logger.getLogger(BotHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadDatabase(File cwd) {
        try {
            BotHandler.database = new SQLiteJDBC(cwd);
        } catch (Exception ex) {
            Logger.getLogger(BotHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        
        try {
            if(database.conn == null || database.conn.isClosed()) {
                try {
                    BotHandler.database = new SQLiteJDBC(new File(System.getProperty("user.dir") + "/grapevineflock.sqlite"));
                } catch (Exception ex) {
                    Logger.getLogger(BotHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            // INIT values
            flock = new Flock();
            fdbhelper = new FlockDBHelper(database, flock);
            
        } catch (SQLException ex) {
            Logger.getLogger(BotHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        loop();
    }

    public void loop() {
        running = true;
        while (running) {
            if (queue <= 0) {
                queue = 0;
                try {
                    Thread.sleep(20);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BotHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // TODO
                queue--;
                processed++;
            }
        }

    }

    public static SQLiteJDBC getDatabase() {
        return BotHandler.database;
    }
    
    public static FlockDBHelper getFlockDBHelper() {
        return BotHandler.fdbhelper;
    }
    
    public static Flock getFlock(){
        return BotHandler.flock;
    }
    
    public void setFlock(Flock flock) {
        BotHandler.flock = flock;
    }

    public void increaseQueue() {
        queue++;
    }

    public void setQueue() {
        lastqueue = queue;
    }

    public int getNumBotsProcesses() {
        int psd = processed;
        processed = 0;
        return psd;
    }

    public static void quit() {
        running = false;
    }
}
