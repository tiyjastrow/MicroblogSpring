package com.theironyard;

/**
 * Created by jeremypitt on 10/4/16.
 */
public class User {
    String userName;

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
