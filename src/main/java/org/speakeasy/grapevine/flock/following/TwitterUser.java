package org.speakeasy.grapevine.flock.following;

/**
 *
 * @author speakeasy
 */
public class TwitterUser {
    public String name = "";
    public Integer twitterId;
    
    public TwitterUser(String name) {
        this.name = name;
    }
    
    public TwitterUser(Integer twitterId) {
        this.twitterId = twitterId;
    }
    
    public TwitterUser(String name, Integer twitterId) {
        this.name = name;
        this.twitterId = twitterId;
    }
}
