package com.hqpulse.helper.resources;

import com.hqpulse.helper.models.ApiRequest;
import com.hqpulse.helper.models.ApiResponse;
import com.hqpulse.helper.models.HMISDataImportModel;
import com.hqpulse.helper.utils.Utils;
import retrofit2.Call;

import java.util.Set;

/**
 * @author Josekutty
 * 28-07-2019
 */
public class DailyImportResource extends BaseResource {

    private Set<HMISDataImportModel> dailyData;


    public DailyImportResource(Set<HMISDataImportModel> dailyData) {
        if (!Utils.allNotNull(dailyData)) {
            throw new IllegalArgumentException("Export data must not be null");
        }
        this.dailyData = dailyData;
    }


    @Override
    protected Call<ApiResponse<String>> callHQPulse() {
        ApiRequest<HMISDataImportModel> hmisDataSyncRequest = new ApiRequest<>();
        hmisDataSyncRequest.setRecords(this.dailyData);
        return client()
                .getApiService()
                .syncDailyData(client().getAuthId(), hmisDataSyncRequest);
    }

}
