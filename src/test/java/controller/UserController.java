package controller;

import config.ItemModel;
import config.UserModel;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.replaceFiltersWith;

public class UserController {
    static Properties prop;
    public UserController(Properties prop){
        RestAssured.baseURI="https://dailyfinanceapi.roadtocareer.net";
        this.prop=prop;
    }
    public Response userLogin(UserModel userModel){
        Response res= given().contentType("application/json")
                .body(userModel)
                .when().post("/api/auth/login");
        return res;
    }
    public Response userAddItem(ItemModel itemModel){
        Response res=given().contentType("application/json")
                .header("Authorization", "Bearer " + prop.getProperty("userToken"))
                .body(itemModel)
                .when().post("/api/costs");
        return res;
    }
    public Response getItemList(){
        Response res = given().contentType("application/json")
                .header("Authorization", "Bearer " + prop.getProperty("userToken"))
                .when().get("/api/costs");
        return res;
    }
    public Response editItem(ItemModel itemModel){
        Response res= given().contentType("application/json")
                .header("Authorization","Bearer "+ prop.getProperty("userToken"))
                .body(itemModel)
                .when().put("api/costs/"+prop.getProperty("itemId"));
        return res;
    }
    public Response deleteItem() throws IOException {

        Response res= given().contentType("application/json")
                .header("Authorization","Bearer "+ prop.getProperty("userToken"))
                .when().delete("api/costs/"+prop.getProperty("itemId"));
        return res;
    }

}