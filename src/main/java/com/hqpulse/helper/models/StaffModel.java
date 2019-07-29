package com.hqpulse.helper.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Calendar;


//@JsonInclude(JsonInclude.Include.NON_NULL)
public class StaffModel implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    @JsonProperty("localId")
    private String localId;
    //@JsonProperty("firstName")
    private String firstName;
    @JsonProperty("middleName")
    private String middleName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("staffId")
    private String staffId;
    @JsonProperty("designation")
    private String designation;
    @JsonProperty("dateOfJoining")
    private Calendar dateOfJoining;
    @JsonProperty("dateResigned")
    private Calendar dateResigned;

    public StaffModel() {
        //Default constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Calendar getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Calendar dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public Calendar getDateResigned() {
        return dateResigned;
    }

    public void setDateResigned(Calendar dateResigned) {
        this.dateResigned = dateResigned;
    }

    @Override
    public String toString() {
        return "StaffModel{" +
                "id=" + id +
                ", localId='" + localId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", staffId='" + staffId + '\'' +
                ", designation='" + designation + '\'' +
                ", dateOfJoining=" + dateOfJoining +
                ", dateResigned=" + dateResigned +
                '}';
    }
}
