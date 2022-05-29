package org.example.spoonacular;


import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import net.javacrumbs.jsonunit.JsonAssert;
import net.javacrumbs.jsonunit.core.Option;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RecipeTest {

    private static final String API_KEY = "08186cf517ba4f4db46e205f3facbc39" ;


    @BeforeAll

    static void beforeAll(){
        RestAssured.baseURI = "https://api.spoonacular.com/recipes/";
    }


    @Test

    void successfulRequest(){
        given()
                .log()
                .all()
                .param("apiKey",API_KEY)
                .when()
                .get("")
                .then()
                .statusCode(200);
    }


    @Test

    void  responseTimeIsLessThan2000ms() {
        given()
                .log()
                .all()
                .param("apiKey", API_KEY)
                .when()
                .get("")
                .then()
                .time(lessThan(2000L));
    }


    @Test

    void getSummarizeRecipeFindId(){

        given()
                .log()
                .all()
                .param("apiKey",API_KEY)
                .pathParam("id",2253)
                .when()
                .get("{id}/summary")
                .body()
                .prettyPrint();
    }


    @Test

    void getSummarizeRecipeBodyCheck() {
        given()
                .log()
                .all()
                .param("apiKey",API_KEY)
                .pathParam("id",3789)
                .expect()
                .log()
                .all()
                .body("title", equalTo("Crusted Mackerel"))
                .body("summary",stringContainsInOrder("98g of protein"))
                .when()
                .get("{id}/summary");
    }


    @Test

    void getSimilarRecipes() {
       given()
                .log()
                .all()
                .param("apiKey",API_KEY)
                .pathParam("id",28022)
                .param("number",2)
                .expect()
                .log()
                .body()
                .when()
                .get("{id}/similar")
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(new File("/Java/backend-test/src/test/resources/org/example/spoonacular/testSimilarRecipes/schema.json")));
    }


    @Test

    void getRandomRecipes(){
        given()
                .log()
                .all()
                .param("apiKey",API_KEY)
                .param("tags","dessert,Foodista,vegan")
                .param("number",1)
                .when()
                .get("random")
                .body()
                .prettyPrint();

    }


    @Test

    void testAnalyzeRecipeSearchQuery() throws IOException{
        String result = given()
                .log()
                .all()
                .param("apiKey",API_KEY)
                .param("q","salmon with vegetables and no gluten")
                .expect()
                .log()
                .body()
                .when()
                .get("queries/analyze")
                .body()
                .prettyPrint();

        String expected = getResourceAsString("testAnalyzeRecipeSearchQuery/expected.json");

        JsonAssert.assertJsonEquals(
                expected,
                result,
                JsonAssert.when(Option.IGNORING_ARRAY_ORDER));
    }


    public String getResourceAsString(String resource) throws IOException{

       InputStream stream = getClass().getResourceAsStream(resource);
            assert stream != null;
            byte[] bytes = IOUtils.toByteArray(stream);
            return new String(bytes, StandardCharsets.UTF_8);
    }

    @Test

    void testClassifyCuisine(){
        given()
                .log()
                .all()
                .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                .queryParam("apiKey",API_KEY)
                .formParam("title", "mochi")
                .expect()
                .log()
                .body()
                .body("cuisine", equalTo("Japanese"))
                .body("confidence",is(0.85F))
                .body("cuisines",contains("Japanese",
                        "Asian"))

                .when()
                .post("/cuisine");

    }

}








