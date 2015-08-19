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
import org.speakeasy.grapevine.flock.Bird;
import org.speakeasy.grapevine.flock.following.TwitterUser;
import org.speakeasy.grapevine.flock.following.TwitterUserMap;

/**
 *
 * @author speakeasy
 */
public class BotImporter {

    private static SQLiteJDBC database;
    private File fimport;
    private boolean isPyImport;
    private HashMap<Integer, String> fileLines = new HashMap();

    /**
     *
     * @param database
     * @param fimport
     * @param isPyImport
     */
    public BotImporter(SQLiteJDBC database, File fimport, boolean isPyImport) {
        this.database = database;
        this.fimport = fimport;
        this.isPyImport = isPyImport;
        readFile(fimport);
    }

    /**
     *
     */
    public void doImport() {
        if (this.isPyImport) {
            doPyImport();
            return;
        }
        // regular import?
    }

    /**
     *
     */
    public void doPyImport() {
        int i = 0;
        int j = fileLines.size() - 1;
        String line = null;
        Bird bird = new Bird();
        while (i <= j) {
            line = fileLines.get(i++);
            if (line.startsWith("OAUTH_TOKEN")) {
                bird.setOAuthToken(line.substring(12, line.length() + 1));
                line = fileLines.get(i++);
                bird.setOAuthSecret(line.substring(13, line.length() + 1));
                line = fileLines.get(i++);
                bird.setConsumerToken(line.substring(13, line.length() + 1));
                line = fileLines.get(i++);
                bird.setConsumerSecret(line.substring(16, line.length() + 1));
                line = fileLines.get(i++);
                bird.setName(line.substring(15, line.length() + 1));
                line = fileLines.get(i+3); // 3: ALREADY_FOLLOWED_FILE:, FOLLOWERS_FILE:, FOLLOWS_FILE:
                bird.setFollowGroup(toUserMap(line.substring(21, line.length() + 1)));
                line = fileLines.get(i++);
                bird.setUsersKeepUnMuted(toUserMap(line.substring(19, line.length() + 1)));
                line = fileLines.get(i++);
                bird.setUsersMuted(toUserMap(line.substring(17, line.length() + 1)));
            }
        }
    }

    private TwitterUserMap toUserMap(String usercsv) {
        TwitterUserMap usermap = new TwitterUserMap();
        TwitterUser user = null;
        String[] users = usercsv.split(",");
        int i = 0;
        while(i < users.length) {
            user = new TwitterUser(Integer.decode(users[i].replaceAll("[^0-9]", "")));
            usermap.add(user);
        }
        return usermap;
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
