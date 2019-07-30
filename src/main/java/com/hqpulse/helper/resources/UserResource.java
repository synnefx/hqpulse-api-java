package com.hqpulse.helper.resources;

import com.hqpulse.helper.exceptions.RequestValidationError;
import com.hqpulse.helper.models.HQPulseRequest;
import com.hqpulse.helper.models.HQPulseResponse;
import com.hqpulse.helper.models.HQPulseUserModel;
import com.hqpulse.helper.models.RecordKeyValue;
import com.hqpulse.helper.utils.Utils;
import retrofit2.Call;

import java.util.Optional;
import java.util.Set;

/**
 * @author Josekutty
 * 30-07-2019
 */
public class UserResource extends BaseResource {

    private Set<HQPulseUserModel> users;

    private String userId;

    public UserResource(Set<HQPulseUserModel> users) {
        if (!Utils.allNotNull(users)) {
            throw new IllegalArgumentException("User details must not be null");
        }

        this.users = users;
    }

    public UserResource(String userId) {
        if (Utils.isEmpty(userId)) {
            throw new IllegalArgumentException("User id must not be null");
        }
        this.setUserId(userId);
    }


    @Override
    protected Call<HQPulseResponse<RecordKeyValue<HQPulseUserModel>>> callHQPulse() {
        HQPulseRequest<HQPulseUserModel> userSyncRequest = new HQPulseRequest<>();
        userSyncRequest.setRecords(users);
        return client()
                .getApiService()
                .syncUsers(client().getAuthId(), userSyncRequest);
    }


    @Override
    protected void validateResourceRequest() throws RequestValidationError {
        Optional<HQPulseUserModel> errorRequest = users.stream().filter(userModel -> !userModel.isValid())
                .findAny();
        if(errorRequest.isPresent()){
            throw new RequestValidationError("Invalid request for user:"+ errorRequest.get().getLocalId());
        }
    }

    @Override
    protected Call<HQPulseResponse<String>> deleteHQPulseResource(){
        return client()
                .getApiService()
                .deleteUser(client().getAuthId(), this.userId);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
