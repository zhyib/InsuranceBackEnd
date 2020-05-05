package com.insurance.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "automobile_invoice")
public class AutomobileInvoice {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int invoiceId;

    private Date date;

    private Date paymentDueDate;

    private double invoiceAmount;

    @ManyToOne
    @JoinColumn(name = "aiId")
    private AutomobileInsuranceCustomer automobileInsuranceCustomer;

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

    public AutomobileInsuranceCustomer getAutomobileInsuranceCustomer() {
        return automobileInsuranceCustomer;
    }

    public void setAutomobileInsuranceCustomer(AutomobileInsuranceCustomer automobileInsuranceCustomer) {
        this.automobileInsuranceCustomer = automobileInsuranceCustomer;
    }

    @Override
    public String toString() {
        return "AutomobileInvoice{" +
                "invoiceId=" + invoiceId +
                ", date=" + date +
                ", paymentDueDate=" + paymentDueDate +
                ", invoiceAmount=" + invoiceAmount +
                ", automobileInsuranceCustomer=" + automobileInsuranceCustomer +
                '}';
    }
}
