package org.example.spoonacular;


import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class ByFileTest extends  RecipeTest{

    @Test

    void testImageAnalysisByFile() {
        given()
                .multiPart(getFile("cheesecake.jpg"))
                .when()
                .post("food/images/analyze")
                .prettyPrint();

    }


    @Test

    void testImageClassificationByFile() {
        given()
                .multiPart(getFile("coffee.jpg"))
                .expect()
                .body("category", is("coffee"))
                .body("probability",lessThanOrEqualTo(1F))
                .when()
                .post("food/images/classify")
                .prettyPrint();

    }
}
