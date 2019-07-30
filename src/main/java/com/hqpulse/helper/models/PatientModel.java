package com.hqpulse.helper.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hqpulse.helper.utils.Utils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PatientModel implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String localId;
    private String mrdNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private Calendar dateOfBirth;
    private Integer genderCode;
    private Set<VisitModel> visits = new HashSet<>();

    public PatientModel() {
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

    public String getMrdNumber() {
        return mrdNumber;
    }

    public void setMrdNumber(String mrdNumber) {
        this.mrdNumber = mrdNumber;
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

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(Integer genderCode) {
        this.genderCode = genderCode;
    }

    public Set<VisitModel> getVisits() {
        return visits;
    }

    public void setVisits(Set<VisitModel> visits) {
        this.visits = visits;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PatientModel{");
        builder.append("id=").append(id).append(", ");
        builder.append("localId=").append(localId).append(", ");
        builder.append("mrdNumber=").append(mrdNumber).append(", ");
        builder.append("firstName=").append(firstName).append(", ");
        builder.append("middleName=").append(middleName).append(", ");
        builder.append("lastName=").append(lastName).append(", ");
        builder.append("genderCode=").append(genderCode);
        if (null != visits) {
            builder.append(", visits=[").append(visits).append("]}");
        } else {
            builder.append("}");
        }
        return builder.toString();
    }

    @JsonIgnore
    public boolean isValid() {
        return Utils.isNotEmpty(this.mrdNumber)
                && (Utils.isNotEmpty(this.firstName) || Utils.isNotEmpty(this.lastName));
    }
}
