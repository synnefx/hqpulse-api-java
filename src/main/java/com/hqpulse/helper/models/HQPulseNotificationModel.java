package com.hqpulse.helper.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Calendar;

/**
 * @author Josekutty
 * 07-08-2019
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HQPulseNotificationModel implements Serializable {

    private Long id;

    private String createdUserHmisId;

    private String updatedUserHmisId;

    private Calendar createdTime;

    private Calendar updatedTime;

    private String description;

    private Calendar targetDate;

    private HQPulseAddressBookModel owner;

    private Calendar assignedOn;

    private String status;

    private String title;

    private HQPulseAddressBookModel completedBy;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedUserHmisId() {
        return createdUserHmisId;
    }

    public void setCreatedUserHmisId(String createdUserHmisId) {
        this.createdUserHmisId = createdUserHmisId;
    }

    public String getUpdatedUserHmisId() {
        return updatedUserHmisId;
    }

    public void setUpdatedUserHmisId(String updatedUserHmisId) {
        this.updatedUserHmisId = updatedUserHmisId;
    }

    public Calendar getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Calendar createdTime) {
        this.createdTime = createdTime;
    }

    public Calendar getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Calendar updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Calendar targetDate) {
        this.targetDate = targetDate;
    }

    public HQPulseAddressBookModel getOwner() {
        return owner;
    }

    public void setOwner(HQPulseAddressBookModel owner) {
        this.owner = owner;
    }

    public Calendar getAssignedOn() {
        return assignedOn;
    }

    public void setAssignedOn(Calendar assignedOn) {
        this.assignedOn = assignedOn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HQPulseAddressBookModel getCompletedBy() {
        return completedBy;
    }

    public void setCompletedBy(HQPulseAddressBookModel completedBy) {
        this.completedBy = completedBy;
    }
}
