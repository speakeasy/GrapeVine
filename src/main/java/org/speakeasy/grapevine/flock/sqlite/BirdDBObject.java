package org.speakeasy.grapevine.flock.sqlite;

/**
 *
 * @author speakeasy
 */
public class BirdDBObject {

    public int id; // IDX
    public String password = ""; // Password for logging into twitter
    public String email = ""; // Email for logging into twitter
    public int twitterId; // The twitter ID of the bot.
    public String oAuthToken = ""; // Twitter API OAuth Token.
    public String oAuthSecret = ""; // Twitter API OAuth Secret.
    public String consumerToken = ""; // Twitter API Consumer Token.
    public String consumerSecret = ""; // Twitter API Consumer Secret.
    public String botName = ""; // Twitter account name (Case sensitive.)
    public String follow = ""; // b64 JSON object, list of who bot is to follow.
    public String followGroup = ""; // b64 JSON object, list of "group" users to repost or generate similar posts to.
    public String followed = ""; // b64 JSON object, list of users that are followed.
    public String following = ""; // b64 JSON object, list of users following bot.
    public String usersMuted = ""; // b64 JSON object, list of users who are muted.
    public String usersUnMuted = ""; // b64 JSON object, list of users who are not muted.
    public String usersKeepUnmuted = ""; // b64 JSON object, list of users to never mute.

    public BirdDBObject(int id, String password, String email, int twitterId, String oAuthToken, String oAuthSecret, String consumerToken, String consumerSecret, String botName, String follow, String followGroup, String followed, String following, String usersMuted, String usersUnMuted, String usersKeepUnMuted){
        this.id = id;
        this.password = password;
        this.email = email;
        this.twitterId = twitterId;
        this.oAuthToken = oAuthToken;
        this.oAuthSecret = oAuthSecret;
        this.consumerToken = consumerToken;
        this.consumerSecret = consumerSecret;
        this.botName = botName;
        this.follow = follow;
        this.followGroup = followGroup;
        this.following = following;
        this.usersMuted = usersMuted;
        this.usersUnMuted = usersUnMuted;
        this.usersKeepUnmuted = usersKeepUnMuted;
    }
    
    public BirdDBObject() {
        ;
    }

}
