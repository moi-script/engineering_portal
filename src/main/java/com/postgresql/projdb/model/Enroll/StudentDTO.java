package com.postgresql.projdb.model.Enroll;


public class StudentDTO {
    private Long id;
    private String adminToken;  // Added adminToken for the student operation
    private String userToken;

    // Constructor, Getters, Setters
    public StudentDTO() {}

    public StudentDTO(String adminToken, String userToken) {
        this.adminToken = adminToken;
        this.userToken = userToken;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getAdminToken() {
        return adminToken;
    }

    public void setAdminToken(String adminToken) {
        this.adminToken = adminToken;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
