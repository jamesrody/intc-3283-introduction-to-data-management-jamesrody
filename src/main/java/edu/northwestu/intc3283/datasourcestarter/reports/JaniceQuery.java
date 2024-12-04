package edu.northwestu.intc3283.datasourcestarter.reports;

import org.springframework.data.relational.core.mapping.Column;

public class JaniceQuery {
    private int donor_id;
    private String first_name;
    private String last_name;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip_code;

    @Column("donation_count")
    public int donation_count;

    @Column("total_donated")
    public int total_donated;


    public int getDonor_id() {
        return donor_id;
    }

    public void setDonor_id(int donor_id) {
        this.donor_id = donor_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getTotal_donated() {
        return total_donated;
    }

    public void setTotal_donated(int total_donated) {
        this.total_donated = total_donated;
    }

    public int getDonation_count() {
        return donation_count;
    }

    public void setDonation_count(int donation_count) {
        this.donation_count = donation_count;
    }
}