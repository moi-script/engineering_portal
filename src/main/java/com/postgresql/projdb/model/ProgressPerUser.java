package com.postgresql.projdb.model;

public class ProgressPerUser {
   
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

    public ProgressPerUser(Long id, String status, int progressPerDays, int totalProgress, int activities, int timeSpent, int scores,
    String admintokens, String name, String password, String token ){

        this.id = id;
        this.status = status;
        this.progressPerDays = progressPerDays;
        this.totalProgress = totalProgress;
        this.activities = activities;
        this.timeSpent = timeSpent;
        this.scores = scores;
        this.admintokens = admintokens;
        this.name = name;
        this.password = password;
        this.token = token;
    }




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
    public Long getId(){
        return id;
    }

// SET
    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public void setId(Long id){
        this.id = id;
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
