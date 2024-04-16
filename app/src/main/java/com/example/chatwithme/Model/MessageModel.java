package com.example.chatwithme.Model;

public class MessageModel {

    String uId, messase,messageId;
    Long timestamp;



    public MessageModel(String uId, String messase, Long timestamp) {
        this.uId = uId;
        this.messase = messase;
        this.timestamp = timestamp;
    }

    public MessageModel(String uId, String messase) {
        this.uId = uId;
        this.messase = messase;
    }

    public MessageModel()
    {


    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getMessase() {
        return messase;
    }

    public void setMessase(String messase) {
        this.messase = messase;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
