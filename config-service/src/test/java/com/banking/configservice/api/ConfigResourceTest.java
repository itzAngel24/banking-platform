package com.banking.configservice.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ConfigResourceTest {

    @Test
    void shouldReturnHealthUp() {
        given()
            .when().get("/api/v1/config/health")
            .then()
            .statusCode(200)
            .body("status", is("UP"));
    }
}
