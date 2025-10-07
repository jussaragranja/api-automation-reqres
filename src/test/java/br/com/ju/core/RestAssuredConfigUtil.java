package br.com.ju.core;

import br.com.ju.config.Config;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;

import java.util.HashMap;
import java.util.Map;

public final class RestAssuredConfigUtil {
    private RestAssuredConfigUtil() {}

    public static void configure() {
        RestAssured.baseURI = Config.getBaseUrl();
        RestAssured.config = RestAssuredConfig.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", Config.getTimeout())
                        .setParam("http.socket.timeout", Config.getTimeout())
                );

        Map<String, String> headers = new HashMap<>(Config.getDefaultHeaders());
        headers.put("x-api-key", Config.getApiKey());

        RestAssured.requestSpecification = RestAssured.given().headers(headers);
    }
}