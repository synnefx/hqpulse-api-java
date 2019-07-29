package com.hqpulse.helper;

import com.hqpulse.helper.exceptions.HQPulseRestException;
import com.hqpulse.helper.models.StaffModel;

import java.io.IOException;
import java.util.Calendar;

/**
 * @author Josekutty
 * 28-07-2019
 */
public class GenericTest {

    public static void main(String[] args) throws IOException, HQPulseRestException {

        HQPulseAPI.init("7df16a276e74452db73d4601335a66e4", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI3ZGYxNmEyNzZlNzQ0NTJkYjczZDQ2MDEzMzVhNjZlNCIsInRlbXAiOiI0NDMuNTM1OTIwMDUzOTE4NSJ9.o1X53hvj-xXoBX0IP15kXqOeIBLdjNROZKu1-pl2ApaHjVulyYwutfOW__anEdnWxUS_rw_POH83URKHeLNn8A");
        StaffModel staffModel = new StaffModel();
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE, -5);
        staffModel.setDateOfJoining(date);
        staffModel.setDateResigned(Calendar.getInstance());
        staffModel.setDesignation("Test API");
        staffModel.setId(1L);
        staffModel.setFirstName("Test API fname");
        staffModel.setLastName("Test API lname");
        staffModel.setStaffId("TESTAPI001");
        HQPulseAPI.syncStaffResource(staffModel);
    }
}
