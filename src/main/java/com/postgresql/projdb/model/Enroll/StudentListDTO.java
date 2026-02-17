package com.postgresql.projdb.model.Enroll;

public class StudentListDTO {
    private String adminToken;
    private String userToken;

    public StudentListDTO(){}

    public StudentListDTO(String userToken){
        this.userToken = userToken;
    }

    // Getters and Setters
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
