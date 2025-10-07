package br.com.ju.config;

import br.com.ju.core.RestAssuredConfigUtil;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {
    @BeforeAll
    static void setup() {
        RestAssuredConfigUtil.configure();
    }
}