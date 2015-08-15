package org.speakeasy.grapevine.flock;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
     String usersKeepUnmuted = ""; // b64 JSON object, list of users to never mute.
     */

    private Flock theFlock;
    private SQLiteJDBC database;
    private Connection conn;

    public FlockDBHelper(SQLiteJDBC database, Flock theFlock) {
        this.database = database;
        this.conn = this.database.getConnection();
        this.theFlock = theFlock;
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

    public String b64EncodeSerialize(HashMap<Integer, String> decoded) {
        Gson gson = new GsonBuilder().create();
        byte[] jsonserialized = gson.toJson(decoded).getBytes();
        return Base64.getEncoder().encodeToString(jsonserialized);
    }

    public HashMap<Integer, String> b64DecodeDeserialize(String encoded) {
        HashMap<Integer, String> decoded;
        byte[] decode = Base64.getDecoder().decode(encoded);
        String json = "";
        int i = 0;
        while (i < decode.length) {
            json += (char) decode[i];
            i++;
        }
        Gson gson = new GsonBuilder().create();
        Type type = new TypeToken<HashMap<Integer, String>>() {
        }.getType();
        decoded = gson.fromJson(json, type);
        return decoded;
    }

}
