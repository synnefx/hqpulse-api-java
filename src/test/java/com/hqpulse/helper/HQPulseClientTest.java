package com.hqpulse.helper;

import com.hqpulse.helper.models.HQPulseResponse;
import com.hqpulse.helper.models.StaffModel;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author Josekutty
 * 28-07-2019
 */
public class HQPulseClientTest extends BaseTest {

    @Test
    public void clientShouldSendCorrectUserAgent() throws Exception {
        String fixtureName = "staffSyncRequest.json";

        expectResponse(fixtureName, 200);
        HQPulseResponse<String> message = HQPulseAPI.syncStaffResource(new StaffModel());

        RecordedRequest request = this.server.takeRequest();
        TestCase.assertTrue(request.getHeader("User-Agent").toLowerCase().contains("hqpulse-java"));
    }
}
