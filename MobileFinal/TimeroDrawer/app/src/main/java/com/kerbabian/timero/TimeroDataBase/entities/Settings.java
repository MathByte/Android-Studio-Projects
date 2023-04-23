package com.kerbabian.timero.TimeroDataBase.entities;

import java.io.Serializable;

public class Settings implements Serializable {
    private int settingsID;
    private String settingsdarkTheme;
    private String settingsBackroundRun;
    private String settingsTimerNotification;
    private String settingsAlarmSound;
    private String settingsVibration;
    private String settingsAlarmVolume;
    private String settingsFlashLight;

    public int getSettingsID() {
        return settingsID;
    }

    public void setSettingsID(int settingsID) {
        this.settingsID = settingsID;
    }

    public String getSettingsdarkTheme() {
        return settingsdarkTheme;
    }

    public void setSettingsdarkTheme(String settingsdarkTheme) {
        this.settingsdarkTheme = settingsdarkTheme;
    }

    public String getSettingsBackroundRun() {
        return settingsBackroundRun;
    }

    public void setSettingsBackroundRun(String settingsBackroundRun) {
        this.settingsBackroundRun = settingsBackroundRun;
    }

    public String getSettingsTimerNotification() {
        return settingsTimerNotification;
    }

    public void setSettingsTimerNotification(String settingsTimerNotification) {
        this.settingsTimerNotification = settingsTimerNotification;
    }

    public String getSettingsAlarmSound() {
        return settingsAlarmSound;
    }

    public void setSettingsAlarmSound(String settingsAlarmSound) {
        this.settingsAlarmSound = settingsAlarmSound;
    }

    public String getSettingsVibration() {
        return settingsVibration;
    }

    public void setSettingsVibration(String settingsVibration) {
        this.settingsVibration = settingsVibration;
    }

    public String getSettingsAlarmVolume() {
        return settingsAlarmVolume;
    }

    public void setSettingsAlarmVolume(String settingsAlarmVolume) {
        this.settingsAlarmVolume = settingsAlarmVolume;
    }

    public String getSettingsFlashLight() {
        return settingsFlashLight;
    }

    public void setSettingsFlashLight(String settingsFlashLight) {
        this.settingsFlashLight = settingsFlashLight;
    }
}
