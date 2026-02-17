package com.postgresql.projdb.model;

import jakarta.persistence.*;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message_content", nullable = false)
    private String messageContent;

    @Column(name = "message_type", nullable = false)
    private String messageType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "messages_list_id", nullable = false)
    private MessagesList messagesList;

    @Column(name = "time_stamp", nullable = false)
    private String timeStamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public MessagesList getMessagesList() {
        return messagesList;
    }

    public void setMessagesList(MessagesList messagesList) {
        this.messagesList = messagesList;
    }

    // Getters and Setters
}
