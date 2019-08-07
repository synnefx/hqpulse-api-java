package com.hqpulse.helper;

import com.hqpulse.helper.models.DocumentModel;
import com.hqpulse.helper.models.HQPulseResponse;
import com.hqpulse.helper.models.StaffModel;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * @author Josekutty
 * 28-07-2019
 */
public class HQPulseAPITest extends BaseTest{

    private HQPulseClient client;

    @Before
    public void setUp() throws Exception {
        super.setUp();
       // client = new HQPulseClient("7df16a276e74452db73d4601335a66e4","eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI3ZGYxNmEyNzZlNzQ0NTJkYjczZDQ2MDEzMzVhNjZlNCIsInRlbXAiOiI0NDMuNTM1OTIwMDUzOTE4NSJ9.o1X53hvj-xXoBX0IP15kXqOeIBLdjNROZKu1-pl2ApaHjVulyYwutfOW__anEdnWxUS_rw_POH83URKHeLNn8A");
    }

    @Test
    public void staffCreateShouldSucceed() throws Exception {
        String fixtureName = "staffSyncRequest.json";
        expectResponse(fixtureName, 200);
        StaffModel staff = new StaffModel();
        staff.setStaffId("mnad");
        staff.setLocalId("1234");
        staff.setFirstName("J21");
        HQPulseAPI.syncStaffResource(staff);
        assertRequest("POST", "staffs/");
    }


    @Test
    public void canListDocuments() throws Exception {
        HQPulseResponse<DocumentModel> documentResponse = HQPulseAPI.listDocuments(null);
        assertNotNull(documentResponse);
        assertNotNull(documentResponse.getRecord());
        //assertRequest("POST", "staffs/");
    }


    @Test
    public void canDownloadDocument() throws Exception {
        File file = HQPulseAPI.downloadDocument("hqp-doc-dbd0a84f-440f-465b-ba33-f156e4fdff27");
        assertNotNull(file);
        //assertRequest("POST", "staffs/");
    }
}
