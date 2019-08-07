package com.hqpulse.helper;

import com.hqpulse.helper.exceptions.HQPulseRestException;
import com.hqpulse.helper.exceptions.RequestValidationError;
import com.hqpulse.helper.models.*;
import com.hqpulse.helper.resources.*;
import com.hqpulse.helper.utils.Utils;

import java.io.File;
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
    public static synchronized void init(String hqPulseHost, String authId, String authToken) {
        if(null != hqPulseHost){
            hqPulseHost = hqPulseHost.trim();
            if(hqPulseHost.endsWith("/")){
                hqPulseHost += "service/v1/";
            }else{
                hqPulseHost += "/service/v1/";
            }

        }
        hqpulseInstance = new HQPulseClient(authId, authToken, hqPulseHost);
    }

    public static synchronized void init( String authId, String authToken) {
        hqpulseInstance = new HQPulseClient(authId, authToken);
    }

    /**
     * Initializes the global {@link HQPulseClient} HQPulseAPIInstance, taking the authId and authToken from
     * environment variables called HQPULSE_AUTH_ID and HQPULSE_AUTH_TOKEN.
     */
    public static synchronized void init() {
        String hostName = System.getenv("HQPULSE_HOST_URL");
        if(null == hostName || "".equals(hostName.trim())){
            init(System.getenv("HQPULSE_AUTH_ID"), System.getenv("HQPULSE_AUTH_TOKEN"));
        }
        init(hostName,System.getenv("HQPULSE_AUTH_ID"), System.getenv("HQPULSE_AUTH_TOKEN"));
    }

    public static HQPulseClient getClient() {
        return hqpulseInstance;
    }


    /**
     *
     * @param date
     * @param patients
     * @return
     * @throws IOException
     * @throws HQPulseRestException
     */
    public static HQPulseResponse<String> syncPatientResource(Calendar date, Set<PatientModel> patients) throws IOException, HQPulseRestException, RequestValidationError {
        return (new PatientResource(patients, date)).syncResource();
    }

    /**
     *
     * @param date
     * @param patient
     * @return
     * @throws IOException
     * @throws HQPulseRestException
     */
    public static HQPulseResponse<String> syncPatientResource(Calendar date, PatientModel patient) throws IOException, HQPulseRestException, RequestValidationError {
        if (null == patient) {
            //
        }
        Set<PatientModel> tempSet = new HashSet<>(1);
        tempSet.add(patient);
        return (new PatientResource(tempSet, date)).syncResource();
    }

    /**
     *
     * @param staffs
     * @return
     * @throws IOException
     * @throws HQPulseRestException
     */
    public static HQPulseResponse<String> syncStaffResource(Set<StaffModel> staffs) throws IOException, HQPulseRestException, RequestValidationError {
        return (new StaffResource(staffs)).syncResource();
    }

    /**
     *
     * @param staff
     * @return
     * @throws IOException
     * @throws HQPulseRestException
     */
    public static HQPulseResponse<String> syncStaffResource(StaffModel staff) throws IOException, HQPulseRestException, RequestValidationError {
        if (null == staff) {
            //
        }
        Set<StaffModel> tempSet = new HashSet<>(1);
        tempSet.add(staff);
        return (new StaffResource(tempSet)).syncResource();
    }

    /**
     *
     * @param dailyData
     * @return
     * @throws IOException
     * @throws HQPulseRestException
     */
    public static HQPulseResponse<String> syncDailayDataResource(Set<HQPulseDataImportModel> dailyData) throws IOException, HQPulseRestException, RequestValidationError {
        return (new DailyImportResource(dailyData)).syncResource();
    }

    /**
     *
     * @param dailyData
     * @return
     * @throws IOException
     * @throws HQPulseRestException
     */
    public static HQPulseResponse<String> syncDailayDataResource(HQPulseDataImportModel dailyData) throws IOException, HQPulseRestException, RequestValidationError {
        if (null == dailyData || null == dailyData.getExportDate()) {
            //
        }
        Set<HQPulseDataImportModel> tempSet = new HashSet<>(1);
        tempSet.add(dailyData);
        return (new DailyImportResource(tempSet)).syncResource();
    }


    public static HQPulseResponse<String> syncUserResource(Set<HQPulseUserModel> users) throws IOException, HQPulseRestException, RequestValidationError {
        return (new UserResource(users)).syncResource();
    }

    /**
     *
     * @param user
     * @return
     * @throws IOException
     * @throws HQPulseRestException
     */
    public static HQPulseResponse<String> syncUserResource(HQPulseUserModel user) throws IOException, HQPulseRestException, RequestValidationError {
        if (null == user) {
            //
        }
        Set<HQPulseUserModel> tempSet = new HashSet<>(1);
        tempSet.add(user);
        return (new UserResource(tempSet)).syncResource();
    }


    public static HQPulseResponse<String> removeUser(String userId) throws IOException, HQPulseRestException, RequestValidationError {
        if (Utils.isNotEmpty(userId)) {

        }
        return (new UserResource(userId)).delete();
    }

    public static HQPulseResponse<String> syncAddressBookResource(HQPulseAddressBookModel addressBookModel) throws IOException, HQPulseRestException, RequestValidationError {
        if (null == addressBookModel) {
            //
        }
        Set<HQPulseAddressBookModel> tempSet = new HashSet<>(1);
        tempSet.add(addressBookModel);
        return (new AddressBookResource(tempSet)).syncResource();
    }


    public static HQPulseResponse<String> removeAddressBookEntry(String addressBookId) throws IOException, HQPulseRestException, RequestValidationError {
        if (Utils.isNotEmpty(addressBookId)) {

        }
        return (new AddressBookResource(addressBookId)).delete();
    }

    public static HQPulseResponse<DocumentModel> listDocuments(String directoryReference) throws IOException, HQPulseRestException, RequestValidationError {
        return (new DocumentResource()).getDocuments(directoryReference);
    }


    public static File downloadDocument(String documentReference) throws RequestValidationError, HQPulseRestException, IOException {
        return (new DocumentResource()).download(documentReference);
    }

    public static HQPulseResponse<HQPulseNotificationModel> getActivieNotification() throws IOException, HQPulseRestException, RequestValidationError {
        return (new NotificationResource()).syncResource();
    }


}
