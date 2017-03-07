package com.theironyard;

public class Message {
    Integer id;
    String text;
    static Integer uniqueId = 0;

    public Message(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public Message() {
    }

    public Message(String text) {
        this.id = uniqueId++;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
