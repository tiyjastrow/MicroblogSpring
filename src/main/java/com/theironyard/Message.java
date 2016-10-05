package com.theironyard;

/**
 * Created by WesleyLewis on 10/4/16.
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
        return "Message " +
                "Id: " + id +
                ", Text: " + text;
    }
}
