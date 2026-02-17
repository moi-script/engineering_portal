
package com.postgresql.projdb.model;

public class AdminLoginDTO {
    private String name;
    private String password;
    private String admintokens;

    public AdminLoginDTO(){}

    public AdminLoginDTO(String name, String password, String admintokens){
        this.name = name;
        this.password = password;
        this.admintokens = admintokens;
    }

    public void setName(String name){
        this.name = name;
    }
    
    public void setPassword(String password){
        this.password = password;
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

    public String getAdmintokens(){
        return admintokens;
    }
}
