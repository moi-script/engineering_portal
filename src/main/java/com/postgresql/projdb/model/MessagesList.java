// package com.postgresql.projdb.model;


// import jakarta.persistence.*;
// import java.util.List;

// @Entity
// @Table(name = "messages_list")
// public class MessagesList {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String name;

//     @ManyToOne
//     @JoinColumn(name = "admin_id")
//     private AdminEntity admin;

//     @Column(name = "admintoken")
//     private String admintoken;

//     @OneToMany(mappedBy = "messagesList", cascade = CascadeType.ALL, orphanRemoval = true)
//     private List<Message> content;

//     // Getters and Setters

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public AdminEntity getAdmin() {
//         return admin;
//     }

//     public void setAdmin(AdminEntity admin) {
//         this.admin = admin;
//     }

//     public List<Message> getContent() {
//         return content;
//     }

//     public void setContent(List<Message> content) {
//         this.content = content;
//     }
//     public String getAdminToken() {
//         return admintoken; // Corrected method name
//     }
    
//     public void setAdminToken(String adminToken) {
//         this.admintoken = adminToken;
//     }
// }



package com.postgresql.projdb.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "messages_list")
public class MessagesList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private AdminEntity admin;

    @Column(name = "admintoken")
    private String admintoken;

    @Column(name = "personconvo_token")
    private String personConvoWithToken;  // New field for identifying user/admin conversation

    @OneToMany(mappedBy = "messagesList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> content;

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

    public AdminEntity getAdmin() {
        return admin;
    }

    public void setAdmin(AdminEntity admin) {
        this.admin = admin;
    }

    public List<Message> getContent() {
        return content;
    }

    public void setContent(List<Message> content) {
        this.content = content;
    }

    public String getAdminToken() {
        return admintoken;
    }

    public void setAdminToken(String adminToken) {
        this.admintoken = adminToken;
    }

    public String getPersonConvoWithToken() {
        return personConvoWithToken;
    }

    public void setPersonConvoWithToken(String personConvoWithToken) {
        this.personConvoWithToken = personConvoWithToken;
    }

    // Add content (messages) to the list
    public void addContent(Message message) {
        if (this.content != null) {
            this.content.add(message);
            message.setMessagesList(this); // Set the relationship from the message side
        }
    }
}
