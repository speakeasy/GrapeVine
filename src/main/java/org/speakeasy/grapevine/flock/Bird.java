package org.speakeasy.grapevine.flock;

import org.speakeasy.grapevine.flock.following.TwitterUserMap;

/**
 *
 * @author speakeasy
 */
public class Bird {

    public final Bird thebird = this;
    private int id; // Database ID.
    private String password;
    private String email;
    private int twitterId; // The twitter ID of the bot.
    private String oAuthToken = ""; // Twitter API OAuth Token.
    private String oAuthSecret = ""; // Twitter API OAuth Secret.
    private String consumerToken = ""; // Twitter API Consumer Token.
    private String consumerSecret = ""; // Twitter API Consumer Secret.
    private String botName = ""; // Twitter account name (Case sensitive.)
    private TwitterUserMap follow; // list of who bot is to follow.
    private TwitterUserMap followGroup; // list of "group" users to repost or generate similar posts to.
    private TwitterUserMap followed; // list of users that are followed.
    private TwitterUserMap following; // list of users following bot.
    private TwitterUserMap usersMuted; // list of users who are muted.
    private TwitterUserMap usersUnMuted; // list of users who are not muted.
    private TwitterUserMap usersKeepUnMuted; // list of users to never mute.
    private static int followBackoffMin = 42; // The minimum amount of time to wait before following a user.
    private static int followBackoffMax = 255; // The maximum amount of time to wait before following a user.

    public Bird(){
        
    }
    
    public Bird(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Bird(String email, String password, int id) {
        this.email = email;
        this.password = password;
        this.id = id;
    }

    public Bird(int id, String password, String email, int twitterId, String oAuthToken, String oAuthSecret, String consumerToken, String consumerSecret, String botName, TwitterUserMap follow, TwitterUserMap followGroup, TwitterUserMap followed, TwitterUserMap following, TwitterUserMap usersMuted, TwitterUserMap usersUnMuted, TwitterUserMap usersKeepUnMuted) {
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
        this.followed = followed;
        this.following = following;
        this.usersMuted = usersMuted;
        this.usersUnMuted = usersUnMuted;
        this.usersKeepUnMuted = usersKeepUnMuted;
    }

    public String getEmail() {
            return this.email;
    }

    public String getPassword() {
        return this.password;
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

    public TwitterUserMap getFollow() {
        return follow;
    }

    public TwitterUserMap getFollowGroup() {
        return followGroup;
    }

    public TwitterUserMap getFollowed() {
        return followed;
    }

    public TwitterUserMap getFollowing() {
        return following;
    }

    public TwitterUserMap getUsersMuted() {
        return usersMuted;
    }

    public TwitterUserMap getUsersUnMuted() {
        return usersUnMuted;
    }

    public TwitterUserMap getUsersKeepUnMuted() {
        return usersKeepUnMuted;
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

    public void setName(String name) {
        this.botName = name;
    }

    public void setFollow(TwitterUserMap users) {
        follow = users;
    }

    public void setFollowGroup(TwitterUserMap users) {
        followGroup = users;
    }

    public void setFollowed(TwitterUserMap users) {
        followed = users;
    }

    public void setFollowing(TwitterUserMap users) {
        following = users;
    }

    public void setUsersMuted(TwitterUserMap users) {
        usersMuted = users;
    }

    public void setUsersUnMuted(TwitterUserMap users) {
        usersUnMuted = users;
    }

    public void setUsersKeepUnMuted(TwitterUserMap users) {
        usersKeepUnMuted = users;
    }

    public void setEmail(String email) {
            this.email = email;
    }

    public void setPassword(String password) {
            this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }
}
