package com.hqpulse.helper;

import com.hqpulse.helper.models.*;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @author Josekutty
 * 28-07-2019
 */
public interface HQPulseAPIService {

    @Headers("Content-Type: application/json")
    @POST("{accountId}/import/")
    Call<HQPulseResponse<String>> syncDailyData(@Path("accountId") String authId,
                                                @Body HQPulseRequest<HQPulseDataImportModel> dataImportModal);

    @Headers("Content-Type: application/json")
    @POST("{accountId}/import/{exportDate}/patients/")
    Call<HQPulseResponse<String>> syncDailyPatients(@Path("accountId") String authId,
                                                    @Path("exportDate") String exportDate,
                                                    @Body HQPulseRequest<PatientModel> patientData);

    @Headers("Content-Type: application/json")
    @POST("{accountId}/import/staffs")
    Call<HQPulseResponse<String>> syncStaffs(@Path("accountId") String authId,
                                             @Body HQPulseRequest<StaffModel> staffData);

    @Headers("Content-Type: application/json")
    @POST("{accountId}/configuration/users/")
    Call<HQPulseResponse<RecordKeyValue<HQPulseUserModel>>> syncUsers(@Path("accountId") String authId,
                                                                      @Body HQPulseRequest<HQPulseUserModel> userData);


    @Headers("Content-Type: application/json")
    @DELETE("{accountId}/configuration/users/{userId}")
    Call<HQPulseResponse<String>> deleteUser(@Path("accountId") String authId,
                                                                       @Path("hmisUserId") String hmisUserId);


    @Headers("Content-Type: application/json")
    @POST("{accountId}/configuration/contacts/")
    Call<HQPulseResponse<RecordKeyValue<HQPulseAddressBookModel>>> syncAddressBook(@Path("accountId") String authId,
                                                                      @Body HQPulseRequest<HQPulseAddressBookModel> contactData);


    @Headers("Content-Type: application/json")
    @DELETE("{accountId}/configuration/contacts/{contactId}")
    Call<HQPulseResponse<String>> deleteContact(@Path("accountId") String authId,
                                             @Path("contactId") String contactId);


}
