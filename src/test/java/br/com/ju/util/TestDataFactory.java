package br.com.ju.util;

import br.com.ju.model.CreateUserRequest;

public final class TestDataFactory {
    private TestDataFactory() {}

    public static CreateUserRequest validUser() {
        return new CreateUserRequest("morpheus", "leader");
    }

    public static CreateUserRequest invalidUser() {
        return new CreateUserRequest("", "");
    }
}