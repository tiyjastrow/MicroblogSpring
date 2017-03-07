package com.theironyard;

public class Message {
    int id;
    String text;
    static int counter = 0;

    public Message(String text) {
        this.id = counter++;
        this.text = text;
    }
}
