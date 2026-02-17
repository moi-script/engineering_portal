package com.postgresql.projdb.model;

public class MessageTokenDTO {
    private String admintoken;

    public MessageTokenDTO(){}

    public MessageTokenDTO(String admintoken){
        this.admintoken = admintoken;
    }

    public void setAdminToken(String admintoken){
        this.admintoken = admintoken;
    }
    public String getAdminToken(){
        return admintoken;
    }
}
