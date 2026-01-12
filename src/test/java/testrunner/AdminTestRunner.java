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

public class AdminTestRunner extends Setup {

//    AdminController adminController;
//    @BeforeClass
//    public void myUserController(){
//        adminController=new AdminController(prop);
//    }
    @Test(priority = 1,description = "Admin can not login with wrong credentials")
    public void adminLoginWrongCreds() throws ConfigurationException {

        AdminController adminController=new AdminController(prop);
        UserModel userModel=new UserModel();
        userModel.setEmail("admin@test.com");
        userModel.setPassword("1234");
        Response res= adminController.doLogin(userModel);

        JsonPath jsonObj=res.jsonPath();

        Assert.assertEquals( jsonObj.get("message") , "Invalid email or password");
        Assert.assertEquals(res.getStatusCode(), 401);

    }
    @Test(priority = 2, description = "Admin can login with valid credentials")
    public void adminLoginCorrectCreds() throws ConfigurationException {
        AdminController adminController=new AdminController(prop);
        UserModel userModel=new UserModel();
        userModel.setEmail("admin@test.com");
        userModel.setPassword("admin123");
        Response res= adminController.doLogin(userModel);

        JsonPath jsonObj=res.jsonPath();

        Assert.assertEquals( jsonObj.get("_id") , "admin_id"  );
        Assert.assertEquals(res.getStatusCode(), 200);

        String accessToken= jsonObj.get("token");
        Utils.setEnv("accessToken",accessToken);

        System.out.println(accessToken);
    }
    @Test(priority = 3 , description = "Admin can get user list")
    public void userList() throws InterruptedException {
        AdminController adminController=new AdminController(prop);
        Response res = adminController.getUserList();
        Assert.assertEquals( res.getStatusCode() , 200 );

    }
    @Test(priority = 4, description = "Admin can search user by his Id")
    public void searchUserById() throws IOException, InterruptedException {
        AdminController adminController=new AdminController(prop);
        Response res = adminController.searchUser(prop.getProperty("userId"));
        Assert.assertEquals(res.getStatusCode() , 200);
    }
    @Test(priority = 5, description = "Admin search user by incorrect Id")
    public void searchUserByIncorrectId() throws IOException, InterruptedException {
        AdminController adminController=new AdminController(prop);
        Response res = adminController.searchUser(prop.getProperty("ID"+Utils.generateRandomNumber(100000,99999)));
        Assert.assertEquals(res.getStatusCode() , 404);
    }

    @Test(priority = 6, description = "Admin update user information")
    public void updateUserInfo() throws IOException, InterruptedException {

        AdminController adminController = new AdminController(prop);
        UserModel userModel = new UserModel();
        Faker faker = new Faker();

        userModel.setFirstName(faker.name().firstName());
        userModel.setLastName(faker.name().lastName());
        userModel.setEmail(prop.getProperty("userEmail"));
        userModel.setPhoneNumber("0120" + Utils.generateRandomNumber(1000000, 9999999));
        userModel.setAddress("Dhaka");
        userModel.setGender("Male");
        userModel.setTermsAccepted(true);
        Response res = adminController.updateUser(userModel);

        System.out.println(res.asString());
        Assert.assertEquals(res.getStatusCode(), 200);
    }
}