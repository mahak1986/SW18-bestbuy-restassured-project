package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when().get("/products").then().statusCode(200);
        //response.log().all();
    }

    //21. Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("The value of limit is : " + limit);

    }

    //22. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("The value of total is : " + total);
    }

    //23. Extract the name of 5th product
    @Test
    public void test003() {
        String productName = response.extract().path("data[4].name");
        System.out.println("The name of 5th product is : " + productName);

    }

    //24. Extract the names of all the products
    @Test
    public void test004() {
        List<?> listOfProducts = response.extract().path("data.name");
        System.out.println("The names of all the products are : " + listOfProducts);
    }

    //25. Extract the productId of all the products
    @Test
    public void test006() {
        List<?> listOfProductIds = response.extract().path("data.categories.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + listOfProductIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //26. Print the size of the data list
    @Test
    public void test007() {
        List<?> data = response.extract().path("data");
        System.out.println("The size of the data is : " + data.size());
    }

    //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-
    //Pack)
    @Test
    public void test008() {
        List<HashMap<String, ?>> product = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("The value of the product where product name = Energizer - MAX Batteries AA (4-Pack) are: " + product);

    }

    //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-
    //Pack)
    @Test
    public void test009() {
        List<HashMap<String, ?>> model = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("The model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack) are: " + model);
    }

    //29. Get all the categories of 8th products
    @Test
    public void test010() {
        List<HashMap<String, ?>> categories = response.extract().path("data[7].categories");
        System.out.println("The services of 8th store are: " + categories);
    }

    //30. Get categories of the store where product id = 150115
    @Test
    public void test011() {
        List<HashMap<String, ?>> id = response.extract().path("data.findAll{it.id == 150115}");
        System.out.println("The categories of the store where product id = 150115 are: " + id);
    }

    //31. Get all the descriptions of all the products
    @Test
    public void test012() {
        List<HashMap<String, ?>> description = response.extract().path("data.description");
        System.out.println("The descriptions of all the products are: " + description);
    }

    //32. Get id of all the all categories of all the products
    @Test
    public void test013() {
        List<HashMap<String, ?>> categories = response.extract().path("data.categories.id");
        System.out.println("id of all the all categories of all the products are: " + categories);
    }

    //33. Find the product names Where type = HardGood
    @Test
    public void test014() {
        List<HashMap<String, ?>> productName = response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println("The product names Where type = HardGood are: " + productName);
    }

    //34. Find the Total number of categories for the product where product name = Duracell - AA
    //1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test015() {
        List<HashMap<String, ?>> totalNumber = response.extract().path("data.findAll{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println("The total number of categories are: " + totalNumber);
    }

    //35. Find the createdAt for all products whose price < 5.49
    @Test
    public void test016() {
        List<?> price = response.extract().path("data.findAll{it.price < 5.49}.createdAt");
        System.out.println("The total number of categories are: " + price);
    }

    //36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-
    //Pack)”
    @Test
    public void test017() {
        List<String> names = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println("The name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)" + names);
    }

    //37. Find the manufacturer of all the products
    @Test
    public void test018() {
        List<String> manufacturer = response.extract().path("data.manufacturer");
        System.out.println("The manufacturer of all the products" + manufacturer);
    }

    //38. Find the image of products whose manufacturer is = Energizer
    @Test
    public void test019() {
        List<String> image = response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");
        System.out.println("The image of products whose manufacturer is = Energizer" + image);
    }

    //39. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test020() {
        List<?> price1 = response.extract().path("data.findAll{it.price > 5.49}.categories.createdAt");
        System.out.println("The createdAt for all categories products whose price > 5.99" + price1);
    }

    //40. Find the url of all the products
    @Test
    public void test021() {
        List<String> url = response.extract().path("data.url");
        System.out.println("The url of all the products : " + url);
    }
}
