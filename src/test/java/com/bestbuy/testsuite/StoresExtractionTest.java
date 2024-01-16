package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when().get("/stores").then().statusCode(200);
        //response.log().all();
    }

    //1. Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th store
    @Test
    public void test003() {
        String storeName = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all the stores
    @Test
    public void test004() {
        List<?> listOfStores = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + listOfStores);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the storeId of all the store
    @Test
    public void test005(){
        List<?> listOfStoreIds = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + listOfStoreIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the size of the data list
    @Test
    public void test006(){
        List<?> data = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data is : " + data.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007(){
    List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'St Cloud'}");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The values for store name 'St Cloud' are: " +  values);
    System.out.println("------------------End of Test---------------------------");
}

//8. Get the address of the store where store name = Rochester
@Test
public void test008(){
    List<HashMap<String, ?>> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The values for store name 'St Cloud' are: " +  address);
    System.out.println("------------------End of Test---------------------------");
}

//9. Get all the services of 8th store
@Test
public void test009(){
    List<HashMap<String, ?>> services = response.extract().path("data[7].services");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The services of 8th store are: " +  services);
    System.out.println("------------------End of Test---------------------------");
}

//10. Get storeservices of the store where service name = Windows Store
@Test
public void test010(){
    List<HashMap<String, ?>> storeServices = response.extract().path("data.services*.findAll{it.name == 'Windows Store'}.storeservices");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The store services for store name 'Windows Store' are: " + storeServices);
    System.out.println("------------------End of Test---------------------------");
}

//11. Get all the storeId of all the store
    @Test
    public void test011(){
        List<?> listOfStoreIds = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + listOfStoreIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //12. Get id of all the store
    //data[*].id
    @Test
    public void test012(){
        List<?> id = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + id);
        System.out.println("------------------End of Test---------------------------");
    }

    //13. Find the store names Where state = ND
    @Test
    public void test013(){
        List<HashMap<String, ?>> state = response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store name for state ND is : " +  state);
        System.out.println("------------------End of Test---------------------------");
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014(){
        List<?> services = response.extract().path("data.findAll{it.name == 'Rochester'}.services");
        int number = services.size();
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Total number of services for the store where store name = Rochester is  : " +  number);
        System.out.println("------------------End of Test---------------------------");
    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test015(){
        List<HashMap<String, ?>> createdATOfServices = response.extract().path("data.services*.findAll{it.name == 'Windows Store'}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The services for store name 'Windows Store' are: " + createdATOfServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test016() {
        List<HashMap<String, ?>> name = response.extract().path("data.findAll{it.name == 'Fargo'}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the createdAt for all services whose name = “Windows Store” is  : " +  name);
        System.out.println("------------------End of Test---------------------------");
    }

    //17. Find the zip of all the store
    @Test
    public void test017(){
        List<HashMap<String, ?>> zip = response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of all the store is  : " +  zip);
        System.out.println("------------------End of Test---------------------------");
    }

    //18. Find the zip of store name = Roseville
    @Test
    public void test018(){
        List<HashMap<String, ?>> zip1 = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of all the store is  : " +  zip1);
        System.out.println("------------------End of Test---------------------------");
    }

    //19. Find the store services details of the service name = Magnolia Home Theater
    @Test
    public void test019(){
        List<HashMap<String, ?>> storeServices = response.extract().path("data.services*.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The services for store name 'Magnolia Home Theater' are: " + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //20. Find the lat of all the stores
    @Test
    public void test020(){
        List<HashMap<String, ?>> lat = response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The lat of all the stores are  : " +  lat);
        System.out.println("------------------End of Test---------------------------");
    }
    }







