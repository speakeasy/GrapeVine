package org.speakeasy.grapevine.flock;

import java.util.HashMap;

/**
 *
 * @author speakeasy
 */
public class Flock {
    private final Flock theflock = this;
    private HashMap<Integer, Bird> birds = new HashMap();
    
    public Flock() {
        ;
    }
    
    public boolean contains(Bird bird) {
        int i = birds.size() - 1;
        while (i >= 0) {
            if (birds.get(i) == bird) {
                return true;
            }
            i--;
        }
        return false;
    }
    
    public Bird getBird(Integer bird) {
        if (birds.containsKey(bird)) {
            return birds.get(bird);
        }
        return null;
    }
    
    public Integer size() {
        return birds.size();
    }
    
    public void addBird(Bird bird) {
        if(bird != null) {
            birds.put(birds.size(), bird);
        }
    }
    
    public boolean removeBird(Bird bird) {
        if(bird != null) {
            if(birds.containsValue(bird)) {
                int h = birds.size() - 1;
                int i = birds.size() - 1;
                while (i >= 0) {
                    if(birds.get(i).equals(bird)) {
                        birds.remove(i);
                    }
                    i--;
                }
                if(birds.size() - 1 > h) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean removeBird(int bird) {
        if(birds.containsKey(bird)) {
            birds.remove(bird);
            return true;
        }
        return false;
    }
}
