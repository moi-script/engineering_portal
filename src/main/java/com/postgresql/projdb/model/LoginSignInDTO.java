package com.postgresql.projdb.model;

public class LoginSignInDTO {
    private String name;
    private String password;
    private String token;
    private String admintokens;

    public LoginSignInDTO(String name, String password, String token, String admintokens) {
        this.name = name;
        this.password = password;
        this.token = token;
        this.admintokens = admintokens;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setTokens(String token){
        this.token = token;
    }

    public void setAdmintokens(String admintokens){
        this.admintokens = admintokens;
    }


    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }

    public String getTokens(){
        return token;
    }

    public String getAdminTokens(){
        return admintokens;
    }


}
