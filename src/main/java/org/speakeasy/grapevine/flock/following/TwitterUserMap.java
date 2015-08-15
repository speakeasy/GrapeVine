package org.speakeasy.grapevine.flock.following;

import java.util.HashMap;

/**
 *
 * @author speakeasy
 */
public class TwitterUserMap extends HashMap<Integer, TwitterUser> {

    private HashMap<Integer, TwitterUser> users = this;

    public TwitterUserMap() {
        ;
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
