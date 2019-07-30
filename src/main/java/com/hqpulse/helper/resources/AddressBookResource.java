package com.hqpulse.helper.resources;

import com.hqpulse.helper.exceptions.RequestValidationError;
import com.hqpulse.helper.models.HQPulseAddressBookModel;
import com.hqpulse.helper.models.HQPulseRequest;
import com.hqpulse.helper.models.HQPulseResponse;
import com.hqpulse.helper.models.RecordKeyValue;
import com.hqpulse.helper.utils.Utils;
import retrofit2.Call;

import java.util.Optional;
import java.util.Set;

/**
 * @author Josekutty
 * 30-07-2019
 */
public class AddressBookResource extends BaseResource{

    private Set<HQPulseAddressBookModel> addressBookModels;

    private String addressBookId;


    public AddressBookResource(Set<HQPulseAddressBookModel> addressBookModels) {
        if (!Utils.allNotNull(addressBookModels)) {
            throw new IllegalArgumentException("Contacts must not be null");
        }
        this.addressBookModels = addressBookModels;
    }

    public AddressBookResource(String addressBookId) {
        if (Utils.isEmpty(addressBookId)) {
            throw new IllegalArgumentException("Id must not be null");
        }
        this.addressBookId = addressBookId;
    }

    @Override
    protected void validateResourceRequest() throws RequestValidationError {
        Optional<HQPulseAddressBookModel> errorRequest = addressBookModels.stream()
                .filter(addressBookModels ->   !addressBookModels.isValid())
                .findAny();
        if(errorRequest.isPresent()){
            throw new RequestValidationError("Invalid request for a contact");
        }
    }

    @Override
    protected Call<HQPulseResponse<RecordKeyValue<HQPulseAddressBookModel>>> callHQPulse() {
        HQPulseRequest<HQPulseAddressBookModel> conatctSyncRequest = new HQPulseRequest<>();
        conatctSyncRequest.setRecords(this.addressBookModels);
        return client()
                .getApiService()
                .syncAddressBook(client().getAuthId(), conatctSyncRequest);
    }


    @Override
    protected Call<HQPulseResponse<String>> deleteHQPulseResource(){
        return client()
                .getApiService()
                .deleteUser(client().getAuthId(), this.addressBookId);
    }


    public String getAddressBookId() {
        return addressBookId;
    }

    public void setAddressBookId(String addressBookId) {
        this.addressBookId = addressBookId;
    }
}
