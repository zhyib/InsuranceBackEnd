package com.insurance.model;

import javax.persistence.*;

@Entity
@Table(name = "automobile_payment")
public class AutomobilePayment {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;

    private String paymentType;

    private int invoiceId;

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Override
    public String toString() {
        return "AutomobilePayment{" +
                "paymentId=" + paymentId +
                ", paymentType='" + paymentType + '\'' +
                ", invoiceId=" + invoiceId +
                '}';
    }
}
