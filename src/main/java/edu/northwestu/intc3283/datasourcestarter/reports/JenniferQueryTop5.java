package edu.northwestu.intc3283.datasourcestarter.reports;

import org.springframework.data.relational.core.mapping.Column;

public class JenniferQueryTop5 {
    private int donor_id;

    @Column
    private int total_donated;

    public int getDonor_id() {
        return donor_id;
    }

    public void setDonor_id(int donor_id) {
        this.donor_id = donor_id;
    }

    public int getTotal_donated() {
        return total_donated;
    }

    public void setTotal_donated(int total_donated) {
        this.total_donated = total_donated;
    }
}
