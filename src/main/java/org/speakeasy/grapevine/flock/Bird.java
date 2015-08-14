package org.speakeasy.grapevine.flock;

/**
 *
 * @author speakeasy
 */
public class Bird {

    public final Bird thebird = this;
    private String password;
    private String email;
    private int id; // Database ID.
    private int twitterId; // The twitter ID of the bot.
    private String oAuthToken = ""; // Twitter API OAuth Token.
    private String oAuthSecret = ""; // Twitter API OAuth Secret.
    private String consumerToken = ""; // Twitter API Consumer Token.
    private String consumerSecret = ""; // Twitter API Consumer Secret.
    private String botName = ""; // Twitter account name (Case sensitive.)

    public Bird(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Bird(String email, String password, int id) {
        this.email = email;
        this.password = password;
        this.id = id;
    }

    public String getEmail() {
        if (this.email != null) {
            return this.email;
        }
        return null;
    }

    public String getPassword() {
        if (this.password != null) {
            return this.password;
        }
        return null;
    }

    public int getId() {
        return this.id;
    }
    
    public String getOAuthToken() {
        return this.oAuthToken;
    }
    
    public String getOAuthSecret() {
        return this.oAuthSecret;
    }
    
    public String getConsumerToken() {
        return this.consumerToken;
    }
    
    public String getConsumerSecret() {
        return this.consumerSecret;
    }
    
    public String getName() {
        return this.botName;
    }
    
    public void setOAuthToken(String token) {
        this.oAuthToken = token;
    }
    
    public void setOAuthSecret(String secret) {
        this.oAuthSecret = secret;
    }
    
    public void setConsumerToken(String token) {
        this.consumerToken = token;
    }
    
    public void setConsumerSecret(String secret) {
        this.consumerSecret = secret;
    }
    
    public void serName(String name) {
        this.botName = name;
    }

    public boolean setEmail(String email) {
        if (email != null) {
            this.email = email;
            return true;
        }
        return false;
    }

    public boolean setPassword(String password) {
        if (password != null && password.length() >= 6) {
            this.password = password;
            return true;
        }
        return false;
    }

    public boolean setId(int id) {
        this.id = id;
        if (this.id == id) {
            return true;
        }
        return false;
    }
}
