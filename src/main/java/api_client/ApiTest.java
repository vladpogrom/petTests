package api_client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

import java.io.File;

import static api_client.RequestBuilder.getRequestBuilder;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiTest {
    public RequestSpecification requestSpecification;

    public ApiTest() {
        this.requestSpecification = getRequestBuilder().build();
    }

    public void getPet(int code, int petId, String schemaPath) {
        given()
                .spec(requestSpecification)
                .get(EndPoints.PET, petId)
                .then()
                .statusCode(code)
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath(schemaPath));
    }

    public void getPetNegative(int code, int petId, String type, String errorMessage) {
        Response response = given()
                .spec(requestSpecification)
                .get(EndPoints.PET, petId)
                .then()
                .extract().response();
        Assertions.assertEquals(code, response.statusCode(), "Ошибка - " + response.body().toString());
        if (response.statusCode() != 200) {
            Assertions.assertEquals(type, response.body().path("type"));
            Assertions.assertEquals(errorMessage, response.body().path("message"));
        }
    }

    public void getPetPositive(int code, Integer petId, String name) {
        Response response = given()
                .spec(requestSpecification)
                .get(EndPoints.PET, petId)
                .then()
                .extract().response();
        Assertions.assertEquals(code, response.statusCode(), "Ошибка - " + response.body().toString());
        if (response.statusCode() == 200) {
            Assertions.assertEquals(petId, response.body().path("id"));
            Assertions.assertEquals(name, response.body().path("name"));
        }
    }

    public void postPet(int code, File body) {
        Response response = given()
                .spec(requestSpecification)
                .body(body)
                .post(EndPoints.PET_POST)
                .then()
                .extract().response();
        Assertions.assertEquals(code, response.statusCode(), "Ошибка - " + response.body().toString());
    }

    public void putPet(int code, String body) {
        Response response = given()
                .spec(requestSpecification)
                .body(body)
                .put(EndPoints.PET_POST)
                .then()
                .extract().response();
        Assertions.assertEquals(code, response.statusCode(), "Ошибка - " + response.body().toString());
    }

    public void deletePet(int code, int petId, String type, String message) {
        Response response = given()
                .spec(requestSpecification)
                .delete(EndPoints.PET, petId)
                .then()
                .extract().response();
        Assertions.assertEquals(code, response.statusCode(), "Ошибка - " + response.body().toString());
        if (response.statusCode() == 200) {
            Assertions.assertEquals(type, response.body().path("type"));
            Assertions.assertEquals(message, response.body().path("message"));
        }
    }
}
