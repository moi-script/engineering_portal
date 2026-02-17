package com.postgresql.projdb.model;

import java.util.List;

public class MessagesListDTO {
    private Long id;
    private String name;
    private String admintoken;  // Admin token
    private String personConvoWithToken;  // User token to identify conversation partner
    private List<MessageDTO> messages;  // List of messages in the conversation

    // Constructors
    public MessagesListDTO() {}

    public MessagesListDTO(Long id, String name, String admintoken, String personConvoWithToken, List<MessageDTO> messages) {
        this.id = id;
        this.name = name;
        this.admintoken = admintoken;
        this.personConvoWithToken = personConvoWithToken;
        this.messages = messages;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdminToken() {
        return admintoken;
    }

    public void setAdminToken(String admintoken) {
        this.admintoken = admintoken;
    }

    public String getPersonConvoWithToken() {
        return personConvoWithToken;
    }

    public void setPersonConvoWithToken(String personConvoWithToken) {
        this.personConvoWithToken = personConvoWithToken;
    }

    public List<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDTO> messages) {
        this.messages = messages;
    }
}
