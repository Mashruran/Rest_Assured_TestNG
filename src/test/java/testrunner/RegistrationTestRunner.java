package testrunner;

import com.github.javafaker.Faker;
import config.Setup;
import config.UserModel;
import controller.AdminController;
import controller.RegistrationController;
import controller.UserController;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.IOException;

public class RegistrationTestRunner extends Setup {
//    RegistrationController registrationController;
//
//    @BeforeClass
//    public void myUserController() {
//        registrationController = new RegistrationController(prop);
//    }

    @Test(priority = 1 , description = "Register new user" )
    public void userRegistration() throws ConfigurationException {

        RegistrationController registrationController=new RegistrationController(prop);
        UserModel userModel = new UserModel();
        Faker faker = new Faker();

        userModel.setFirstName(faker.name().firstName());
        userModel.setLastName(faker.name().lastName());
        userModel.setEmail("salmansrabon+"+ Utils.generateRandomNumber(1000,9999)+"@gmail.com");
        userModel.setPassword("1234");
        userModel.setPhoneNumber("0130"+ Utils.generateRandomNumber(10000000,99999999));
        userModel.setAddress(faker.address().fullAddress());
        userModel.setGender("Male");
        userModel.setTermsAccepted(true);

        Response res = registrationController.createUser(userModel);
        Assert.assertEquals(res.getStatusCode(),201);

        JsonPath jsonObj=res.jsonPath();

        String userId= jsonObj.get("_id");
        Utils.setEnv("userId",userId);
        String userEmail=jsonObj.get("email");
        Utils.setEnv("userEmail",userEmail);
    }
    @Test(priority = 2 , description = "Register new user (Mandatory field)" )
    public void userRegistrationMandatoryField() throws ConfigurationException {

        RegistrationController registrationController=new RegistrationController(prop);
        UserModel userModel = new UserModel();
        Faker faker = new Faker();

        userModel.setFirstName(faker.name().firstName());
        userModel.setLastName("");
        userModel.setEmail("salmansrabon+"+ Utils.generateRandomNumber(1000,9999)+"@gmail.com");
        userModel.setPassword("1234");
        userModel.setPhoneNumber("0130"+ Utils.generateRandomNumber(10000000,99999999));
        userModel.setAddress("");
        userModel.setGender("Male");
        userModel.setTermsAccepted(true);

        Response res = registrationController.createUser(userModel);
        Assert.assertEquals(res.getStatusCode(),201);
    }
    @Test(priority = 3 , description = "Existing user can not register(duplicate email)" )
    public void existingUserRegistration() throws ConfigurationException {

        RegistrationController registrationController=new RegistrationController(prop);
        UserModel userModel = new UserModel();
        Faker faker = new Faker();

        userModel.setFirstName(faker.name().firstName());
        userModel.setLastName(faker.name().lastName());
        userModel.setEmail(prop.getProperty("userEmail"));
        userModel.setPassword("1234");
        userModel.setPhoneNumber("0130"+ Utils.generateRandomNumber(10000000,99999999));
        userModel.setAddress(faker.address().fullAddress());
        userModel.setGender("Male");
        userModel.setTermsAccepted(true);

        Response res = registrationController.createUser(userModel);
        Assert.assertEquals(res.getStatusCode(),400);

    }

}