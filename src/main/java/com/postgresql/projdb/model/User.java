package com.postgresql.projdb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Use the correct strategy for the SERIAL column
    private Long id;
    private String status;
    private int progressPerDays;
    private int totalProgress;
    private int activities;
    private int timeSpent;
    private int scores;
    private String admintokens;
    private String name;
    private String password; 
    private String token;


    public User() {}

    public String getName() {
        return name;
    }
    public String getAdminTokens(){
        return admintokens;
    }
    public String getPassword() {
        return password;
    }
    public String getToken() {
        return token;
    }
    public int progressPerDays() {
        return progressPerDays;
    }
    public int totalProgress() {
        return totalProgress;
    }
    public int activities() {
        return activities;
    }
    public int timeSpent(){
        return timeSpent;
    }
    public int scores(){
        return scores;
    }

// SET
    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setPassword (String password){
        this.password = password;
    } 
    public void setAdminTokens(String admintokens){
        this.admintokens = admintokens;
    }
    public void setToken (String token){
        this.token = token;
    } 
    public void setProgressPerDays (int progressPerDays){
        this.progressPerDays = progressPerDays;
    } 
    public void setTotalProgress (int totalProgress){
        this.totalProgress = totalProgress;
    }
    public void setActivities(int activities){
        this.activities = activities;
    }
    public void setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
    }
    public void setScores(int scores){
        this.scores = scores;
    }

   
}
