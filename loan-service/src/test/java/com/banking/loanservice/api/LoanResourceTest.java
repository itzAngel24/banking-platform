package com.banking.loanservice.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class LoanResourceTest {

    @Test
    void shouldReturnHealthUp() {
        given()
            .when().get("/api/v1/loan/health")
            .then()
            .statusCode(200)
            .body("status", is("UP"));
    }
}
