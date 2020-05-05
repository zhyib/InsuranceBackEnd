package com.insurance.model;

import javax.persistence.*;

@Entity
@Table(name = "home_payment")
public class HomePayment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int paymentId;

    private String paymentType;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private HomeInvoice homeInvoice;

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

    public HomeInvoice getHomeInvoice() {
        return homeInvoice;
    }

    public void setHomeInvoice(HomeInvoice homeInvoice) {
        this.homeInvoice = homeInvoice;
    }

    @Override
    public String toString() {
        return "HomePayment{" +
                "paymentId=" + paymentId +
                ", paymentType='" + paymentType + '\'' +
                ", homeInvoice=" + homeInvoice +
                '}';
    }
}
