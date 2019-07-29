package com.hqpulse.helper;

import com.hqpulse.helper.models.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author Josekutty
 * 28-07-2019
 */
public interface HQPulseAPIService {

    @Headers("Content-Type: application/json")
    @POST("{accountId}/import/")
    Call<ApiResponse<String>> syncDailyData(@Path("accountId") String authId,
                                            @Body ApiRequest<HMISDataImportModel> dataImportModal);

    @Headers("Content-Type: application/json")
    @POST("{accountId}/import/{exportDate}/patients/")
    Call<ApiResponse<String>> syncDailyPatients(@Path("accountId") String authId,
                                                @Path("exportDate") String exportDate,
                                                @Body ApiRequest<PatientModel> patientData);

    @Headers("Content-Type: application/json")
    @POST("{accountId}/import/staffs")
    Call<ApiResponse<String>> syncStaffs(@Path("accountId") String authId,
                                         @Body ApiRequest<StaffModel> staffData);
}
