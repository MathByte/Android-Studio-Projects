package com.kerbabian.timero.TimeroDataBase.entities;

import java.io.Serializable;

public class Timer implements Serializable {

    private int timerID;
    private String timer_Lable;
    private String timer_Time;

    public int getTimerID() {
        return timerID;
    }

    public void setTimerID(int timerID) {
        this.timerID = timerID;
    }

    public String getTimer_Lable() {
        return timer_Lable;
    }

    public void setTimer_Lable(String timer_Lable) {
        this.timer_Lable = timer_Lable;
    }

    public String getTimer_Time() {
        return timer_Time;
    }

    public void setTimer_Time(String timer_Time) {
        this.timer_Time = timer_Time;
    }
}
