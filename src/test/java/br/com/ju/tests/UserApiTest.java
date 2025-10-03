package br.com.ju.tests;

import br.com.ju.config.BaseTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserApiTest extends BaseTest {

    @Test
    void testListUsers() {
        given()
                .when()
                .get("/api/users?page=2")
                .then()
                .log().all()
                .statusCode(200)
                .body("data", not(empty()))
                .body("data.name", hasItem("morpheus"));
    }

    @Test
    void testCreateUser() {
        var requestBody = """
            {
                "name": "morpheus",
                "job": "leader"
            }
            """;
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"));
    }

    @Test
    void testGetSingleUser() {
        given()
                .when()
                .get("/api/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.name", containsString("fuchsia rose"));
    }

    @Test
    void testUserNotFound() {
        given()
                .when()
                .get("/api/users/23")
                .then()
                .log().all()
                .statusCode(404);
    }
}