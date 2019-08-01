package com.hqpulse.helper.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hqpulse.helper.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Josekutty
 * 08-07-2019
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HQPulseUserModel implements Serializable {

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
    private HQPulseContactModel contact;

    private List<RoleModal> roles = new ArrayList<>();

    public HQPulseUserModel(){

    }

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

    public HQPulseContactModel getContact() {
        return contact;
    }

    public void setContact(HQPulseContactModel contact) {
        this.contact = contact;
    }

    public List<RoleModal> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleModal> roles) {
        this.roles = roles;
    }

    public void setUserRoles(Iterable<Utils.UserRole> roleCodes) {
        if(null != roleCodes){
            for (Utils.UserRole code:roleCodes) {
                //Validate role code here
                this.getRoles().add(new RoleModal(code.getRoleCode()));
            }
        }
        this.roles = roles;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    @JsonIgnore
    public boolean isValid(){
        return Utils.isNotEmpty(this.userId) && this.contact.isValid()
                && Utils.isNotEmpty(this.firstName)
                && Utils.isNotEmpty(this.lastName)
                && ( null != this.roles && this.roles.size() > 0);
    }


}
