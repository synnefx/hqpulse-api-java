package com.hqpulse.helper;

import com.hqpulse.helper.models.*;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @author Josekutty
 * 28-07-2019
 */
public interface HQPulseAPIService {

    @Headers("Content-Type: application/json")
    @POST("hmis/{accountId}/import/")
    Call<HQPulseResponse<String>> syncDailyData(@Path("accountId") String authId,
                                                @Body HQPulseRequest<HQPulseDataImportModel> dataImportModal);

    @Headers("Content-Type: application/json")
    @POST("hmis/{accountId}/import/{exportDate}/patients/")
    Call<HQPulseResponse<String>> syncDailyPatients(@Path("accountId") String authId,
                                                    @Path("exportDate") String exportDate,
                                                    @Body HQPulseRequest<PatientModel> patientData);

    @Headers("Content-Type: application/json")
    @POST("hmis/{accountId}/import/staffs/")
    Call<HQPulseResponse<String>> syncStaffs(@Path("accountId") String authId,
                                             @Body HQPulseRequest<StaffModel> staffData);

    @Headers("Content-Type: application/json")
    @POST("integration/{accountId}/configuration/users/")
    Call<HQPulseResponse<RecordKeyValue<HQPulseUserModel>>> syncUsers(@Path("accountId") String authId,
                                                                      @Body HQPulseRequest<HQPulseUserModel> userData);


    @Headers("Content-Type: application/json")
    @DELETE("integration/{accountId}/configuration/users/{userId}/")
    Call<HQPulseResponse<String>> deleteUser(@Path("accountId") String authId,
                                             @Path("hmisUserId") String hmisUserId);


    @Headers("Content-Type: application/json")
    @POST("integration/{accountId}/configuration/contacts/")
    Call<HQPulseResponse<RecordKeyValue<HQPulseAddressBookModel>>> syncAddressBook(@Path("accountId") String authId,
                                                                                   @Body HQPulseRequest<HQPulseAddressBookModel> contactData);


    @Headers("Content-Type: application/json")
    @DELETE("integration/{accountId}/configuration/contacts/{contactId}/")
    Call<HQPulseResponse<String>> deleteContact(@Path("accountId") String authId,
                                                @Path("contactId") String contactId);


    @Headers("Content-Type: application/json")
    @GET("hmisDocument/{accountId}/documents/")
    Call<HQPulseResponse<DocumentModel>> viewRootDirectory(@Path("accountId") String authId);


    @Headers("Content-Type: application/json")
    @GET("hmisDocument/{accountId}/documents/{directoryId}/")
    Call<HQPulseResponse<DocumentModel>> getDirectoryContents(@Path("accountId") String authId,
                                                              @Path("directoryId") String directoryId);

    //@Streaming
    @Headers("Content-Type: application/octet-stream")
    @GET("hmisDocument/{accountId}/documents/{documentId}/download")
    Call<ResponseBody> downloadDocument(@Path("accountId") String authId,
                                        @Path("documentId") String documentId);


    @Headers("Content-Type: application/json")
    @GET("hmisNotification/{accountId}/notifications/")
    Call<HQPulseResponse<HQPulseNotificationModel>> getActiveNotifications(@Path("accountId") String authId);

}
