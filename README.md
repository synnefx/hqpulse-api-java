# Installing HQPulse

## System Requirements
1.	Java 1.8+
2.	MySQL
3.	Redis
4.	Minimum 2GB JVM Memory (Can vary based on the number of users and the features being used)

## Installation
1.	Create database “cqmsdb” in your mysql server

```
create database cqmsdb;

create user 'hqpulsedbuser'@'localhost' identified by ‘password’;

grant all on cqmsdb.* to 'hqpulsedbuser' identified by password';
```
2.	Create a directory for keeping flat files. (In linux machines, restrict access to this directory. Only the tomcat user must have access to this directory. Ex: /hqpulse/resources)
3.	Inside this directory create subdirectory named “logos” and keep your product logos names logo_primary.png and logo_secondary.png (If you have different version of the logo else the same file as the primary). The logo dimension must be approximately 140x40. 
4.	Set Environment variables
```
Variable name	Value	Notes
REDIS_HOST	localhost	Hostname of your redis server
REDIS_PORT	6379	Redis server port number
spring.profiles.active	local	local/staging/production
RELEASE_ENV	local	prod for staging and live. Will be removed later
APP_BUILD_VERSION	1.02	HQPulse version
STRUTS_DEV	true	False in live versions
BATCH_ENABLED	true	
RDS_HOSTNAME	localhost	Mysql server hostname
RDS_USERNAME	hqpulsedbuser	User pasword
RDS_PASSWORD	erdegfert	Mysql password
RDS_PORT	3306	MySQL port number
app.upload.root	/hqpulse/resources	This is the directory created in step 2 of Installation. In windows it can be like “C:\Work\HQPulse\resources”
CONFIG_ENV	false	false
UPDATE_DB	false	False
ENV_ENCRYPTION_KEY	Random string	Please note that you must not change this key. If this is lost your entire data stored till now database is lost or will not be readable.
```

5.	Copy the war file to webapp directory of tomcat
6.	Start/Restart server and hit localhost:8080 (or domainname:8080, based on your hosting environment). This will get you to a configuration screen.

## Configuration

 
The above screenshots display a sample form. You will need to fill the details applicable to the current installation. Installation id and product key will be shared later.

If Single sign on is selected, you need to add the secret key used at authentication server for authentication token encryption, an environment parameter. Based on the preference, you may need to add few more environment parameters which will be listed in the set up wizard screen.

auth.sso.key	The secret key to decrypt the auth token
email.mailgun.token	Mailgun api token obtained from mailgun if you select mailgun as email service provider
plivo.auth.token	Plivo API token if plivo is selctyed as SMS service provider
	

Finally click save button. If validation successful, configuration will be completed and you will be asked to login (The user you set as SSO authentication URL in the set up wizard). It is always advice to restart the tomcat server after successful configuration to avoid any potential configuration conflicts.

## HQPulse SDK
	
  HQPulse offers an SDK for HMIS integration. This SDK works as an abstraction over the HQPulse APIs.
  
1.	HQPulse offers java SDK for HMIS integration. Please note that the SDK requires JDK 1.8+.
2.	Clone/or download HQPulse SDK from github, available at SDK available at https://github.com/synnefx/hqpulse-api-java
3.	Build and install the SDK using mvn compile install -DskipTests  which will build the jar file.
4.	Add the SDK dependency in pom.xml
<dependency>
            <groupId>com.hqpulse.helper</groupId>
            <artifactId>hqpulse-api-java</artifactId>
            <version>1.0-SNAPSHOT</version>
  </dependency>

5.	Now you can start using HQPulse APIs.

  1.	Get API credentials from HQPulse. Login to HQPulse as Quality Team Admin user.
  2.	Go to Configuration -> API Security 
  3.	Click Generate token. This will issue an API token. Copy the Id and key.
  4.	In HMIS initiate HQPulse SDK as below. Before calling HQPulse API, the SDK must be initiated. (Required to initiate only once hence use static loading)

```
String hqpulseApiId = “7df16a276e74452db73d4601335a66e4”;
String hqpulseToken = “hbGciOiJIUzUxMiJ9.eyJzdWIiOiI3ZGYxNmEyNzZlNzQ0NTJkYjczZDQ2MDEzMzV”;

HQPulseAPI.init(hqpulseApiId, hqpulseToken); 
```

# HQPulse APIs
## Sync Users

Call this API to sync HMIS users with HQPulse users. You can sync all HMIS users or optionally select users who actually need access to this feature.


```
POST   {{HMIS_API_BASE_URL}}/integration/v1/{{API_ACCOUNT_ID}}/configuration/users/
```
API Authentication method - Bearer Token authentication

### Request Sample:
```
{
	"records" : [
		{
			"localId" : "TEST122",
			"userId": "abc0010",
			"roles" : [ {"code":"COMPLAINT_MANGER"},{"code": "AUDIT_MANGER"}],
			"firstName":"Amarthya",
			"lastName" : "Sen",
			"type" : "STAFF",
			"contact" : {
				"emailID" : "qwer@synnefx.com",
				"phoneNumber" : "98746958"
				
			}
		}
		]
}
```

### Response Sample:
```
{
    "status": "SUCCESS",
    "message": "Records Processed",
    "records": [
        {
            "sourceId": "TEST122",
            "status": "Error",
            "messages": [
                "Last name required",
                "Valid email or phone required",
                "Contact type not selected"
            ]
        }
    ]
}
{
    "status": "SUCCESS",
    "message": "Records Processed",
    "records": [
        {
            "sourceId": "TEST122",
            "status": "Updated"
        }
    ]
}
```

