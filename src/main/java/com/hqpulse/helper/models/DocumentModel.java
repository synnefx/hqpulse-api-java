package com.hqpulse.helper.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * @author Josekutty
 * 07-08-2019
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentModel implements Serializable {

    private String createdBy;
    private Calendar createdDate;
    private Long id = 0L;
    private String updatedBy;
    private Calendar updatedDate;

    private Long hospitalID;

    private String documentId;

    private String name;

    //Directory
    private boolean directory;

    private List<DocumentModel> contents;

    private DocumentModel container;

    private DocumentVersionModel currentVersion;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Calendar getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Calendar createdDate) {
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Calendar getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Calendar updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(Long hospitalID) {
        this.hospitalID = hospitalID;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDirectory() {
        return directory;
    }

    public void setDirectory(boolean directory) {
        this.directory = directory;
    }

    public List<DocumentModel> getContents() {
        return contents;
    }

    public void setContents(List<DocumentModel> contents) {
        this.contents = contents;
    }

    public DocumentModel getContainer() {
        return container;
    }

    public void setContainer(DocumentModel container) {
        this.container = container;
    }

    public DocumentVersionModel getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(DocumentVersionModel currentVersion) {
        this.currentVersion = currentVersion;
    }
}




