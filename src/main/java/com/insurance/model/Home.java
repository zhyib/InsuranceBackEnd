package com.insurance.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "home")
public class Home {
    @Id
    //@GeneratedValue(strategy= GenerationType.IDENTITY)
    private int homeId;

    private Date purchaseDate;

    private Date purchaseValue;

    private double area;

    private char type;

    private int autoFireNotification;

    private int homeSecuritySystem;

    private char swimmingPool;

    private int basement;

    private int hiId;

    public int getHomeId() {
        return homeId;
    }

    public void setHomeId(int homeId) {
        this.homeId = homeId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getPurchaseValue() {
        return purchaseValue;
    }

    public void setPurchaseValue(Date purchaseValue) {
        this.purchaseValue = purchaseValue;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public int getAutoFireNotification() {
        return autoFireNotification;
    }

    public void setAutoFireNotification(int autoFireNotification) {
        this.autoFireNotification = autoFireNotification;
    }

    public int getHomeSecuritySystem() {
        return homeSecuritySystem;
    }

    public void setHomeSecuritySystem(int homeSecuritySystem) {
        this.homeSecuritySystem = homeSecuritySystem;
    }

    public char getSwimmingPool() {
        return swimmingPool;
    }

    public void setSwimmingPool(char swimmingPool) {
        this.swimmingPool = swimmingPool;
    }

    public int getBasement() {
        return basement;
    }

    public void setBasement(int basement) {
        this.basement = basement;
    }

    public int getHiId() {
        return hiId;
    }

    public void setHiId(int hiId) {
        this.hiId = hiId;
    }

    @Override
    public String toString() {
        return "Home{" +
                "homeId=" + homeId +
                ", purchaseDate=" + purchaseDate +
                ", purchaseValue=" + purchaseValue +
                ", area=" + area +
                ", type=" + type +
                ", autoFireNotification=" + autoFireNotification +
                ", homeSecuritySystem=" + homeSecuritySystem +
                ", swimmingPool=" + swimmingPool +
                ", basement=" + basement +
                ", hiId=" + hiId +
                '}';
    }
}
