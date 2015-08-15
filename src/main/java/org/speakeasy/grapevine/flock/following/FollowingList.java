package org.speakeasy.grapevine.flock.following;

import java.util.HashMap;

/**
 *
 * @author speakeasy
 */
public class FollowingList {

    private HashMap<Integer, Follower> followers;

    public FollowingList() {
        this.followers = new HashMap();
    }

    public void addFollower(Follower follower) {
        followers.put(followers.size(), follower);
    }

    public void removeFollower(Follower follower) {
        if (followers.containsValue(follower)) {
            int i = followers.size() - 1;
            while (i >= 0) {
                if (follower.equals(followers.get(i))) {
                    followers.remove(i);
                }
                i--;
            }
        }
    }

    public boolean contains(Follower follower) {
        int i = followers.size() - 1;
        while (i >= 0) {
            if (followers.get(i) == follower) {
                return true;
            }
            i--;
        }
        return false;
    }
    
    
    
}
