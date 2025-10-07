package br.com.ju.core;

import br.com.ju.model.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ApiClient {

    public Response listUsers(int page) {
        return given()
                .queryParam("page", page)
                .when()
                .get("/api/users");
    }

    public Response getUser(int id) {
        return given()
                .when()
                .get("/api/users/{id}", id);
    }

    public Response createUser(CreateUserRequest request) {
        return given()
                .body(request)
                .when()
                .post("/api/users");
    }
}