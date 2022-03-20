package com.example.boster;

public class APIResponse {

    private String status;
    private DataResponse data;

    public String status(){
        return this.status;
    }

    public DataResponse data(){
        return this.data;
    }
}
