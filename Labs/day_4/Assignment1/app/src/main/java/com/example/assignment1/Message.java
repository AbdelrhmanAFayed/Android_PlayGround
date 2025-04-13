package com.example.assignment1;

public class Message {

    String Mobile  ;
    String Message ;

    public Message(String message, String mobile) {
        Message = message;
        Mobile = mobile;
    }

    public String getMessage() {
        return Message;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
