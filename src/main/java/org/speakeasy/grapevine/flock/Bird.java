package org.speakeasy.grapevine.flock;

/**
 *
 * @author speakeasy
 */
public class Bird {

    public final Bird thebird = this;
    private String password;
    private String email;
    private int id;

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
