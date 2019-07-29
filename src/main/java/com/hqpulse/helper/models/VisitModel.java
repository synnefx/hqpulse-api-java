package com.hqpulse.helper.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Calendar;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class VisitModel implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String localId;
    private String visitNumber;
    private Calendar reportingTime;
    private Calendar initialAssessmentTime;
    private Calendar dischargedOn;
    private Integer age;
    private Integer visitTypeCode;
    private Double height;
    private Double weight;
    private String consultantName;
    private String diagnosis;
    private Calendar deathDate;

    public VisitModel() {
        //Default constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(String visitNumber) {
        this.visitNumber = visitNumber;
    }

    public Calendar getReportingTime() {
        return reportingTime;
    }

    public void setReportingTime(Calendar reportingTime) {
        this.reportingTime = reportingTime;
    }

    public Calendar getInitialAssessmentTime() {
        return initialAssessmentTime;
    }

    public void setInitialAssessmentTime(Calendar initialAssessmentTime) {
        this.initialAssessmentTime = initialAssessmentTime;
    }

    public Calendar getDischargedOn() {
        return dischargedOn;
    }

    public void setDischargedOn(Calendar dischargedOn) {
        this.dischargedOn = dischargedOn;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getVisitTypeCode() {
        return visitTypeCode;
    }

    public void setVisitTypeCode(Integer visitTypeCode) {
        this.visitTypeCode = visitTypeCode;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Calendar getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Calendar deathDate) {
        this.deathDate = deathDate;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    @Override
    public String toString() {
        return "VisitModel{" +
                "id=" + id +
                ", localId='" + localId + '\'' +
                ", visitNumber='" + visitNumber + '\'' +
                ", reportingTime=" + reportingTime +
                ", initialAssessmentTime=" + initialAssessmentTime +
                ", dischargedOn=" + dischargedOn +
                ", age=" + age +
                ", visitTypeCode=" + visitTypeCode +
                ", height=" + height +
                ", weight=" + weight +
                ", consultantName='" + consultantName + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", deathDate=" + deathDate +
                '}';
    }
}
