package com.postgresql.projdb.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "admin")
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(name = "adminTokens", length = 255)
    private String admintokens;

    @Column(nullable = false)
    private Integer studentcounts;

    // One-to-Many relationship with MessagesList
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MessagesList> messagesList;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdminTokens() {
        return admintokens;
    }

    public void setAdminTokens(String admintokens) {
        this.admintokens = admintokens;
    }

    public Integer getStudentCounts() {
        return studentcounts;
    }

    public void setStudentCounts(Integer studentcounts) {
        this.studentcounts = studentcounts;
    }

    public List<MessagesList> getMessagesList() {
        return messagesList;
    }

    public void setMessagesList(List<MessagesList> messagesList) {
        this.messagesList = messagesList;
    }
}

