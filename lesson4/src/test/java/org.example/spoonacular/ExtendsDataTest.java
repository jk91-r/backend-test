package org.example.spoonacular;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.junit.jupiter.api.BeforeAll;

public class ExtendsDataTest extends Assert{


    private static final String API_KEY = "08186cf517ba4f4db46e205f3facbc39" ;

    @BeforeAll

    static void beforeAll(){

        RestAssured.baseURI = "https://api.spoonacular.com/";
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey",API_KEY )
                .log(LogDetail.ALL)
                .build();
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.BODY)
                .build();
    }
}
