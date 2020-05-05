package com.insurance.model;

import javax.persistence.*;

@Entity
@Table(name = "automobile_payment")
public class AutomobilePayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;

    private String paymentType;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private AutomobileInvoice automobileInvoice;

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

    public AutomobileInvoice getAutomobileInvoice() {
        return automobileInvoice;
    }

    public void setAutomobileInvoice(AutomobileInvoice automobileInvoice) {
        this.automobileInvoice = automobileInvoice;
    }

    @Override
    public String toString() {
        return "AutomobilePayment{" +
                "paymentId=" + paymentId +
                ", paymentType='" + paymentType + '\'' +
                ", automobileInvoice=" + automobileInvoice +
                '}';
    }
}
