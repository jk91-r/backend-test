package org.example.spoonacular;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;
import java.io.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RecipeTest extends ExtendsDataTest {

    @Test

    void successfulRequest(){
        given()
                .when()
                .get("recipes/")
                .then()
                .statusCode(200);
    }


    @Test

    void  responseTimeIsLessThan2000ms() {
        given()
                .when()
                .get("recipes/")
                .then()
                .time(lessThan(2000L));
    }


    @Test

    void getSummarizeRecipeFindId(){

        given()
                .pathParam("id",2253)
                .when()
                .get("recipes/{id}/summary")
                .prettyPrint();
    }


    @Test

    void getSummarizeRecipeBodyCheck() {
        given()
                .pathParam("id",3789)
                .expect()
                .body("title", equalTo("Crusted Mackerel"))
                .body("summary",stringContainsInOrder("98g of protein"))
                .when()
                .get("recipes/{id}/summary");
    }


    @Test

    void getSimilarRecipes() {
       given()
                .pathParam("id",28022)
                .param("number",2)
                .expect()
                .when()
                .get("recipes/{id}/similar")
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(new File("/Java/backend-test/src/test/resources/org/example/spoonacular/testSimilarRecipes/schema.json")));
    }


    @Test

    void getRandomRecipes(){
        given()
                .param("tags","dessert,Foodista,vegan")
                .param("number",1)
                .when()
                .get("recipes/random")
                .prettyPrint();
    }


    @Test

    void testAnalyzeRecipeSearchQuery() throws Exception {
        String result = given()
                .param("q","salmon with vegetables and no gluten")
                .expect()
                .when()
                .get("recipes/queries/analyze")
                .prettyPrint();

        String expected = getResourceAsString("expected.json");

               assertJson(expected,result);
    }


    @Test

    void testClassifyCuisine(){
        given()
                .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                .formParam("title", "mochi")
                .expect()
                .body("cuisine", equalTo("Japanese"))
                .body("confidence",is(0.85F))
                .body("cuisines",contains("Japanese",
                        "Asian"))
                .when()
                .post("recipes/cuisine");

    }

}








