package org.speakeasy.grapevine.flock;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.speakeasy.grapevine.flock.following.TwitterUserMap;
import org.speakeasy.grapevine.flock.sqlite.BirdDBObject;
import org.speakeasy.grapevine.flock.sqlite.CreateDatabase;
import org.speakeasy.grapevine.flock.sqlite.SQLiteJDBC;

/**
 *
 * @author speakeasy
 */
public class FlockDBHelper {
    /*
     int id,
     String password,
     String email,
     int twitterId; // The twitter ID of the bot.
     String oAuthToken = ""; // Twitter API OAuth Token.
     String oAuthSecret = ""; // Twitter API OAuth Secret.
     String consumerToken = ""; // Twitter API Consumer Token.
     String consumerSecret = ""; // Twitter API Consumer Secret.
     String botName = ""; // Twitter account name (Case sensitive.)
     String follow = ""; // b64 JSON object, list of who bot is to follow.
     String followGroup = ""; // b64 JSON object, list of "group" users to repost or generate similar posts to.
     String followed = ""; // b64 JSON object, list of users that are followed.
     String following = ""; // b64 JSON object, list of users following bot.
     String usersMuted = ""; // b64 JSON object, list of users who are muted.
     String usersUnMuted = ""; // b64 JSON object, list of users who are not muted.
     String usersKeepUnMuted = ""; // b64 JSON object, list of users to never mute.
     */

    private Flock theFlock;
    private SQLiteJDBC database;
    private Connection conn;
    private PreparedStatement psUpdateBird;
    private PreparedStatement psInsertBird;

    public FlockDBHelper(SQLiteJDBC database, Flock theFlock) {
        this.database = database;
        this.conn = this.database.getConnection();
        this.theFlock = theFlock;
        initPs();
    }

    public FlockDBHelper(SQLiteJDBC database) {
        this.database = database;
        this.conn = this.database.getConnection();
        this.theFlock = new Flock();
        initPs();
    }
    
