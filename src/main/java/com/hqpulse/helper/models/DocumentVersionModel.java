package com.hqpulse.helper.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Calendar;

/**
 * @author Josekutty
 * 07-08-2019
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentVersionModel implements Serializable {

    private String name;

    private String path;

    private String mimeType;

    private Long fileSize;

    private Integer version;

    private String code;

    private Calendar uploadedDate;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Calendar getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(Calendar uploadedDate) {
        this.uploadedDate = uploadedDate;
    }
}
