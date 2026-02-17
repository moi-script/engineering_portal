
package com.postgresql.projdb.model;

public class GetInt {
    private int scores;
    private String tokens;
    GetInt(int scores,  String tokens){
        this.scores = scores;
        this.tokens= tokens;
    }

    public void setScores(int scores){
        this.scores = scores;
    }

    public int getScores(){
        return scores;
    }

    public void setToken(String tokens){
        this.tokens = tokens;
    }

    public String getToken(){
        return tokens;
    }
    
}
