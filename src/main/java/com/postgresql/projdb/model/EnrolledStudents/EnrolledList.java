package com.postgresql.projdb.model.EnrolledStudents;

import java.util.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "enrolled_list") // Map the entity to the table
public class EnrolledList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "admin_token", nullable = false) // Map to the admin_token column
    private String adminToken;

    @Column(name = "user_tokens", columnDefinition = "jsonb", nullable = false)
    private List<String> userTokens = new ArrayList<>();

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdminToken() {
        return adminToken;
    }

    public void setAdminToken(String adminToken) {
        this.adminToken = adminToken;
    }

    public List<String> getUserTokens() {
        return userTokens;
    }

    public void setUserTokens(List<String> userTokens) {
        this.userTokens = userTokens;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}