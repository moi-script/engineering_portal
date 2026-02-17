package com.postgresql.projdb.model;


public class MessageDTO {
    private String messageContent;
    private String messageType;
    private String timeStamp;

    // Constructors, Getters, and Setters

    public MessageDTO() {
    }
    public MessageDTO(String messageContent, String messageType, String timeStamp) {
        this.messageContent = messageContent;
        this.messageType = messageType;
        this.timeStamp = timeStamp;
    }

    // Getters and Setters
    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

}