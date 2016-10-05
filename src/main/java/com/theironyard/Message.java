package com.theironyard;

/**
 * Created by Zach on 10/4/16.
 */
public class Message {
    int id;
    String text;

    public Message() {
    }

    public Message(int id, String text) {
        this.id = id;
        this.text = text;
    }

    @Override
    public String toString() {
        return String.format("%s",text);
    }
}
