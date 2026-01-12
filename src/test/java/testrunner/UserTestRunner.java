package testrunner;

import com.github.javafaker.Faker;
import config.ItemModel;
import config.Setup;
import config.UserModel;
import controller.UserController;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.IOException;

public class UserTestRunner extends Setup {
//    UserController userController;
//    @BeforeClass
//    public void myUserController(){
//        userController=new UserController(prop);
//    }
    @Test(priority = 1, description = "User login")
    public void doLogin() throws ConfigurationException, InterruptedException {

        UserController userController=new UserController(prop);
        UserModel userModel=new UserModel();

        userModel.setEmail(prop.getProperty("userEmail"));
        userModel.setPassword("1234");

        Response res= userController.userLogin(userModel);

        System.out.println(res.asString());
        JsonPath jsonObj=res.jsonPath();
        String userToken= jsonObj.get("token");
        Utils.setEnv("userToken",userToken);

        Assert.assertEquals( res.getStatusCode() , 200 );
    }
    @Test(priority = 2, description = "User can add any item")
    public void addItem() throws ConfigurationException, InterruptedException {
        UserController userController=new UserController(prop);
        ItemModel itemModel = new ItemModel();
        Faker faker = new Faker();

        itemModel.setAmount(""+Utils.generateRandomNumber(10000,99999));
        itemModel.setItemName(faker.commerce().productName());
        itemModel.setMonth("January");
        itemModel.setPurchaseDate("2026-01-10");
        itemModel.setQuantity(10);
        itemModel.setRemarks(faker.lorem().sentence());

        Response res= userController.userAddItem(itemModel);

        System.out.println(res.asString());
        JsonPath jsonObj=res.jsonPath();
        String itemId= jsonObj.get("_id");
        Utils.setEnv("itemId",itemId);

        Assert.assertEquals( res.getStatusCode() , 201 );
    }
    @Test(priority = 3, description = "User gets item list")
    public void userGetItemList() throws InterruptedException {
        UserController userController=new UserController(prop);
        Response res = userController.getItemList();
        Assert.assertEquals( res.getStatusCode() , 200 );
    }
    @Test(priority = 4, description = "User edits item")
    public void userEditItem() throws IOException, InterruptedException {
        UserController userController=new UserController(prop);
        ItemModel itemModel = new ItemModel();
        Faker faker = new Faker();

        itemModel.setAmount(""+Utils.generateRandomNumber(10,99));
        itemModel.setItemName(faker.commerce().productName());
        itemModel.setMonth("February");
        itemModel.setPurchaseDate("2026-02-20");
        itemModel.setQuantity(50);
        itemModel.setRemarks(faker.lorem().sentence());

        Response res= userController.editItem(itemModel);

        Assert.assertEquals( res.getStatusCode() , 200 );
    }
    @Test(priority = 5, description = "User deletes item")
    public void userDeleteItem() throws IOException, InterruptedException {
        UserController userController=new UserController(prop);
        Response res = userController.deleteItem();
        Assert.assertEquals( res.getStatusCode() , 200 );
    }

}