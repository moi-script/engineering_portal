package com.postgresql.projdb.model;

import java.time.LocalDateTime;

public class ProcessedMessageDTO {

    private Long id;
    private String senderName;
    private String receiverName;
    private String senderToken;
    private String receiverToken;
    private String content;
    private String messageType;
    private String status;
    private LocalDateTime createdAt;
    private Long messagesListId;   // optional — include when you want to link to a conversation

    public ProcessedMessageDTO() {}

    public ProcessedMessageDTO(Long id, String senderName, String receiverName,
                               String senderToken, String receiverToken,
                               String content, String messageType, String status,
                               LocalDateTime createdAt, Long messagesListId) {
        this.id = id;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.senderToken = senderToken;
        this.receiverToken = receiverToken;
        this.content = content;
        this.messageType = messageType;
        this.status = status;
        this.createdAt = createdAt;
        this.messagesListId = messagesListId;
    }

    // ── Getters & Setters ──────────────────────────────────────────────────────

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSenderName() { return senderName; }
    public void setSenderName(String senderName) { this.senderName = senderName; }

    public String getReceiverName() { return receiverName; }
    public void setReceiverName(String receiverName) { this.receiverName = receiverName; }

    public String getSenderToken() { return senderToken; }
    public void setSenderToken(String senderToken) { this.senderToken = senderToken; }

    public String getReceiverToken() { return receiverToken; }
    public void setReceiverToken(String receiverToken) { this.receiverToken = receiverToken; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getMessageType() { return messageType; }
    public void setMessageType(String messageType) { this.messageType = messageType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Long getMessagesListId() { return messagesListId; }
    public void setMessagesListId(Long messagesListId) { this.messagesListId = messagesListId; }
}
