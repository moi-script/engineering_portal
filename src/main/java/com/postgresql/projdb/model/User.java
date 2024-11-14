package com.postgresql.projdb.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "hello")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Use the correct strategy for the SERIAL column
    private long id;
    private String name;
    private String email;
    private String message;

     // Getter and Setter methods (you can use Lombok annotations if needed)
     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
