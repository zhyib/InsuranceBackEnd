package com.insurance.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "automobile")
public class Automobile {
    @Id
    private int automobileId;
    private String vin;
    private int makeModelYear;
    private char status;
    private int automobileInsuranceCustomerAiId;

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

    public int getAutomobileInsuranceCustomerAiId() {
        return automobileInsuranceCustomerAiId;
    }

    public void setAutomobileInsuranceCustomerAiId(int automobileInsuranceCustomerAiId) {
        this.automobileInsuranceCustomerAiId = automobileInsuranceCustomerAiId;
    }

    @Override
    public String toString() {
        return "Automobile{" +
                "automobileId=" + automobileId +
                ", vin='" + vin + '\'' +
                ", makeModelYear=" + makeModelYear +
                ", status=" + status +
                ", automobileInsuranceCustomerAiId=" + automobileInsuranceCustomerAiId +
                '}';
    }
}
