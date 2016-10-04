package com.theironyard;

/**
 * Created by jeremypitt on 10/4/16.
 */
public class Message {
    int id;
    String text;

    public Message(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return String.format("%s", text);
    }
}
