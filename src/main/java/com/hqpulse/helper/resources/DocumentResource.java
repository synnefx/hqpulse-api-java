package com.hqpulse.helper.resources;

import com.hqpulse.helper.exceptions.HQPulseRestException;
import com.hqpulse.helper.exceptions.RequestValidationError;
import com.hqpulse.helper.models.DocumentModel;
import com.hqpulse.helper.models.HQPulseResponse;
import com.hqpulse.helper.utils.Utils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * @author Josekutty
 * 07-08-2019
 */
public class DocumentResource extends BaseResource<DocumentModel> {
    @Override
    protected void validateResourceRequest() throws RequestValidationError {
        //Do nothing
    }

    @Override
    protected Call<HQPulseResponse<DocumentModel>> callHQPulse() {
        return client()
                .getApiService()
                .viewRootDirectory(client().getAuthId());
    }


    public HQPulseResponse<DocumentModel> getDocuments(String directoryReference) throws IOException, HQPulseRestException, RequestValidationError {
        Response<HQPulseResponse<DocumentModel>> response;
        if (null == directoryReference || "".equals(directoryReference.trim())) {
            response = client()
                    .getApiService()
                    .viewRootDirectory(client().getAuthId()).execute();
        } else {
            response = client()
                    .getApiService()
                    .getDirectoryContents(client().getAuthId(), directoryReference).execute();
        }
        handleResponse(response);
        return response.body();
    }

    public File download(String documentReference) throws IOException, HQPulseRestException, RequestValidationError {

        Response<ResponseBody> response = client()
                .getApiService().downloadDocument(client().getAuthId(), documentReference).execute();
        if (null != response && response.isSuccessful()) {

            String fileNameHeader = response.headers().get("Content-Disposition");
            if (null != fileNameHeader) {
                fileNameHeader = String.format("%s_%s", Calendar.getInstance().getTimeInMillis(), Utils.substringAfter(fileNameHeader, "filename="));
            }
            OutputStream output = null;
            try {
                byte[] data = response.body().bytes();
                File file = new File(fileNameHeader);
                file.createNewFile();
                output = new FileOutputStream(file);
                if (data != null) {
                    output.write(data);
                }

                return file;
            } catch (IOException e) {
                e.printStackTrace();
                //TODO
            } finally {
                if (null != output) {
                    output.close();
                }
            }
        } else {
            //handle failed request
        }
        /*
        Asyn call
        client()
                .getApiService().downloadDocument(client().getAuthId(), documentReference)
                .enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                if (response.isSuccessful()) {

                    String fileNameHeader = response.headers().get("Content-Disposition");
                    if(null != fileNameHeader){
                        fileNameHeader = String.format("%s_%s", LocalDateTime.now().toString(), Utils.substringAfter(fileNameHeader, "filename="));
                    }

                    try {
                        byte [] data =response.body().bytes();
                        File file = new File(fileNameHeader);
                        file.createNewFile();
                        OutputStream output = new FileOutputStream(file);
                        if (data != null) {
                            output.write(data);
                        }
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        //TODO
                    }
                }else {
                    //handle failed request
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //Handle failed exception
            }
        });
        */
        return null;
    }


}
