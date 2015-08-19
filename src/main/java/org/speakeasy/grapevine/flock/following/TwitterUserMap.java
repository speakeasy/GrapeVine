package org.speakeasy.grapevine.flock.following;

import java.util.HashMap;

/**
 *
 * @author speakeasy
 */
public class TwitterUserMap extends HashMap<Integer, TwitterUser> {

    private final HashMap<Integer, TwitterUser> users = this;

    public TwitterUser getUser(String name) {
        TwitterUser user = null;
        int i = users.size() - 1;
        while (i >= 0) {
            user = users.get(i);
            if (user.name == null ? name == null : user.name.equals(name)) {
                return user;
            }
            i--;
        }
        return null;
    }

    public TwitterUser getUser(Integer twitterId) {
        TwitterUser user = null;
        int i = users.size() - 1;
        while (i >= 0) {
            user = users.get(i);
            if (user.twitterId == 0 ? twitterId == null : user.twitterId.equals(twitterId)) {
                return user;
            }
            i--;
        }
        return null;
    }

    public TwitterUser getUser(String name, Integer twitterId) {
        TwitterUser user = null;
        int i = users.size() - 1;
        while (i >= 0) {
            user = users.get(i);
            if (user.name == null ? name == null : user.name.equals(name)) {
                if (user.twitterId == 0 ? twitterId == null : user.twitterId.equals(twitterId)) {
                    return user;
                }
            }
            i--;
        }
        return null;
    }

    public void add(TwitterUser user) {
        users.put(users.size(), user);
    }

    public void remove(TwitterUser user) {
        if (users.containsValue(user)) {
            int i = users.size() - 1;
            while (i >= 0) {
                if (user.equals(users.get(i))) {
                    users.remove(i);
                }
                i--;
            }
        }
    }

    public boolean contains(TwitterUser user) {
        int i = users.size() - 1;
        while (i >= 0) {
            if (users.get(i) == user) {
                return true;
            }
            i--;
        }
        return false;
    }

}
