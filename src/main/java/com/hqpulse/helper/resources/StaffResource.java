package com.hqpulse.helper.resources;

import com.hqpulse.helper.exceptions.RequestValidationError;
import com.hqpulse.helper.models.HQPulseRequest;
import com.hqpulse.helper.models.HQPulseResponse;
import com.hqpulse.helper.models.StaffModel;
import com.hqpulse.helper.utils.Utils;
import retrofit2.Call;

import java.util.Optional;
import java.util.Set;

/**
 * @author Josekutty
 * 28-07-2019
 */
public class StaffResource extends BaseResource {

    private Set<StaffModel> staffs;


    public StaffResource(Set<StaffModel> staffs) {
        if (!Utils.allNotNull(staffs)) {
            throw new IllegalArgumentException("Staff details must not be null");
        }

        this.staffs = staffs;
    }


    @Override
    protected void validateResourceRequest() throws RequestValidationError {
        Optional<StaffModel> errorRequest = staffs.stream().filter(staffModel -> !staffModel.isValid())
                .findAny();
        if(errorRequest.isPresent()){
            throw new RequestValidationError("Invalid request for staff:"+ errorRequest.get().getLocalId());
        }
    }

    protected Call<HQPulseResponse<String>> callHQPulse() {
        HQPulseRequest<StaffModel> staffSyncRequest = new HQPulseRequest<>();
        staffSyncRequest.setRecords(staffs);
        return client()
                .getApiService()
                .syncStaffs(client().getAuthId(), staffSyncRequest);
    }


    public Set<StaffModel> getStaffs() {
        return staffs;
    }

    public void setStaffs(Set<StaffModel> staffs) {
        this.staffs = staffs;
    }
}
