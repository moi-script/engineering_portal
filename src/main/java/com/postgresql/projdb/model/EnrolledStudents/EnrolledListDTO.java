package com.postgresql.projdb.model.EnrolledStudents;


public class EnrolledListDTO {

    private String adminToken;
    private String userToken;

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
