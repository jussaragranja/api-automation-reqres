package br.com.ju.tests;

import br.com.ju.config.BaseTest;
import br.com.ju.core.ApiClient;
import br.com.ju.model.CreateUserRequest;
import br.com.ju.util.TestDataFactory;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;

import static org.hamcrest.Matchers.*;

@DisplayName("Testes da API de Usuários (Reqres.in)")
class UserApiTest extends BaseTest {

    private static ApiClient api;

    @BeforeAll
    static void initClient() {
        api = new ApiClient();
    }

    @Nested
    @DisplayName("Listagem de usuários")
    class ListUsers {
        @Test
        @DisplayName("Deve retornar lista de usuários na página 2")
        void testListUsers() {
            api.listUsers(2)
                    .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_OK)
                    .body("data", not(empty()))
                    .body("data[0].id", notNullValue());
        }
    }

    @Nested
    @DisplayName("Criação de usuário")
    class CreateUser {
        @Test
        @DisplayName("Deve criar usuário com sucesso")
        void testCreateUser() {
            CreateUserRequest request = TestDataFactory.validUser();
            api.createUser(request)
                    .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_CREATED)
                    .body("id", notNullValue())
                    .body("name", equalTo(request.name()))
                    .body("job", equalTo(request.job()));
        }

        @Test
        @DisplayName("Não deve criar usuário com dados inválidos")
        void testCreateUserInvalid() {
            CreateUserRequest request = TestDataFactory.invalidUser();
            api.createUser(request)
                    .then()
                    .log().all()
                    .statusCode(anyOf(is(400), is(422)));
        }
    }

    @Nested
    @DisplayName("Busca de usuário")
    class GetUser {
        @Test
        @DisplayName("Deve retornar usuário existente")
        void testGetSingleUser() {
            api.getUser(2)
                    .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_OK)
                    .body("data.id", equalTo(2));
        }

        @Test
        @DisplayName("Deve retornar 404 para usuário inexistente")
        void testUserNotFound() {
            api.getUser(23)
                    .then()
                    .log().all()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
        }
    }
}