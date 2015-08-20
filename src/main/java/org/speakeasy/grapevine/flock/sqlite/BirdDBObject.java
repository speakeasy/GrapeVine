package org.speakeasy.grapevine.flock.sqlite;

import org.speakeasy.grapevine.flock.Bird;
import org.speakeasy.grapevine.flock.FlockDBHelper;

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
    public String usersKeepUnMuted = ""; // b64 JSON object, list of users to never mute.

    public BirdDBObject(int id, String password, String email, int twitterId, String oAuthToken, String oAuthSecret, String consumerToken, String consumerSecret, String botName, String follow, String followGroup, String followed, String following, String usersMuted, String usersUnMuted, String usersKeepUnMuted) {
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

    public BirdDBObject() {
        ;
    }

    public static BirdDBObject birdToDBObject(Bird bird) {
        BirdDBObject birdobj = new BirdDBObject();
        birdobj.id = bird.getId();
        birdobj.password = bird.getPassword();
        birdobj.email = bird.getEmail();
        birdobj.twitterId = bird.getTwitterId();
        birdobj.oAuthToken = bird.getOAuthToken();
        birdobj.oAuthSecret = bird.getOAuthSecret();
        birdobj.consumerToken = bird.getConsumerToken();
        birdobj.consumerSecret = bird.getConsumerSecret();
        birdobj.botName = bird.getName();
        birdobj.follow = FlockDBHelper.b64EncodeSerialize(bird.getFollow());
        birdobj.followGroup = FlockDBHelper.b64EncodeSerialize(bird.getFollowGroup());
        birdobj.followed = FlockDBHelper.b64EncodeSerialize(bird.getFollowed());
        birdobj.following = FlockDBHelper.b64EncodeSerialize(bird.getFollowing());
        birdobj.usersMuted = FlockDBHelper.b64EncodeSerialize(bird.getUsersMuted());
        birdobj.usersUnMuted = FlockDBHelper.b64EncodeSerialize(bird.getUsersUnMuted());
        birdobj.usersKeepUnMuted = FlockDBHelper.b64EncodeSerialize(bird.getUsersKeepUnMuted());
        return birdobj;
    }

    public static Bird birdDBObjectToBird(BirdDBObject birdobj) {
        Bird bird = new Bird();
        bird.setId(birdobj.id);
        bird.setPassword(birdobj.password);
        bird.setEmail(birdobj.email);
        bird.setTwitterId(birdobj.twitterId);
        bird.setOAuthToken(birdobj.oAuthToken);
        bird.setOAuthSecret(birdobj.oAuthSecret);
        bird.setConsumerToken(birdobj.consumerToken);
        bird.setConsumerSecret(birdobj.consumerSecret);
        bird.setName(birdobj.botName);
        bird.setFollow(FlockDBHelper.b64DecodeDeserialize(birdobj.follow));
        bird.setFollowGroup(FlockDBHelper.b64DecodeDeserialize(birdobj.followGroup));
        bird.setFollowed(FlockDBHelper.b64DecodeDeserialize(birdobj.followed));
        bird.setFollowing(FlockDBHelper.b64DecodeDeserialize(birdobj.following));
        bird.setUsersMuted(FlockDBHelper.b64DecodeDeserialize(birdobj.usersMuted));
        bird.setUsersUnMuted(FlockDBHelper.b64DecodeDeserialize(birdobj.usersUnMuted));
        bird.setUsersKeepUnMuted(FlockDBHelper.b64DecodeDeserialize(birdobj.usersKeepUnMuted));
        return bird;
    }

}
