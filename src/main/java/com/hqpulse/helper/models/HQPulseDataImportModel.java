package com.hqpulse.helper.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hqpulse.helper.utils.Utils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HQPulseDataImportModel implements Serializable {

    private static final long serialVersionUID = 1L;
    private Calendar exportDate;
    private Set<PatientModel> patients;
    private Set<StaffModel> staffs;
    private Integer deaths;
    private Integer discharges;

    public HQPulseDataImportModel() {
        //Default constructor
    }

    public Calendar getExportDate() {
        return exportDate;
    }

    public void setExportDate(Calendar exportDate) {
        this.exportDate = exportDate;
    }

    public Set<PatientModel> getPatients() {
        return patients;
    }

    public void setPatients(Set<PatientModel> patients) {
        this.patients = patients;
    }

    public Set<StaffModel> getStaffs() {
        return staffs;
    }

    public void setStaffs(Set<StaffModel> staffs) {
        this.staffs = staffs;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getDischarges() {
        return discharges;
    }

    public void setDischarges(Integer discharges) {
        this.discharges = discharges;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("HQPulseDataImportModel{");
        builder.append("exportDate=").append(exportDate).append(", ");
        builder.append("deaths=").append(deaths).append(", ");
        builder.append("discharges=").append(discharges);
        if (null != patients) {
            builder.append(", patients=[").append(patients).append("]");
        }
        if (null != patients) {
            builder.append(", staffs=[").append(staffs).append("]");
        }
        builder.append("}");
        return builder.toString();
    }

    @JsonIgnore
    public boolean isValid(){
        return Utils.allNotNull(this.exportDate);
    }
}
