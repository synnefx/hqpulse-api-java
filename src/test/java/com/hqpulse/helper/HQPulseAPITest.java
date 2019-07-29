package com.hqpulse.helper;

import com.hqpulse.helper.models.StaffModel;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Josekutty
 * 28-07-2019
 */
public class HQPulseAPITest extends BaseTest{

    private HQPulseClient client;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        client = new HQPulseClient("7df16a276e74452db73d4601335a66e4","eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI3ZGYxNmEyNzZlNzQ0NTJkYjczZDQ2MDEzMzVhNjZlNCIsInRlbXAiOiI0NDMuNTM1OTIwMDUzOTE4NSJ9.o1X53hvj-xXoBX0IP15kXqOeIBLdjNROZKu1-pl2ApaHjVulyYwutfOW__anEdnWxUS_rw_POH83URKHeLNn8A");
    }

    @Test
    public void staffCreateShouldSucceed() throws Exception {
        String fixtureName = "staffSyncRequest.json";
        expectResponse(fixtureName, 200);
        StaffModel staff = new StaffModel();
        HQPulseAPI.syncStaffResource(staff);

        assertRequest("POST", "staffs/");
    }

}
