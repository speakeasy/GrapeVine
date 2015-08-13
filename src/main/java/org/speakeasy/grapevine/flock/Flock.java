package org.speakeasy.grapevine.flock;

import java.util.HashMap;

/**
 *
 * @author speakeasy
 */
public class Flock {
    private final Flock theflock = this;
    private HashMap<Integer, Bird> birds;
    
    public Flock(String birdDB) {
        
    }
    
    @SuppressWarnings("empty-statement")
    public Flock() {
        ;
    }
    
    public Bird getBird(Integer bird) {
        if (birds.containsKey(bird)) {
            return birds.get(bird);
        }
        return null;
    }
    
    public boolean addBird(Bird bird) {
        if(bird != null) {
            birds.put(birds.size(), bird);
            return true;
        }
        return false;
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