You can either call this API using any RestClient library or use HQPulse SDK for integration. Code snippet given below.

### The SDK way:
```
import com.hqpulse.helper.HQPulseAPI;
import com.hqpulse.helper.exceptions.HQPulseRestException;
import com.hqpulse.helper.exceptions.RequestValidationError;
import com.hqpulse.helper.models.HQPulseContactModel;
import com.hqpulse.helper.models.HQPulseResponse;
import com.hqpulse.helper.models.HQPulseUserModel;
import com.hqpulse.helper.models.PatientModel;
import com.hqpulse.helper.utils.Utils;
		
		
		//Code snippet
				
		
List<User> hmisUsers = //Query database to get all user
 Set<HQPulseUserModel> hqPulseUsers;
        if(CollectionUtils.isNotEmpty(hmisUsers)){
            hqPulseUsers = hmisUsers.stream().map(user -> {
                HQPulseUserModel hqPulseUser = new HQPulseUserModel();

                //Set username/userid/loginid
                hqPulseUser.setUserId(user.getLogin());
                
              //Set patient ID (the primary key) in HMIS database, as localId
              hqPulseUser.setLocalId(String.valueOf(user.getId()));

                //SetHQPusle roles to be assigned to this user
                //Implement your own logic in assignUserRoles(user)
                //Available roles are in Utils.UserRole
                Set<Utils.UserRole> hqpulseRolesForThisUser = assignUserRoles(user);
                hqPulseUser.setUserRoles(hqpulseRolesForThisUser);

                hqPulseUser.setFirstName(user.getFirstName());
                hqPulseUser.setMiddleName(user.getMiddleName());
                hqPulseUser.setLastName(user.getLastName());

                hqPulseUser.setContact(new HQPulseContactModel());
                hqPulseUser.getContact().setEmailID(user.getEmail());
                hqPulseUser.getContact().setPhoneNumber(user.getPhone());
                return hqPulseUser;
            }).collect(Collectors.toSet());
            try {
                HQPulseResponse<String> response = HQPulseAPI.syncUserResource(hqPulseUsers);
                log.debug("Users export status for hospital : " + hospital.getHqpAuthId() + "is "+response.getStatus());
            } catch (IOException e) {
                //log
            } catch (HQPulseRestException e) {
                //log
            } catch (RequestValidationError requestValidationError) {
                //log
            }
        }
```


### Sync Patients

	SDK example for syncing patient details
```
//Query HMIS database to fetch list of patients in admitted, consulted and in treatment
import com.hqpulse.helper.HQPulseAPI;
import com.hqpulse.helper.exceptions.HQPulseRestException;
import com.hqpulse.helper.exceptions.RequestValidationError;
import com.hqpulse.helper.models.HQPulseResponse;
import com.hqpulse.helper.models.PatientModel;
		
		
		//Code snippet
				
		
		
		Calendar exportDate = CalendarUtils.addDays(Calendar.getInstance(), -1);
        List<Patient> patientsToExport = //Query database to fetch all patients present in the given date
        Set<PatientModel> hqpulsePatients;
        if(CollectionUtils.isNotEmpty(patientsToExport)){
            hqpulsePatients = patientsToExport.stream().map(patient -> {
            PatientModel hqpulsePatient = new PatientModel();
//Set patient ID (the primary key) in HMIS database, as localId
           hqpulsePatient.setLocalId(String.valueOf(patient.getId()));
                if(null != patient.getPerson().getDateOfBirth()){
                    hqpulsePatient.setDateOfBirth(CalendarUtils.localDateToCalendar(patient.getPerson().getDateOfBirth()));
                }
                hqpulsePatient.setFirstName(patient.getPerson().getFirstName());
                hqpulsePatient.setMiddleName(patient.getPerson().getMiddleName());
                hqpulsePatient.setLastName(patient.getPerson().getLastName());

                hqpulsePatient.setGenderCode(patient.getPerson().getGender().CODE());
                hqpulsePatient.setMrdNumber(patient.getPatientNumber());
                hqpulsePatient.setLocalId(patient.getId());

                //Set visit details optionally
                return hqpulsePatient;
            }).collect(Collectors.toSet());
            try {
                HQPulseResponse<String> response = HQPulseAPI.syncPatientResource(exportDate,hqpulsePatients);
            } catch (IOException e) {
                //log
            } catch (HQPulseRestException e) {
                //log
            } catch (RequestValidationError requestValidationError) {
               //log
            }
        }

```

### Delete/Remove a user

```
import com.hqpulse.helper.HQPulseAPI;		
              
        try {

              String userNameToBeRemoved = “someuser_resigned”;
	HQPulseAPI.removeUser(userNameToBeRemoved);
               
            } catch (IOException e) {
               //log
            } catch (HQPulseRestException e) {
                //log
            } catch (RequestValidationError requestValidationError) {
               //log
            });

```
 
Like this HQPulse SDK will be evolving and the integration team can forget about how to send the data and handle proper API request and response contract but worry on how to get the correct data. More APIs will be added as and when needed.
 

## Single Sign-on

	If Single sign-on is enabled the authentication provider must also be configured accordingly. It must generate the authentication token using the utility method available in the SDK.

```
import com.hqpulse.helper.utils.HQPulseSSOUtils;

String ssoAuthToken = HQPulseSSOUtils.generateHQPulseToken("the_secret_key","currentUserLoginId")

```

When an HMIS user clicks on the link/button to HQPulse, the HMIS must redirect the request to HQPulse as below
```
{hqpulse_url}?token=ssoAuthToken
```
