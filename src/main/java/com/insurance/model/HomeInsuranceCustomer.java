package com.insurance.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "home_insurance_customer")
public class HomeInsuranceCustomer {
    @Id
    private int customerId;

    private int hiId;

    private Date startDate;

    private Date endDate;

    double premiumAmount;

    private char status;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getHiId() {
        return hiId;
    }

    public void setHiId(int hiId) {
        this.hiId = hiId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "HomeInsuranceCustomer{" +
                "customerId=" + customerId +
                ", hiId=" + hiId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", premiumAmount=" + premiumAmount +
                ", status=" + status +
                '}';
    }
}
