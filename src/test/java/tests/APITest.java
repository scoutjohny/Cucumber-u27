package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITest {

    @Test
    public void getBooks(){

        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);

        Response response = request.get("https://bookstore.toolsqa.com/Bookstore/v1/Books");
        ValidatableResponse validatableResponse = response.then();
        validatableResponse.statusCode(200);
        validatableResponse.contentType(ContentType.JSON);
        response.getBody().prettyPeek();
    }
}
