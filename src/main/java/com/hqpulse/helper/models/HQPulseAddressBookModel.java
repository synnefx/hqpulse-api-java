/*
 *      Copyright (c)  Synnefx Health Technologies Pvt Ltd  2019.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 *       Unless required by applicable law or agreed to in writing, software distributed under
 *       the License is distributed on an "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 *       INCLUDING, BUT NOT LIMITED TO,
 *       THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 *       PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 *       CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *       EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *       PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 *       PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 *       LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *       NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *       SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 */

package com.hqpulse.helper.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hqpulse.helper.utils.Utils;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HQPulseAddressBookModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long ID = 0L;

    private String localId;

    private boolean active;

    private AddressBookType type;

    private int typeCode;

    private String hospitalUUID;

    private Long associatedHospital;

    private String associatedHospitalName;

    private HQPulseContactModel contact;

    private boolean favorite;

    private String firstName;

    private String fullName;

    private String lastName;

    private String middleName;

    private Long associatedUserId;

    public HQPulseAddressBookModel() {
        //Default constructor
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public AddressBookType getType() {
        return type;
    }

    public void setType(AddressBookType type) {
        this.type = type;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }

    public String getHospitalUUID() {
        return hospitalUUID;
    }

    public void setHospitalUUID(String hospitalUUID) {
        this.hospitalUUID = hospitalUUID;
    }

    public Long getAssociatedHospital() {
        return associatedHospital;
    }

    public void setAssociatedHospital(Long associatedHospital) {
        this.associatedHospital = associatedHospital;
    }

    public String getAssociatedHospitalName() {
        return associatedHospitalName;
    }

    public void setAssociatedHospitalName(String associatedHospitalName) {
        this.associatedHospitalName = associatedHospitalName;
    }

    public HQPulseContactModel getContact() {
        return contact;
    }

    public void setContact(HQPulseContactModel contact) {
        this.contact = contact;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Long getAssociatedUserId() {
        return associatedUserId;
    }

    public void setAssociatedUserId(Long associatedUserId) {
        this.associatedUserId = associatedUserId;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long iD) {
        ID = iD;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }


    @JsonIgnore
    public boolean isValid(){
        return (Utils.isNotEmpty(this.firstName)
                || Utils.isNotEmpty(this.lastName))
                && null != this.contact
                && this.contact.isValid();
    }
}
