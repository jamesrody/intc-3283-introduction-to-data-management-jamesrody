package edu.northwestu.intc3283.datasourcestarter.reports;

import org.springframework.data.relational.core.mapping.Column;

public class JenniferQueryWeekly {
    @Column
    private String week_donated;

    @Column
    private int total_donated;

    public String getWeek_donated() {
        return week_donated;
    }

    public void setWeek_donated(String week_donated) {
        this.week_donated = week_donated;
    }

    public int getTotal_donated() {
        return total_donated;
    }

    public void setTotal_donated(int total_donated) {
        this.total_donated = total_donated;
    }
}
