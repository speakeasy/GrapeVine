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
    public void start() {
        
        try {
            if(database.conn == null || database.conn.isClosed()) {
                BotHandler.database = new SQLiteJDBC(new File(System.getProperty("user.dir") + "/grapevineflock.sqlite"));
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
        while (running) {
            if (queue == 0) {
                try {
                    Thread.sleep(2);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BotHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            // TODO
        }

    }

    public static SQLiteJDBC getDatabase() {
        return BotHandler.database;
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
        return lastqueue - queue;
    }

    public static void quit() {
        running = false;
    }
}
