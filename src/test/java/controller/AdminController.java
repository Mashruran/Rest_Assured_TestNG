package controller;

import config.UserModel;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.awt.*;
import java.io.IOException;
import java.util.Properties;
import java.util.SimpleTimeZone;

import static io.restassured.RestAssured.given;

public class AdminController {
    static Properties prop;
    public AdminController(Properties prop){
        RestAssured.baseURI="https://dailyfinanceapi.roadtocareer.net";
        this.prop=prop;
    }
    public Response doLogin(UserModel userModel){
        Response res= given().contentType("application/json")
                .body(userModel)
                .when().post("/api/auth/login");
        return res;
    }
    public Response getUserList(){
        Response res = given().contentType("application/json")
                .header("Authorization", "Bearer " + prop.getProperty("accessToken"))
                .when().get("/api/user/users");

        return res;
    }

    public Response searchUser(String userId) throws IOException {
        Response res= given().contentType("application/json")
                .header("Authorization","Bearer "+ prop.getProperty("accessToken"))
                .when().get("/api/user/"+userId);
        return res;
    }
    public static Response updateUser(UserModel userModel) throws IOException {
        Response res= given().contentType("application/json")
                .header("Authorization","Bearer "+ prop.getProperty("accessToken"))
                .body(userModel)
                .when().put("/api/user/"+prop.getProperty("userId"));
        return res;
    }

}