package br.com.ju;

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
                .statusCode(200)
                .log().all()
                .body("data", not(empty()))
                .body("data.first_name", hasItem("Michael"));
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
                .header("x-api-key", "reqres-free-v1")
                .body(requestBody)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"));
    }

    @Test
    void testGetSingleUser() {
        given()
                .header("x-api-key", "reqres-free-v1")
                .when()
                .get("/api/users/2")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.email", containsString("@reqres.in"));
    }

    @Test
    void testUserNotFound() {
        given()
                .when()
                .header("x-api-key", "reqres-free-v1")
                .get("/api/users/23")
                .then()
                .statusCode(404);
    }
}