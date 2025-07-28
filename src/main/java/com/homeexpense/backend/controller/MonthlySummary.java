package com.homeexpense.backend.controller;

public class MonthlySummary {
    private String month;
    private long presentDays;
    private long absentDays;

    public MonthlySummary(String month, long presentDays, long absentDays) {
        this.month = month;
        this.presentDays = presentDays;
        this.absentDays = absentDays;
    }

    public String getMonth() {
        return month;
    }

    public long getPresentDays() {
        return presentDays;
    }

    public long getAbsentDays() {
        return absentDays;
    }
}
