package org.speakeasy.grapevine.flock.sqlite;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author speakeasy
 */
public class CreateDatabase {

    private String sMakeTableBirds = "CREATE TABLE IF NOT EXISTS birds (id INTEGER PRIMARY KEY ASC, password TEXT, email TEXT, twitterid INTEGER, oauthtoken TEXT, oauthsecret TEXT, consumertoken TEXT, consumersecret TEXT, botname TEXT, follow TEXT, followgroup TEXT, followed TEXT, following TEXT, usersmuted TEXT, usersunmuted TEXT, userskeepunmuted TEXT)";
    private SQLiteJDBC database;
    
    public CreateDatabase(SQLiteJDBC database) {
        this.database = database;
    }
    
    public void createDBTableBirds() {
        try {
            PreparedStatement ps = database.getConnection().prepareStatement(sMakeTableBirds);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CreateDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
