package com.postgresql.projdb.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "processed_messages")
public class ProcessedMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender_name", nullable = false, length = 255)
    private String senderName;

    @Column(name = "receiver_name", nullable = false, length = 255)
    private String receiverName;

    @Column(name = "sender_token", nullable = false, length = 255)
    private String senderToken;

    @Column(name = "receiver_token", nullable = false, length = 255)
    private String receiverToken;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "message_type", length = 50)
    private String messageType = "text";   // e.g. 'text', 'image', 'file'

    @Column(name = "status", length = 20)
    private String status = "sent";        // e.g. 'sent', 'delivered', 'read'

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // FK → messages_list
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "messages_list_id")
    private MessagesList messagesList;

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
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

    public MessagesList getMessagesList() { return messagesList; }
    public void setMessagesList(MessagesList messagesList) { this.messagesList = messagesList; }
}
