package com.insurance.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int driverId;

    private String licenseNo;

    private String lastName;

    private String firstName;

    private Date birthdate;

    @ManyToOne
    @JoinColumn(name = "automobile_id")
    private Automobile automobile;

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverId=" + driverId +
                ", licenseNo='" + licenseNo + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthdate=" + birthdate +
                ", automobile=" + automobile +
                '}';
    }
}
