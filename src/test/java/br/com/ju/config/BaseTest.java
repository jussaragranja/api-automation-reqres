package br.com.ju.config;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    @BeforeAll
    static void setup() {
        RestAssured.baseURI = Config.getBaseUrl();
        RestAssured.config = RestAssured.config()
                .httpClient(
                        RestAssured.config().getHttpClientConfig()
                                .setParam("http.connection.timeout", Config.getTimeout())
                                .setParam("http.socket.timeout", Config.getTimeout())
                );
        RestAssured.requestSpecification = RestAssured.given().headers(Config.getDefaultHeaders());
    }
}