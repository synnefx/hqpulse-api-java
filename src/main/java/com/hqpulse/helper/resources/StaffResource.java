package com.hqpulse.helper.resources;

import com.hqpulse.helper.models.ApiRequest;
import com.hqpulse.helper.models.ApiResponse;
import com.hqpulse.helper.models.StaffModel;
import com.hqpulse.helper.utils.Utils;
import retrofit2.Call;

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


    protected Call<ApiResponse<String>> callHQPulse() {
        ApiRequest<StaffModel> staffSyncRequest = new ApiRequest<>();
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
