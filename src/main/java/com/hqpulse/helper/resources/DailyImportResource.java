package com.hqpulse.helper.resources;

import com.hqpulse.helper.exceptions.RequestValidationError;
import com.hqpulse.helper.models.HQPulseDataImportModel;
import com.hqpulse.helper.models.HQPulseRequest;
import com.hqpulse.helper.models.HQPulseResponse;
import com.hqpulse.helper.models.PatientModel;
import com.hqpulse.helper.utils.Utils;
import retrofit2.Call;

import java.util.Optional;
import java.util.Set;

/**
 * @author Josekutty
 * 28-07-2019
 */
public class DailyImportResource extends BaseResource {

    private Set<HQPulseDataImportModel> dailyData;


    public DailyImportResource(Set<HQPulseDataImportModel> dailyData) {
        if (!Utils.allNotNull(dailyData)) {
            throw new IllegalArgumentException("Export data must not be null");
        }
        this.dailyData = dailyData;
    }


    @Override
    protected Call<HQPulseResponse<String>> callHQPulse() {
        HQPulseRequest<HQPulseDataImportModel> hmisDataSyncRequest = new HQPulseRequest<>();
        hmisDataSyncRequest.setRecords(this.dailyData);
        return client()
                .getApiService()
                .syncDailyData(client().getAuthId(), hmisDataSyncRequest);
    }

    @Override
    protected void validateResourceRequest() throws RequestValidationError {
        Optional<HQPulseDataImportModel> errorRequest = dailyData.stream().filter(importModel -> !importModel.isValid())
                .findAny();
        if(errorRequest.isPresent()){
            throw new RequestValidationError("Invalid request for data export");
        }
    }

}