    public void initPs() {
        try {
            psUpdateBird = conn.prepareStatement("UPDATE birds SET password = ?, email = ?, twitterid = ?, oauthtoken = ?, oauthsecret = ?, consumertoken = ?, consumersecret = ?, botname = ?, follow = ?, followgroup = ?, followed = ?, following = ?, usersmuted = ?, usersunmuted = ?, userskeepunmuted = ? WHERE botname = ?");
            psInsertBird = conn.prepareStatement("INSERT INTO birds (password, email, twitterid, oauthtoken, oauthsecret, consumertoken, consumersecret, botname, follow, followgroup, followed, following, usersmuted, usersunmuted, userskeepunmuted) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        } catch (SQLException ex) {
            Logger.getLogger(FlockDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Integer getBirdCount() {
        try {
            ResultSet rs = executeQuery("SELECT Count(*) FROM birds");
            rs.last();
            return rs.getRow();
        } catch (SQLException ex) {
            Logger.getLogger(CreateDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Flock getFlockFromDB() {
        Flock tflock = new Flock();
        Bird bird = null;
        ResultSet rs = executeQuery("SELECT * FROM birds");
        String[] columnNames = getColumnNameArray(rs);
        String[] columnTypes = getColumnTypeArray(rs);
        // Objects in row.
        int id = 0; // Database ID.
        String password = null;
        String email = null;
        int twitterId = 0; // The twitter ID of the bot.
        String oAuthToken = ""; // Twitter API OAuth Token.
        String oAuthSecret = ""; // Twitter API OAuth Secret.
        String consumerToken = ""; // Twitter API Consumer Token.
        String consumerSecret = ""; // Twitter API Consumer Secret.
        String botName = ""; // Twitter account name (Case sensitive.)
        TwitterUserMap follow = null; // list of who bot is to follow.
        TwitterUserMap followGroup = null; // list of "group" users to repost or generate similar posts to.
        TwitterUserMap followed = null; // list of users that are followed.
        TwitterUserMap following = null; // list of users following bot.
        TwitterUserMap usersMuted = null; // list of users who are muted.
        TwitterUserMap usersUnMuted = null; // list of users who are not muted.
        TwitterUserMap usersKeepUnMuted = null; // list of users to never mute.
        
        
        try {
            while (rs.next()) {
                id = Integer.decode(rs.getObject(columnNames[0]).toString());
                password = rs.getObject(columnNames[1]).toString();
                email = rs.getObject(columnNames[2]).toString();
                twitterId = Integer.decode(rs.getObject(columnNames[3]).toString());
                oAuthToken = rs.getObject(columnNames[4]).toString();
                oAuthSecret = rs.getObject(columnNames[5]).toString();
                consumerToken = rs.getObject(columnNames[6]).toString();
                consumerSecret = rs.getObject(columnNames[7]).toString();
                botName = rs.getObject(columnNames[8]).toString();
                follow = b64DecodeDeserialize(rs.getObject(columnNames[9]).toString());
                followGroup = b64DecodeDeserialize(rs.getObject(columnNames[10]).toString());
                followed = b64DecodeDeserialize(rs.getObject(columnNames[11]).toString());
                following = b64DecodeDeserialize(rs.getObject(columnNames[12]).toString());
                usersMuted = b64DecodeDeserialize(rs.getObject(columnNames[13]).toString());
                usersUnMuted = b64DecodeDeserialize(rs.getObject(columnNames[14]).toString());
                usersKeepUnMuted = b64DecodeDeserialize(rs.getObject(columnNames[15]).toString());
                
                bird = new Bird(id, password, email, twitterId, oAuthToken, oAuthSecret, consumerToken, consumerSecret, botName, follow, followGroup, followed, following, usersMuted, usersUnMuted, usersKeepUnMuted);
                
                tflock.addBird(bird);
            }
        } catch (SQLException | NumberFormatException ex) {
            Logger.getLogger(FlockDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tflock;
    }
    
    public void writeFlockToDB(Flock theflock) {
        int i = 0;
        int j = theFlock.size() - 1;
        Bird bird = null;
        while(i<= j) {
            bird = theFlock.getBird(i);
            writeBirdToDB(bird);
            i++;
        }
    }
    
    public void writeBirdToDB(Bird bird) {
        try {
            BirdDBObject birdobj = BirdDBObject.birdToDBObject(bird);
            

            // "UPDATE birds SET
            psUpdateBird.setString(1,birdobj.password); // password = ?, TEXT
            psUpdateBird.setString(2,birdobj.email); // email = ?, TEXT
            psUpdateBird.setInt(3,birdobj.twitterId); // twitterid = ?, INTEGER
            psUpdateBird.setString(4,birdobj.oAuthToken); // oauthtoken = ?, TEXT
            psUpdateBird.setString(5,birdobj.oAuthSecret); // oauthsecret = ?, TEXT
            psUpdateBird.setString(6,birdobj.consumerToken); // consumertoken = ?, TEXT
            psUpdateBird.setString(7,birdobj.consumerSecret); // consumersecret = ?, TEXT
            psUpdateBird.setString(8,birdobj.botName); // botname = ?, TEXT
            psUpdateBird.setString(9,birdobj.follow); // follow = ?, TEXT
            psUpdateBird.setString(10,birdobj.followGroup); // followgroup = ?, TEXT
            psUpdateBird.setString(11,birdobj.followed); // followed = ?, TEXT
            psUpdateBird.setString(12,birdobj.following); // following = ?, TEXT
            psUpdateBird.setString(13,birdobj.usersMuted); // usersmuted = ?, TEXT
            psUpdateBird.setString(14,birdobj.usersUnMuted); // usersunmuted = ?, TEXT
            psUpdateBird.setString(15,birdobj.usersKeepUnMuted); // userskeepunmuted = ? TEXT
            psUpdateBird.setString(16,birdobj.botName); // WHERE botname = ? TEXT
            
            psUpdateBird.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(FlockDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void insertBirdToDB(Bird bird) {
        try {
            BirdDBObject birdobj = BirdDBObject.birdToDBObject(bird);
            

            // "INSERT INTO birds
            psInsertBird.setString(1,birdobj.password); // password = ?, TEXT
            psInsertBird.setString(2,birdobj.email); // email = ?, TEXT
            psInsertBird.setInt(3,birdobj.twitterId); // twitterid = ?, INTEGER
            psInsertBird.setString(4,birdobj.oAuthToken); // oauthtoken = ?, TEXT
            psInsertBird.setString(5,birdobj.oAuthSecret); // oauthsecret = ?, TEXT
            psInsertBird.setString(6,birdobj.consumerToken); // consumertoken = ?, TEXT
            psInsertBird.setString(7,birdobj.consumerSecret); // consumersecret = ?, TEXT
            psInsertBird.setString(8,birdobj.botName); // botname = ?, TEXT
            psInsertBird.setString(9,birdobj.follow); // follow = ?, TEXT
            psInsertBird.setString(10,birdobj.followGroup); // followgroup = ?, TEXT
            psInsertBird.setString(11,birdobj.followed); // followed = ?, TEXT
            psInsertBird.setString(12,birdobj.following); // following = ?, TEXT
            psInsertBird.setString(13,birdobj.usersMuted); // usersmuted = ?, TEXT
            psInsertBird.setString(14,birdobj.usersUnMuted); // usersunmuted = ?, TEXT
            psInsertBird.setString(15,birdobj.usersKeepUnMuted); // userskeepunmuted = ? TEXT
            
            psInsertBird.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(FlockDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Flock getFlock() {
        return theFlock;
    }

    public void setFlock(Flock flock) {
        this.theFlock = flock;
    }

    public ResultSet executeQuery(String query) {
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            return s.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(FlockDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String b64EncodeSerialize(TwitterUserMap decoded) {
        Gson gson = new GsonBuilder().create();
        byte[] jsonserialized = gson.toJson(decoded).getBytes();
        return Base64.getEncoder().encodeToString(jsonserialized);
    }

    public static TwitterUserMap b64DecodeDeserialize(String encoded) {
        TwitterUserMap decoded;
        byte[] decode = Base64.getDecoder().decode(encoded);
        String json = "";
        int i = 0;
        while (i < decode.length) {
            json += (char) decode[i];
            i++;
        }
        Gson gson = new GsonBuilder().create();
        Type type = new TypeToken<TwitterUserMap>() {
        }.getType();
        decoded = gson.fromJson(json, type);
        return decoded;
    }

    public static String[] getColumnNameArray(ResultSet rs) {
        String sArr[] = null;
        try {
            ResultSetMetaData rm = rs.getMetaData();
            String sArray[] = new String[rm.getColumnCount()];
            for (int ctr = 1; ctr <= sArray.length; ctr++) {
                String s = rm.getColumnName(ctr);
                sArray[ctr - 1] = s;
            }
            return sArray;
        } catch (Exception e) {
            System.out.println(e);
            return sArr;
        }
    }

    public static String[] getColumnTypeArray(ResultSet rs) {
        String sArr[] = null;
        try {
            ResultSetMetaData rm = rs.getMetaData();
            String sArray[] = new String[rm.getColumnCount()];
            for (int ctr = 1; ctr <= sArray.length; ctr++) {
                String s = rm.getColumnTypeName(ctr);
                sArray[ctr - 1] = s;
            }
            return sArray;
        } catch (Exception e) {
            System.out.println(e);
            return sArr;
        }
    }

}
