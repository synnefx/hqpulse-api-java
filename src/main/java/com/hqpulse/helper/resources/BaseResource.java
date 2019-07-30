package com.hqpulse.helper.resources;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.hqpulse.helper.HQPulseAPI;
import com.hqpulse.helper.HQPulseClient;
import com.hqpulse.helper.exceptions.*;
import com.hqpulse.helper.models.HQPulseResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

// Needed because we use fluent style APIs
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public abstract class BaseResource<K extends Serializable> {
    @JsonIgnore
    protected HQPulseClient hqPulseClient = HQPulseAPI.getClient();

    public HQPulseClient client() {
        return this.hqPulseClient;
    }

    public BaseResource client(final HQPulseClient hqPulseClient) {
        this.hqPulseClient = hqPulseClient;
        return this;
    }

    protected abstract void validateResourceRequest() throws RequestValidationError;

    protected void validate() throws RequestValidationError {
        this.validateResourceRequest();
        if (hqPulseClient == null) {
            throw new IllegalStateException("client cannot be null");
        }

        // Convenient way to test setters and getters
        if (hqPulseClient.isTesting()) {
            HashMap<String, Object> values = new HashMap<>();
            for (Method method : this.getClass().getMethods()) {
                if (method.getParameterCount() == 0) {
                    String methodName = method.getName();
                    try {
                        this.getClass().getDeclaredField(methodName);
                        values.put(methodName, method.invoke(this));
                    } catch (NoSuchFieldException | SecurityException | IllegalAccessException | InvocationTargetException e) {
                        //nop
                    }
                }
            }
            for (Method method : this.getClass().getMethods()) {
                if (method.getParameterCount() == 1) {
                    String methodName = method.getName();
                    try {
                        this.getClass().getDeclaredField(methodName);
                        Object value = values.get(methodName);
                        method.invoke(this, value);
                    } catch (NoSuchFieldException | SecurityException | IllegalAccessException | InvocationTargetException e) {
                        //nop
                    }
                }
            }
        }
    }

    protected void handleResponse(Response response) throws HQPulseRestException, IOException {
        if (hqPulseClient.isTesting()) {
            if (response.body() != null) {
                if (!(response.body() instanceof ResponseBody)) {
                    client().getObjectMapper().convertValue(response.body(), JsonNode.class);
                }
                //noinspection ResultOfMethodCallIgnored
                response.body().toString();
            }
        }

        int responseCode = response.code();
        switch (responseCode) {
            case 400:
                throw new InvalidRequestException(response.errorBody().string());
            case 401:
                throw new AuthenticationException(response.errorBody().string());
            case 404:
                throw new ResourceNotFoundException(response.errorBody().string());
            case 405:
                throw new InvalidRequestException(response.errorBody().string());
            case 500:
                throw new ServerException(response.errorBody().string());
        }

    /*if (this instanceof Deleter && responseCode != 204) {
      throw new HQPulseRestException(response.errorBody().string());
    }*/

        if (!response.isSuccessful()) {
            throw new HQPulseRestException(response.errorBody().string());
        }
    }

    protected abstract Call<HQPulseResponse<K>> callHQPulse();

    protected Call<HQPulseResponse<K>> deleteHQPulseResource(){
        return null;
    }

    //protected abstract HQPulseResponse<String> syncResource() throws IOException, HQPulseRestException;
    //@Override
    public HQPulseResponse<K> syncResource() throws IOException, HQPulseRestException, RequestValidationError {
        validate();
        Response<HQPulseResponse<K>> response = callHQPulse().execute();
        handleResponse(response);
        return response.body();
    }

    public HQPulseResponse<K> delete() throws IOException, HQPulseRestException, RequestValidationError {
        Response<HQPulseResponse<K>> response = callHQPulse().execute();
        handleResponse(response);
        return response.body();
    }
}
