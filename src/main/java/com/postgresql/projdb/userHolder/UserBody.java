package com.postgresql.projdb.userHolder;

public class UserBody {
    private String name;
    private String email;
    private String message;

    public UserBody(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }

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
