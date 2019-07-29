package com.hqpulse.helper.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * @author Josekutty
 * 08-07-2019
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HMISUserModel implements Serializable {

    private Long id = 0L;

    private String localId;

    private String firstName;

    private String fullName;

    private String middleName;

    private String lastName;

    private Boolean locked;

    private Calendar dateLocked;

    private String password;

    private String userId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private HMISContactModel contact;

    private List<RoleModal> roles;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Calendar getDateLocked() {
        return dateLocked;
    }

    public void setDateLocked(Calendar dateLocked) {
        this.dateLocked = dateLocked;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public HMISContactModel getContact() {
        return contact;
    }

    public void setContact(HMISContactModel contact) {
        this.contact = contact;
    }

    public List<RoleModal> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleModal> roles) {
        this.roles = roles;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }
}
