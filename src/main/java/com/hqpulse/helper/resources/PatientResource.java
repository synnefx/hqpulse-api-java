package com.hqpulse.helper.resources;

import com.hqpulse.helper.models.ApiRequest;
import com.hqpulse.helper.models.ApiResponse;
import com.hqpulse.helper.models.PatientModel;
import com.hqpulse.helper.utils.Utils;
import retrofit2.Call;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

/**
 * @author Josekutty
 * 28-07-2019
 */
public class PatientResource<K extends Serializable> extends BaseResource {

    private Set<PatientModel> patients;

    private Calendar date;

    public PatientResource(Set<PatientModel> patients, Calendar date) {
        if (!Utils.allNotNull(date, patients)) {
            throw new IllegalArgumentException("Date of export and list of patients must not be null");
        }

        this.date = date;
        this.patients = patients;
    }


    public Set<PatientModel> getPatients() {
        return patients;
    }

    public void setPatients(Set<PatientModel> patients) {
        this.patients = patients;
    }


    protected Call<ApiResponse<String>> callHQPulse() {
        ApiRequest<PatientModel> patientSyncRequest = new ApiRequest<>();
        patientSyncRequest.setRecords(this.patients);
        return client()
                .getApiService()
                .syncDailyPatients(client().getAuthId(), Utils.formatCalendarToString(date), patientSyncRequest);
    }


}
