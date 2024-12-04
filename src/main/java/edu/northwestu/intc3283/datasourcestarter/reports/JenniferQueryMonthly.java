package edu.northwestu.intc3283.datasourcestarter.reports;

import org.springframework.data.relational.core.mapping.Column;

public class JenniferQueryMonthly {
    @Column
    private String month_donated;

    @Column
    private int total_donated;

    public String getMonth_donated() {
        return month_donated;
    }

    public void setMonth_donated(String month_donated) {
        this.month_donated = month_donated;
    }

    public int getTotal_donated() {
        return total_donated;
    }

    public void setTotal_donated(int total_donated) {
        this.total_donated = total_donated;
    }
}
