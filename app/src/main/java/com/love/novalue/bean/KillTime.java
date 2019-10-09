package com.love.novalue.bean;

public class KillTime {
    private String time;
    private int  status;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public KillTime(String time, int status) {
        this.time = time;
        this.status = status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
