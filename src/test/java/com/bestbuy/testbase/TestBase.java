package com.bestbuy.testbase;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

/**
 * Created by Jay Vaghani
 */
public class TestBase {

    @BeforeClass
    public void inIt() {
        //RestAssured is a class ,baseURI is a method
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;

    }
}
