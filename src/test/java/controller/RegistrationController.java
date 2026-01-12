package controller;

import config.UserModel;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class RegistrationController {
    Properties prop;
    public RegistrationController(Properties prop){
        RestAssured.baseURI="https://dailyfinanceapi.roadtocareer.net";
        this.prop=prop;
    }
    public static Response createUser(UserModel userModel) {
        Response res = given().contentType("application/json")
                .body(userModel).when().post("/api/auth/register");
        return res;
    }

}