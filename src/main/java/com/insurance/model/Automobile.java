package com.insurance.model;

import javax.persistence.*;

@Entity
@Table(name = "automobile")
public class Automobile {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int automobileId;

    private String vin;

    private int makeModelYear;

    private char status;

    private int aiId;

    public int getAutomobileId() {
        return automobileId;
    }

    public void setAutomobileId(int automobileId) {
        this.automobileId = automobileId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getMakeModelYear() {
        return makeModelYear;
    }

    public void setMakeModelYear(int makeModelYear) {
        this.makeModelYear = makeModelYear;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public int getAiId() {
        return aiId;
    }

    public void setAiId(int aiId) {
        this.aiId = aiId;
    }

    @Override
    public String toString() {
        return "Automobile{" +
                "automobileId=" + automobileId +
                ", vin='" + vin + '\'' +
                ", makeModelYear=" + makeModelYear +
                ", status=" + status +
                ", aiId=" + aiId +
                '}';
    }
}
