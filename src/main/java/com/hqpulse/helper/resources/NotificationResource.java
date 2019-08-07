package com.hqpulse.helper.resources;

import com.hqpulse.helper.exceptions.RequestValidationError;
import com.hqpulse.helper.models.HQPulseDataImportModel;
import com.hqpulse.helper.models.HQPulseNotificationModel;
import com.hqpulse.helper.models.HQPulseRequest;
import com.hqpulse.helper.models.HQPulseResponse;
import retrofit2.Call;

/**
 * @author Josekutty
 * 07-08-2019
 */
public class NotificationResource extends BaseResource<HQPulseNotificationModel> {
    @Override
    protected void validateResourceRequest() throws RequestValidationError {

    }

    @Override
    protected Call<HQPulseResponse<HQPulseNotificationModel>> callHQPulse() {
        return client()
                .getApiService()
                .getActiveNotifications(client().getAuthId());
    }
}
