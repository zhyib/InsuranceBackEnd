package com.insurance.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "home_invoice")
public class HomeInvoice {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int invoiceId;
    private Date date;
    private Date paymentDueDate;
    private double invoiceAmount;
    private int hiId;

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getPaymentDueDate() {
        return paymentDueDate;
    }

    public void setPaymentDueDate(Date paymenttDueDate) {
        this.paymentDueDate = paymenttDueDate;
    }

    public double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public int getHiId() {
        return hiId;
    }

    public void setHiId(int hiId) {
        this.hiId = hiId;
    }

    @Override
    public String toString() {
        return "HomeInvoice{" +
                "invoiceId=" + invoiceId +
                ", date=" + date +
                ", paymentDueDate=" + paymentDueDate +
                ", invoiceAmount=" + invoiceAmount +
                ", hiId=" + hiId +
                '}';
    }
}
