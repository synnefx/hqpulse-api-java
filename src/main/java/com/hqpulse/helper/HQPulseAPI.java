package com.hqpulse.helper;

import com.hqpulse.helper.exceptions.HQPulseRestException;
import com.hqpulse.helper.models.ApiResponse;
import com.hqpulse.helper.models.HMISDataImportModel;
import com.hqpulse.helper.models.PatientModel;
import com.hqpulse.helper.models.StaffModel;
import com.hqpulse.helper.resources.DailyImportResource;
import com.hqpulse.helper.resources.PatientResource;
import com.hqpulse.helper.resources.StaffResource;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Josekutty
 * 28-07-2019
 */
public class HQPulseAPI {

    private static HQPulseClient hqpulseInstance = null;

    /**
     * Initializes the global {@link HQPulseClient} hqpulseInstance
     */
    public static synchronized void init(String authId, String authToken) {
        hqpulseInstance = new HQPulseClient(authId, authToken);
    }

    /**
     * Initializes the global {@link HQPulseClient} HQPulseAPIInstance, taking the authId and authToken from
     * environment variables called HQPULSE_AUTH_ID and HQPULSE_AUTH_TOKEN.
     */
    public static synchronized void init() {
        init(System.getenv("HQPULSE_AUTH_ID"), System.getenv("HQPULSE_AUTH_TOKEN"));
    }

    public static HQPulseClient getClient() {
        return hqpulseInstance;
    }


    public static ApiResponse<String> syncPatientResource(Calendar date, Set<PatientModel> patients) throws IOException, HQPulseRestException {
        return (new PatientResource(patients, date)).syncResource();
    }

    public static ApiResponse<String> syncPatientResource(Calendar date, PatientModel patient) throws IOException, HQPulseRestException {
        if (null == patient) {
            //
        }
        Set<PatientModel> tempSet = new HashSet<>(1);
        tempSet.add(patient);
        return (new PatientResource(tempSet, date)).syncResource();
    }

    public static ApiResponse<String> syncStaffResource(Set<StaffModel> staffs) throws IOException, HQPulseRestException {
        return (new StaffResource(staffs)).syncResource();
    }

    public static ApiResponse<String> syncStaffResource(StaffModel staff) throws IOException, HQPulseRestException {
        if (null == staff) {
            //
        }
        Set<StaffModel> tempSet = new HashSet<>(1);
        tempSet.add(staff);
        return (new StaffResource(tempSet)).syncResource();
    }

    public static ApiResponse<String> syncDailayDataResource(Set<HMISDataImportModel> dailyData) throws IOException, HQPulseRestException {
        return (new DailyImportResource(dailyData)).syncResource();
    }

    public static ApiResponse<String> syncDailayDataResource(HMISDataImportModel dailyData) throws IOException, HQPulseRestException {
        if (null == dailyData || null == dailyData.getExportDate()) {
            //
        }
        Set<HMISDataImportModel> tempSet = new HashSet<>(1);
        tempSet.add(dailyData);
        return (new DailyImportResource(tempSet)).syncResource();
    }

}
